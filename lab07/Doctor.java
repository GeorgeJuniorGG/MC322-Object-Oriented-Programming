package lab07;

public class Doctor extends Units {

    public Doctor(int hp, int ap) {
        super(hp, ap, 0);
    }

    private void refreshPosition(Map map) {
        int newPosition = map.getFurthestDalek();
        if(newPosition <= 45) {
            this.position = newPosition;
        }
    }

    void move(Map map) {
        map.remove(this);
        this.refreshPosition(map);
        map.add(this);
    }

    void attack(Map map) {
        if(map.isThereADalek(position)) {
            map.attackDalek(position, this.ap);
        }
    }
    
}