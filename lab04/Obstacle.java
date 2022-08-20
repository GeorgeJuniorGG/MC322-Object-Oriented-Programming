package lab04;

public class Obstacle {
    private int x;
    private int y;
    private ObstacleType type;

    public Obstacle(int x, int y, ObstacleType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    ObstacleType getType() {
        return type;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}