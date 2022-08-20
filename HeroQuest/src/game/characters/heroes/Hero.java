package game.characters.heroes;

import game.characters.Character;
import game.characters.monsters.Monster;
import game.inventory.Inventory;
import game.items.Item;
import game.map.Coordinate;
import game.map.Map;
import game.map.MapESymbol;


public abstract class Hero extends Character {

    protected int pointsOfSmart;
    protected Inventory inventory;
    private HeroClass heroClass;

    
    

    public Hero(String name, int quantityAttackDices, int quantityDefenseDices, int pointsOfLife, int pointsOfSmart, Coordinate coordinate, MapESymbol symbol, HeroClass heroClass) {
        super(name, pointsOfLife, quantityAttackDices, quantityDefenseDices, coordinate, symbol);
        this.pointsOfSmart = pointsOfSmart;
        this.heroClass = heroClass;
        this.inventory = new Inventory();
    }


    private int effectiveAttack() {
        return this.damageOfDice() + inventory.getExtraAttackDices();
    }

    @Override
    protected int effectiveDefense() {
        return this.defenseOfDice() + inventory.getExtraDefenseDices();
    }

    public int weaponRange() {
        return inventory.getRange();
    }

    @Override
    public boolean isDaneable() {
        return true;
    }

    // ## ATACAR
    @Override
    public void attack(Character character) {
        // pegar numero de ataque dos dados e do bônus do inventário
        int damage = effectiveAttack();
        mediator.setAttacker(this);
        character.defense(damage);
        inventory.destroy();
    }

    public boolean battleAttack(Map map) {
        Monster[] targets = map.getNearMonsters(coordinate, direction, inventory.getRange(), 1, true);
        if (targets == null) {
            return false;
        } else {
            attack(targets[0]);
        }
        emptyDeadMonster(targets, map);
        return true;
    }

    

    // ## DEFENDER
    @Override
    public void defense(int damage) {
        // pegar numero de ataque dos dados e do bônus do inventário
        int defense = effectiveDefense();
        int damageEffective = damage - defense;
        if (damageEffective > 0) {
            pointsOfLife -= damageEffective;
            mediator.battleMonster(this, damageEffective);
        } else {
            mediator.battleMonster(this, 0);
        }
    }

    private int defenseOfDice() {
        int defense = 0;
        for (int i = 0; i < quantityDefenseDices; i++) {
            if (defenseDice.getNumber() == 2) {
                defense += 1;
            }
        }
        return defense;
    }

    public void collectItem(Item item) {
        inventory.collectItem(item);
    }

    public void changeItems() {
        inventory.changeItem(this, mediator);
    }

    public int getMoney() {
        return inventory.getMoney();
    }
    
    /*
    public void collectTreasure(Treasure treasure) {
        Item[] items = treasure.getItems();
        for (Item item : items) {
            inventory.collectItem(item);
        }
    }
    */

    
    public void collectTreasure(Item[] items) {
        for (Item item : items) {
            inventory.collectItem(item);
        }
    }
    

    protected void emptyDeadMonster(Monster[] monsters, Map map) {
        Monster[] deadTargets = new Monster[monsters.length];
        for (int i = 0; i < deadTargets.length; i++) {
            deadTargets[i] = null;
            if (monsters[i] != null && monsters[i].isDead()) {
                deadTargets[i] = monsters[i];
            }
        }
        map.removeDeadMonsters(deadTargets, this.coordinate.getPlace());
    }

    public boolean useMagic(Map map) {
        mediator.alert("Sua classe não faz uso de magias.");
        return false;
    }

    @Override
    public String toString() {
        String description;
        description = "Nome: " + name + "\n";
        description += "Classe: " + heroClass.toString() + "\n";
        description += "Dados de Ataque: " + quantityAttackDices + " | Dados de Defesa: " + quantityDefenseDices + "\n";
        description += "Pontos de Vida: " + pointsOfLife + " | Pontos de Inteligência: " + pointsOfSmart + "\n";
        description += inventory.toString();
        return description;
    }
}