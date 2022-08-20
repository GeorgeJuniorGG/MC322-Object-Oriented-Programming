package game.map;

import java.util.ArrayList;
import java.util.List;

import game.characters.Character;
import game.characters.heroes.Hero;
import game.characters.monsters.Monster;
import game.interfaces.IOMediator;
import game.interfaces.MapElementVisitor;
import game.map.treasure.Treasure;

public abstract class MapArea {

    private int width;
    private int height;
    private int geSum;
    private String name;
    private boolean discovery;

    private Treasure[] treasures;
    private List <Monster> monsters;

    protected MapElement[][] area;
    private List<MapElement> mapBuffer;

    MapArea(int width, int height, String name, int inGeSum, MapElement[][] area, Treasure[] treasures, Monster[] monsters) {

        this.width = width;
        this.height = height;
        this.name = name;
        this.geSum = inGeSum;
        this.area = area;
        this.discovery = false;
        this.treasures = treasures;
        
        initMonsterArray(monsters);

        mapBuffer = new ArrayList<MapElement>();
    }

    private void initMonsterArray(Monster[] mArray) {
        monsters = new ArrayList<Monster>();
        for(Monster i : mArray){
            if(i != null){
                monsters.add(i);
            }
        }
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    String getName() {
        return name;
    }

    void discover(){
        discovery = true;
    }

    boolean wasDiscovered(){
        return discovery;
    }

    private boolean isFull(){
        return (geSum == (width*height)) ? true : false;
    }

    void setMediator(IOMediator mediator){
        for(Monster i: monsters){
            if(i != null){
                i.setMediator(mediator);
            }
        }
    }

    void insertMonster(Monster monster) {
        if(!isFull()){
            monsters.add(monster);
            addElement(monster);
        }
    }

    int getMonstersSum(){
        return monsters.size();
    }

    void initHero(Character player){
        addElement(player);
    }

    Treasure getTreasure(Coordinate center, Direction vision) {
        Treasure chest = null;

        Coordinate[] positions = new Coordinate[5];
        positions[0] = new Coordinate(center.getX()+vision.getxOffset(), center.getY()+vision.getyOffset(), this.name);

        int arrow = 0; // indica o sentido do incremento/decremento de posição em relação ao centro

        for(int i = 1; i < positions.length; i++) {
            if(i % 2 == 0) {
                arrow = (int) Math.pow(-1, i/2 + 1); 
                positions[i] = new Coordinate(center.getX()+arrow, center.getY(), this.name);
            }
            else{
                arrow = (int) Math.pow(-1, (i-1)/2 + 1);
                positions[i] = new Coordinate(center.getX(), center.getY()+arrow, this.name);
            }
        }

        for(int i = 0; i < treasures.length; i++) {
            chest = treasures[i];
            if(chest != null && comparePositions(chest, positions)) {
                treasures[i] = null;
                removeElement(chest);
                return chest;
            }
        }
        
        return null;
    }

    private boolean comparePositions(MapElement e, Coordinate[] vet){
        for(Coordinate i : vet){
            if(i != null && i.isSame(e.getCoordinate())){
                return true;
            }
        }
        return false;
    }

    private void addElement(MapElement element, int x, int y) {
        area[x][y] = element;
        geSum++;
    }

    private void addElement(MapElement element) {
        Coordinate pos = element.getCoordinate();
        addElement(element, pos.getX(), pos.getY());
    }

    private void removeElement(Coordinate position) {
        int x = position.getX();
        int y = position.getY();
        geSum--;

        MapElement overlaid = queryBuffer(position);
        if(overlaid != null){
            area[x][y] = overlaid;
            mapBuffer.remove(overlaid);
            return;
        }

        area[x][y] = new Empty(x, y, name);
        
    }

    private void removeElement(MapElement e){
        Coordinate pos = e.getCoordinate();
        removeElement(pos);
    }

    private MapElement queryBuffer(Coordinate position) {
        for(MapElement i : mapBuffer){
            if(i.compareCoordinate(position)){
                return i;
            }
        }
        return null;
    }

    private MapElement queryBuffer(Character character) {

        for(MapElement i : mapBuffer) {
            if(i.compareCoordinate(character)) {
                return i;
            }
        }

        return null;
    }

    private MapElement getElement(Coordinate pos) {
        return area[pos.getX()][pos.getY()];
    }

    private boolean checkPosition(int x, int y) {
        return (x >= 0 && x < height) && (y >= 0 && y < width);
    }

    public boolean updatePosition(MapElement oldElement, Character newElement) {
        if(isFull()){
            return false;
        }

        Coordinate oldPos = oldElement.getCoordinate();

        if(name.equals(oldPos.getPlace())){
            MapElement inside = area[oldPos.getX()][oldPos.getY()];
            if(inside == oldElement){
                addElement(newElement, oldPos.getX(), oldPos.getY());
                mapBuffer.add(oldElement);
                return true;
            }
        }
        
        return false;
    }

    void allowMove(Character character, Direction direction, IOMediator mediator) {

        Coordinate cPos = character.getCoordinate(); // Posição atual do personagem

        if(getElement(cPos) != character) {
            return;
        }

        Coordinate cNPos = character.nextPosition(direction); // Nova posição do personagem
        MapElement overlaid;

        if(!checkPosition(cNPos.getX(), cNPos.getY())) {
            overlaid = queryBuffer(character);
            if(overlaid == null) {
                // Tratar exceção de Posição inválida
                return;
            }
            
        }
        else {
            overlaid = getElement(cNPos);
        }

        if(overlaid.overpass(character, mediator)){

            removeElement(cPos);
            cNPos = character.getCoordinate();
            // Como existem mudanças de cenário, é necessário verificar se o character mudou de cenário
            if(name.equals(cNPos.getPlace())){
                addElement(character);
                if(!overlaid.isEmpty()){
                    mapBuffer.add(overlaid);
                }
            }

        }
    }

    void moveMonsters(Map map, Hero player) {
        List<Monster> moved = new ArrayList<Monster>();
        for(Monster monster : monsters) {
            monster.movement(map, player);
            Coordinate mPos = monster.getCoordinate();
            if(!name.equals(mPos.getPlace())) { // Caso o monstro mudar de localidade
                moved.add(monster);
                //monsters.remove(monster);
                map.insertMonster(monster, mPos.getPlace());
            }
        }
        for(Monster monster: moved){
            monsters.remove(monster);
        }
    }

    Coordinate getFreePosition() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(area[i][j].isEmpty()){
                    return area[i][j].getCoordinate().getClone();
                }
            }
        }
        return null;
    }

    public void moveToRandomPosition(Character character) {
        Coordinate rPos = getFreePosition();
        Coordinate cPos = character.getCoordinate();
        removeElement(cPos);
        character.move(rPos);
        addElement(character);
    }

    Monster[] getNearMonsters(Coordinate center, Direction reference, int range, int amount, boolean diagonal) {
        
        int xc = center.getX();
        int yc = center.getY();
        int count = 0;

        Monster[] nearby = new Monster[amount];
        Monster monster = null;

        for(int i = 1; i <= range; i++){
            monster = getMonster(xc+(reference.getxOffset())*i, yc+(reference.getyOffset())*i);
            if(monster != null){
                nearby[count++] = monster;
                break;
            }
        }

        for(int i = xc-range; i <= xc+range; i++) {
            for(int j = yc-range; j <= yc+range; j++) {

                if(count == amount){
                    return nearby;
                }

                if(!checkConditions(xc, yc, i, j, diagonal)){
                    continue;
                }

                monster = getMonster(i, j);
                if(monster != null){
                    nearby[count++] = monster;
                }
            }
        }

        return nearby;
    }

    private Monster getMonster(int x, int y) {
        for(Monster i : monsters){
            if(i != null && i.compareCoordinate(new Coordinate(x, y, this.name))){
                return i;
            }
        }
        return null;
    }

    private boolean isDiagonal(int xc, int yc,int x, int y) {
        return (x != xc && y != yc);
    }

    private boolean checkConditions(int xc, int yc,int x, int y, boolean diagonal) {
        boolean valid = true;
        if(!checkPosition(x, y) ||(x == xc && y == yc) ){
            valid = false;
        }

        if((!diagonal && isDiagonal(xc, yc, x, y))){
            valid = false;
        }

        return valid;
    }

    void removeDeadMonsters(Monster[] deadMonsters) {
        for(Monster i : deadMonsters) {
            if(i != null && i.isDead() && monsters.contains(i)){
                monsters.remove(i);
                removeElement(i);
            }
        }
    }

    void accept(MapElementVisitor visitor, Character player) {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                area[i][j].accept(visitor);
            }
        }
    }

    boolean isValidMovement(Coordinate coordinate) {
        MapElement mapElement = getElement(coordinate);
        return mapElement.isEmpty();
    }
}