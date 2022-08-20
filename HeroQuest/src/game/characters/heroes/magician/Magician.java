package game.characters.heroes.magician;

import game.characters.heroes.Hero;
import game.characters.heroes.HeroClass;
import game.characters.monsters.Monster;
import game.interfaces.IOMediator;
import game.magic.Fireball;
import game.magic.Magic;
import game.magic.MagicMissile;
import game.magic.SimpleHeal;
import game.magic.Teleport;
import game.map.Coordinate;
import game.map.Map;
import game.map.MapESymbol;

public abstract class Magician extends Hero {

    protected Fireball fireball;
    protected MagicMissile magicMissile;
    protected SimpleHeal simpleHeal;
    protected Teleport teleport;
    private int quantityFireball;
    private int quantityMagicMissile;
    private int quantitySimpleHeal;
    private int quantityTeleport;

    public Magician(String name, int quantityAttackDices, int quantityDefenseDices, int pointsOfLife, int pointsOfSmart, Coordinate coordinate, MapESymbol symbol, HeroClass heroClass) {
        super(name, quantityAttackDices, quantityDefenseDices, pointsOfLife, pointsOfSmart, coordinate, symbol, heroClass);
    }

    // no modifier: pkg private 
    void addMagic(Fireball fireball, int count) {
        this.fireball = fireball;
        this.quantityFireball = count;
    }

    void addMagic(MagicMissile magicMissile, int count) {
        this.magicMissile = magicMissile;
        this.quantityMagicMissile = count;
    }

    void addMagic(SimpleHeal simpleHeal, int count) {
        this.simpleHeal = simpleHeal;
        this.quantitySimpleHeal = count;
    }

    void addMagic(Teleport teleport, int count) {
        this.teleport = teleport;
        this.quantityTeleport = count;
    }

    // ############## ATACAR

    private boolean throwMagic() {
        if (movementDice.getNumber() < pointsOfSmart) {
            return true;
        }
        return false;
    }

    private boolean attackFireball(Map map) {
        if (quantityFireball <= 0) {
            return false;
        }
        Monster[] targets = map.getNearMonsters(coordinate, direction, fireball.getReach(), 3, true);
        if (targets == null) {
            return false;
        } else {
            fireball.useSpell(targets);
            quantityFireball--;
            map.removeDeadMonsters(targets, coordinate.getPlace());
            return true;
        }

    }

    private boolean attackMagicMissile(Map map) {
        if (quantityMagicMissile <= 0) {
            return false;
        }
        Monster[] targets = map.getNearMonsters(coordinate, direction, magicMissile.getReach(), 1, true);
        if (targets == null) {
            return false;
        }
        magicMissile.useSpell(targets[0]);
        quantityMagicMissile--;
        map.removeDeadMonsters(targets, coordinate.getPlace());
        return true;
    }

    private boolean useTeleport(IOMediator mediator, Map map) {
        if (quantityTeleport <= 0) {
            return false;
        }
         String query = "Escolha um alvo para o teleporte:\n[0] - Monstro mais próximo\n[1] - Seu personagem";
        int choice = mediator.getAnswer(query);
        if (choice == 0) { // teleporta o monstro mais próximo
            Monster[] monster = map.getNearMonsters(this.coordinate, this.direction, 1, 1, true);
            if (monster != null) {
                teleport.useSpell(monster[0], map.getArea(this));
                quantityTeleport--;
                return true;
            }
        } else { // teleporta o personagem
            teleport.useSpell(this, map.getArea(this));
            quantityTeleport--;
            return true;
        }
        return false;
    }

    private boolean useSimpleHeal() {
        if (quantitySimpleHeal <= 0) {
            return false;
        }
        simpleHeal.useSpell(this);
        quantitySimpleHeal--;
        return true;        
    }

    @Override
    public boolean useMagic(Map map) {
        Magic[] magics = {fireball, magicMissile, simpleHeal, teleport};
        int[] quantityMagics = {quantityFireball, quantityMagicMissile, quantitySimpleHeal, quantityTeleport};
        int magic = mediator.chooseMagic(magics, quantityMagics);
        boolean success = false;
        if (throwMagic()) {
            switch (magic) {
            case 0:
                success = false;
                break;
            case 1:
                success = attackFireball(map);
                break;
            case 2:
                success = attackMagicMissile(map);
                break;
            case 3:
                success = useSimpleHeal();
                break;
            case 4:
                success = useTeleport(mediator, map);
                break;
            }
        }
        return success;
    }








    /*
    lançar magia
    */

}