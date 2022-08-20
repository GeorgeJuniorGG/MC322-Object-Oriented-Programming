package lab07;

public class Wave {
    private Tower[] towers;
    private Dalek[] daleks;
    
    public Wave(Tower[] towers, Dalek[] daleks) {
        this.towers = towers;
        this.daleks = daleks;
    }

    boolean hasItFinished() {
        for(int i = 0; i < daleks.length; i++) {
            if (daleks[i].getHP() > 0) {
                return false;
            }
        }
        return true;
    }

    Dalek[] getDaleks() {
        return daleks;
    }

    Tower[] getTowers() {
        return towers;
    }
}