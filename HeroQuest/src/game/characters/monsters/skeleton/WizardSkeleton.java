package game.characters.monsters.skeleton;

import game.characters.Character;
import game.characters.monsters.Monster;
import game.magic.MagicMissile;
import game.map.MapESymbol;

public class WizardSkeleton extends Monster {

    private int pointsOfSmart;
    private MagicMissile magicMissile;
    private int quantityMagicMissile;
    public WizardSkeleton(String name, int x, int y, String place) {
        super(name, 4, 3, 1, x, y, place, MapESymbol.WIZARDSKELETON);
        this.pointsOfSmart = 5;
        this.quantityMagicMissile = 2;
        this.magicMissile = new MagicMissile();
    }

    private boolean throwMagic() {
        if (movementDice.getNumber() < pointsOfSmart) {
            return true;
        }
        return false;
    }

    private void attackMagicMissile(Character hero) {
        if (throwMagic()) {
            magicMissile.useSpell(hero);
            quantityMagicMissile--;
        }
    }

    @Override
    public void attack(Character character) {
        if (quantityMagicMissile >= 0) {
            attackMagicMissile(character);
        } else {
            super.attack(character);
        }   
    }
}