package lab07;

public class EliteGuard extends Dalek{

    public EliteGuard(int position, int hp, int ap) {
        super(hp, ap, position);
    }
    
    @Override
    protected void move(Map map) {
        if(hp > 0) {
            if (position >= 0) {
                if(this.hp > 100){
                    map.remove(this);
                    this.position++;
                    map.add(this);
                }
                else {
                    map.remove(this);
                    this.position += 3;
                    map.add(this);
                }
            }
            else {
                this.position++;
            }
        }
    }
    
    @Override
    protected void attack(Map map) {
        if (position > 0 && position < 46) {
            int victim = map.checkForTowers(position);
            if(victim != 0) {
                map.attackTower(victim, ap);
            }
            else {
                victim = map.checkForDoctor(position);
                if(victim != 0) {
                    map.attackDoctor(position, victim, ap);
                }
            }
        }
    }
}