package lab13.Jewels;

import lab13.Rechargeable;

public class BlueJewel extends Jewel implements Rechargeable {
    private static int BONUS_ENERGY = 5;

    public BlueJewel(int x, int y) {
        super(JewelType.Blue, x, y);
    }

    public int getBonusEnergy() {
        return BONUS_ENERGY;
    }

    @Override
    public String getSymbol() {
        return "JB";
    }
}