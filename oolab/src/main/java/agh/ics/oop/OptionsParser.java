package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> table = new ArrayList<MoveDirection>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> table.add(MoveDirection.FORWARD);
                case "b" -> table.add(MoveDirection.BACKWARD);
                case "r" -> table.add(MoveDirection.RIGHT);
                case "l" -> table.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg + " is not a legal move specification");
            }
        }
        return table;
    }

    public static List<MoveDirection> specialParse(String[] args) {
        List<MoveDirection> table = new ArrayList<MoveDirection>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> table.add(MoveDirection.FORWARD);
                case "b" -> table.add(MoveDirection.BACKWARD);
            }
        }
        return table;
    }



}