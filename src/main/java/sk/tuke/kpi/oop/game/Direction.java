package sk.tuke.kpi.oop.game;

public enum Direction {

    NORTH(0,1),
    EAST(1,0),
    SOUTH(0,-1),
    WEST(-1,0),
    SOUTHWEST(-1,-1),
    NORTHWEST(-1,1),
    SOUTHEAST(1,-1),
    NORTHEAST(1,1),
    NONE(0,0);

    private  int dx;
    private  int dy;
    Direction(int dx,int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    public float getAngle(){
        switch (this){
            case NORTH:
                return 0;
            case NORTHWEST:
                return 45;
            case WEST:
                return 90;
            case SOUTHWEST:
                return 135;
            case SOUTH:
                return 180;
            case SOUTHEAST:
                return 225;
            case EAST:
                return 270;
            case NORTHEAST:
                return 315;
            default:
                return 0;
        }
    }
    public Direction combine(Direction other){
        int x= getDx() + other.dx;
        int y= getDy() + other.dy;
        if (x>1 || x< -1){
            x = other.dx;
        }
        if (y>1 || y< -1){
            y = other.dy;
        }
        for (Direction direction : Direction.values()){
            if ((direction.getDx() == x) && (direction.getDy() == y)){
                return direction;
            }
        }
        return Direction.NONE;
    }

    public static Direction fromAngle(float angel){
        switch ((int) angel){
            case 0:
                return NORTH;
            case 45:
                return NORTHWEST;
            case 90:
                return WEST;
            case 135:
                return SOUTHWEST;
            case 180:
                return SOUTH;
            case 225:
                return SOUTHEAST;
            case 270:
                return EAST;
            case 315:
                return NORTHEAST;
            default:
                return NONE;
        }
    }


}
