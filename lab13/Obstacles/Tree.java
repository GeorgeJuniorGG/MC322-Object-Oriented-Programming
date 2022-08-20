package lab13.Obstacles;

import lab13.Rechargeable;

public class Tree extends Obstacle implements Rechargeable {
    public static int BONUS_ENERGY = 3;

    public Tree(int x, int y) {
        super(x, y, ObstacleType.Tree);
    }
    
    public int getBonusEnergy() {
        return BONUS_ENERGY;
    }

    @Override
    public String getSymbol() {
        return "$$";
    }
}