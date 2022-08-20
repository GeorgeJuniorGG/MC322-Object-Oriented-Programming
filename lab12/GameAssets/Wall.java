package GameAssets;

import GameEngine.LabyrinthObjectVisitor;

public class Wall extends LabyrinthObject {
    Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void accept(LabyrinthObjectVisitor visitor) {
        visitor.visit(this);
    } 
    
}