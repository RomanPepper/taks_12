package ru.cs.vsu.pertsev;

import java.util.*;

public class Logic {

    public static TreeNode solution(String inputStr) throws MyException {
        inputStr = advancedStrip(inputStr, "()[]");

        String vertexValue;
        String[] inputArray;
        int branchLen = inputStr.split(",").length;

        if (branchLen == 1) {
            inputArray = new String[]{inputStr.strip()};
            vertexValue = advancedStrip(inputArray[0], "()[]");

        } else if (branchLen > 1) {
            inputArray = specificStringSplit(inputStr);
            vertexValue = advancedStrip(inputArray[0], "()[]");

        } else {
            throw new MyException("Solution method error...");
        }


        List<TreeNode> supportList = stringArrayToNodeList(inputArray);
        TreeNode node = new TreeNode(vertexValue, supportList.subList(1, supportList.size()));

        return node;
    }

    public static List<TreeNode> stringArrayToNodeList(String[] strArr) throws MyException {
        List<TreeNode> nodeList = new ArrayList<>();

        for (String elem : strArr) {
            //Если элемент имеет нулевую вложенность, задаём вершину, оставляя лист потомков нулевым.
            //В противном случае, задаём ему вершину и идём вглубь, после полного погружения, загружаем древо в children
            if (elem.split(",").length == 1) {
                TreeNode simpleNode = new TreeNode(advancedStrip(elem, "()[]"));
                nodeList.add(simpleNode);
            } else {
                //Алгоритм действий: преобразовываю elem в stringArray по старым правилам, а затем рекурсирую эту парашу
                CustomTransmitter transmitter = specificStringToStringArray(elem);

                String localVertex = transmitter.getVertex();
                String[] localChildrenList = Arrays.copyOfRange(transmitter.getElemsArray(), 1, transmitter.getElemsArray().length);
                TreeNode compositeNode = new TreeNode(advancedStrip(localVertex, "()[]"), stringArrayToNodeList(localChildrenList));
                nodeList.add(compositeNode);
            }
        }

        return nodeList;
    }

    public static CustomTransmitter specificStringToStringArray(String inputStr) throws MyException {
        inputStr = advancedStrip(inputStr, "()[]");

        String vertexValue;
        String[] inputArray;
        int branchLen = inputStr.split(",").length;

        if (branchLen == 1) {
            inputArray = new String[]{inputStr.strip()};
            vertexValue = advancedStrip(inputArray[0], "()[]");

        } else if (branchLen > 1) {
            try {
                inputArray = specificStringSplit(inputStr);
            } catch (Exception exception) {
                throw new MyException("Некорректно введённые данные");
            }
            vertexValue = advancedStrip(inputArray[0], "()[]");

        } else {
            throw new MyException("strToStringArray method error");
        }

        return new CustomTransmitter(vertexValue, inputArray);
    }

    public static String advancedStrip(String processedString, String charsToDelete) {
        processedString = processedString.strip();

        boolean putAwayBeginElem = false;
        boolean putAwayEndElem = false;
        char[] charsElems = processedString.toCharArray();

        for (char elem : charsToDelete.toCharArray()) {
            if (charsElems[0] == elem) {
                putAwayBeginElem = true;
            }
            if (charsElems[processedString.length() - 1] == elem) {
                putAwayEndElem = true;
            }
        }

        if (putAwayBeginElem && putAwayEndElem) {
            return processedString.substring(1, processedString.length() - 1);
        } else if (putAwayBeginElem && !putAwayEndElem) {
            return processedString.substring(1);
        } else if (!putAwayBeginElem && putAwayEndElem) {
            return processedString.substring(0, processedString.length() - 1);
        } else {
            return processedString;
        }
    }

    public static String[] specificStringSplit(String processedString) {
        //Фактически, мы просто сплитим по запятой, если слева нет открывающих скобок без закрывающих,
        // а справа закрывающих без открывающих...

        char[] charArr = processedString.toCharArray();


        //Сначала просто ищем запятые
        for (int i = 0; i < charArr.length; i++) {
            if (String.valueOf(charArr[i]).equals(",")) {
                //Заглядываем назад
                int countOpenBack = 0;
                int countCloseBack = 0;
                for (int j = i - 1; j <= 0; j++) {
                    if (String.valueOf(charArr[j]).equals("(")) {
                        countOpenBack++;
                        continue;
                    } else if (String.valueOf(charArr[j]).equals(")")) {
                        countCloseBack++;
                        continue;
                    }
                }
                if (countOpenBack == countCloseBack) { //Если сзади проблем нет, заглядываем вперёд
                    int countOpenFront = 0;
                    int countCloseFront = 0;
                    for (int j = i + 1; j < charArr.length; j++) {
                        if (String.valueOf(charArr[j]).equals(")")) {
                            countCloseFront++;
                            continue;
                        } else if (String.valueOf(charArr[j]).equals("(")) {
                            countOpenFront++;
                            continue;
                        }
                    }
                    if ((countOpenBack == countCloseBack) && (countOpenFront == countCloseFront)) {
                        //Какой-то символ, вероятность попадания которого во входные данные минимальна
                        charArr[i] = "!".charAt(0);
                    }
                }
            }
        }
        return String.valueOf(charArr).split("!");
    }

    public static String duplicateStr(String inputStr, int count) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < count; i++) {
            sb.append(inputStr);
        }
        return sb.toString();
    }

}