package ru.ifmo.ctddev.shalamov.topdown;

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

}
