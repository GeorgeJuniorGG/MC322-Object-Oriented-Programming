package game.map.gates;

import game.characters.Character;
import game.interfaces.IOMediator;
import game.map.Coordinate;
import game.map.MapArea;
import game.map.MapESymbol;
import game.map.MapElement;

public class Gateway extends MapElement {
    private Gateway link;
    private String linkName;
    private MapArea local;
    

    protected Gateway(int x, int y, String place, MapESymbol symbol) {
        super(new Coordinate(x, y, place), symbol);
        local = null;
        link = null;
    }

    protected Gateway(int x, int y, String place, MapESymbol symbol, String linkName) {
        super(new Coordinate(x, y, place), symbol);
        local = null;
        link = null;
        this.linkName = linkName;
    }

    public Gateway(int x, int y, String place, String linkName){
        this(x, y, place, MapESymbol.GATEWAY);
        this.linkName = linkName;
    }

    public Gateway(int x, int y, String place){
        this(x, y, place, MapESymbol.GATEWAY);
    }

    public String getLinkName(){
        return linkName;
    }

    public String getLocalName(){
        return coordinate.getPlace();
    }

    public void setLinks(MapArea local, Gateway link){
        if(this.local == null){
            this.local = local;
        }
        if(this.link == null){
            this.link = link;
        }
    }

    protected boolean open(){
        return true;
    }

    private boolean notifyLocation(Character character) {
        return local.updatePosition(this, character);
    }

    protected boolean cross(Character character, IOMediator mediator) {
        if(link.notifyLocation(character)) {
            link.open();
            Coordinate newPosition = link.coordinate.getClone();
            character.move(newPosition);
            return true;
        }
        mediator.alert("\nNão foi Possível atravessar a passagem\n");
        mediator.notifyMap(link.getLocalName());
        return false;
    }

    @Override
    protected boolean overpass(Character character, IOMediator mediator) {
        return cross(character, mediator);
    }
}