package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public String vertex;
    public List<TreeNode> children = new ArrayList<>();

    public TreeNode(String vertex) {
        this.vertex = vertex;
    }

    public TreeNode(String vertex, List<TreeNode> children) {
        this.vertex = vertex;
        this.children = children;
    }

    public String getVertex() {
        return vertex;
    }

    public List<TreeNode> getChildren() {
        return children;
    }



}
