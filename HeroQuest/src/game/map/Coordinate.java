package game.map;

public class Coordinate {
    private int x;
    private int y;
    private String place;

    public Coordinate(int x, int y, String place){
        this.x = x;
        this.y = y;
        this.place = place; 
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPlace() {
        return place;
    }

    public boolean isSame(Coordinate another){
        if(this.x == another.x && this.y == another.y && this.place.equals(another.place)){
            return true;
        }
        return false;
    }

    public boolean isSame(int x, int y, String place){
        return (this.x == x && this.y == y && this.place.equals(place));
    }

    public Coordinate getClone(){
        return new Coordinate(x, y, place);
    }

}