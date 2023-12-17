package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;
    public String toString(){
        return switch(this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case EAST -> "Wschód";
            case WEST -> "Zachód";
        };

    }

    public MapDirection next(){
//        return switch (this){
//            case NORTH -> EAST;
//            case SOUTH -> WEST;
//            case EAST -> SOUTH;
//            case WEST -> NORTH;
//        };
        return values()[(this.ordinal()+1) % 4];
    }

    public MapDirection previous(){
//        return switch (this){
//            case NORTH -> WEST;
//            case SOUTH -> EAST;
//            case EAST -> NORTH;
//            case WEST -> SOUTH;
//        };
        if(this.ordinal() -1 < 0){
            return values()[3];
        }
        return values()[(this.ordinal()-1)%4];
    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0,1);
            case SOUTH -> new Vector2d(0,-1);
            case EAST -> new Vector2d(1,0);
            case WEST -> new Vector2d(-1,0);
        };

    }
}
