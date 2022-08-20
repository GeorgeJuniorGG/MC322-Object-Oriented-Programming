package game.magic;

import game.characters.Character;

public class MagicMissile extends Magic {
    private static int DAMAGE = 2;
    private static int REACH = 3;

    private MagicType type = MagicType.AIR;

    public void useSpell(Character character) {
        if (character != null) {
            for(int i = 0; i < 3; i++) {
                character.defense(DAMAGE);
            }
        } 
    }

    public int getReach() {
        return REACH;
    }
    
    @Override
    public MagicType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MagicMissile";
    }
}