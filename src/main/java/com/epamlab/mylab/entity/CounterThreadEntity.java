package com.epamlab.mylab.entity;

public class CounterThreadEntity extends Thread {
    @Override
    public void run() {
        CounterEntity.add();
    }
}
