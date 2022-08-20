package game.map;

import game.characters.Character;
import game.characters.monsters.Monster;
import game.interfaces.IOMediator;
import game.interfaces.MapElementVisitor;
import game.map.treasure.Treasure;

public class Corridor extends MapArea {

    private boolean vertical;
    
    Corridor(int width, String name, int inGeSum, MapElement[][] area, Treasure[] treasures, Monster[] monsters,int vertical){
        super(width, 3, name, inGeSum, area, treasures, monsters);
        if(vertical == 1){
            this.vertical = true;
        }else{
            this.vertical = false;
        }
    }

    Corridor(int width, String name, int inGeSum, MapElement[][] area,int vertical){
        this(width, name, inGeSum, area, new Treasure[1], new Monster[1], vertical);
    }

    Corridor(int width, String name, int inGeSum, MapElement[][] area, Treasure[] treasures,int vertical){
        this(width, name, inGeSum, area, treasures, new Monster[1], vertical);
    }

    Corridor(int width, String name, int inGeSum, MapElement[][] area, Monster[] monsters,int vertical){
        this(width, name, inGeSum, area, new Treasure[1], monsters, vertical);
    }

    public boolean isVertical(){
        return vertical;
    }

    @Override
    public int getWidth() {
        if(vertical){
            return super.getHeight();
        }
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        if(vertical){
            return super.getWidth();
        }
        return super.getHeight();
    }

    @Override
    void allowMove(Character character, Direction direction, IOMediator mediator) {
        Direction converted = direction;
        if(vertical && character.isDaneable()){
            converted = convertDirection(direction);
        }
        super.allowMove(character, converted, mediator);
    }

    private Direction convertDirection(Direction in){
        if(in == Direction.RIGHT){
            return Direction.DOWN;
        }
        else if(in == Direction.LEFT){
            return Direction.UP;
        }
        else if(in == Direction.UP){
            return Direction.LEFT;
        }
        else if(in == Direction.DOWN){
            return Direction.RIGHT;
        }
        return in;
    }

    @Override
    void accept(MapElementVisitor visitor, Character player) {
        visitor.visitCorridor(this);

        Coordinate pos = player.getCoordinate();
        int pX = pos.getX();
        int pY = pos.getY();
        Direction dir = player.getDirection();

        //Porta lateral
        if(pX != 1){
            if(dir == Direction.RIGHT || dir == Direction.DOWN){
             iterateRight(pX, pY, visitor);
            }
            else if(dir == Direction.LEFT || dir == Direction.UP){
                iterateLeft(pX, pY, visitor);
            }           
        }
        else{
            iterateForward(pX, pY, visitor);
            iterateBack(pX, pY, visitor);
        }
        
    }

    private void iterateForward(int oX, int oY, MapElementVisitor visitor){
        MapElement element;

        for(int i = oY; i < super.getWidth(); i++) {
            element = area[oX][i];
            area[oX-1][i].accept(visitor);
            area[oX+1][i].accept(visitor);
            element.accept(visitor);
            if(element.blockVision() && i != oY){
                return;
            }
        }
    }

    private void iterateBack(int oX, int oY, MapElementVisitor visitor){
        MapElement element;

        for(int i = oY; i >= 0; i--){
            element = area[oX][i];
            area[oX-1][i].accept(visitor);
            area[oX+1][i].accept(visitor);
            element.accept(visitor);
            if(element.blockVision() && i != oY){
                return;
            }            
        }
    }

    private void iterateRight(int oX, int oY, MapElementVisitor visitor){
        area[oX][oY].accept(visitor);
        area[oX+1][oY].accept(visitor);
        area[oX+2][oY].accept(visitor);
    }

    private void iterateLeft(int oX, int oY, MapElementVisitor visitor){
        area[oX][oY].accept(visitor);
        area[oX-1][oY].accept(visitor);
        area[oX-2][oY].accept(visitor);
    }
}