package game.interfaces;

import game.characters.Character;
import game.items.Item;
import game.magic.Magic;

public interface IOMediator {

    public int chooseItem(Item[] items);
    public void alert(String message);
    public String changeItemMenu();
    public int getAnswer(String query);
    public int chooseMagic(Magic[] magics, int[] quantity); // 1-FB, 2-MM, 3-SH, 4-TP

    public void battleHero(Character character, int damage);
    public void setAttacker(Character character);
    public void battleMonster(Character character, int damage);

    public void showMap();
    public void notifyMap(String place);

    public void showCollectItems(Item[] items);
}