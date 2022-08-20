package GameAssets;

import GameEngine.LabyrinthObjectVisitor;

public abstract class LabyrinthObject {
    private Coordinate coordinate;

    public LabyrinthObject(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }

    public int getX() {
        return coordinate.getX();
    }

    public int getY() {
        return coordinate.getY();
    }

    protected Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isSameCoordinates(int xCandidate, int yCandidate) {
        if(this.getX() == xCandidate && this.getY() == yCandidate) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isSameCoordinates(LabyrinthObject candidate) {
        if(this.coordinate == candidate.getCoordinate()) {
            return true;
        }
        else {
            return false;
        }
    }

    public abstract void accept(LabyrinthObjectVisitor visitor);
}