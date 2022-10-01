package com.ood;

import com.sun.org.apache.bcel.internal.generic.IFLE;

import java.util.ArrayList;
import java.util.List;

public class AFileSearch {

    /**
     * requirements：
     * 1.根据name/size/extension搜索文件
     * 2.文件不存在需要校验
     *
     * 定义
     * 1.INode接口，定义getName/setName/getSize/isDirectory
     * 2.抽象类Node实现INode接口的getName/setName，有name属性
     * 3.File类继承Node，有content字段，实现getSize/getExtension/isDirectory方法
     * 4.Directory类继承Node，有List<Node> children字段，实现getSize/isDirectory/addNode方法
     * 5.IFilter接口，定义 boolean apply(File file)  !!!注意该方法的入参一定是file，不能是directory
     * 6.NameFilter(有name字段)/SizeFilter(有minSize/maxSize字段)/ExtensionFilter(有extension字段)都实现IFilter接口
     * 7.为了实现逻辑and/or/not，定义额外的AndFilter/OrFilter/NotFilter
     * 8.定义Search类，有方法 List<File> search(Filter filter)方法，用dfs搜索
     */

    interface INode{
        String getName();
        void setName(String name);
        int getSize();
        boolean isDirectory();
    }

    abstract static class Node implements INode{
        String name;
        @Override
        public String getName() {
            return this.name;
        }
        @Override
        public void setName(String name) {
            this.name = name;
        }
    }

    static class File extends Node{
        byte[] content;
        @Override
        public int getSize() {
            return content.length;
        }
        @Override
        public boolean isDirectory() {
            return false;
        }
        public byte[] getContent() {return content;}
        public void setContent(byte[] content) {this.content = content;}
        public String getExtension() {
            return this.name.substring(this.name.indexOf(".")+1);
        }
        public File(String name,byte[] content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    ", size='" + getSize() + '\'' +
                    '}';
        }
    }

    static class Directory extends Node{
        List<Node> children;
        public Directory(){this.children = new ArrayList<>();}
        @Override
        public int getSize() {
            int size = 0;
            for(Node child : children) {
                size += child.getSize();
            }
            return size;
        }
        @Override
        public boolean isDirectory() {
            return true;
        }
        public void addNode(Node node) {this.children.add(node);}
    }

    interface IFilter{
        boolean apply(File file);
    }

    abstract static class Filter implements IFilter{

    }

    static class NameFilter extends Filter{
        String name;
        public NameFilter(String name) {this.name = name;}
        @Override
        public boolean apply(File file) {
            return name.equals(file.getName());
        }
    }

    static class SizeFilter extends Filter{
        int minSize,maxSize;
        public SizeFilter(int min, int max) {this.minSize = min;this.maxSize = max;}
        @Override
        public boolean apply(File file) {
            return file.getSize()>=minSize&&file.getSize()<=maxSize;
        }
    }

    static class ExtensionFilter extends Filter{
        String extension;
        public ExtensionFilter(String extension) {this.extension = extension;}
        @Override
        public boolean apply(File file) {
            return extension.equals(file.getExtension());
        }
    }

    static class AndFilter extends Filter{
        List<Filter> filterList;
        public AndFilter() {
            this.filterList = new ArrayList<>();
        }
        public AndFilter(List<Filter> filterList) {
            this.filterList = filterList;
        }
        public void addFilter(Filter filter){if(filterList.contains(filter)) return;this.filterList.add(filter);}
        @Override
        public boolean apply(File file) {
            for(Filter filter : filterList) {
                if(!filter.apply(file)) return false;
            }
            return true;
        }
    }

    static class OrFilter extends Filter{
        List<Filter> filterList;
        public OrFilter() {
            this.filterList = new ArrayList<>();
        }
        public OrFilter(List<Filter> filterList) {
            this.filterList = filterList;
        }
        public void addFilter(Filter filter){if(filterList.contains(filter)) return;this.filterList.add(filter);}
        @Override
        public boolean apply(File file) {
            for(Filter filter : filterList) {
                if(filter.apply(file)) return true;
            }
            return false;
        }
    }

    static class Searcher{
        Filter filter;
        public Searcher(Filter filter) {this.filter = filter;}

        public List<File> search(Directory dir) {
            if(!dir.isDirectory()) {
                throw new IllegalArgumentException();
            }
            List<File> res = new ArrayList<>();
            search(dir, filter, res);
            return res;
        }

        void search(Directory dir, Filter filter, List<File> res) {
            if(dir.children.size()==0) return;
            for(Node node : dir.children) {
                if(!node.isDirectory()) {
                    File file = (File) node;
                    if(filter.apply(file)) res.add(file);
                } else {
                    search((Directory) node, filter, res);
                }
            }
        }
    }

    public static void main(String[] args) {
        Directory dir = new Directory();
        dir.addNode(new File("1.txt", new byte[0]));
        dir.addNode(new File("2.mov", new byte[1]));
        dir.addNode(new File("3.php", new byte[3]));
        dir.addNode(new File("4.txt", new byte[4]));

        Directory dir2 = new Directory();
        dir2.addNode(new File("5.txt", new byte[4]));
        dir2.addNode(new File("6.mov", new byte[11]));
        dir2.addNode(new File("7.php", new byte[2]));

        dir.addNode(dir2);

        AndFilter andFilter = new AndFilter();
        andFilter.addFilter(new ExtensionFilter("php"));
        andFilter.addFilter(new SizeFilter(2, 2));

        OrFilter orFilter = new OrFilter();
        orFilter.addFilter(new ExtensionFilter("txt"));
        orFilter.addFilter(andFilter);
        orFilter.addFilter(new NameFilter("2.mov"));

        Searcher searcher = new Searcher(orFilter);
        List<File> res = searcher.search(dir);
        System.out.println(res);
    }


}
