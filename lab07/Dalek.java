package lab07;

public abstract class Dalek extends Units{
    public Dalek(int hp, int ap, int position) {
        super(hp, ap, position);
    }

    protected void move(Map map) {
        if(hp > 0) {
            if(position >= 0) {
                map.remove(this);
                this.position++;
                map.add(this);
            }
            else {
                this.position++;
            }
        }
    }

    protected void attack(Map map) {
    }
    
}