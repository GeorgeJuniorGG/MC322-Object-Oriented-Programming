package game.magic;

import game.characters.Character;
import game.dices.RedDice;

public class SimpleHeal extends Magic {
    
    private MagicType type = MagicType.WATER;

    public void useSpell(Character character) {
        RedDice dice = new RedDice();
        character.heal(dice.getNumber());
    }
    
    @Override
    public MagicType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "SimpleHeal";
    }
}