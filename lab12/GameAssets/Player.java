package GameAssets;

import GameEngine.LabyrinthObjectVisitor;

public class Player extends LabyrinthObject {
    private Direction currentDirection;

    Player(int x, int y) {
        super(x, y);
        currentDirection = Direction.RIGHT;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    void move(Direction direction, Wall[] walls) {
        Coordinate coordinate = this.getCoordinate();
        boolean ableToMove = true;
        if(direction == Direction.RIGHT) {
            for(int i = 0; i < walls.length; i++) {
                if(walls[i].isSameCoordinates(this.getX(), this.getY() + 1)) {
                    ableToMove = false;
                }
            }
            if(ableToMove) {
                currentDirection = Direction.RIGHT;
                coordinate.changeCoordinates(this.getX(), this.getY() + 1);
            }           
        }
        else if(direction == Direction.LEFT) {
            for(int i = 0; i < walls.length; i++) {
                if(walls[i].isSameCoordinates(this.getX(), this.getY() - 1)) {
                    ableToMove = false;
                }
            }
            if(ableToMove) {
                currentDirection = Direction.LEFT;
                coordinate.changeCoordinates(this.getX(), this.getY() - 1);
            }  
        }
        else if(direction == Direction.UP) {
            for(int i = 0; i < walls.length; i++) {
                if(walls[i].isSameCoordinates(this.getX() - 1, this.getY())) {
                    ableToMove = false;
                }
            }
            if(ableToMove) {
                currentDirection = Direction.UP;
                coordinate.changeCoordinates(this.getX() - 1, this.getY());
            }  
        }
        else if(direction == Direction.DOWN) {
            for(int i = 0; i < walls.length; i++) {
                if(walls[i].isSameCoordinates(this.getX() + 1, this.getY())) {
                    ableToMove = false;
                }
            }
            if(ableToMove) {
                currentDirection = Direction.DOWN;
                coordinate.changeCoordinates(this.getX() + 1, this.getY());
            }  
        }
    }

    @Override
    public void accept(LabyrinthObjectVisitor visitor) {
        visitor.visit(this);
    }  
    
}