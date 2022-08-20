package game.dices;

public class WhiteDice {
    public static int SKULL = 1;
    public static int HERO_SHIELD = 2;
    public static int MONSTER_SHIELD = 3;

    private int number;

    public int getNumber() {
        number = (int) (Math.random() * 6) + 1;
        if(number <= 3) {
            return SKULL;
        }
        else if(number > 3 && number < 6) {
            return HERO_SHIELD;
        }
        else {
            return MONSTER_SHIELD;
        }
    }
}