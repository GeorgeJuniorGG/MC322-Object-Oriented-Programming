package game.map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import game.characters.monsters.Monster;
import game.characters.monsters.goblin.Goblin;
import game.characters.monsters.skeleton.Skeleton;
import game.characters.monsters.skeleton.WizardSkeleton;
import game.map.gates.Door;
import game.map.gates.Gateway;
import game.map.traps.Trap;
import game.map.treasure.Treasure;
import game.map.walls.Wall;

public class MapLoader {
    private static MapLoader instance;

    private final String STARTROOM = "StartRoom";
    private final String CORRIDOR = "Corridor";
    private final String BATTLEROOM = "BattleRoom";
    private final int CORRIDOR_HEIGHT = 3;

    private List<Gateway> gateways;
    private Scanner file;

    private boolean random;
    private Random rGenerator;
    private int numPlaces;
    private int numRooms;
    private int[] cAreaDims;
    private int countName;
    private String[][] plNames;
    private int numMonsters;
    private MapElement[][] area;
    private MapElement[][] startArea;

    private MapLoader(){
        gateways = new ArrayList<Gateway>();
        countName = 0;
        random = false;
        cAreaDims = new int[2];
    }

    public static MapLoader getInstace() {

        if(instance == null){
            instance = new MapLoader();
        }

        return instance;
    }

    private void insertWalls(MapElement[][] area, String areaName){
        int height = area.length-1;
        int width = area[0].length-1; 

        for(int i = 0; i <= width; i++) {
            area[0][i] = new Wall(0, i, areaName);
            area[height][i] = new Wall(height, i, areaName);
        }

        for(int i = 0; i <= height; i++) {
            area[i][0] = new Wall(i, 0, areaName);
            area[i][width] = new Wall(i, width, areaName);
        }
    }

    private void insertWhiteSpaces(MapElement[][] area, String areaName) {
        for(int i = 0; i < area.length; i++){
            for(int j = 0; j < area[0].length; j++) {
                area[i][j] = new Empty(i, j, areaName);
            }
        }
    }

    private Gateway insertGateway(MapElement[][] area, String areaName, int x, int y) { 
        Gateway gate = new Gateway(x, y, areaName);
        area[x][y] = gate;
        return gate;
    }

    private void insertElement(MapElement[][] area, MapElement element, int x, int y){
        area[x][y] = element;
    }

    private void generateMonsters(Monster[] monsters, String place, MapElement[][] area){
        for(int i = 0; i < monsters.length; i++){
            if(i % 2 == 0){
                monsters[i] = new Skeleton("Skeleton-"+i, i+2, 4, place);
                insertElement(area, monsters[i], i+2, 4);
            }
            else{
                monsters[i] = new Goblin("Goblin-"+i, i+2, 5, place);
                insertElement(area, monsters[i], i+2, 5);
            }
        }
    }

    private int getSInt(){
        String buffer = file.nextLine();
        int num = Integer.parseInt(buffer);
        return num;
    }

    private int[] getDInt(){
        String buffer = file.nextLine();
        String[] line = buffer.split(" ");
        int[] duo = {Integer.parseInt(line[0]), Integer.parseInt(line[1])};
        return duo;
    }

    private void initPlacesNames() {
        String[] aux = new String[this.numPlaces];
        plNames = new String[this.numPlaces][3];
        for(int i = 0; i < this.numPlaces; i++){
            aux[i] = "RandomPlace-"+(i+1);
        }
        for(int i = 0; i < this.numPlaces; i++) {
            plNames[i][0] = aux[i];
            if(i != this.numPlaces-1){
                plNames[i][2] = aux[i+1];
            }
            if(i != 0){
                plNames[i][1] = aux[i-1];
            } 
        }

    }

    private int getNumPlaces(){
        if(!random){
            return getSInt();
        }
        this.numPlaces = rGenerator.nextInt(7) + 4;
        initPlacesNames();
        return this.numPlaces;
    }

    private int getNumRooms(){
        if(!random){
            return getSInt();
        }
        // Gera quartos de forma que sobrem pelo menos dois places para corredores
        int aux = rGenerator.nextInt(this.numPlaces-3) + 2;
        this.numRooms = aux;
        return aux;
    }

    private int getNumCorridors(){
        if(!random){
            return getSInt();
        }
        return (numPlaces-numRooms);
    }

    private int[] getRoomDimentions(){
        if(!random){
            return getDInt();
        }
        // Areas de dimensões variando de 6 a 10 de largura e altura
        cAreaDims[0] = rGenerator.nextInt(5) + 6;
        cAreaDims[1] = rGenerator.nextInt(5) + 6; 

        return cAreaDims;
    }

    private int getCorridorWidht(){
        if(!random){
            return getSInt();
        }
        // Corredores de largura variando de 5 a 20 
        cAreaDims[0] = CORRIDOR_HEIGHT;
        cAreaDims[1] = rGenerator.nextInt(16) + 5;
        return cAreaDims[1];

    }
    // numero máximo de portas de uma sala
    private int getGateNum() {
        String[] cPlace = plNames[countName-1];
        int count = 0;
        for(int i = 1; i < cPlace.length; i++){
            if(cPlace[i] != null){
                count++;
            }
        }
        return count;
    }

    private int getNumGateways() {
        if(!random){
            return getSInt();
        }

        int aux = rGenerator.nextInt(getGateNum()+1); 
        return aux;
    }

    private int getNumDoors() {
        if(!random){
            return getSInt();
        }
        int aux = getGateNum();
        return aux;
    }

    private String getString(){
        return file.nextLine();
    }

    private int getLen(){
        if(!random){
            return getSInt();
        }
        // Pode gerar até 10% do tamanho da Area
        int aux = (int) ((cAreaDims[0]*cAreaDims[1])*(0.1));
        int len = rGenerator.nextInt(aux+2);
        return len;
    }

    private int getTotalMonsters(){
        if(!random){
            return getSInt();
        }
        int len = getLen() + 3;
        this.numMonsters = len;
        return len;
    }

    private int getNumMonsters(){
        if(!random){
            return getSInt();
        }
        return (this.numMonsters/3);
    }

    private int[] getBorderPos() {
        if(!random){
            return getDInt();
        }
        // Gerar uma posição aleatória na borda do mapa
        int[] pos = new int[2];
        int aux = rGenerator.nextInt(2);
        if(aux == 0){
            aux = rGenerator.nextInt(2);
            pos[0] = (cAreaDims[0]-1)*aux;
            pos[1] = rGenerator.nextInt(cAreaDims[1]-2)+1;
        }else{
            aux = rGenerator.nextInt(2);
            pos[1] = (cAreaDims[1]-1)*aux;
            pos[0] = rGenerator.nextInt(cAreaDims[0]-2)+1;            
        }

        return pos;  
    }

    private int[] getPos() {
        if(!random){
            return getDInt();
        }
        int pos[] = new int[2];
        if(cAreaDims[0] > 3){
            pos[0] = rGenerator.nextInt(cAreaDims[0]-3)+2;
        }else{
            pos[0] = 1;
        }
        
        pos[1] = rGenerator.nextInt(cAreaDims[1]-3)+2;        

        return pos;
    }

    private int[] getMonsterPos(){
        if(!random){
            return getDInt();
        }
        int pos[] = getPos();
        if(!this.area[pos[0]][pos[1]].isEmpty()){
            pos = getFreePos();
        }

        return pos;
    }

    private int[] getFreePos(){
        int pos[] = {1,1};
        for(int i = 1; i < this.area.length; i++){
            for(int j = 1; j < this.area[0].length -1; j++){
                if(this.area[i][j].isEmpty()){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    private int[] getHeroPos(){
        if(!random){
            return getDInt();
        }
        int pos[] = {1,1};
        for(int i = 1; i < this.startArea.length; i++){
            for(int j = 0; j < this.startArea[0].length; j++){
                if(this.startArea[i][j].isEmpty()){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    private int sumElements(){
        if(!random){
            return getSInt();
        }
        int sum = 0;
        for(int i = 0; i < this.area.length; i++){
            for(int j = 0; j < this.area[0].length; j++){
                if(!this.area[i][j].isEmpty()){
                   sum++;
                }
            }
        }    
        return sum;    
    }

    private int getVertical(){
        if(!random){
            return getSInt();
        }
        int aux = rGenerator.nextInt(2);
        return aux;
    }

    private String getHeroPlace(){
        if(!random){
            return getString();
        }
        return plNames[0][0];
    }

    private String getPlaceName() {
        if(!random){
            return getString();
        }

        String placeName = plNames[countName++][0];

        return placeName;
    }

    private String getLinkName(String local){
        if(!random){
            return getString();
        }

        for(int i = 0; i < numPlaces; i++){
            if(local.equals(plNames[i][0])){
                return selectLink(plNames[i]);
            }
        }
        return null;
    }

    private String selectLink(String[] place){
        String linkName;
        for(int i = 1; i < place.length; i++){
            if(place[i] != null) {
                linkName = place[i];
                place[i] = null;
                return linkName;
            }
        }
        return null;
    }

    private void generateGateways(MapElement[][] area, int len, String place){
        Gateway[] gateways = new Gateway[len];
        int[] pos;
        String linkName;
        for(int i = 0; i < len; i++){
            pos = getBorderPos();
            linkName = getLinkName(place);
            gateways[i] = new Gateway(pos[0], pos[1], place, linkName);
            area[pos[0]][pos[1]] = gateways[i];
            this.gateways.add(gateways[i]);
        }
    }

    private void generateDoors(MapElement[][] area, int len, String place){
        Door[] doors = new Door[len];
        int[] pos;
        for(int i = 0; i < len; i++){
            pos = getBorderPos();
            doors[i] = new Door(pos[0], pos[1], place, getLinkName(place));
            area[pos[0]][pos[1]] = doors[i];
            this.gateways.add(doors[i]);
        }
        
    }

    private Treasure[] generateTreasures(MapElement[][] area, int len, String place){
        Treasure[] treasures = new Treasure[len];
        int[] pos;
        for(int i = 0; i < len; i++){
            pos = getPos();
            treasures[i] = new Treasure(pos[0], pos[1], place);
            area[pos[0]][pos[1]] = treasures[i];
        }
        return treasures;     
    }

    private void generateTraps(MapElement[][] area, int len, String place){
        Trap[] traps = new Trap[len];
        int[] pos;
        for(int i = 0; i < len; i++){
            pos = getPos();
            traps[i] = new Trap(pos[0], pos[1], place, 1);
            area[pos[0]][pos[1]] = traps[i];
        }       
    }

    private Monster[] generateMonsters(MapElement[][] area, int len, String place){
        Monster[] monsters = new Monster[len];
        if(len > 0){
            int count = 0;
            int[] pos;
            int numGoblins = getNumMonsters();
            for(int i = 0; i < numGoblins; i++){
                pos = getMonsterPos();
                monsters[count++] = new Goblin("Goblin-"+i, pos[0], pos[1], place);
                area[pos[0]][pos[1]] = monsters[count-1];
            }
            int numSkeletons =  getNumMonsters();
            for(int i = 0; i < numSkeletons; i++){
                pos = getMonsterPos();
                monsters[count++] = new Skeleton("Skeleton-"+i, pos[0], pos[1], place);
                area[pos[0]][pos[1]] = monsters[count-1];
            }
            int numWizarSkeletons =  getNumMonsters();
            for(int i = 0; i < numWizarSkeletons; i++){
                pos = getMonsterPos();
                monsters[count++] = new WizardSkeleton("WizardSkeleton-"+i, pos[0], pos[1], place);
                area[pos[0]][pos[1]] = monsters[count-1];
            }
        }    
        
        return monsters;
    }

    private MapArea initRoom(){
        
        int [] duo; 

        // Iniciando Matriz
        duo = getRoomDimentions();
        this.area = new MapElement[duo[0]][duo[1]];
        if(this.startArea == null){
            this.startArea = this.area;
        }
        String areaName = getPlaceName();

        // Iniciando a Matriz com espaços em branco em paredes
        insertWhiteSpaces(area, areaName);
        insertWalls(area, areaName);

        // Gateways
        generateGateways(area, getNumGateways(), areaName);

        // Doors
        generateDoors(area, getNumDoors(), areaName);

        // Treasures
        Treasure[] treasures = generateTreasures(area, getLen(), areaName);

        // Traps
        generateTraps(area, getLen(), areaName);

        // Monsters
        Monster[] monsters = generateMonsters(area, getTotalMonsters(), areaName);

        MapArea place = new Room(duo[1], duo[0], areaName, sumElements(), area, treasures, monsters);


        return place;
    }

    private MapArea initCorridor(){
 
        // Iniciando Matriz
        int width = getCorridorWidht();
        this.area = new MapElement[CORRIDOR_HEIGHT][width];
        int vertical = getVertical();
        String areaName = getPlaceName();

        // Iniciando a Matriz com espaços em branco em paredes
        insertWhiteSpaces(area, areaName);
        insertWalls(area, areaName);

        // Gateways
        generateGateways(area, getNumGateways(), areaName);

        // Doors
        generateDoors(area, getNumDoors(), areaName);

        // Treasures
       Treasure[] treasures = generateTreasures(area, getLen(), areaName);

        // Traps
        generateTraps(area, getLen(), areaName);

        // Monsters
        Monster[] monsters = generateMonsters(area, getTotalMonsters(), areaName);

        MapArea corridor = new Corridor(width, areaName, sumElements(), area, treasures, monsters, vertical);
        return corridor;        
    }

    private void connectGateways(MapArea[] places){
        MapArea local;
        Gateway link;
        for(Gateway i : this.gateways){
            local = getLocal(places, i.getLocalName());
            link = getLink(i.getLocalName(), i.getLinkName());
            if(local != null && link != null){
                i.setLinks(local, link);
            }
        }
    }

    private MapArea getLocal(MapArea[] places, String name){
        for(MapArea i: places){
            if(name.equals(i.getName())){
                return i;
            }
        }

        return null;
    }

    private Gateway getLink(String localName, String linkName){
        for(Gateway link : this.gateways){
            if(linkName.equals(link.getLocalName()) && localName.equals(link.getLinkName())){
                return link;
            }
        }

        return null;
    }

    private Map createMap() {
        
        int qAreas = getNumPlaces();
        MapArea[] places = new MapArea[qAreas];
        int count = 0;
        int qRooms = getNumRooms();
        
        for(int i = 0; i < qRooms; i++){
            places[count++] = initRoom();
        }

        int qCorridors = getNumCorridors();

        for(int i = 0; i < qCorridors; i++){
            places[count++] = initCorridor();
        }

        int[] posHero = getHeroPos();
        String placeHero = getHeroPlace();
        Coordinate startHeroPosition = new Coordinate(posHero[0], posHero[1], placeHero);

        connectGateways(places);
        sortPlaces(places);

        Map map = new Map(places, startHeroPosition);

        return map;
    }

    private void sortPlaces(MapArea[] places){
        int n = places.length;
        String name1;
        String name2;
        MapArea aux;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                name1 = places[j].getName();
                name2 = places[j+1].getName();
                if(name1.compareTo(name2) > 0){
                    aux = places[j];
                    places[j] = places[j+1];
                    places[j+1] = aux;
                }
            } 
        }
    }

    public Map createRandomMap() {

        this.random = true;
        this.rGenerator = new Random();

        return createMap();
    }


    public Map loadMapFromFile(String path){
        try {
            // Concatenar o caminho absoluto
            String lPath = "HeroQuest/files/"+path;
            FileReader rFile = new FileReader(lPath);
            file = new Scanner(rFile);
            Map map = createMap();
            file.close();
            return map;
            
        } catch(FileNotFoundException e){
            System.out.println("Não foi possível Encontrar o Arquivo.\nCarregando o Mapa Padrão.");
            return createDefautMap();
        } catch(Exception e){
            System.out.println("Ocorreu algum problema durante a leitura do arquivo.\nCarregando o Mapa Padrão.");
            return createDefautMap();
        }
        
    }

    public Map createDefautMap(){

        MapArea[] places = new MapArea[3];

        // StartRoom
        MapElement[][] startArea = new MapElement[7][7];
        insertWhiteSpaces(startArea, STARTROOM);
        insertWalls(startArea, STARTROOM);
        Gateway gate1 = insertGateway(startArea, STARTROOM, 4, 6);
        Treasure t1 = new Treasure(1, 5, STARTROOM);
        Treasure t2 = new Treasure(2, 1, STARTROOM);
        Treasure[] treasures = {t1,t2};
        insertElement(startArea, t1, 1, 5);
        insertElement(startArea, t2, 2, 1);
        Room startRoom = new Room(7, 7, STARTROOM, 27, startArea, treasures);
        Coordinate heroStartPosition = new Coordinate(3, 3, STARTROOM);

        // Corridor
        MapElement[][] corridorArea = new MapElement[3][9];
        insertWhiteSpaces(corridorArea, CORRIDOR);
        insertWalls(corridorArea, CORRIDOR); 
        Gateway gate2 = insertGateway(corridorArea, CORRIDOR, 1, 0);
        Door door1 = new Door(1, 8, CORRIDOR);
        insertElement(corridorArea, door1, 1, 8);
        Corridor corridor = new Corridor(9, CORRIDOR, 20, corridorArea, 0);

        //BattleRoom
        MapElement[][] battleArea = new MapElement[9][9];
        insertWhiteSpaces(battleArea, BATTLEROOM);
        insertWalls(battleArea, BATTLEROOM);
        Door door2 = new Door(4, 0, BATTLEROOM);
        insertElement(battleArea, door2, 4, 0);
        Monster[] monsters = new Monster[4];
        generateMonsters(monsters, BATTLEROOM, battleArea);
        insertElement(battleArea, new Trap(2, 2, BATTLEROOM, 1), 2, 2);
        insertElement(battleArea, new Trap(6, 2, BATTLEROOM, 1), 6, 2);
        insertElement(battleArea, new Trap(6, 5, BATTLEROOM, 1), 6, 5);
        insertElement(battleArea, new Trap(1, 7, BATTLEROOM, 2), 1, 7);
        Treasure[] treasures2 = {new Treasure(1, 3, BATTLEROOM), new Treasure(4, 7, BATTLEROOM)};
        insertElement(battleArea, treasures2[0], 1, 3);
        insertElement(battleArea, treasures2[1], 4, 7);
        Room battleRoom = new Room(9, 9, BATTLEROOM, 42, battleArea, treasures2, monsters);

        //Links
        gate1.setLinks(startRoom, gate2);
        gate2.setLinks(corridor, gate1);
        door1.setLinks(corridor, door2);
        door2.setLinks(battleRoom, door1);

        places[0] = battleRoom;
        places[1] = corridor;
        places[2] = startRoom;

        return new Map(places, heroStartPosition);
    }
}