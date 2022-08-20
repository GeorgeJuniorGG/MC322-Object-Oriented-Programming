package game.characters.heroes.magician;

import game.characters.heroes.HeroClass;
import game.items.weapons.Dagger;
import game.magic.*;
import game.map.Coordinate;
import game.map.MapESymbol;

public class Wizard extends Magician {
    
    public Wizard(String name, Coordinate coordinate) {
        super(name, 1, 2, 4, 6, coordinate, MapESymbol.WIZARD, HeroClass.WIZARD);
        Dagger daggerOne = new Dagger();
        Dagger daggerTwo = new Dagger();
        Dagger daggerThree = new Dagger();
        inventory.equipWeapon(daggerOne);
        inventory.equipWeapon(daggerTwo);
        inventory.equipWeapon(daggerThree);
        addInitialMagic();

    }
    
    private void addInitialMagic() {
        MagicMissile magicMissile = new MagicMissile();
        Fireball fireball = new Fireball();
        Teleport teleport = new Teleport();
        this.addMagic(magicMissile, 3);
        this.addMagic(fireball, 1);
        this.addMagic(teleport, 1);
    }
}