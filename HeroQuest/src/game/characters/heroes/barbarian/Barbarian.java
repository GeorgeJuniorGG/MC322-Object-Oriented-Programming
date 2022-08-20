package game.characters.heroes.barbarian;

import game.characters.heroes.Hero;
import game.characters.heroes.HeroClass;
import game.items.weapons.LongSword;
import game.map.Coordinate;
import game.map.MapESymbol;

public class Barbarian extends Hero {
    
    public Barbarian(String name, Coordinate coordinate) {
        super(name, 3, 2, 8, 2, coordinate, MapESymbol.BARBERIAN, HeroClass.BARBARIAN);
        LongSword longSword = new LongSword();
        inventory.equipWeapon(longSword);
    }
}