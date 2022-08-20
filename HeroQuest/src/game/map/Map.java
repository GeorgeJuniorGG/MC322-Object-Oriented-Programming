package game.map;

import java.util.ArrayList;
import java.util.List;

import game.characters.heroes.Hero;
import game.characters.heroes.barbarian.Barbarian;
import game.characters.heroes.dwarf.Dwarf;
import game.characters.heroes.magician.Elf;
import game.characters.heroes.magician.Wizard;
import game.characters.monsters.Monster;
import game.characters.monsters.goblin.Goblin;
import game.interfaces.IOMediator;
import game.interfaces.MapElementVisitor;
import game.map.treasure.Treasure;

public class Map {

    private MapArea[] places;
    private List<MapArea> discovery;
    private MapArea lastPlace;

    private Hero player;
    private Coordinate heroStartPosition;
    private IOMediator mediator;

    Map(MapArea[] places, Coordinate heroStartPosition) {
        this.places = places;
        this.lastPlace = searchPlace("StartRoom", 0, places.length -1);
        this.player = null;
        this.mediator = null;
        this.heroStartPosition = heroStartPosition;
        discovery = new ArrayList<MapArea>();
    }

    public void setMediator(IOMediator mediator) {
        if (this.mediator == null) {
            this.mediator = mediator;
        }
        for(MapArea i : places){
            i.setMediator(mediator);
        }
    }
    
    public void chooseHero(int option, String name){
        if(player == null){
            switch (option){
                case 4:
                    player = new Barbarian(name, heroStartPosition);
                    break;
                case 3:
                    player = new Dwarf(name, heroStartPosition);
                    break;
                case 2:
                    player = new Elf(name, heroStartPosition);
                    break;
                case 1:
                    player = new Wizard(name, heroStartPosition);
                    break;
            }

            player.setMediator(this.mediator);
            checkLastPlace(heroStartPosition.getPlace());
            if(lastPlace != null){
                lastPlace.initHero(player);
            }
        }

    }

    private MapArea searchPlace(String keyName, int ini, int end) {

        if (end < ini) {
            return null;
        }

        int middle = (ini + end) / 2;
        String Mname = places[middle].getName();

        if (Mname.compareTo(keyName) == 0) {
            return places[middle];
        } else if (Mname.compareTo(keyName) < 0) {
            return searchPlace(keyName, middle + 1, end);
        }
        return searchPlace(keyName, ini, middle - 1);
    }

    private void addDiscoveryArea(MapArea area) {
        area.discover();
        discovery.add(area);
    }

    public void updateArea(String placeName){
        MapArea area = searchPlace(placeName, 0, places.length-1);
        if(area != null){
            area.moveMonsters(this, player);
        }

    }

    public int getMonstersSum(){
        int sum = 0;
        for(MapArea area : places){
            sum += area.getMonstersSum();
        }
        return sum;
    }

    public boolean playerDead(){
        return player.isDead();
    }

    private void checkLastPlace(String placeName) {
        if(lastPlace == null || !placeName.equals(lastPlace.getName())){
            lastPlace = searchPlace(placeName, 0, places.length-1);
        }
    }

    public void movePlayer(Direction direction) {
        
        Coordinate pPos = player.getCoordinate();
        String placeName = pPos.getPlace();

        checkLastPlace(placeName);

        if (lastPlace != null) {
            if (!lastPlace.wasDiscovered()) {
                addDiscoveryArea(lastPlace);
            }

            lastPlace.allowMove(player, direction, mediator);
        }

    }

    public void moveMonsters() {

        for(MapArea discovered : discovery){
            discovered.moveMonsters(this, player);
        }
       
    }

    public void moveMonster(Monster monster, Direction direction) {
        Coordinate mPos = monster.getCoordinate();
        String placeName = mPos.getPlace();

        checkLastPlace(placeName);

        if(lastPlace != null) {
            
            lastPlace.allowMove(monster, direction, mediator);  
           
        }
    }

    void insertMonster(Monster monster, String place){
        MapArea area = searchPlace(place, 0, places.length-1);
        if(area != null){
            area.insertMonster(monster);
        }
    }

    public MapArea getArea(MapElement e){
        checkLastPlace(e.getCoordinate().getPlace());
        return lastPlace; 
    }

    public boolean openTreasure() {

        String placeName = player.getCoordinate().getPlace();
        checkLastPlace(placeName);

         Treasure found = null;

        if(lastPlace != null){
            found = lastPlace.getTreasure(player.getCoordinate(), player.getDirection());
        }

        if(found != null) {
            int chance = (int) (Math.random() * 10) + 1;
            
            if(chance <= 2) {
                mediator.alert("Um monstro Surpresa foi ativado, uma batalha vai comecar!");
                Coordinate fPos = found.getCoordinate();
                Monster surprise = new Goblin("Surprise", fPos.getX(), fPos.getY(), lastPlace.getName());
                surprise.setMediator(mediator);
                player.attack(surprise);
                // Verificar se o surprise sobreviveu ao Ataque
                if(!surprise.isDead()){
                    lastPlace.insertMonster(surprise);
                }
                
            }

            else {
                mediator.alert("\nBaú coletado com sucesso!");
                player.collectTreasure(found.getItems());
                mediator.showCollectItems(found.getItems());
            }
            return true;
        }

        return false;
    }

    public Monster[] getNearMonsters(Coordinate center, Direction reference, int range, int amount, boolean diagonal) {

        String placeName = center.getPlace();
        checkLastPlace(placeName);

        if(lastPlace != null){
            Monster[] near = lastPlace.getNearMonsters(center, reference, range, amount, diagonal);
            for(Monster i : near){
                if(i != null){
                    return near;
                }
            } 
        }
        mediator.alert("\nNão Há monstros no alcance.");
        return null;
    } 

    public void removeDeadMonsters(Monster[] deadMonsters, String placeName) {
        checkLastPlace(placeName);
        if(lastPlace != null){
            lastPlace.removeDeadMonsters(deadMonsters);
        }
    }

    public int valueMovementDices() {
        return player.playMovementDices();
    }

    public boolean battlePhase(){
        return player.battleAttack(this);
    }

    public void changeItems(){
        player.changeItems();
    }

    public boolean invokeMagic(){
        return player.useMagic(this);
    }


    public void accept(MapElementVisitor visitor) {
        checkLastPlace(player.getCoordinate().getPlace());
        if(lastPlace != null){
            lastPlace.accept(visitor, player);
        }
    }

    public String getHeroStats() {
        return player.toString();
    }

    public boolean isValidMovement(Coordinate coordinate, Direction direction) {
        checkLastPlace(coordinate.getPlace());
        if (lastPlace != null) {
            Coordinate testPos = new Coordinate(coordinate.getX()+direction.getxOffset(), coordinate.getY()+direction.getyOffset(), coordinate.getPlace());
            return lastPlace.isValidMovement(testPos);
        }
        return false;
    }
}