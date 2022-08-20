package GameAssets;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeCoordinates(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public boolean isSameCoordinates(int xCandidate, int yCandidate) {
        if(this.x == xCandidate && this.y == yCandidate) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        else {
            return false;
        }
    }
    
}