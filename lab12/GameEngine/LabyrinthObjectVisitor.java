package GameEngine;

import GameAssets.Checkpoint;
import GameAssets.Player;
import GameAssets.Wall;

public interface LabyrinthObjectVisitor {
    public void visit(Player player);
    public void visit(Wall wall);
    public void visit(Checkpoint checkpoint);    
}