package game.items.coins;

import game.items.Item;
import game.items.ItemTypes;

public class Coin extends Item {
    private int value;

    public Coin (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public ItemTypes getItemType() {
        return ItemTypes.COIN;
    }

    @Override 
    public String getName() {
        return "Coin";
    }
}