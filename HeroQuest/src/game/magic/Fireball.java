package game.magic;

import game.characters.Character;

public class Fireball extends Magic {
    public static int TARGET_DAMAGE = 6;
    public static int RANGE_DAMAGE = 3;

    private MagicType type = MagicType.FIRE;

    public static int REACH = 3;
    
    public void useSpell(Character[] characters) {
        if (characters != null) {
            if (characters[0] != null) {
            characters[0].defense(TARGET_DAMAGE);
            }
            for (int i = 1; i < characters.length; i++) {
                if (characters[i] != null) {
                    characters[i].defense(RANGE_DAMAGE);
                }
            }
        }
        
    }

    @Override
    public MagicType getType() {
        return type;
    }
    public int getReach() {
        return REACH;
    }

    @Override
    public String toString() {
        return "Fireball";
    }


    
}