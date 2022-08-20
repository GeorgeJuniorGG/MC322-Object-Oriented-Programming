package game.engine;

import game.map.Map;

public abstract class GameEngine {

    protected Map map;

    public GameEngine(){
        
    }

    protected Map getMap() {
        return map;
    }

    public abstract void chooseMap();
    public abstract void gameLoop();
}