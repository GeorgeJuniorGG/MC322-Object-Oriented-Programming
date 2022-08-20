package GameEngine;

import GameAssets.Checkpoint;
import GameAssets.LabyrinthMap;
import GameAssets.LabyrinthObject;
import GameAssets.Player;
import GameAssets.Wall;

class TextRenderManager implements LabyrinthObjectVisitor {
    private char[][] charMap;

    TextRenderManager(int width, int heigth) {
        this.charMap = new char[width][heigth];
    }

    private void clearMap() {
        for(int i = 0; i < charMap.length; i++) {
            for(int j = 0; j < charMap[0].length; j++) {
                charMap[i][j] = '-';
            }
        }        
    }

    private void printMap() {
        for(int i = 0; i < charMap.length; i++) {
            for(int j = 0; j < charMap[0].length; j++) {
                System.out.print(charMap[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private void setSymbol(LabyrinthObject object, char character) {
        charMap[object.getX()][object.getY()] = character;  
    }

    @Override
    public void visit(Player player) {
        setSymbol(player, 'P');
    }
    @Override
    public void visit(Wall wall) {
        setSymbol(wall, 'X');
    }
    @Override
    public void visit(Checkpoint checkpoint) {
        if(checkpoint.isConquered()) {
            setSymbol(checkpoint, ' ');
        }
        else {
            setSymbol(checkpoint, 'C');
        }
    }

    public void render(LabyrinthMap map) {
        clearMap();
        map.accept(this);
        printMap();
    }
}