package game.items.weapons;

public class Dagger extends Weapon {
    private static int RANGE = 1;
    private static int BONUS_ATTACK_DICES = 1;
    private static int INVENTORY_SPACE = 1;
    private static boolean DESTRUCTION = true;
    private static int NUMBER_OF_TARGETS = 1;
    private static boolean DIAGONAL = false;

    public Dagger() {
        super(RANGE, BONUS_ATTACK_DICES, DESTRUCTION, INVENTORY_SPACE, NUMBER_OF_TARGETS, DIAGONAL);
    }

    @Override
    public String getName() {
        return "Dagger";
    }

}