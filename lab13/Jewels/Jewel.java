package lab13.Jewels;

import lab13.Item;

public abstract class Jewel extends Item{
    private int x;
    private int y;
    private JewelType type;

    public Jewel(JewelType type, int x, int y) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public JewelType getType() {
        return type;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

}