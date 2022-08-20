package lab04;

import java.util.Scanner;

public class JewelCollector {
    public static void main(String[] args) {
        Map map = new Map(10, 10);
        Jewel jewel1 = new Jewel(JewelType.Red, 9, 1);
        Jewel jewel2 = new Jewel(JewelType.Red, 8, 8);
        Jewel jewel3 = new Jewel(JewelType.Green, 1, 9);
        Jewel jewel4 = new Jewel(JewelType.Green, 6, 7);
        Jewel jewel5 = new Jewel(JewelType.Blue, 4, 3);
        Jewel jewel6 = new Jewel(JewelType.Blue, 1, 2);
        Obstacle obstacle1 = new Obstacle(0, 5, ObstacleType.Water);
        Obstacle obstacle2 = new Obstacle(1, 5, ObstacleType.Water);
        Obstacle obstacle3 = new Obstacle(2, 5, ObstacleType.Water);
        Obstacle obstacle4 = new Obstacle(3, 5, ObstacleType.Water);
        Obstacle obstacle5 = new Obstacle(4, 5, ObstacleType.Water);
        Obstacle obstacle6 = new Obstacle(5, 5, ObstacleType.Water);
        Obstacle obstacle7 = new Obstacle(6, 5, ObstacleType.Water);
        Obstacle obstacle8 = new Obstacle(9, 5, ObstacleType.Tree);
        Obstacle obstacle9 = new Obstacle(9, 3, ObstacleType.Tree);
        Obstacle obstacle10 = new Obstacle(3, 8, ObstacleType.Tree);
        Obstacle obstacle11 = new Obstacle(5, 2, ObstacleType.Tree);
        Obstacle obstacle12 = new Obstacle(4, 1, ObstacleType.Tree);
        Robot robot = new Robot(0, 0, 6);
        map.addJewel(jewel1);
        map.addJewel(jewel2);
        map.addJewel(jewel3);
        map.addJewel(jewel4);
        map.addJewel(jewel5);
        map.addJewel(jewel6);
        map.addObstacle(obstacle1);
        map.addObstacle(obstacle2);
        map.addObstacle(obstacle3);
        map.addObstacle(obstacle4);
        map.addObstacle(obstacle5);
        map.addObstacle(obstacle6);
        map.addObstacle(obstacle7);
        map.addObstacle(obstacle8);
        map.addObstacle(obstacle9);
        map.addObstacle(obstacle10);
        map.addObstacle(obstacle11);
        map.addObstacle(obstacle12);
        map.addRobot(robot);
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;
        while (running) {
            map.printMap();
            robot.getJewels();
            System.out.print("Enter the command : ");
            String command = keyboard.nextLine();
            if (command.compareTo("quit") == 0) {
                running = false;
            } 
            else if (command.compareTo("w") == 0) {
                robot.moveForwards(map);
            } 
            else if (command.compareTo("a") == 0) {
                robot.moveToTheLeft(map);
            } 
            else if (command.compareTo("s") == 0) {
                robot.moveBackwards(map);
            } 
            else if (command.compareTo("d") == 0) {
                robot.moveToTheRight(map);
            } 
            else if (command.compareTo("g") == 0) {
                robot.collectJewels(map);
            }
        }
        keyboard.close();
    }
}