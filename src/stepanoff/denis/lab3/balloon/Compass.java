package stepanoff.denis.lab3.balloon;

public class Compass {
    public Direction getDirection() {
        return Direction.values()[(int) (Math.random()*4)];
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
}
