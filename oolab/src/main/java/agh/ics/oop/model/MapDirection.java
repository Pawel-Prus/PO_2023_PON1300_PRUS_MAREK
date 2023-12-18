package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;
    public String toString(){
        return switch(this) {
            case NORTH -> "Północ";
            case NORTHEAST -> "Północy-wschoód";
            case EAST -> "Wschód";
            case SOUTHEAST -> "Połudiowy-wschód";
            case SOUTH -> "Południe";
            case SOUTHWEST -> "Połudiowy-zachód";
            case WEST -> "Zachód";
            case NORTHWEST -> "Północy-zachód";
        };

    }



    public MapDirection next(){

        return values()[(this.ordinal()+1) % 8];
    }

    public MapDirection previous(){
        if(this.ordinal() -1 < 0){
            return values()[7];
        }
        return values()[(this.ordinal()-1)%8];
    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0,1);
            case NORTHEAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTHWEST -> new Vector2d(-1,1);
        };

    }
}
