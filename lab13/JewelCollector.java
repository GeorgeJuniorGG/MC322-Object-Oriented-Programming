package lab13;

import java.util.Scanner;

import lab13.Jewels.BlueJewel;
import lab13.Jewels.GreenJewel;
import lab13.Jewels.RedJewel;
import lab13.Obstacles.Water;
import lab13.Obstacles.Tree;

public class JewelCollector {
    public static void main(String[] args) {
        Map map = new Map(10, 10);
        RedJewel jewel1 = new RedJewel(9, 1);
        RedJewel jewel2 = new RedJewel(8, 8);
        GreenJewel jewel3 = new GreenJewel(1, 9);
        GreenJewel jewel4 = new GreenJewel(6, 7);
        BlueJewel jewel5 = new BlueJewel(4, 3);
        BlueJewel jewel6 = new BlueJewel(1, 2);
        Water obstacle1 = new Water(0, 5);
        Water obstacle2 = new Water(1, 5);
        Water obstacle3 = new Water(2, 5);
        Water obstacle4 = new Water(3, 5);
        Water obstacle5 = new Water(4, 5);
        Water obstacle6 = new Water(5, 5);
        Water obstacle7 = new Water(6, 5);
        Tree obstacle8 = new Tree(9, 5);
        Tree obstacle9 = new Tree(9, 3);
        Tree obstacle10 = new Tree(3, 8);
        Tree obstacle11 = new Tree(5, 2);
        Tree obstacle12 = new Tree(4, 1);
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
            else if(robot.getEnergyPoints() == 0) {
                System.out.println("You lost!");
                running = false;
            }
            else if(robot.getJewelsFound() == 6) {
                System.out.println("You won!");
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
            else if (command.compareTo("u") == 0) {
                robot.checkSorroundings(map);
            }
        }
        keyboard.close();
    }
}