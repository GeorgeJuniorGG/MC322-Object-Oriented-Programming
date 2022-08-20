package game.engine.text;

import game.characters.Character;
import game.engine.GameEngine;
import game.interfaces.IOMediator;
import game.items.Item;
import game.magic.Magic;
import game.map.Direction;
import game.map.MapLoader;


public class TextEngine extends GameEngine implements IOMediator{

    private final String UP = "w";
    private final String DOWN = "s";
    private final String RIGHT = "d";
    private final String LEFT = "a";
    private final String CHANGE = "c";
    private final String FIND = "f";
    private final String BATTLE = "b";
    private final String QUIT = "q";
    private final String MAGIC = "m";

    private TextRender tRender;
    private Reader keyboard;

    private Character charact;


    public TextEngine() {
        tRender = new TextRender();
        keyboard = new Reader(tRender);
        chooseMap();
        map.setMediator(this);
    }

    @Override
    public void chooseMap(){
        MapLoader loader = MapLoader.getInstace();
        tRender.showChooseMapMenu();
        String command = keyboard.readString();
        if(command.equals("f")) {
            String path = chooseFile();
            if(path.equals("Default")){
                this.map = loader.createDefautMap();
            }
            else{
                this.map = (loader.loadMapFromFile(path));
            }
            
        }
        else if(command.equals("r")) {
            this.map = (loader.createRandomMap());
        }
        else {
            this.map = (loader.createDefautMap());    
        }
    }

    private String chooseFile(){
        String path = "";
        tRender.show("Escolha o arquivo para gerar o mapa:");
        tRender.showOption(0, "Para Carregar o Mapa Default.");
        tRender.showOption(1, "ZargonDungeon.txt");
        tRender.showOption(2, "MorcarDungeon.txt");

        boolean success = false;
        int opt = -1;
        
        while(!success){
            opt = keyboard.readInt();
            if(opt >=0 && opt <=2){
                success = true;
            }
            else{
                tRender.show("Opção inválida! Tente novamente.");
            }
        } 
        
        if(opt == 0){
            path = "Default";
        }
        else if(opt == 1){
            path = "ZargonDungeon.txt";
        }
        else if(opt ==2 ){
            path = "MorcarDungeon.txt";
        }

        return path;
    }

    private void initHeroes() {

        tRender.show("Escolha seu heroi digitando o numero equivalente:");
        tRender.showOption(1, "Feiticeiro (Wizard)");
        tRender.showOption(2, "Elfo (Elf)");
        tRender.showOption(3, "Anão (Dwarf)");
        tRender.showOption(4, "Bárbaro (Barbarian)");

        boolean success = false;
        int opt = -1;
        
        while(!success){
            opt = keyboard.readInt();
            if(opt >=1 && opt <=4){
                success = true;
            }
            else{
                tRender.show("Opção inválida! Tente novamente.");
            }
        }
    
        tRender.show("Escolha um nome para o seu personagem: ");

        String name = keyboard.readString();

        map.chooseHero(opt, name);

    }

    @Override
    public String changeItemMenu() {
        tRender.show("Escolha qual tipo de item voce deseja trocar, digitando a letra correspondente: ");
        tRender.showOption('a' , "Armadura");
        tRender.showOption('w' ,"Arma");
        tRender.showOption('p' ,"Poção");
        tRender.showOption('q' ,"Sair do Menu");
        
        return keyboard.readString();
    }

    @Override
    public int chooseItem(Item[] items) {
        tRender.show("Escolha o número correspondente ao item que quer equipar: ");
        tRender.showOption(0, "sair");
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null){
                tRender.showOption(i + 1 ,items[i].getName());
            }
            
        }

        boolean success = false;
        int choose = -1;

        while(!success){
            choose = keyboard.readInt();
            if(choose >= 0 && choose <= items.length){
                success = true;
            }
            else {
                tRender.show("Opção inválida! Tente novamente.");
            }
        }

        return choose - 1;
    }

    @Override
    public void alert(String message) {
        tRender.alert(message);
        
    }

    @Override
    public int getAnswer(String query) {
        tRender.show(query);

        boolean success = false;
        int answer = -1;

        while(!success) {
            answer = keyboard.readInt();
            if(answer == 1 || answer == 0) {
                success = true;
            }
            else {
                tRender.show("Opção inválida! Tente novamente.");
            }
        }

        return answer;
    }

    @Override
    public void setAttacker(Character character) {
        this.charact = character;
        
    }

    @Override
    public int chooseMagic(Magic[] magics, int[] quantity) {
        tRender.show("Digite o número da magia p/ ser usada.");
        tRender.showOption(0, "sair.");
        for (int i = 0; i < magics.length; i++) {
            if (magics[i] != null && quantity[i] > 0) {
                tRender.showOption(i+1, magics[i].toString() + " | Magias restantes: " +quantity[i]);
            }
        }
        int choice = keyboard.readInt();
        while (choice < 0 || choice > 4 || (choice > 0 && magics[choice-1] == null)) {
            tRender.show("Digite outra opção.");
            choice = keyboard.readInt();
        }
        return choice;
    }

        @Override
    public void battleHero(Character character, int damage) {

        tRender.alert("Fase de Combate do " + charact.getName() + ".");

        tRender.alert("Início da Batalha, seu dano efetivo será: " + damage);
        tRender.alert("Alvo: " + character.getName() + " | Pontos de vida do Monstro: " + (character.getPointsOfLife()+damage));
        if (isDead(character)) {
            tRender.alert("O alvo morreu.");
        } else {
            tRender.alert("O alvo sobreviveu ao ataque.\n");
        }
    }

    @Override
    public void battleMonster(Character character, int damage) {

        tRender.alert("Fase de Combate do Monstro:");

        tRender.alert("Início da Batalha, o " + charact.getName() + " é o seu atacante. O dano efetivo do Monstro será: " + damage);

        tRender.alert("Alvo: " + character.getName() + " | Pontos de vida do Herói: " + (character.getPointsOfLife()+damage));
        if (isDead(character)) {
            tRender.alert("O herói morreu.");
        } else {
            tRender.alert("O herói sobreviveu ao ataque.\n");
        }
    }

    @Override
    public void showMap() {
        tRender.render(map);
        
    }

    @Override
    public void notifyMap(String place) {
        map.updateArea(place);
    }
        
    public void showCollectItems(Item[] items) {
        tRender.alertW("Itens coletados: ");
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (i < items.length-1) {
                tRender.alertW(items[i].getName() + ", ");
                } else {
                tRender.alertW(items[i].getName() + ".");
                } 
            }  
        }
        tRender.alert("");
    }

    private boolean isDone(){
        boolean done = false;
        if(map.getMonstersSum() <= 0 || map.playerDead()){
            done = true;
        }
        return done;
    }

    @Override
    public void gameLoop() {

       
        boolean running = true;
    
        initHeroes();

        while(running && !isDone()) {
            printStats();
            tRender.render(map);
            running = !readToKeyboard();    
        }
        
        tRender.show("Jogo Finalizado!");
        if(map.playerDead()) {
            alert("Seu herói morreu, você perdeu!");
        }
        else if(map.getMonstersSum() == 0) {
            alert("Você matou todos os monstros! Vitória!");
        }
        keyboard.close();
        
    }

    private boolean readToKeyboard() {
        boolean stop = false;
        
        tRender.alert("Se você deseja se mover antes de fazer alguma ação, digite y");
        tRender.alert("Caso deseja fazer alguma ação antes de se mover, digite n");
        tRender.alert("Se quiser encerrar o jogo, digite q");

        String command = keyboard.readString();

        if(command.equals("y")){
            movePhase();
            if(!isDone()) {
                actionPhase();
            }
        }
        else if(command.equals("n")){
            actionPhase();
            if(!isDone()) {
                movePhase();
            }
        }
        else if(command.equals(QUIT)){
            stop = true;
        }

        return stop;
    }

    private boolean isDead(Character character) {
        if (character.getPointsOfLife() <= 0) {
            return true;
        }
        return false;
    }



    private void actionPhase() {
        
        boolean success = false;

        while(!success) {

            tRender.show("Escolha sua ação digitando o caractere correspondente:");
            tRender.showOption('c', "Para troca ou uso de item");
            tRender.showOption('f', "Para buscar por tesouros");
            tRender.showOption('b', "Para atacar");
            tRender.showOption('m', "Para usar alguma magia (se seu personagem puder usar magia)");
            tRender.showOption('q', "Para sair do menu");

            String command = keyboard.readString();

            if(command.equals(CHANGE)) {
                success = changeItem();
            }
            else if(command.equals(FIND)) {
                success = findTreasure();
            }
            else if(command.equals(BATTLE)) {
                success = battlePhase();
            }
            else if(command.equals(MAGIC)){
                success = map.invokeMagic();
            } 
            else if(command.equals(QUIT)){
                success = true;
            }

            if(!success){
                tRender.show("Não foi possível concluir a ação! Tente outra opção");
            }
        }

    }

    private void movePhase() {
        
        tRender.show("Fase de movimentação:");

        int steps = map.valueMovementDices();
        tRender.show("Quantidade de passos gerados no dados: " + steps);
        
        tRender.render(map); 

        String command;
        boolean success = false;

        for(int i = 0; i < steps; i++) {
            while(!success) {
                alert("Quantidade de passos restantes: " + (steps - i));
                alert("Movimente-se usando as teclas W A S D, ou digite q para interromper o movimento");
                command = keyboard.readString();
                success = true;

                if(command.equals(UP)){
                    movePlayer(Direction.UP);
                }
                else if(command.equals(DOWN)){
                    movePlayer(Direction.DOWN);
                }
                else if(command.equals(LEFT)){
                    movePlayer(Direction.LEFT);
                }
                else if(command.equals(RIGHT)){
                    movePlayer(Direction.RIGHT);
                }
                else if(command.equals(QUIT)){
                    map.moveMonsters();
                    tRender.render(map);
                    return;
                } 
                else{
                    success = false;
                } 
                tRender.render(map);
                if(isDone()){
                    return;
                }
            }  
            success = false;   
        }

        //Movimento dos Monstros
        map.moveMonsters();
        tRender.render(map);

    }

    private void movePlayer(Direction direction) {
        map.movePlayer(direction);
    }

    private boolean battlePhase() {
        return map.battlePhase();
    }

    private boolean changeItem(){
        map.changeItems();
        return true;
    }

    private boolean findTreasure() {
       return map.openTreasure();
    } 

    private void printStats() {
        tRender.show(map.getHeroStats());
    }
}