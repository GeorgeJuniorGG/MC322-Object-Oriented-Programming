package game;

import game.engine.GameEngine;
import game.engine.text.TextEngine;


public class Game {
    public static void main(String[] args) {
        GameEngine engine = new TextEngine();
        engine.gameLoop();
    }
}