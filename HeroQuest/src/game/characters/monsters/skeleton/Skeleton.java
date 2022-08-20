package game.characters.monsters.skeleton;

import game.characters.monsters.Monster;
import game.items.weapons.Dagger;
import game.items.weapons.LongSword;
import game.items.weapons.ShortSword;
import game.items.weapons.Weapon;
import game.map.MapESymbol;

public class Skeleton extends Monster {
    
    public Skeleton(String name, int x, int y, String place) {
        super(name, 5, 3, 2, x, y, place, MapESymbol.SKELETON);
        setWeapon(generateWeapon());
    }

    private Weapon generateWeapon() {
        int number = (int) (Math.random() * 3) + 1;
        if (number == 1) {
            Dagger dagger = new Dagger();
            return dagger;
        } else if (number == 2) {
            return new LongSword();
        } else {
            return new ShortSword();
        }
    }
}