package game.map;

import game.characters.Character;
import game.interfaces.IOMediator;

class Empty extends MapElement {
    
    Empty(int x, int y, String place){
        super(new Coordinate(x, y, place), MapESymbol.EMPTY);
    }

    @Override
    protected boolean isEmpty(){
        return true;
    }
    
    @Override
    protected boolean blockVision() {
        return false;
    }

    @Override
    protected boolean overpass(Character character, IOMediator mediator) {
        Coordinate clone = coordinate.getClone();
        character.move(clone);
        return true;
    }
}