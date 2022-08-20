package game.map.traps;

import game.interfaces.IOMediator;
import game.map.Coordinate;
import game.map.MapESymbol;
import game.map.MapElement;
import game.characters.Character;

public class Trap extends MapElement {

    private int damagePoints;
    private boolean activated;
    private boolean detected;

    public Trap(int x, int y, String place, int damagePoints) {
        super(new Coordinate(x, y, place), MapESymbol.TRAP);
        this.damagePoints = damagePoints;
        this.activated = false;
        this.detected = false;
    }

    @Override
    public MapESymbol getSymbol(){
        if(!detected || activated){
            return MapESymbol.EMPTY;
        }
        return super.getSymbol();
        
    }

    @Override 
    protected boolean overpass(Character character, IOMediator mediator) {
        if(character.isDaneable() && !activated) {
            character.receivesDamage(damagePoints);
            activated = true;
            mediator.alert("\nUma armadilha foi acionada. Você sofreu " + damagePoints + " de dano!\n");
        }
        Coordinate clone = coordinate.getClone();
        character.move(clone);
        return true;
    }

    @Override
    protected boolean blockVision() {
        return false;
    }
}