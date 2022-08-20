package game.items.armors;

public enum ArmorTypes {
    
    LEATHER(1, "Leather Armor"),
    IRON(2, "Iron Armor"),
    GOLD(3, "Gold Armor"),
    DIAMOND(4, "Diamond Armor");

    private int extraDefenseDices;
    private String name;

    private ArmorTypes(int defenseDices, String name){
        this.extraDefenseDices = defenseDices;
        this.name = name;
    }

    public int getDefenseDices() {
        return extraDefenseDices;
    }

    public String getName() {
        return name;
    }
    
}