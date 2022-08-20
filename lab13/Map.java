package lab13;

import lab13.Obstacles.Obstacle;
import lab13.Jewels.Jewel;
import lab13.Jewels.GreenJewel;
import lab13.Jewels.BlueJewel;
import lab13.Jewels.RedJewel;

public class Map {
    public final int DEFAULT_POSITION = 0;

    private int xSize;
    private Void blank;
    private int ySize;
    private Item[][] matrix;

    public Map (int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.matrix = new Item[ySize][xSize];
        this.blank = new Void();
        for(int i = 0; i < ySize; i++) {
            for(int j = 0; j < xSize; j++) {
                matrix[i][j] = blank;
            }
        }
    }

    void addRobot (Robot robot) {
        int x = robot.getX();
        int y = robot.getY();
        matrix[y][x] = robot;
    }

    void addJewel (Jewel jewel) {
        int x = jewel.getX();
        int y = jewel.getY();
        if(x < this.xSize && y < this.ySize) {
            matrix[y][x] = jewel;
        }
        else
            System.out.println("This jewel position is invalid!");
    }

    void removeJewel (int x, int y) {
        if(x < this.xSize && y < this.ySize) {
            matrix[y][x] = blank;
        }
        else
            System.out.println("This jewel position is invalid!");
    }

    void addObstacle (Obstacle obstacle) {
        int x = obstacle.getX();
        int y = obstacle.getY();
        if (x < this.xSize && y < this.ySize) {
            matrix[y][x] = obstacle;           
        }
        else
            System.out.println("This obstacle position is invalid!");
    }

    void removeObstacle (int x, int y) {
        if(x < this.xSize && y < this.ySize) {
            matrix[y][x] = blank;
        }
        else
            System.out.println("This obstacle position is invalid!");
    }

    void printMap() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                System.out.print(matrix[i][j].getSymbol());
            }
            System.out.println("");
        }
    }

    boolean moveBack(int x, int y, Robot robot) {
        try {
            if (matrix[y + 1][x] == blank){
                matrix[y][x] = blank;
                matrix[y + 1][x] = robot;
                return true;
            }
            else {
                throw new SpaceNotValidException("There's an obstacle or a Jewel there!");
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
        catch (SpaceNotValidException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    boolean moveFront(int x, int y, Robot robot) {
        try {
            if (matrix[y - 1][x] == blank){
                matrix[y][x] = blank;
                matrix[y - 1][x] = robot;
                return true;
            }
            else {
                throw new SpaceNotValidException("There's an obstacle or a Jewel there!");
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
        catch (SpaceNotValidException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    boolean moveLeft(int x, int y, Robot robot) {
        try{
            if (matrix[y][x - 1] == blank){
                matrix[y][x] = blank;
                matrix[y][x - 1] = robot;
                return true;
            }
            else {
                throw new SpaceNotValidException("There's an obstacle or a Jewel there!");
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
        catch (SpaceNotValidException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    boolean moveRight(int x, int y, Robot robot) {
        try {
            if (matrix[y][x + 1] == blank){
                matrix[y][x] = blank;
                matrix[y][x + 1] = robot;
                return true;
            }
            else {
                throw new SpaceNotValidException("There's an obstacle or a Jewel there!");
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
        catch (SpaceNotValidException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Jewel checkNorth(int x, int y) {
        if (y - 1 > 0){
            if(matrix[y - 1][x].getSymbol() == "JG"){
                GreenJewel jewel = (GreenJewel) matrix[y - 1][x];
                removeJewel(x, y - 1);
                return jewel;
            }
            else if(matrix[y - 1][x].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y - 1][x];
                removeJewel(x, y - 1);
                return jewel;
            }
            else if(matrix[y - 1][x].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y - 1][x];
                removeJewel(x, y - 1);
                return jewel;
            }
        }
        return null;

    }

    private Jewel checkSouth(int x, int y) {
        if (y + 1 < ySize){
            if(matrix[y + 1][x].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y + 1][x];
                removeJewel(x, y + 1);
                return jewel;
            }
            else if(matrix[y + 1][x].getSymbol() == "JB"){
                BlueJewel jewel = (BlueJewel) matrix[y + 1][x];
                removeJewel(x, y + 1);
                return jewel;
            }
            else if(matrix[y + 1][x].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y + 1][x];
                removeJewel(x, y + 1);
                return jewel;
            }
        }
        return null;
    }

    private Jewel checkEast (int x, int y) {
        if (x + 1 < xSize){
            if(matrix[y][x + 1].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y][x + 1];
                removeJewel(x + 1, y);
                return jewel;
            }
            else if(matrix[y][x + 1].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y][x + 1];
                removeJewel(x + 1, y);
                return jewel;
            }
            else if(matrix[y][x + 1].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y][x + 1];
                removeJewel(x + 1, y);
                return jewel;
            }
        }
        return null;
    }

    private Jewel checkWest (int x, int y) {
        if (x - 1 > 0){
            if(matrix[y][x - 1].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y][x - 1];
                removeJewel(x - 1, y);
                return jewel;
            }
            else if(matrix[y][x - 1].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y][x - 1];
                removeJewel(x - 1, y);
                return jewel;
            }
            else if(matrix[y][x - 1].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y][x - 1];
                removeJewel(x - 1, y);
                return jewel;
            }
        }
        return null;
    }

    private Jewel checkNortheast (int x, int y) {
        if (y - 1 > 0 && x + 1 < xSize){
            if(matrix[y - 1][x + 1].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y - 1][x + 1];
                removeJewel(x + 1, y - 1);
                return jewel;
            }
            else if(matrix[y - 1][x + 1].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y - 1][x + 1];
                removeJewel(x + 1, y - 1);
                return jewel;
            }
            else if(matrix[y - 1][x + 1].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y - 1][x + 1];
                removeJewel(x + 1, y - 1);
                return jewel;
            }
        }
        return null;
    }

    private Jewel checkNorthwest (int x, int y) {
        if (y - 1 > 0 && x - 1 > 0){
            if(matrix[y - 1][x - 1].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y - 1][x - 1];
                removeJewel(x - 1, y - 1);
                return jewel;
            }
            else if(matrix[y - 1][x - 1].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y - 1][x - 1];
                removeJewel(x - 1, y - 1);
                return jewel;
            }
            else if(matrix[y - 1][x - 1].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y - 1][x - 1];
                removeJewel(x - 1, y - 1);
                return jewel;
            }
        }
        return null;
    }

    private Jewel checkSoutheast (int x, int y) {
        if (y + 1 < ySize && x + 1 < xSize){
            if(matrix[y + 1][x + 1].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y + 1][x + 1];
                removeJewel(x + 1, y + 1);
                return jewel;
            }
            else if(matrix[y + 1][x + 1].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y + 1][x + 1];
                removeJewel(x + 1, y + 1);
                return jewel;
            }
            else if(matrix[y + 1][x + 1].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y + 1][x + 1];
                removeJewel(x + 1, y + 1);
                return jewel;
            }
        }
        return null;
    }

    private Jewel checkSouthwest (int x, int y) {
        if (y + 1 < ySize && x - 1 > 0){
            if(matrix[y + 1][x - 1].getSymbol() == "JG") {
                GreenJewel jewel = (GreenJewel) matrix[y + 1][x - 1];
                removeJewel(x - 1, y + 1);
                return jewel;
            }
            else if(matrix[y + 1][x - 1].getSymbol() == "JB") {
                BlueJewel jewel = (BlueJewel) matrix[y + 1][x - 1];
                removeJewel(x - 1, y + 1);
                return jewel;
            }
            else if(matrix[y + 1][x - 1].getSymbol() == "JR") {
                RedJewel jewel = (RedJewel) matrix[y + 1][x - 1];
                removeJewel(x - 1, y + 1);
                return jewel;
            }
        }
        return null;
    }

    Jewel[] checkForJewels(int x, int y) {
        Jewel[] jewelsFound = new Jewel[8];
        jewelsFound[0] = checkNorthwest(x, y);
        jewelsFound[1] = checkNorth(x, y);
        jewelsFound[2] = checkNortheast(x, y);
        jewelsFound[3] = checkEast(x, y);
        jewelsFound[4] = checkSoutheast(x, y);
        jewelsFound[5] = checkSouth(x, y);
        jewelsFound[6] = checkSouthwest(x, y);
        jewelsFound[7] = checkWest(x, y);
        return jewelsFound;
    }

    Item[] getRechargeables(int x, int y) {
        Item[] items = new Item[8];
        if(x + 1 < xSize) {
            items[0] = matrix[y][x + 1];
        }
        if(x - 1 > 0) {
            items[1] = matrix[y][x - 1];
        }
        if(y + 1 < ySize) {
            items[2] = matrix[y + 1][x];
        }
        if(y - 1 > 0) {
            items[3] = matrix[y - 1][x];
        }
        if(y + 1 < ySize && x + 1 < xSize) {
            items[4] = matrix[y + 1][x + 1];
        }
        if(y + 1 < ySize && x - 1 > 0) {
            items[5] = matrix[y + 1][x - 1];
        }
        if(y - 1 > 0 && x + 1 < xSize) {
            items[6] = matrix[y - 1][x + 1];
        }
        if(y - 1 > 0 && x - 1 > 0) {
            items[7] = matrix[y - 1][x - 1];
        }
        return items;
    }
}