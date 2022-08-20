package lab07;

public abstract class Units {
    protected int hp;
    protected int ap;
    protected int position;

    public Units(int hp, int ap, int position) {
        this.hp = hp;
        this.ap = ap;
        this.position = position;
    }    

    int getPosition() {
        return this.position;
    }
    
    int getHP() {
        return this.hp;
    }

    void takeDamage(int damage) {
        this.hp -= damage;    
    }

}