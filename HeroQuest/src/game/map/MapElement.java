package game.map;

import game.characters.Character;
import game.interfaces.IOMediator;
import game.interfaces.MapElementVisitor;

public abstract class MapElement {

    protected Coordinate coordinate;
    private MapESymbol symbol;

    public MapElement(Coordinate coordinate, MapESymbol symbol){
        this.coordinate = coordinate;
        this.symbol = symbol;
    }

    public MapESymbol getSymbol(){
        return symbol;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected boolean compareCoordinate(MapElement another){
        return coordinate.isSame(another.coordinate);
    }

    protected boolean compareCoordinate(Coordinate position){
        return coordinate.isSame(position);
    }

    protected boolean overpass(Character character, IOMediator mediator){
        return false;
    }
    
    protected boolean isEmpty() {
        return false;
    }

    protected boolean blockVision() {
        return true;
    }

    @Override
    public String toString(){
        return "" + getSymbol().getTSymbol();
    }

    void accept(MapElementVisitor visitor){
        visitor.visit(this, coordinate.getX(), coordinate.getY());
    }
}