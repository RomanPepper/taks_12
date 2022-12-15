package ru.cs.vsu.pertsev;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static TreeNode solution(String inputStr) throws MyException {
        inputStr = advancedStrip(inputStr, "()[]");

        String vertexValue;
        String[] inputArray;
        int branchLen = inputStr.split(",").length;

        if (branchLen == 1) {
            inputArray = new String[]{inputStr.strip()};
            vertexValue = inputArray[0];

        } else if (branchLen > 1) {
            inputArray = specificStringSplit(inputStr);
            vertexValue = inputArray[0];

        } else {
            throw new MyException("Solution method error...");
        }


        TreeNode node = new TreeNode(vertexValue, stringArrayToNodeList(inputArray));

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
                String[] localChildrenList = transmitter.getElemsArray();
                TreeNode compositeNode = new TreeNode(localVertex, stringArrayToNodeList(localChildrenList));
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
            vertexValue = inputArray[0];

        } else if (branchLen > 1) {
            try{
                inputArray = specificStringSplit(inputStr);
            } catch (Exception exception) {
                throw new MyException("Некорректно введённые данные");
            }
            vertexValue = inputArray[0];

        } else {
            throw new MyException("strToStringArray method error");
        }

        return new CustomTransmitter(vertexValue, inputArray);
    }

    public static boolean isNested(String elem) {
        return String.valueOf(elem.toCharArray()[0]).equals("(");
    }

    public static String advancedStrip(String processedString, String charsToDelete) {
//        int countBeginFinal = 0;
//        int countEndFinal = 0;

//        for (char charToDelete : charsToDelete.toCharArray()) {
//            int countBeginCurr = 0;
//            for (char strI : processedString.toCharArray()) {
//                if (strI == charToDelete) {
//                    countBeginCurr++;
//                } else {
//                    break;
//                }
//            }
//
//            int countEndCurr = 0;
//            for (char strI : reverseCharArray(processedString.toCharArray())) {
//                if (strI == charToDelete) {
//                    countEndCurr++;
//                } else {
//                    break;
//                }
//            }
//
//            countBeginFinal = Math.max(countBeginCurr, countBeginFinal);
//            countEndFinal = Math.max(countEndCurr, countEndFinal);
//        }

        boolean putAwayBeginElem = false;
        boolean putAwayEndElem = false;

        for(char elem: charsToDelete.toCharArray()) {
            if(processedString.toCharArray()[0] == elem) {
                putAwayBeginElem = true;
            }
            if(processedString.toCharArray()[processedString.length() - 1] == elem) {
                putAwayEndElem = true;
            }
        }

        if(putAwayEndElem && putAwayBeginElem) {
            return
        }
    }

    public static char[] reverseCharArray(char[] array) {
        char[] arrayFinal = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayFinal[array.length - i - 1] = array[i];
        }
        return arrayFinal;
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
                        charArr[i] = "‽".charAt(0);
                    }
                }
            }
        }
        return String.valueOf(charArr).split("‽");
    }
}
// 1.    "(a, b, (c), d)"    или    "(a, second, (abc, y, (x, 7), uuu, (8, 9, (10, 1)), abcddcdba)"
// 2.    "a, b, (c), d" или  "a, second, (abc, y, (x, 7), uuu, (8, 9, (10, 1)), abcddcdba"



