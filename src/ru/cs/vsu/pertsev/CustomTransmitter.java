package ru.cs.vsu.pertsev;

public class CustomTransmitter {
    String vertex;
    String[] elemsArray;

    public CustomTransmitter(String vertex, String[] elemsArray) {
        this.vertex = vertex;
        this.elemsArray = elemsArray;
    }

    public String getVertex() {
        return vertex;
    }

    public String[] getElemsArray() {
        return elemsArray;
    }
}
