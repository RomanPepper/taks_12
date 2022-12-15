package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public String vertex;
    public List<TreeNode> children = new ArrayList<>();

    public TreeNode(String nodeValue, List<TreeNode> children) throws MyException {
        this.vertex = nodeValue;
        this.children = children;
    }
}
