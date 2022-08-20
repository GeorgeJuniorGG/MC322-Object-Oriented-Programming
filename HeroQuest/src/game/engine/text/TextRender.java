package game.engine.text;

import game.interfaces.MapElementVisitor;
import game.map.Corridor;
import game.map.Map;
import game.map.MapESymbol;
import game.map.MapElement;
import game.map.Room;

class TextRender implements MapElementVisitor {

    private char[][] mapView;
    private boolean vertical = false;

    @Override
    public void visit(MapElement element, int x, int y) {
        MapESymbol symbol = element.getSymbol();

        if(this.vertical){
            mapView[y][x] = symbol.getTSymbol();
        }
        else{
            mapView[x][y] = symbol.getTSymbol();
        }
    }
       

    @Override
    public void visitRoom(Room room) {
        mapView = new char[room.getHeight()][room.getWidth()];
        this.vertical = false;
    }

    @Override
    public void visitCorridor(Corridor corridor) {
        mapView = new char[corridor.getHeight()][corridor.getWidth()];
        this.vertical = corridor.isVertical();
        cleanView();
    }

    private void printMap(){
        char check = 0;
        int count;
        System.out.println();
        for(int i = 0; i < mapView.length; i++){
            count = 0;
            for(int j = 0; j < mapView[0].length; j++){
                check = mapView[i][j];
                if(check != 0){
                    System.out.format("%c ", check);
                    count++;
                }
            }
            if(count > 0){
                System.out.println();
            }
            
        }
        System.out.println();
    }

    private void cleanView(){
        for(int i = 0; i < mapView.length; i++){
            for(int j = 0; j < mapView[0].length; j++){
                mapView[i][j] = 0;
            }
        }
    }

    void render(Map map){
        map.accept(this);
        printMap();
    }

    void showChooseMapMenu(){
        System.out.println("Escolha qual tipo de mapa você deseja jogar, digitando a letra correspondente: ");
        System.out.println("[f] - para ler o mapa de um arquivo");
        System.out.println("[r] - para gerar um mapa aleatório");
        System.out.println("Digite outra tecla para jogar no mapa padrão");
    }

    void show(String message){
        System.out.println("\n" + message);
    }

    void alert(String message){
        System.out.println(message);
    }

    void alertW(String message){
        System.out.print(message);
    }

    void showOption(int i, String option){
        System.out.format("[%d] - %s\n", i, option);
    }

    void showOption(char c, String option){
        System.out.format("[%c] - %s\n", c, option);
    }
}