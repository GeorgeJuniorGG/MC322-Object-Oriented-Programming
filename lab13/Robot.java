package lab13;

import lab13.Jewels.BlueJewel;
import lab13.Jewels.Jewel;
import lab13.Jewels.JewelType;
import lab13.Obstacles.Tree;


public class Robot extends Item{
    public final int RED_VALUE = 100;
    public final int BLUE_VALUE = 10;
    public final int GREEN_VALUE = 50;

    private int x;
    private int y;
    private int bagSize;
    private int jewelsFound;
    private int energyPoints;
    private Jewel[] bag;

    public Robot(int x, int y, int bagSize) {
        this.x = x;
        this.y = y;
        this.bagSize = bagSize;
        this.bag = new Jewel[bagSize];
        this.energyPoints = 5;   
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getEnergyPoints() {
        return energyPoints;
    }

    int getJewelsFound() {
        return jewelsFound;
    }

    void moveBackwards(Map map) {
        boolean permission = map.moveBack(x, y, this);
        if (permission && energyPoints > 0) {
            this.y = y + 1;
            energyPoints--;
        }
    }

    void moveForwards(Map map) {
        boolean permission = map.moveFront(x, y, this);
        if (permission && energyPoints > 0) {
            this.y = y - 1;
            energyPoints--;
        }
    }

    void moveToTheLeft(Map map) {
        boolean permission = map.moveLeft(x, y, this);
        if (permission && energyPoints > 0) {
            this.x = x - 1;
            energyPoints --;
        }
    }

    void moveToTheRight(Map map) {
        boolean permission = map.moveRight(x, y, this);
        if (permission && energyPoints > 0) {
            this.x = x + 1;
            energyPoints --;
        }    
    }

    void checkSorroundings(Map map) {
        getBonusEnergy(map);
        collectJewels(map);
    }

    private void collectJewels(Map map) {
        Jewel[] jewelsFound = map.checkForJewels(x, y);
        for (int i = 0; i < jewelsFound.length; i++) {
            if(jewelsFound[i] != null) {
                this.bag[this.jewelsFound] = jewelsFound[i];
                this.jewelsFound++;
            }
        }
    }

    private void getBonusEnergy(Map map) {
        Item[] sorroundings = map.getRechargeables(x, y);
        for(int i = 0; i < sorroundings.length; i++) {
            if(sorroundings[i] != null) {
                if(sorroundings[i].getSymbol() == "$$") {
                    energyPoints += ((Tree) sorroundings[i]).getBonusEnergy();
                }
                else if(sorroundings[i].getSymbol() == "JB") {
                    energyPoints += ((BlueJewel) sorroundings[i]).getBonusEnergy();
                }
            }
        }
    }

    private int getJewelValue(Jewel jewel) {
        if (jewel.getType() == JewelType.Blue) {
            return BLUE_VALUE;
        }
        else if (jewel.getType() == JewelType.Green) {
            return GREEN_VALUE;
        }
        return RED_VALUE;
    }

    void getJewels() {
        int points = 0;
        for (int i = 0; i < bagSize; i++) {
            if (bag[i] != null) {
                points += getJewelValue(bag[i]);
            }
        }
        System.out.println("Bag total items: " + jewelsFound + " | Bag total value: " + points + " | Total energy points: " + energyPoints);
    }

    @Override
    public String getSymbol() {
        return "ME";
    }
}