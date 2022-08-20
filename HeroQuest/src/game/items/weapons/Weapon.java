package game.items.weapons;

import game.items.Item;
import game.items.ItemTypes;

public abstract class Weapon extends Item {
    private int range;
    private int bonusAttackDices;
    private boolean destruction;
    private int space;
    private int numberOfTargets;
    private boolean diagonal;

    public Weapon(int range, int bonusAttackDices, boolean destruction, int space, int numberOfTargets, boolean diagonal) {
        this.range = range;
        this.bonusAttackDices = bonusAttackDices;
        this.destruction = destruction;
        this.space = space;
        this.numberOfTargets = numberOfTargets;
        this.diagonal = diagonal;
    }

    public  int getRange() {
        return range;
    }

    public int getBonusAttackDices() {
        return bonusAttackDices;
    }

    public boolean isDestructed() {
        return destruction;
    }

    public int getSpace() {
        return space;
    }

    public int getNumberOfTargets() {
        return numberOfTargets;
    }

    public boolean attackDiagonal() {
        return diagonal;
    }

    public ItemTypes getItemType() {
        return ItemTypes.WEAPON;
    }
    
    @Override
    public abstract String getName();
}