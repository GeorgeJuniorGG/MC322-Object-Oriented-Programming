package game.map.walls;

import game.map.Coordinate;
import game.map.MapESymbol;
import game.map.MapElement;

public class Wall extends MapElement {
    
    public Wall(int x, int y, String place){
        super(new Coordinate(x, y, place), MapESymbol.WALL);
    }
}