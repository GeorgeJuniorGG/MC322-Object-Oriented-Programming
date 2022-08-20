package game.map;

public enum Direction {
    
    UP(-1,0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private int xOffset;
    private int yOffset;

    private Direction(int x, int y){
        xOffset = x;
        yOffset = y;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
    
}