package game.items.armors;

import game.items.Item;
import game.items.ItemTypes;

public class Armor extends Item {
    private String name;
    private int extraDefenseDices;

    public Armor(ArmorTypes armorType) {
        this.extraDefenseDices = armorType.getDefenseDices();
        this.name = armorType.getName();
    }

    public int getExtraDefenseDices() {
        return extraDefenseDices;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public ItemTypes getItemType() {
        return ItemTypes.ARMOR;
    }
}