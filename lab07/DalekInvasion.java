package lab07;

public class DalekInvasion {
    public static void main(String[] args) {
        DalekInvasion dalekInvasion = new DalekInvasion();
        Doctor doctor = new Doctor(999999999, 10);
        Map map = new Map();
        Worker worker1 = new Worker(0, 100, 0);
        Worker worker2 = new Worker(-1, 100, 0);
        Worker worker3 = new Worker(-5, 100, 0);
        EliteGuard elite1 = new EliteGuard(-3, 200, 20);
        EliteGuard elite2 = new EliteGuard(-10, 200, 20);
        Tower tower1 = new Tower(7, 11, 9, 0, 300, 25);
        Tower tower2 = new Tower(31, 41, 0, 0, 300, 25);
        Tower tower3 = new Tower(13, 23, 0, 0, 300, 25);
        Tower[] towers1 = {tower1, tower2};
        Tower[] towers2 = {tower1, tower2, tower3};
        Dalek[] daleks1 = {worker1};
        Dalek[] daleks2 = {worker2, worker3, elite2, elite1};
        Wave wave1 = new Wave(towers1, daleks1);
        Wave wave2 = new Wave(towers2, daleks2);
        dalekInvasion.rollWave(wave1, map, doctor);
        dalekInvasion.rollWave(wave2, map, doctor);     
    }
    
    private void rollWave(Wave wave, Map map, Doctor doctor) {
        map.addWave(wave);
        while(!wave.hasItFinished() && !map.getStatus()) {
            for(int i = 0; i < wave.getDaleks().length; i++) {
                wave.getDaleks()[i].move(map);
                wave.getDaleks()[i].attack(map);
            }
            for(int i = 0; i < wave.getTowers().length; i++) {
                wave.getTowers()[i].attack(map);
            }
            doctor.move(map);
            doctor.attack(map);
            map.printMap();
        }
        if(!map.getStatus()) {
            System.out.println("Fim da wave!");
            System.out.println("=======================");
        }
    }

}