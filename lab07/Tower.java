package lab07;

public class Tower extends Units{

    private int upperPosition;
    private int leftPosition;
    private int rightPosition;

    public Tower(int positionBelow, int upperPosition, int leftPosition, int rightPosition, int hp, int ap) {
        super(hp, ap, positionBelow);
        this.upperPosition = upperPosition;
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
    }

    int getUpperPosition(){
        return upperPosition;
    }

    int getRightPosition() {
        return rightPosition;
    }

    int getLeftPosition() {
        return leftPosition;
    }

    private int findNearestDalek(Map map) {
        if (position == 1) {
            if(map.isThereADalek(17)) {
                return 17;
            }
            else if(map.isThereADalek(16)) {
                return 16;
            }
            else if(map.isThereADalek(2)) {
                return 2;
            }
            else if(map.isThereADalek(1)) {
                return 1;
            }
        }
        else if (position == 10) {
            if(map.isThereADalek(26)) {
                return 26;
            }
            else if(map.isThereADalek(25)) {
                return 25;
            }
            else if(map.isThereADalek(11)) {
                return 11;
            }
            else if(map.isThereADalek(10)) {
                return 10;
            }
        }
        else if(position == 19) {
            if(map.isThereADalek(35)) {
                return 35;
            }
            else if(map.isThereADalek(34)) {
                return 34;
            }
            else if(map.isThereADalek(20)) {
                return 20;
            }
            else if(map.isThereADalek(19)) {
                return 19;
            }
        }
        else if(position == 28) {
            if(map.isThereADalek(44)) {
                return 44;
            }
            else if(map.isThereADalek(43)) {
                return 43;
            }
            else if(map.isThereADalek(29)) {
                return 29;
            }
            else if(map.isThereADalek(28)) {
                return 28;
            }
        }
        else if(leftPosition == 0 && rightPosition == 0) {
            if(map.isThereADalek(upperPosition + 1)) {
                return upperPosition + 1;
            }
            else if(map.isThereADalek(upperPosition)) {
                return upperPosition;
            }
            else if(map.isThereADalek(upperPosition - 1)) {
                return upperPosition - 1;
            }
            else if(map.isThereADalek(position + 1)) {
                return position + 1;
            }
            else if(map.isThereADalek(position)) {
                return position;
            }
            else if(map.isThereADalek(position - 1)) {
                return position - 1;
            }

        }
        else if(leftPosition != 0) {
            if(map.isThereADalek(upperPosition + 1)) {
                return upperPosition + 1;
            }
            else if(map.isThereADalek(upperPosition)) {
                return upperPosition;
            }
            else if(map.isThereADalek(upperPosition - 1)) {
                return upperPosition - 1;
            }
            else if(map.isThereADalek(leftPosition)) {
                return leftPosition;
            }
            else if(map.isThereADalek(position + 1)) {
                return position + 1;
            }
            else if(map.isThereADalek(position)) {
                return position;
            }
            else if(map.isThereADalek(position - 1)) {
                return position - 1;
            }
        }
        else if(rightPosition != 0) {
            if(map.isThereADalek(upperPosition + 1)) {
                return upperPosition + 1;
            }
            else if(map.isThereADalek(upperPosition)) {
                return upperPosition;
            }
            else if(map.isThereADalek(upperPosition - 1)) {
                return upperPosition - 1;
            }
            else if(map.isThereADalek(rightPosition)) {
                return rightPosition;
            }
            else if(map.isThereADalek(position + 1)) {
                return position + 1;
            }
            else if(map.isThereADalek(position)) {
                return position;
            }
            else if(map.isThereADalek(position - 1)) {
                return position - 1;
            }
        }
        return 0;
    }

    void attack(Map map) {
        int victim = findNearestDalek(map);
        if(victim != 0) {
            map.attackDalek(victim, ap);
        }
    }

}