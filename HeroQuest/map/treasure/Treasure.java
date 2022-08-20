package game.map.treasure;

import game.items.Item;
import game.items.armors.Armor;
import game.items.armors.ArmorTypes;
import game.items.coins.Coin;
import game.items.potions.DefensePotion;
import game.items.potions.HealingPotion;
import game.items.potions.Potion;
import game.items.potions.StrengthPotion;
import game.items.weapons.Dagger;
import game.items.weapons.LongSword;
import game.items.weapons.ShortSword;
import game.items.weapons.Weapon;
import game.map.Coordinate;
import game.map.MapESymbol;
import game.map.MapElement;

public class Treasure extends MapElement {

    private final int MAX = 4;

    private Item[] items;

    public Treasure(int x, int y, String place) {
        super(new Coordinate(x, y, place), MapESymbol.TREASURE);
        items = new Item[MAX];
        items[0] = generateCoin();
        items[1] = generateArmor();
        items[2] = generateWeapon();
        items[3] = generatePotion();
    }

    public Item[] getItems() {
        return items;
    }

    private Armor generateArmor() {
        int chance = (int) (Math.random() * 10) + 1;
        if (chance == 10) {
            Armor armor = new Armor(ArmorTypes.DIAMOND);
            return armor;
        }
        else if (chance == 9) {
            Armor armor = new Armor(ArmorTypes.GOLD);
            return armor;
        }
        else if (chance == 8 || chance == 9) {
            Armor armor = new Armor(ArmorTypes.IRON);
            return armor;
        }
        else if (chance > 4) {
            Armor armor = new Armor(ArmorTypes.LEATHER);
            return armor;
        }
        else {
            return null;
        }
    }

    private Coin generateCoin() {
        int value = (int) (Math.random() * 100) + 1;
        Coin coin = new Coin(value);
        return coin;
    }

    private Weapon generateWeapon() {
        int chance = (int) (Math.random() * 4) + 1;
        if (chance == 1) {
            Dagger dagger = new Dagger();
            return dagger;
        }
        else if (chance == 2) {
            LongSword longSword = new LongSword();
            return longSword;
        }
        else if (chance == 3) {
            ShortSword shortSword = new ShortSword();
            return shortSword;
        }
        else {
            return null;
        }
    }

    private Potion generatePotion() {
        int chance = (int) (Math.random() * 4) + 1;
        if (chance == 1) {
            StrengthPotion strengthPotion = new StrengthPotion();
            return strengthPotion;
        }
        else if (chance == 2) {
            DefensePotion defensePotion = new DefensePotion();
            return defensePotion;
        }
        else if (chance == 3) {
            HealingPotion healingPotion = new HealingPotion();
            return healingPotion;
        }
        else {
            return null;
        }
    }
}