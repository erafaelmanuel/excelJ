package io.ermdev.excelj.test;

import io.ermdev.excelj.Column;
import io.ermdev.excelj.Sheet;
import io.ermdev.excelj.Style;

@Sheet
public class Zombie {

    @Column(name="IS_ALIVE", style = @Style(scope = Style.Scope.TITLE))
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
