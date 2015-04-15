package ru.ifmo.ctddev.shalamov.topdown;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by viacheslav on 15.04.2015.
 */
public class Tree {

    String node;
    List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node) {
        this.node = node;
    }

    public Tree(String node, List<Tree> subtrees) {
        this.node = node;
        this.children = subtrees;
    }

    /**
     * Returns the exact string the matches a pars-tree.
     * @return
     */
    @Override
    public String toString() {
        if (children == null || children.size() == 0)
        {
            if(node.equals("e"))
                return "";
            return node;
        }
        StringBuilder sb = new StringBuilder();
        for (Tree t : children) sb.append(t.toString());
        return sb.toString();
    }


    public void writeGraph(String path)
    {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println("digraph parseTree {");
            writer.println("\tordering=out;");
            writeGraph(writer, new VertexNumber());
            writer.println("}");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class VertexNumber
    {
        int value = 0;
        public void inc(){++value;}
    }

    private void writeGraph(PrintWriter writer, VertexNumber vertex) {
        if (children == null || (children.size() == 0 && !node.equals("e"))) {
            writer.println(String.format("\ta_%s [label=\"%s\"; style=filled; fillcolor=red;];", vertex.value, node));
        } else {
            writer.println(String.format("\ta_%s [label=\"%s\"];", vertex.value, node));
        }
        if (children != null) {
            List<Integer> successors = new ArrayList<>();
            int curVertex = vertex.value;
            for (Tree t : children) {
                vertex.inc();
                successors.add(vertex.value);
                t.writeGraph(writer, vertex);
            }
            for (Integer i : successors) {
                writer.println(String.format("\ta_%s -> a_%s", curVertex, i));
            }
        }

    }

}
