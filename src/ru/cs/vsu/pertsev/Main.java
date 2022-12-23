package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws MyException {
        TreeNode amogus = Logic.solution("(a, second, (abc, y, (x, 7), uuu, (8, 9, (10, 1))), abcddcdba)");

        Set<Integer> supportSet = new HashSet<>();
        TreeNode.printTreeNode(amogus, 0, supportSet);
    }
}