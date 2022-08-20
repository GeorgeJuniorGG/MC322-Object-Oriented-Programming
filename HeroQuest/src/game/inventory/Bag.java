package game.inventory;

import game.items.Item;
import game.items.ItemTypes;
import game.items.armors.Armor;
import game.items.coins.Coin;
import game.items.potions.Potion;
import game.items.weapons.Weapon;

public class Bag {
    private static int MAX_CAPACITY = 20;

    private Armor[] armors;
    private Coin[] coins;
    private Potion[] potions;
    private Weapon[] weapons;
    private int currentArmorCapacity;
    private int currentCoinCapacity;
    private int currentPotionCapacity;
    private int currentWeaponCapacity;

    Bag() {
        this.armors = new Armor[MAX_CAPACITY];
        this.currentArmorCapacity = 0;
        this.coins = new Coin[MAX_CAPACITY];
        this.currentCoinCapacity = 0;
        this.potions = new Potion[MAX_CAPACITY];
        this.currentPotionCapacity = 0;
        this.weapons = new Weapon[MAX_CAPACITY];
        this.currentWeaponCapacity = 0;
    }

    void addItem(Item item) {
        if (item != null) {
            ItemTypes type = item.getItemType();
            if(type == ItemTypes.ARMOR) {
                addArmor((Armor) item);
            }
            else if(type == ItemTypes.COIN) {
                addCoin((Coin) item);
            }
            else if(type == ItemTypes.POTION) {
                addPotion((Potion) item);
            }
            else if(type == ItemTypes.WEAPON) {
                addWeapon((Weapon) item);
            }
        }    
    }

    private void addArmor(Armor armor) {
        if(currentArmorCapacity < MAX_CAPACITY) {
            for(int i = 0; i < armors.length; i++) {
                if(armors[i] == null) {
                    armors[i] = armor;
                    currentArmorCapacity++;
                    break;
                }
            }
        }
    }

    private void addCoin(Coin coin) {
        if(currentCoinCapacity < MAX_CAPACITY) {
            for(int i = 0; i < coins.length; i++) {
                if(coins[i] == null) {
                    coins[i] = coin;
                    currentCoinCapacity++;
                    break;
                }
            }
        }
    }

    private void addPotion(Potion potion) {
        if(currentPotionCapacity < MAX_CAPACITY) {
            for(int i = 0; i < potions.length; i++) {
                if(potions[i] == null) {
                    potions[i] = potion;
                    currentPotionCapacity++;
                    break;
                }
            }
        }
    }
    private void addWeapon(Weapon weapon) {
        if(currentWeaponCapacity < MAX_CAPACITY) {
            for(int i = 0; i < weapons.length; i++) {
                if(weapons[i] == null) {
                    weapons[i] = weapon;
                    currentWeaponCapacity++;
                    break;
                }
            }
        }
    }
    void removeItem(Item item) {
        if(item != null) {
            if(item.getItemType() == ItemTypes.ARMOR) {
                removeArmor((Armor) item);
            }
            else if(item.getItemType() == ItemTypes.COIN) {
                removeCoin((Coin) item);
            }
            else if(item.getItemType() == ItemTypes.POTION) {
                removePotion((Potion) item);
            }
            else if(item.getItemType() == ItemTypes.WEAPON) {
                removeWeapon((Weapon) item);
            }
        }
    }

    private void removeArmor(Armor armor) {
        if(currentArmorCapacity != 0) {
            for(int i = 0; i < armors.length; i++) {
                if(armors[i] == armor) {
                    armors[i] = null;
                    currentArmorCapacity--;
                    break;
                }
            }
        }
    }
    private void removeCoin(Coin coin) {
        if(currentCoinCapacity != 0) {
            for(int i = 0; i < coins.length; i++) {
                if(coins[i] == coin) {
                    coins[i] = null;
                    currentCoinCapacity--;
                    break;
                }
            }
        }
    }
    private void removePotion(Potion potion) {
        if(currentPotionCapacity != 0) {
            for(int i = 0; i < potions.length; i++) {
                if(potions[i] == potion) {
                    potions[i] = null;
                    currentPotionCapacity--;
                    break;
                }
            }
        }
    }
    private void removeWeapon(Weapon weapon) {
        if(currentWeaponCapacity != 0) {
            for(int i = 0; i < weapons.length; i++) {
                if(weapons[i] == weapon) {
                    weapons[i] = null;
                    currentWeaponCapacity--;
                    break;
                }
            }
        }
    }

    Armor[] getArmors() {
        if(currentArmorCapacity > 0) {
            return armors;
        }
        return null;
    }

    Weapon[] getWeapons() {
        if(currentWeaponCapacity > 0) {
            return weapons;
        }
        return null;
    }
    
    Potion[] getPotions() {
        if(currentPotionCapacity > 0) {
            return potions;
        }
        return null;
    }

    Coin[] getCoins() {
        if(currentCoinCapacity > 0) {
            return coins;
        }
        return null;
    }

    int getMoney() {
        int money = 0;
        for(Coin coin : coins) {
            if(coin != null) {
                money += coin.getValue();
            }
        }
        return money;
    }

    @Override
    public String toString() {
        String description = "";
        //Armaduras
        description += "Armaduras:";
        for(int i = 0; i < armors.length; i++) {
            if(armors[i] != null) {
                description += " " + armors[i].getName() + "|";
            }
        }
        //Armas
        description += "\nArmas:";
        for(int i = 0; i < weapons.length; i++) {
            if(weapons[i] != null) {
                description += " " + weapons[i].getName() + "|";
            }
        }
        //Poções
        description += "\nPoções:";
        for(int i = 0; i < potions.length; i++) {
            if(potions[i] != null) {
                description += " " + potions[i].getName() + "|";
            }
        }
        description += "\nSaldo: " + getMoney() + " moedas.";
        return description;
    }
}