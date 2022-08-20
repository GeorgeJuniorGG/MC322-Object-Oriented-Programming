package game.items.weapons;

public class LongSword extends Weapon{
    private static int RANGE = 1;
    private static int BONUS_ATTACK_DICES = 3;
    private static int INVENTORY_SPACE = 2;
    private static boolean DESTRUCTION = false;
    private static int NUMBER_OF_TARGETS = 1;
    private static boolean DIAGONAL = true;

    public LongSword() {
        super(RANGE, BONUS_ATTACK_DICES, DESTRUCTION, INVENTORY_SPACE, NUMBER_OF_TARGETS, DIAGONAL);
    }
   
    @Override
    public String getName() {
        return "Long Sword";
    }
}