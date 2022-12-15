package ru.cs.vsu.pertsev;

import java.util.Arrays;
import java.util.List;

public class Logic {
    public static void solution(String inputStr) throws MyException {
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
            throw new MyException("Ошибка где-то в рекурсии...");
        }

        ;

    }  // 1.    "(a, b, (c), d)"    или    "(a, second, (abc, y, (x, 7), uuu, (8, 9, (10, 1)), abcddcdba)"
    // 2.    "a, b, (c), d" или  "a, second, (abc, y, (x, 7), uuu, (8, 9, (10, 1)), abcddcdba"

    public static String advancedStrip(String processedString, String charsToDelete) {
        int countBeginFinal = 0;
        int countEndFinal = 0;

        for (char charToDelete : charsToDelete.toCharArray()) {
            int countBeginCurr = 0;
            for (char strI : processedString.toCharArray()) {
                if (strI == charToDelete) {
                    countBeginCurr++;
                } else {
                    break;
                }
            }

            int countEndCurr = 0;
            for (char strI : reverseCharArray(processedString.toCharArray())) {
                if (strI == charToDelete) {
                    countEndCurr++;
                } else {
                    break;
                }
            }

            countBeginFinal = Math.max(countBeginCurr, countBeginFinal);
            countEndFinal = Math.max(countEndCurr, countEndFinal);
        }

        return processedString.substring(countBeginFinal, processedString.length() - countEndFinal);
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
