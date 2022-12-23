package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static void printTreeNode(TreeNode node, int globalDepth, Set<Integer> supportSet) {


        //Печаталка
        if (globalDepth == 0) {
            System.out.println(node.getVertex());
        } else {
            StringBuilder stringBuilder = new StringBuilder();

            if(supportSet.size() != 0) {
                for (int i = 0; i < globalDepth - 1; i++) {
                    if (supportSet.contains(Integer.valueOf(i))) {
                        stringBuilder.append("| ");
                    } else {
                        stringBuilder.append("  ");
                    }
                }
            }
            stringBuilder.append("+-");
            stringBuilder.append(node.getVertex());


            System.out.println(stringBuilder.toString());
        }

        globalDepth++;

        //Рекурсия
        List<TreeNode> childrenList = node.getChildren();

        int supportIndex = 0;
        for (TreeNode childNode : childrenList) {
            if (childNode.getChildren().size() == 0) {
                //Если у узла нет потомков, то...
                printTreeNode(childNode, globalDepth, supportSet);
            } else {
                //Если у узла есть потомки, то проверяем на наличие siblings после себя.

                //Переменная, указывающая на наличие братьев/сестёр после текущего узла в очереди на обработку
                boolean flag = false;

                if (supportIndex < childrenList.size() - 1) {
                    flag = true;
                } else {
                    flag = false;
                }


                Set<Integer> newSet = new HashSet<>(supportSet);

                if(flag) {
                    newSet.add(globalDepth - 1);
                }

                printTreeNode(childNode, globalDepth, newSet);
            }
            supportIndex++;
        }

    }
}
//При проходе по массиву потомков "a" я должен наткнуться на second и передать внутрь пустой массив,
//  а вот, когда наткнуть на abc,у которого есть потомки, должен передать, что при globalDepth == 0,
//  его потомки должны распечатать "| " по globalDepth == 0, т.к. после меня будут ещё siblings. Затем, я зайду в abc: дойду до y и передам внутрь
//  пустой массив, а когда наткнусь на x, то передам его потомкам то, что дополнительно с globalDepth == 0 нужно
//  ещё и по globalDepth == 1 распечатать "| ", т.к. после меня будут siblings. А когда дойду до 8, то, т.к. после меня
//  уже нет siblings в очереди на обработку, я не передаю внутрь дополнительный globalDepth == 1, а
//  только globalDepth == 0.
