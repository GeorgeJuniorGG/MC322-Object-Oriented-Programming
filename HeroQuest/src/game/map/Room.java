package game.map;

import game.characters.Character;
import game.characters.monsters.Monster;
import game.interfaces.MapElementVisitor;
import game.map.treasure.Treasure;

public class Room extends MapArea {
    
    Room(int width, int height, String name, int inGeSum, MapElement[][] area, Treasure[] treasures, Monster[] monsters){
        super(width, height, name, inGeSum, area, treasures, monsters);
    }

    Room(int width, int height, String name, int inGeSum, MapElement[][] area){
        this(width, height, name, inGeSum, area, new Treasure[1], new Monster[1]);
    }

    Room(int width, int height, String name, int inGeSum, MapElement[][] area, Treasure[] treasures){
        this(width, height, name, inGeSum, area, treasures, new Monster[1]);
    }

    Room(int width, int height, String name, int inGeSum, MapElement[][] area, Monster[] monsters){
        this(width, height, name, inGeSum, area, new Treasure[1], monsters);
    }

    @Override
    void accept(MapElementVisitor visitor, Character player) {
        visitor.visitRoom(this);
        super.accept(visitor, player);
    }
}