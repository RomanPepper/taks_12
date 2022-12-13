package ru.cs.vsu.pertsev;

public class Logic {
    public static void F(String str) {
        str = str.substring(1, str.length() - 1);
        String[] stringArray = str.split(", ");

        for(String strChlen: stringArray) {
            F(strChlen);
        }
    }
}
