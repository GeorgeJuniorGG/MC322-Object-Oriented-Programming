package game.map;

public enum MapESymbol {

    EMPTY('-'),
    WALL('#'),
    GATEWAY(' '),
    CLOSEDDOOR('U'),
    TRAP('T'),
    TREASURE('$'),
    GOBLIN('G'),
    SKELETON('S'),
    WIZARDSKELETON('V'),
    WIZARD('W'),
    BARBERIAN('B'),
    ELF('E'),
    DWARF('D');
    
    private char textSymbol;

    private MapESymbol(char symbol){
        textSymbol = symbol;
    }

    public char getTSymbol(){
        return textSymbol;
    }
}