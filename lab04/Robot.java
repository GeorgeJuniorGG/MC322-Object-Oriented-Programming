package lab04;

public class Robot {
    public final int RED_VALUE = 100;
    public final int BLUE_VALUE = 10;
    public final int GREEN_VALUE = 50;

    private int x;
    private int y;
    private int bagSize;
    private int jewelsFound;
    private JewelType[] bag;

    public Robot(int x, int y, int bagSize) {
        this.x = x;
        this.y = y;
        this.bagSize = bagSize;
        this.bag = new JewelType[bagSize];    
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void moveBackwards(Map map) {
        boolean permission = map.moveBack(x, y);
        if (permission)
            this.y = y + 1;
    }

    void moveForwards(Map map) {
        boolean permission = map.moveFront(x, y);
        if (permission)
            this.y = y - 1;
    }

    void moveToTheLeft(Map map) {
        boolean permission = map.moveLeft(x, y);
        if (permission)
            this.x = x - 1;
    }

    void moveToTheRight(Map map) {
        boolean permission = map.moveRight(x, y);
        if (permission)
            this.x = x + 1;
    }

    void collectJewels(Map map) {
        JewelType[] jewelsFound = map.checkForJewels(x, y);
        for (int i = 0; i < 8; i++) {
            if(jewelsFound[i] != null) {
                this.bag[this.jewelsFound] = jewelsFound[i];
                this.jewelsFound++;
            }
        }
    }
    private int getJewelValue(JewelType jewelType) {
        if (jewelType == JewelType.Blue) {
            return BLUE_VALUE;
        }
        else if (jewelType == JewelType.Green) {
            return GREEN_VALUE;
        }
        return RED_VALUE;
    }

    void getJewels() {
        int points = 0;
        for (int i = 0; i < bagSize; i++) {
            if (bag[i] != null) {
                points += getJewelValue(bag[i]);
            }
        }
        System.out.println("Bag total items: " + jewelsFound + " | Bag total value: " + points);
    }
}