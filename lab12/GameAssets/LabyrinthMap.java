package GameAssets;

import GameEngine.LabyrinthObjectVisitor;

public class LabyrinthMap {
    private Player player;
    private Checkpoint[] checkpoints;
    private Wall[] walls;
    private int width;
    private int heigth;

    protected LabyrinthMap(int width, int heigth, Player player, Checkpoint[] checkpoints, Wall[] walls) {
        this.player = player;
        this.checkpoints = checkpoints;
        this.walls = walls;
        this.width = width;
        this.heigth = heigth;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWidth() {
        return width;
    }

    public void updateMap(Direction direction) {
        if(direction != null) {
            player.move(direction, walls);
            Coordinate newCoordinate = new Coordinate(player.getX(), player.getY());
            for(int i = 0; i < checkpoints.length; i++) {
                if(checkpoints[i].isSameCoordinates(newCoordinate.getX(), newCoordinate.getY())) {
                    checkpoints[i].conquer();
                    break;
                }
            }
        }
    }

    public boolean isDone() {
        for(int i = 0; i < checkpoints.length; i++) {
            if(!checkpoints[i].isConquered()) {
                return false;
            }
        }
        return true;
    }

    public void accept(LabyrinthObjectVisitor visitor) {
        for(Wall wall : walls) {
            wall.accept(visitor);
        }
        for(Checkpoint checkpoint : checkpoints) {
            checkpoint.accept(visitor);
        }
        player.accept(visitor);
    }
}