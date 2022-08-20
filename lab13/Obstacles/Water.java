package lab13.Obstacles;

public class Water extends Obstacle {
    public Water(int x, int y) {
        super(x, y, ObstacleType.Water);
    }

    @Override
    public String getSymbol() {
        return "##";
    }    
}