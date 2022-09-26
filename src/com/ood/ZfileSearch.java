package com.ood;

// "static void main" must be defined in a public class.
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum NodeType {
    File,
    Directory,
}

/////////// Visitor pattern /////////////////////////////////////////

// Visitable accepts visitors
interface Visitable <T extends Node> {
    void accept(Visitor<T> visitor, Worker<T> worker);
}

// Visitor visits nodes
interface Visitor <T extends Node> {
    default void visit(T node, Worker<T> worker) {
        worker.work(node);
    }
}

interface Worker<T> {
    void work(T obj);
}

/////////// Filesystem model /////////////////////////////////////////

// Node is an interface for file system elements
interface Node extends Visitable<Node> {
    NodeType getNodeType();
    String getName();
    long getSize();

    default void accept(Visitor<Node> visitor, Worker<Node> worker) {
        visitor.visit(this, worker);
    }
}

// Regular file
class File implements Node {
    private final String name;
    private byte[] data;

    public File(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.File;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getSize() {
        return data.length;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                '}';
    }
}

// Directory in file system
class Directory implements Node {
    private final String name;
    private List<Node> contents = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public Directory addNode(Node node) {
        contents.add(node);
        return this;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.Directory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return 4096;
    }

    public void accept(Visitor<Node> visitor, Worker<Node> worker) {
        visitor.visit(this, worker);

        for (Node child : contents) {
            child.accept(visitor, worker);
        }
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                '}';
    }
}

/////////// Search params /////////////////////////////////////////

// Interface for search params
interface SearchPredicate<T> {
    boolean test(T t);

    default SearchPredicate<T> and(SearchPredicate<T> searchPredicate) {
        return t -> test(t) && searchPredicate.test(t);
    }

    default SearchPredicate<T> or(SearchPredicate<T> searchPredicate) {
        return t -> test(t) || searchPredicate.test(t);
    }
}

// Search by name - sample predicate impl
class NamePredicate implements SearchPredicate<Node> {
    private String name;

    public static NamePredicate create() {
        return new NamePredicate();
    }

    public NamePredicate setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean test(Node node) {
        return Objects.equals(this.name, node.getName());
    }
}

// Search by type - sample predicate impl
class TypePredicate implements SearchPredicate<Node> {
    private NodeType type;

    public static TypePredicate create() {
        return new TypePredicate();
    }

    public TypePredicate setType(NodeType type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean test(Node node) {
        return Objects.equals(this.type, node.getNodeType());
    }
}

/////////// Implementation /////////////////////////////////////////

// FileSearcher - accepts fs nodes and calls worker
class FileSearcher implements Visitor<Node> {
    public List<Node> search(Directory start, SearchPredicate<Node> params) {
        List<Node> result = new ArrayList<>();

        Worker<Node> worker = (Node n) -> {
            if (params.test(n))
                result.add(n);
        };

        start.accept(this, worker);

        return result;
    }
}

public class FileSearch {
    public static void main(String[] args) {
        Directory root = new Directory("/");
        root.addNode(new Directory("bin"));
        root.addNode(new Directory("etc"));
        root.addNode(new Directory("sbin"));
        root.addNode(new Directory("tmp"));
        root.addNode(new Directory("user"));
        root.addNode(new Directory("var")
                .addNode(new Directory("log")
                        .addNode(new File("system.log", new byte[]{}))));

        TypePredicate typePredicate = TypePredicate.create().setType(NodeType.File);
        NamePredicate namePredicate = NamePredicate.create().setName("etc");

        SearchPredicate<Node> params = typePredicate.or(namePredicate);

        FileSearcher fileSearcher = new FileSearcher();
        List<Node> search = fileSearcher.search(root, params);

        search.forEach(n -> System.out.println(n));
    }
}

