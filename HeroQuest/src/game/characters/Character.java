package game.characters;

import game.dices.RedDice;
import game.dices.WhiteDice;
import game.interfaces.IOMediator;
import game.map.Coordinate;
import game.map.Direction;
import game.map.MapESymbol;
import game.map.MapElement;

public abstract class Character extends MapElement {
    protected String name;
    protected WhiteDice attackDice;
    protected WhiteDice defenseDice;
    protected int pointsOfLife; 
    protected RedDice movementDice;
    protected int quantityAttackDices;
    protected int quantityDefenseDices;
    protected Direction direction;

    protected IOMediator mediator;

    protected Character(String name, int pointsOfLife, int quantityAttackDices, int quantityDefenseDices, Coordinate coordinate, MapESymbol symbol) {
        super(coordinate, symbol);
        this.name = name; 
        this.attackDice = new WhiteDice();
        this.defenseDice = new WhiteDice();
        this.movementDice = new RedDice();
        this.pointsOfLife = pointsOfLife;
        this.quantityAttackDices = quantityAttackDices;
        this.quantityDefenseDices = quantityDefenseDices;
        this.direction = Direction.UP;
    }

    public abstract void attack(Character character);
    protected abstract int effectiveDefense();

    public void setMediator(IOMediator mediator) {
        if (this.mediator == null) {
            this.mediator = mediator;
        }
    }

    public boolean isDead() {
        if (this.pointsOfLife <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addDefenseDices(int bonus) {
        this.quantityDefenseDices += bonus;
    }

    public void addAttackDices(int bonus) {
        this.quantityAttackDices += bonus;
    }

    public void heal(int bonus) {
        this.pointsOfLife += bonus;
    }

    

    public void receivesDamage(int damage) {
        this.pointsOfLife -= damage;
    }

    public boolean isDaneable() {
        return false;
    }


    // ############# ATAQUE && DEFESA ##################
    protected int damageOfDice() {
        int damage = 0;
        for (int i = 0; i < quantityAttackDices; i++) {
            if (attackDice.getNumber() == 1) {
                damage +=1;
            }
        }
        return damage;
    } 

    public void defense(int damage) {
        int defense = effectiveDefense();
        int damageEffective = damage - defense;
        if (damageEffective > 0) {
            pointsOfLife -= damageEffective;
            mediator.battleHero(this, damageEffective);
        } else {
            mediator.battleHero(this, 0);
        }
    }
    
    // ## MOVIMENTAÇÃO
    public void move(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int playMovementDices() {
        int steps = 0;
        for (int i = 0; i < 2; i++) {
            steps += movementDice.getNumber();
        }
        return steps;
    }



    public Coordinate nextPosition(Direction direction) {
        String place = this.coordinate.getPlace();
        this.direction = direction;
        Coordinate nextPosition = new Coordinate(coordinate.getX() + direction.getxOffset(), coordinate.getY() + direction.getyOffset(), place);
        return nextPosition;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public String getName() {
        return this.name;
    }

    public int getPointsOfLife() {
        return this.pointsOfLife;
    }
    
}