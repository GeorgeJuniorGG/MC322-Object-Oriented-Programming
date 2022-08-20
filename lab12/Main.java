import GameAssets.LabyrinthMap;
import GameAssets.LabyrinthMapLoader;
import GameEngine.GameEngine;
import GameEngine.TextEngine;

public class Main {

    private static void runGame(GameEngine gameEngine) {
        gameEngine.gameLoop();
    }
    public static void main(String[] args) {
        LabyrinthMap map = LabyrinthMapLoader.getInstance().createDefaultMap();
        TextEngine engine = new TextEngine(map);
        runGame(engine);
    }
}