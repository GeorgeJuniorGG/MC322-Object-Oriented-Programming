package game.magic;

import game.characters.Character;
import game.map.MapArea;

public class Teleport extends Magic {

    private MagicType type = MagicType.EARTH;

    public void useSpell(Character character, MapArea mapArea) {
        mapArea.moveToRandomPosition(character);
    }
    
    @Override
    public MagicType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Teleport";
    }
}