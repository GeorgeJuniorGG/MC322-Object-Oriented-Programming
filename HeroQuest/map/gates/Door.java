package game.map.gates;

import game.characters.Character;
import game.interfaces.IOMediator;
import game.map.MapESymbol;

public class Door extends Gateway {

    private boolean closedStatus;

    public Door(int x, int y, String place){
        super(x, y, place, MapESymbol.CLOSEDDOOR);
        this.closedStatus = true;
    }

    public Door(int x, int y, String place, String linkName){
        super(x, y, place, MapESymbol.CLOSEDDOOR, linkName);
        this.closedStatus = true;
    }

    @Override
    protected boolean open(){
        closedStatus = false;
        return true;
    }

    @Override
    protected boolean cross(Character character, IOMediator mediator) {

        if(!closedStatus){
            return super.cross(character, mediator);
        }

        else if(closedStatus && character.isDaneable()) {
            String query = "A porta está fechada!\nDeseja abri-la:\n[0] - não\n[1] - sim"; 
            int response = mediator.getAnswer(query);
            if(response == 1){
                closedStatus = false;
                return super.cross(character, mediator);
            }       
        }
        
        return false;
    }

    @Override
    protected boolean overpass(Character character, IOMediator mediator) {
        return cross(character, mediator);
    }

    @Override
    public MapESymbol getSymbol() {
        if(closedStatus) {
            return MapESymbol.CLOSEDDOOR;
        }
        else {
            return MapESymbol.GATEWAY;
        }
    }
}