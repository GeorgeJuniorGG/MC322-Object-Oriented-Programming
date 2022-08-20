package game.items.potions;

import game.characters.Character;
import game.items.Item;
import game.items.ItemTypes;

public abstract class Potion extends Item {

    public ItemTypes getItemType() {
        return ItemTypes.POTION;
    }
    
    public abstract void usePotion(Character character);

    @Override
    public abstract String getName();
}