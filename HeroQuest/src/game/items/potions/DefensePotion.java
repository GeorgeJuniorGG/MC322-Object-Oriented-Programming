package game.items.potions;

import game.characters.Character;

public class DefensePotion extends Potion {
    public static int BONUS_DEFENSE_DICES = 2;

    public void usePotion(Character character) {
        character.addDefenseDices(BONUS_DEFENSE_DICES);
    }

    @Override
    public String getName() {
        return "Defense Potion";
    }    
}