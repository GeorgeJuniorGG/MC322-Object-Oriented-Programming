package lab13.Obstacles;

import lab13.Item;

public abstract class Obstacle extends Item{
    private int x;
    private int y;
    private ObstacleType type;

    public Obstacle(int x, int y, ObstacleType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public ObstacleType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}