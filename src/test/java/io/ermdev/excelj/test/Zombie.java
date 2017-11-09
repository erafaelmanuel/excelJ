package io.ermdev.excelj.test;

import io.ermdev.excelj.annotation.Column;
import io.ermdev.excelj.annotation.Sheet;

@Sheet
public class Zombie {

    @Column(name="IS_ALIVE")
    int isAlive;
    @Column(name="NAME")
    String name;

    @Override
    public String toString() {
        return "Zombie{" +
                "isAlive=" + isAlive +
                ", name='" + name + '\'' +
                '}';
    }
}
