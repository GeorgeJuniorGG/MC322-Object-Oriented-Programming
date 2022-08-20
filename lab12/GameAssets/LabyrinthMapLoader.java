package GameAssets;

public class LabyrinthMapLoader {
    private static LabyrinthMapLoader instance;


    private LabyrinthMapLoader() {
    }

    public static LabyrinthMapLoader getInstance() {
        if(instance == null) {
            instance = new LabyrinthMapLoader();
        }
        return instance;
    }

    public LabyrinthMap loadMapFromFile(String path) {
        return null;
    }

    public LabyrinthMap createDefaultMap() {
        Player player = new Player(1, 1);
        int width = 10;
        int heigth = 10;
        Wall[] walls = new Wall[36];
        for (int i = 0; i < 10; i++) {
            walls[i] = new Wall(i, 0);
        }
        for(int i = 10; i < 20; i++) {
            walls[i] = new Wall(i - 10, 9);
        }
        for(int i = 20; i < 28; i++) {
            walls[i] = new Wall(0, i - 19);
        }
        for(int i = 28; i < 36; i++) {
            walls[i] = new Wall(9, i - 27);
        }
        Checkpoint[] checkpoints = new Checkpoint[3];
        checkpoints[0] = new Checkpoint(5, 5);
        checkpoints[1] = new Checkpoint(8, 1);
        checkpoints[2] = new Checkpoint(1, 8);
        LabyrinthMap map = new LabyrinthMap(width, heigth, player, checkpoints, walls);
        return map;
    }
}