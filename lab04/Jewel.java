package lab04;

public class Jewel {
    private int x;
    private int y;
    private JewelType type;

    public Jewel(JewelType type, int x, int y) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    JewelType getType() {
        return type;
    }

    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
}