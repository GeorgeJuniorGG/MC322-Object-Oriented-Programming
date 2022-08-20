package game.interfaces;

import game.map.Corridor;
import game.map.MapElement;
import game.map.Room;

public interface MapElementVisitor {
    public void visit(MapElement element, int x, int y);
    public void visitRoom(Room room);
    public void visitCorridor(Corridor corridor);
}