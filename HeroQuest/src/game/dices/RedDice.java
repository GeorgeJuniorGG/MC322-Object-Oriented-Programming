package game.dices;

public class RedDice {
    private int number;

    public int getNumber() {
        number = (int) (Math.random() * 6) + 1;
        return number;        
    }
}