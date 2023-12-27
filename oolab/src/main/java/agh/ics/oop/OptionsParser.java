package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

//    public static String[] getExpectedMoves(String[] array){
//        int length = 0;
//        for(String elem : array){
//            int cnt = switch (elem){
//                case "f", "b" ,"l" , "r" -> 1;
//                default -> 0;
//            };
//            length += cnt;
//        }
//        String[] expectedMoves = new String[length];
//        int idx = 0;
//        for(String a : array){
//            if (a.equals("f") || a.equals("b") || a.equals("l") || a.equals("r")){
//                expectedMoves[idx] = a;
//                idx += 1;
//            }
//            if (idx > length){
//                break;
//            }
//
//
//        }
//        return expectedMoves;
//
//    }
//    public static MoveDirection[] parseToEnum(String[] args){
//        String[] expectedMoves = getExpectedMoves(args);
//        MoveDirection[]  Directions = new MoveDirection[expectedMoves.length];
//        for(int idx = 0; idx < expectedMoves.length ; idx ++){
//            switch (expectedMoves[idx]) {
//                case "f" -> Directions[idx] = MoveDirection.FORWARD;
//                case "b" -> Directions[idx] = MoveDirection.BACKWARD;
//                case "r" -> Directions[idx] = MoveDirection.RIGHT;
//                case "l" -> Directions[idx] = MoveDirection.LEFT;
//            }
//
//        }
//
//        return Directions;
//
//    }

    // W implementacji directions wykorzystuję LinkedList, gdyż na tej liście będą wykonwyane tylko operacja dodawania
    // elementów, zatem uzycie LinkedList powinno zwiększyć szybkość działania programu
    public static List<MoveDirection> parseToEnum(Integer[] args) throws IllegalArgumentException{
        List<MoveDirection> directions = new LinkedList<>();
        for(Integer a : args){
            switch (a) {
                case 0 -> directions.add(MoveDirection.FORWARD);
                case 1 -> directions.add(MoveDirection.UPPERRIGHT);
                case 2 -> directions.add(MoveDirection.RIGHT);
                case 3 -> directions.add(MoveDirection.LOWERRIGHT);
                case 4 -> directions.add(MoveDirection.BACKWARD);
                case 5 -> directions.add(MoveDirection.LOWERLEFT);
                case 6 -> directions.add(MoveDirection.LEFT);
                case 7 -> directions.add(MoveDirection.UPPERLEFT);
                default -> throw new IllegalArgumentException(a + " is not legal move specification");
            }

        }
        return directions;

    }


}
