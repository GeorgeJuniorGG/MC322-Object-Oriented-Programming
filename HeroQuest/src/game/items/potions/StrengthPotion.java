package game.items.potions;

import game.characters.Character;

public class StrengthPotion extends Potion {
    public static int BONUS_ATTACK_DICES = 2;

    public void usePotion(Character character) {
        character.addAttackDices(BONUS_ATTACK_DICES);
    }
    
    @Override
    public String getName() {
        return "Strength Potion";
    }
}