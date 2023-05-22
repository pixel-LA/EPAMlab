package com.epamlab.mylab.entity;

public class CounterEntity {
    public static Integer counter = 0;

    public static synchronized void add() {
        counter++;
    }

    public static synchronized Integer getCounter() {
        return counter;
    }
}
