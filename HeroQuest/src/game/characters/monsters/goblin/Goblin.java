package game.characters.monsters.goblin;

import game.characters.heroes.Hero;
import game.characters.monsters.Monster;
import game.items.weapons.Dagger;
import game.map.Coordinate;
import game.map.Direction;
import game.map.Map;
import game.map.MapESymbol;

public class Goblin extends Monster {
    
    private int numberOfDaggers;

    public Goblin(String name, int x, int y, String place) {
        super(name, 3, 1, 1, x, y, place, MapESymbol.GOBLIN);
        setWeapon(new Dagger());
        this.numberOfDaggers = 5;
    }

    private Direction moveX(Coordinate coordinate) {
        int xHero = coordinate.getX(); // x do herói
        int xMonster = this.coordinate.getX();
        if (coordinate.getPlace().equals(this.coordinate.getPlace())) {
            if (xMonster > xHero) {
                return Direction.UP;
            } else if (xMonster < xHero) {
                return Direction.DOWN;
            }
        }
        return null;
    }

    private Direction moveY(Coordinate coordinate) {
        int yHero = coordinate.getY(); // y do herói
        int yMonster = this.coordinate.getY(); // y do monster
        if (coordinate.getPlace().equals(this.coordinate.getPlace())) {
            if (yMonster > yHero) {
                return Direction.LEFT;
            } else if (yMonster < yHero) {
                return Direction.RIGHT;
            }
        }
        return null;
    }

    private Direction generationOfDirection(Map map, Hero hero) {

        // movimentação em x
        Direction directionX = moveX(hero.getCoordinate());
        Direction directionY = moveY(hero.getCoordinate());
        if (directionX != null) {
            if (map.isValidMovement(this.coordinate, directionX)) {
                return directionX;
            }
        }
        if (directionY != null) {
            if (map.isValidMovement(this.coordinate, directionY)) {
                return directionY;
            }
        }
        return generationOfDirection();
    }


    @Override
    public void movement(Map map, Hero hero) {
        
        int steps = playMovementDices();


        for (int i = 0; i < steps; i++) {
            if (isHeroNear(coordinate, hero)) {
                 break;
            }
            map.moveMonster(this, generationOfDirection(map, hero));
        }
        
    }

    @Override
    protected void useWeapon() {
        if (this.numberOfDaggers > 0) {
            this.numberOfDaggers--;
        } else {
            super.useWeapon();
        }
        
    } 
}