package game.characters.heroes.dwarf;

import game.characters.heroes.Hero;
import game.characters.heroes.HeroClass;
import game.items.weapons.ShortSword;
import game.map.Coordinate;
import game.map.MapESymbol;

public class Dwarf extends Hero {
    
    public Dwarf(String name, Coordinate coordinate) {
        super(name, 2, 2, 7, 3, coordinate, MapESymbol.DWARF, HeroClass.DWARF);
        ShortSword shortSword = new ShortSword();
        inventory.equipWeapon(shortSword);
    }
}