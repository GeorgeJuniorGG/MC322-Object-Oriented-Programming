package GameEngine;

import java.util.Scanner;

import GameAssets.Direction;
import GameAssets.LabyrinthMap;

public class TextEngine extends GameEngine {
    private TextRenderManager renderManager;

    public TextEngine(LabyrinthMap map) {
        super(map);
        this.renderManager = new TextRenderManager(map.getWidth(), map.getHeigth());
    }
    
    public Direction readCommandFromKeyboard(Scanner scanner) {
        while(true) {
            String move = scanner.next();
            if(move.equals("w")) {
                return Direction.UP;
            }
            else if(move.equals("a")) {
                return Direction.LEFT;
            }
            else if(move.equals("s")) {
                return Direction.DOWN;
            }
            else if(move.equals("d")) {
                return Direction.RIGHT;
            }
        }
    }

    @Override
    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        LabyrinthMap map = getMap();
        Direction newDirection;
        while(!map.isDone()) {
            renderManager.render(map);
            newDirection = readCommandFromKeyboard(scanner);
            map.updateMap(newDirection);
        }
        System.out.println("Labirinto finalizado!!");
        scanner.close();
    }
}