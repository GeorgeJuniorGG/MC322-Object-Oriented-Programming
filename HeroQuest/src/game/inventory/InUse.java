package game.inventory;

import game.items.armors.Armor;
import game.items.weapons.Weapon;

public class InUse {
    private Armor armor;
    private Weapon leftHand;
    private Weapon rightHand;
    private int freeHands;
    
    InUse() {
        this.armor = null;
        this.leftHand = null;
        this.rightHand = null;
        this.freeHands = 2;
    }

    boolean equipArmor(Armor armor) {
        if(this.armor == null) {
            this.armor = armor;
            return true;
        }
        return false;
    }

    void removeArmor() {
        this.armor = null;
    }

    boolean equipWeapon(Weapon weapon) {
        try {
            if(weapon.getSpace() == 2 && rightHand == null && leftHand == null) {
                rightHand = weapon;
                freeHands -= weapon.getSpace();
                return true;
            }
            else if (weapon.getSpace() == 1 && freeHands > 0) {
                if (rightHand == null) {
                    rightHand = weapon;
                    freeHands -= weapon.getSpace();
                }
                else if (leftHand == null) {
                    leftHand = weapon;
                    freeHands -= weapon.getSpace();
                }
                return true;
            }
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    boolean removeWeapon (Weapon weapon) {
        try {
            if (rightHand == weapon) {
                rightHand = null;
                freeHands += weapon.getSpace();
            }
            else if (leftHand == weapon) {
                leftHand = null;
                freeHands += weapon.getSpace();
            }
            return true;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    int getExtraDefenseDices() {
        if (armor == null) {
            return 0;
        }
        else {
            return armor.getExtraDefenseDices();
        }
    }

    int getExtraAttackDices() {
        if (freeHands == 2) {
            return 0;
        }
        else {
            int bonusDices = 0;
            if (rightHand != null) {
                bonusDices += rightHand.getBonusAttackDices();
                if(rightHand.isDestructed()) {
                    removeWeapon(rightHand);
                }
            }
            if (leftHand != null) {
                bonusDices += leftHand.getBonusAttackDices();
                if(leftHand.isDestructed()) {
                    removeWeapon(leftHand);
                }
            }
            return bonusDices;
        }
    }

    int getRange() {
        if(freeHands == 2) {
            return 1;
        }
        else {
            int bonusRange = 0;
            if(rightHand != null) {
                bonusRange += rightHand.getRange();
            }
            if(leftHand != null) {
                bonusRange += leftHand.getRange();
            }
            return bonusRange;
        }
    }

    Armor getArmor() {
        return this.armor;
    }

    Weapon getRightHand() {
        return rightHand;
    }

    Weapon getLeftHand() {
        return leftHand;
    }

    @Override
    public String toString() {
        String description = "";

        if(armor != null) {
            description += "Armadura: " + armor.getName() + "\n";
        }
        else {
            description += "Armadura: Nenhuma \n";
        }
        if(rightHand != null) {
            description += "Mão direita: " + rightHand.getName() + "\n";
        }
        else {
            description += "Mão direita: Nenhuma \n";
        }
        if(leftHand != null) {
            description += "Mão esquerda: " + leftHand.getName() + "\n";
        }
        else {
            if(freeHands == 0) {
                description += "Mão esquerda: Ocupada";
            }
            else {
                description += "Mão esquerda: Nenhuma";
            }
        }
        return description;
    }
}