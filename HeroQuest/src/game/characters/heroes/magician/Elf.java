package game.characters.heroes.magician;

import game.characters.heroes.HeroClass;
import game.items.weapons.ShortSword;
import game.magic.*;
import game.map.Coordinate;
import game.map.MapESymbol;

public class Elf extends Magician {
    
    public Elf(String name, Coordinate coordinate) {  
        super(name, 2, 2, 6, 4, coordinate, MapESymbol.ELF, HeroClass.ELF);
        ShortSword shortSword = new ShortSword();
        inventory.equipWeapon(shortSword);
        addInitialMagic();
    }

    private void addInitialMagic() {
        SimpleHeal simpleHeal = new SimpleHeal();
        this.addMagic(simpleHeal, 1);
    }



}