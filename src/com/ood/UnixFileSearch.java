package com.ood;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UnixFileSearch {
    public static void main(String[] args) {
        new UnixFileSearch().test();
    }

    /**
     * 代码可以省略泛型
     * 1. 明确需求：可以通过名称、大小、扩展名来搜索
     * 2. 需要定义的主要类有文件/目录、 搜索类型/模式、 filter、 search类
     * 3. file和directory有共同的抽象父类，实现一个接口，接口中有setName/getName/getSize/isDirectory；file中有content和extension，directory中有list<Entry>操作
     * 4. SearchParams包含四种类型
     * 5. filter接口有isValid(SearchParams params, File file)，四个filter分别实现，同时还有默认的and和or逻辑实现
     * 6. 用一个大的FileFilter类，里面list存储四个实现的filter，逐个valid校验，有一个不通过即返回false
     * 7. 定义FileSearch类，有方法List<File> search(Directory dir, SearchParams params)，使用bfs遍历，如果碰到文件则用filter.isValid(params, file)，
     * 返回true说明搜索成功，加入结果
     * 8. 对于and和or等逻辑，可以使用装饰者模式，新定义一个filter，有两个filter变量，isvalid方法执行f1.valid&&f2.valid
     */
    public interface IEntry {
        String getName();
        void setName(String name);
        int getSize();
        boolean isDirectory();
    }

    private abstract class Entry implements IEntry {
        protected String name;
        @Override
        public String getName() {
            return name;
        }
        @Override
        public void setName(String name) {
            this.name = name;
        }
    }

    public class File extends Entry {
        private byte[] content;
        public String getExtension() {
            return name.substring(name.indexOf(".") + 1);
        }
        public void setContent(byte[] content) {
            this.content = content;
        }
        public byte[] getContent() {
            return content;
        }
        @Override
        public int getSize() {
            return content.length;
        }
        @Override
        public boolean isDirectory() {
            return false;
        }
        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public class Directory extends Entry {
        private List<Entry> entries = new ArrayList<>();
        @Override
        public int getSize() {
            int size = 0;
            for (Entry entry  : entries) {
                size += entry.getSize();
            }
            return size;
        }
        @Override
        public boolean isDirectory() {
            return true;
        }
        public void addEntry(Entry entry) {
            this.entries.add(entry);
        }
    }

    public class SearchParams {
        String extension;
        Integer minSize;
        Integer maxSize;
        String name;
    }

    public interface IFilter {
        boolean isValid(SearchParams params, File file);

    }

    public class ExtensionFilter implements IFilter {
        @Override
        public boolean isValid(SearchParams params, File file) {
            if (params.extension == null) {
                return true;
            }
            return file.getExtension().equals(params.extension);
        }
    }

    public class MinSizeFilter implements IFilter {
        @Override
        public boolean isValid(SearchParams params, File file) {
            if (params.minSize == null) {
                return true;
            }
            return file.getSize() >= params.minSize;
        }

    }

    public class MaxSizeFilter implements IFilter {
        @Override
        public boolean isValid(SearchParams params, File file) {
            if (params.maxSize == null) {
                return true;
            }

            return file.getSize() <= params.maxSize;
        }
    }

    public class NameFilter implements IFilter {
        @Override
        public boolean isValid(SearchParams params, File file) {
            if (params.name == null) {
                return true;
            }
            return file.getName().equals(params.name);
        }

    }

    public class FileFilter {
        private final List<IFilter> filters = new ArrayList<>();
        public FileFilter() {
            filters.add(new NameFilter());
            filters.add(new MaxSizeFilter());
            filters.add(new MinSizeFilter());
            filters.add(new ExtensionFilter());
        }
        public boolean isValid(SearchParams params, File file) {
            for (IFilter filter : filters) {
                if (!filter.isValid(params, file)) {
                    return false;
                }
            }
            return true;
        }
    }

    public class FileSearcher {
        private FileFilter filter = new FileFilter();
        public List<File> search(Directory dir, SearchParams params) {
            List<File> files = new ArrayList<>();
            Queue<Directory> queue = new LinkedList<>();
            queue.add(dir);
            while (!queue.isEmpty()) {
                Directory folder = queue.poll();
                for (IEntry entry : folder.entries) {
                    if (entry.isDirectory()) {
                        queue.add((Directory) entry);
                    } else {
                        File file = (File) entry;
                        if (filter.isValid(params, file)) {
                            files.add(file);
                        }
                    }
                }
            }
            return files;
        }
    }


    private void test() {
        SearchParams params = new SearchParams();
        params.extension = "xml";
        params.minSize = 2;
        params.maxSize = 100;

        File xmlFile = new File();
        xmlFile.setContent("aaa.xml".getBytes());
        xmlFile.name = "aaa.xml";

        File txtFile = new File();
        txtFile.setContent("bbb.txt".getBytes());
        txtFile.name = "bbb.txt";

        File jsonFile = new File();
        jsonFile.setContent("ccc.json".getBytes());
        jsonFile.name = "ccc.json";

        Directory dir1 = new Directory();
        dir1.addEntry(txtFile);
        dir1.addEntry(xmlFile);

        Directory dir0 = new Directory();
        dir0.addEntry(jsonFile);
        dir0.addEntry(dir1);

        FileSearcher searcher = new FileSearcher();
        System.out.println(searcher.search(dir0, params));
    }
}
