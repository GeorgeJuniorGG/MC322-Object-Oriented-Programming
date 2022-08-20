package game.items.potions;

import game.characters.Character;
import game.dices.RedDice;

public class HealingPotion extends Potion {

    public void usePotion(Character character) {
        RedDice dice = new RedDice();
        int number = dice.getNumber();
        character.heal(number);
    }

    @Override
    public String getName(){
        return "Healing Potion";
    }
    
}