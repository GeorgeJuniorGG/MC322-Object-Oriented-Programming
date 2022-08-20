package lab04;

public class Map {
    public final int DEFAULT_POSITION = 0;
    public final int BLUE_JEWEL_POSITION = 1;
    public final int GREEN_JEWEL_POSITION = 2;
    public final int RED_JEWEL_POSITION = 3;
    public final int TREE_OBSTACLE_POSITION = 4;
    public final int WATER_OBSTACLE_POSITION = 5;
    public final int ROBOT_POSITION = 6;

    private int xSize;
    private int ySize;
    private int[][] matrix;

    public Map (int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.matrix = new int[ySize][xSize];
    }

    void addRobot (Robot robot) {
        int x = robot.getX();
        int y = robot.getY();
        matrix[y][x] = ROBOT_POSITION;
    }

    void addJewel (Jewel jewel) {
        int x = jewel.getX();
        int y = jewel.getY();
        if(x < this.xSize && y < this.ySize) {
            if (jewel.getType() == JewelType.Blue)
                matrix[y][x] = BLUE_JEWEL_POSITION;
            else if (jewel.getType() == JewelType.Green)
                matrix[y][x] = GREEN_JEWEL_POSITION;
            else
                matrix[y][x] = RED_JEWEL_POSITION;
        }
        else
            System.out.println("This jewel position is invalid!");
    }

    void removeJewel (int x, int y) {
        if(x < this.xSize && y < this.ySize) {
            matrix[y][x] = DEFAULT_POSITION;
        }
        else
            System.out.println("This jewel position is invalid!");
    }

    void addObstacle (Obstacle obstacle) {
        int x = obstacle.getX();
        int y = obstacle.getY();
        if (x < this.xSize && y < this.ySize) {
            if (obstacle.getType() == ObstacleType.Tree)
                matrix[y][x] = TREE_OBSTACLE_POSITION;
            else
                matrix[y][x] = WATER_OBSTACLE_POSITION;            
        }
        else
            System.out.println("This obstacle position is invalid!");
    }

    void removeObstacle (int x, int y) {
        if(x < this.xSize && y < this.ySize) {
            matrix[y][x] = DEFAULT_POSITION;
        }
        else
            System.out.println("This obstacle position is invalid!");
    }

    void printMap() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (matrix[i][j] == DEFAULT_POSITION) {
                    System.out.print(" -- ");
                }
                else if (matrix[i][j] == BLUE_JEWEL_POSITION) {
                    System.out.print(" JB ");
                }
                else if (matrix[i][j] == GREEN_JEWEL_POSITION) {
                    System.out.print(" JG ");
                }
                else if (matrix[i][j] == RED_JEWEL_POSITION) {
                    System.out.print(" JR ");
                }
                else if (matrix[i][j] == TREE_OBSTACLE_POSITION) {
                    System.out.print(" $$ ");
                }
                else if (matrix[i][j] == WATER_OBSTACLE_POSITION) {
                    System.out.print(" ## ");
                }
                else if (matrix[i][j] == ROBOT_POSITION) {
                    System.out.print(" ME ");
                }
            }
            System.out.println("");
        }
    }

    boolean moveBack(int x, int y) {
        if (y + 1 < ySize) {
            if (matrix[y + 1][x] == DEFAULT_POSITION){
                matrix[y][x] = DEFAULT_POSITION;
                matrix[y + 1][x] = ROBOT_POSITION;
                return true;
            }
        }
        return false;
    }

    boolean moveFront(int x, int y) {
        if (y - 1 >= 0) {
            if (matrix[y - 1][x] == DEFAULT_POSITION){
                matrix[y][x] = DEFAULT_POSITION;
                matrix[y - 1][x] = ROBOT_POSITION;
                return true;
            }
        }
        return false;
    }
    
    boolean moveLeft(int x, int y) {
        if (x - 1 >= 0) {
            if (matrix[y][x - 1] == DEFAULT_POSITION){
                matrix[y][x] = DEFAULT_POSITION;
                matrix[y][x - 1] = ROBOT_POSITION;
                return true;
            }
        }
        return false;
    }

    boolean moveRight(int x, int y) {
        if (x + 1 < xSize) {
            if (matrix[y][x + 1] == DEFAULT_POSITION){
                matrix[y][x] = DEFAULT_POSITION;
                matrix[y][x + 1] = ROBOT_POSITION;
                return true;
            }
        }
        return false;
    }

    private JewelType checkNorth(int x, int y) {
        if (y - 1 > 0){
            if(matrix[y - 1][x] == GREEN_JEWEL_POSITION){
                removeJewel(x, y - 1);
                return JewelType.Green;
            }
            else if(matrix[y - 1][x] == BLUE_JEWEL_POSITION) {
                removeJewel(x, y - 1);
                return JewelType.Blue;
            }
            else if(matrix[y - 1][x] == RED_JEWEL_POSITION) {
                removeJewel(x, y - 1);
                return JewelType.Red;
            }
        }
        return null;

    }

    private JewelType checkSouth(int x, int y) {
        if (y + 1 < ySize){
            if(matrix[y + 1][x] == GREEN_JEWEL_POSITION) {
                removeJewel(x, y + 1);
                return JewelType.Green;
            }
            else if(matrix[y + 1][x] == BLUE_JEWEL_POSITION){
                removeJewel(x, y + 1);
                return JewelType.Blue;
            }
            else if(matrix[y + 1][x] == RED_JEWEL_POSITION) {
                removeJewel(x, y + 1);
                return JewelType.Red;
            }
        }
        return null;
    }

    private JewelType checkEast (int x, int y) {
        if (x + 1 < xSize){
            if(matrix[y][x + 1] == GREEN_JEWEL_POSITION) {
                removeJewel(x + 1, y);
                return JewelType.Green;
            }
            else if(matrix[y][x + 1] == BLUE_JEWEL_POSITION) {
                removeJewel(x + 1, y);
                return JewelType.Blue;
            }
            else if(matrix[y][x + 1] == RED_JEWEL_POSITION) {
                removeJewel(x + 1, y);
                return JewelType.Red;
            }
        }
        return null;
    }

    private JewelType checkWest (int x, int y) {
        if (x - 1 > 0){
            if(matrix[y][x - 1] == GREEN_JEWEL_POSITION) {
                removeJewel(x - 1, y);
                return JewelType.Green;
            }
            else if(matrix[y][x - 1] == BLUE_JEWEL_POSITION) {
                removeJewel(x - 1, y);
                return JewelType.Blue;
            }
            else if(matrix[y][x - 1] == RED_JEWEL_POSITION) {
                removeJewel(x - 1, y);
                return JewelType.Red;
            }
        }
        return null;
    }

    private JewelType checkNortheast (int x, int y) {
        if (y - 1 > 0 && x + 1 < xSize){
            if(matrix[y - 1][x + 1] == GREEN_JEWEL_POSITION) {
                removeJewel(x + 1, y - 1);
                return JewelType.Green;
            }
            else if(matrix[y - 1][x + 1] == BLUE_JEWEL_POSITION) {
                removeJewel(x + 1, y - 1);
                return JewelType.Blue;
            }
            else if(matrix[y - 1][x + 1] == RED_JEWEL_POSITION) {
                removeJewel(x + 1, y - 1);
                return JewelType.Red;
            }
        }
        return null;
    }

    private JewelType checkNorthwest (int x, int y) {
        if (y - 1 > 0 && x - 1 > 0){
            if(matrix[y - 1][x - 1] == GREEN_JEWEL_POSITION) {
                removeJewel(x - 1, y - 1);
                return JewelType.Green;
            }
            else if(matrix[y - 1][x - 1] == BLUE_JEWEL_POSITION) {
                removeJewel(x - 1, y - 1);
                return JewelType.Blue;
            }
            else if(matrix[y - 1][x - 1] == RED_JEWEL_POSITION) {
                removeJewel(x - 1, y - 1);
                return JewelType.Red;
            }
        }
        return null;
    }

    private JewelType checkSoutheast (int x, int y) {
        if (y + 1 < ySize && x + 1 < xSize){
            if(matrix[y + 1][x + 1] == GREEN_JEWEL_POSITION) {
                removeJewel(x + 1, y + 1);
                return JewelType.Green;
            }
            else if(matrix[y + 1][x + 1] == BLUE_JEWEL_POSITION) {
                removeJewel(x + 1, y + 1);
                return JewelType.Blue;
            }
            else if(matrix[y + 1][x + 1] == RED_JEWEL_POSITION) {
                removeJewel(x + 1, y + 1);
                return JewelType.Red;
            }
        }
        return null;
    }

    private JewelType checkSouthwest (int x, int y) {
        if (y + 1 < ySize && x - 1 > 0){
            if(matrix[y + 1][x - 1] == GREEN_JEWEL_POSITION) {
                removeJewel(x - 1, y + 1);
                return JewelType.Green;
            }
            else if(matrix[y + 1][x - 1] == BLUE_JEWEL_POSITION) {
                removeJewel(x - 1, y + 1);
                return JewelType.Blue;
            }
            else if(matrix[y + 1][x - 1] == RED_JEWEL_POSITION) {
                removeJewel(x - 1, y + 1);
                return JewelType.Red;
            }
        }
        return null;
    }

    JewelType[] checkForJewels(int x, int y) {
        JewelType[] jewelsFound = new JewelType[8];
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
}