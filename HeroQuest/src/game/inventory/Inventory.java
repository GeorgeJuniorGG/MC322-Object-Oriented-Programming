package game.inventory;

import game.characters.Character;
import game.interfaces.IOMediator;
import game.items.Item;
import game.items.armors.Armor;
import game.items.potions.Potion;
import game.items.weapons.Weapon;

public class Inventory {
    private Bag bag;
    private InUse inUse;

    public Inventory() {
        this.bag = new Bag();
        this.inUse = new InUse();
    }

    public void collectItem(Item item) {
        bag.addItem(item);
    }

    public void changeItem(Character character, IOMediator mediator) {
        mediator.alert(this.toString());
        String command;
        while(true) {
            command = mediator.changeItemMenu();
            if(command.equals("a")) {
                equipArmor(mediator);
            }
            else if(command.equals("w")) {
                equipWeapon(mediator);
            }
            else if(command.equals("p")) {
                drinkPotion(character, mediator);
            }
            else if(command.equals("q")) {
                break;
            }
        }
    }

    private void equipArmor(IOMediator mediator) {
        boolean success;
        Armor[] armors = bag.getArmors();
        if(armors != null) {
            int chosen = mediator.chooseItem(armors);
            if(chosen != -1) {
                success = inUse.equipArmor(armors[chosen]);
                if(success) {
                    bag.removeItem(armors[chosen]);
                }
                else {
                    Armor currentArmor = inUse.getArmor();
                    inUse.removeArmor();
                    bag.addItem(currentArmor);
                    inUse.equipArmor(armors[chosen]);
                    bag.removeItem(armors[chosen]);
                }
            }
        }
        else {
            mediator.alert("Você não possui nenhuma armadura!");
        }
    }

    private void equipWeapon(IOMediator mediator) {
        boolean success;
        Weapon[] weapons = bag.getWeapons();
        if(weapons != null) {
            int chosen = mediator.chooseItem(weapons);
            if(chosen != -1) {
                success = inUse.equipWeapon(weapons[chosen]);
                if(success) {
                    bag.removeItem(weapons[chosen]);
                }
                else {
                    changeWeapon(mediator);
                }
            }
        }
        else {
            mediator.alert("Você não possui nenhuma arma!");
        }
    }
    
    public void equipWeapon(Weapon weapon) {
        boolean success;
        success = inUse.equipWeapon(weapon);
        if(!success) {
            bag.addItem(weapon);
        }
    }

    private void drinkPotion(Character character, IOMediator mediator) {
        Potion[] potions = bag.getPotions();
        if(potions != null) {
            int chosen = mediator.chooseItem(potions);
            if(chosen != -1) {
                potions[chosen].usePotion(character);
                bag.removeItem(potions[chosen]);
            }
        }
        else {
            mediator.alert("Você não possui nenhuma poção!");
        }
    }

    public int getMoney() {
        int amount = bag.getMoney();
        return amount;
    }

    public int getExtraAttackDices() {
        return inUse.getExtraAttackDices();
    }

    public int getExtraDefenseDices() {
        return inUse.getExtraDefenseDices();
    }

    public int getRange() {
        return inUse.getRange();
    }

    private void changeWeapon(IOMediator mediator) {
        int answer;
        boolean success;
        Weapon rightHand = inUse.getRightHand();
        Weapon leftHand = inUse.getLeftHand();

        mediator.alert("Voce precisa remover uma arma antes de equipar a arma escolhida!\n");
        mediator.alert(inUse.toString());
        answer = mediator.getAnswer("Digite 1 se deseja remover a arma da mão direita, 0 se deseja remover a da mão esquerda: ");
        mediator.alert("\nLembre-se que remover a arma não substitui a arma atual pela arma escolhida, tente equipar novamente!");

        if(answer == 1) {
            success = inUse.removeWeapon(rightHand);
            if(success) {
                bag.addItem(rightHand);
            }
        }
        else {
            success = inUse.removeWeapon(leftHand);
            if(success) {
                bag.addItem(leftHand);
            }
        }
        if(success == false) {
            mediator.alert("Selecione uma posição válida!");
            changeWeapon(mediator);
        }
    }

    public void destroy() {
        Weapon rightHand = inUse.getRightHand();
        Weapon leftHand = inUse.getLeftHand();
        if(rightHand != null && rightHand.isDestructed()) {
            inUse.removeWeapon(rightHand);
        }
        if(leftHand != null && leftHand.isDestructed()) {
            inUse.removeWeapon(leftHand);
        }
    }

    @Override
    public String toString() {

        String description = "";

        description += "Items em uso\n";
        description += inUse.toString();

        description += "\nItems na mochila\n";
        description += bag.toString();
        
        return description;
    }
}