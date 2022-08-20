package GameEngine;

import GameAssets.LabyrinthMap;

public abstract class GameEngine {
    private LabyrinthMap map;

    public GameEngine(LabyrinthMap map) {
        this.map = map;
    }

    protected LabyrinthMap getMap() {
        return map;
    }

    public abstract void gameLoop();
}