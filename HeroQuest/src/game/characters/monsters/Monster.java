package game.characters.monsters;

import game.characters.Character;
import game.characters.heroes.Hero;
import game.items.weapons.Weapon;
import game.map.Coordinate;
import game.map.Direction;
import game.map.Map;
import game.map.MapESymbol;

public abstract class Monster extends Character {

    private Weapon weapon;
    
    public Monster(String name, int pointsOfLife, int quantityAttackDices, int quantityDefenseDices, int x, int y, String place, MapESymbol symbol) {
        super(name, pointsOfLife, quantityAttackDices, quantityDefenseDices, new Coordinate(x, y, place), symbol);
    }

    protected void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    // ## ATAQUE
    private int effectiveAttack() {
        if (weapon != null) {
            return this.damageOfDice() + weapon.getBonusAttackDices();
        }
        return this.damageOfDice();
    }

    public void attack(Character character) {
        int damage = effectiveAttack();
        mediator.setAttacker(this);
        character.defense(damage);
        if (this.weapon != null && this.weapon.isDestructed()) {
            useWeapon();
        }
    }

    // ## DEFESA
    private int defenseOfDice() {
        int defense = 0;
        for (int i = 0; i < quantityDefenseDices; i++) {
            if (defenseDice.getNumber() == 3) {
                defense += 1;
            }
        }
        return defense;
    }

    @Override
    protected int effectiveDefense() {
        return defenseOfDice();
    }

    public boolean hasHero(Coordinate coordinate, Hero hero) {
        Coordinate heroCoordinate = hero.getCoordinate();
        Coordinate coordinateUP = new Coordinate(coordinate.getX()-1, coordinate.getY(), coordinate.getPlace());
        Coordinate coordinateDOWN = new Coordinate(coordinate.getX()+1, coordinate.getY(), coordinate.getPlace());
        Coordinate coordinateLEFT = new Coordinate(coordinate.getX(), coordinate.getY()-1, coordinate.getPlace());
        Coordinate coordinateRIGHT = new Coordinate(coordinate.getX(), coordinate.getY()+1, coordinate.getPlace());
        if (coordinateUP.isSame(heroCoordinate)) {
            attack(hero);
            return true;
        } else if (coordinateDOWN.isSame(heroCoordinate)) {
            attack(hero);
            return true;
        } else if (coordinateLEFT.isSame(heroCoordinate)) {
            attack(hero);
            return true;
        } else if (coordinateRIGHT.isSame(heroCoordinate)) {
            attack(hero);
            return true;
        } else {
            return false;
        }
         
    }

    public boolean isHeroNear(Coordinate coordinate, Hero hero) {
        if (hasHero(coordinate, hero)) {
            return true;
        }
        return false;
    }

    public Direction generationOfDirection() {
        int number = (int) (Math.random() * 4) + 1;
        if (number == 1) {
            return Direction.UP;
        } else if (number == 2) {
            return Direction.DOWN;
        } else if (number == 3) {
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }

    public void movement(Map map, Hero hero) {
        int steps = playMovementDices();
        for (int i = 0; i < steps; i++) {
            if (isHeroNear(coordinate, hero)) {
                break;
            }
            map.moveMonster(this, generationOfDirection());
        }
    }

    protected void useWeapon() {
        this.weapon = null;
    }

}