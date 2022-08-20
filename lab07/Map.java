package lab07;

// O mapa representará o seguinte:
//                               Essas são as correspondências dos vetores no mapa:
// 44 43 42 41 40 39 38 37         44 43 42 41 40 39 38 37
// TT TT TT TT TT TT TT 36         22'23'24'25'26'27'28'36
// 28 29 30 31 32 33 34 35         28 29 30 31 32 33 34 35
// 27 TT TT TT TT TT TT TT         27 21'20'19'18'17'16'15'
// 26 25 24 23 22 21 20 19         26 25 24 23 22 21 20 19
// TT TT TT TT TT TT TT 18         08'09'10'11'12'13'14'18
// 10 11 12 13 14 15 16 17         10 11 12 13 14 15 16 17
// 09 TT TT TT TT TT TT TT         09 07'06'05'04'03'02'01'
// 08 07 06 05 04 03 02 01 WP      08 07 06 05 04 03 02 01 00
//
// TT é a posição para torres e WP é o ponto de espera

public class Map {
    private static int MAX_UNITS_PER_TILE = 10;

    private Units[][] map;
    private Tower[] towers;
    private int[] count;
    private boolean end;

    public Map() {
        map = new Units[45][MAX_UNITS_PER_TILE];
        towers = new Tower[28];
        count = new int[45];
        end = false;
    }

    void addWave(Wave wave) {
        Dalek[] daleks = wave.getDaleks();
        for (int i = 0; i < daleks.length; i++) {
            this.add(daleks[i]);
        }
        for(int i = 0; i < wave.getTowers().length; i++) {
            this.addTower(wave.getTowers()[i]);
        }
    }

    void add(Units unit) {
        if(unit instanceof Dalek && unit.getPosition() >= 45) {
            System.out.println("Os Daleks venceram o jogo!");
            end = true;
        }
        else {
            if(unit.getPosition() <= 0) {
                count[0]++;
            }
            else {
                count[unit.getPosition()] ++;
            }
            for(int i = 0; i < MAX_UNITS_PER_TILE; i++) {
                if(unit.getPosition() <= 0 && map[0][i] == null) {
                    map[0][i] = unit;
                    break;
                }
                else if(unit.getPosition() > 0 && map[unit.getPosition()][i] == null) {
                    map[unit.getPosition()][i] = unit;
                    break;
                }
            }
        }
    }

    void remove(Units unit) {
        if(count[unit.getPosition()] != 0) {
            count[unit.getPosition()] --;
        }
        for(int i = 0; i < MAX_UNITS_PER_TILE; i++) {
            if(map[unit.getPosition()][i] == unit) {
                map[unit.getPosition()][i] = null;
                break;
            }
        }
    }

    private void addTower(Tower tower) {
        for (int i = 0; i < towers.length; i++) {
            if (towers[i] == null) {
                towers[i] = tower;
                break;
            }
        }
    }

    private void removeTower(Tower tower) {
        for(int i = 0; i < towers.length; i++) {
            if (towers[i] == tower) {
                towers[i] = null;
                break;
            }
        }
    }

    int checkForTowers(int position){
        for(int i = 0; i < towers.length; i++) {
            if (towers[i]!= null && (towers[i].getPosition() == position || towers[i].getUpperPosition() == position || towers[i].getLeftPosition() == position || towers[i].getRightPosition() == position)) {
                return i;
            }
        }
        return 0;
    }
    
    void attackTower(int position, int damage) {
        towers[position].takeDamage(damage);
        if (towers[position].getHP() <= 0) {
            removeTower(towers[position]);
        }
    }

    int checkForDoctor(int position) {
        for (int i = 0; i < MAX_UNITS_PER_TILE; i++) {
            if(position < 45 && map[position][i] instanceof Doctor) {
                return i;
            }
        }
        return 0;
    }

    void attackDoctor(int position, int index, int damage) {
        map[position][index].takeDamage(damage);
    }

    boolean isThereADalek(int position) {
        for(int i = 0; i < MAX_UNITS_PER_TILE; i++) {
            if(map[position][i] instanceof Dalek && map[position][i].getHP() > 0) {
                return true;
            }
        }
        return false;        
    }

    boolean getStatus() {
        return end;
    }
    
    void attackDalek (int position, int damage) {
        for(int i = 0; i < MAX_UNITS_PER_TILE; i++) {
            if(map[position][i] instanceof Dalek) {
                map[position][i].takeDamage(damage);
                if(map[position][i].getHP() <= 0) {
                    map[position][i] = null;
                    count[position]--;
                }
                break;
            }
        }
    }

    int getFurthestDalek() {
        for (int i = 43; i > 0; i--) {
            if (isThereADalek(i)) {
                return i;
            }
        }
        return 0;
    }

    //Para a impressao do mapa, optei por imprimir quantas unidades (Dalek ou Doctor) ha em cada posicao normal, 
    //e imprimir um TT na posicao de cada torre
    //Alem disso, as posicoes que poderiam ter torre mas nao tem estao representadas por --
    
    void printMap() {
        if (!end) {
            boolean gotThePrint = true;
            for (int i = 44; i > 36; i--) {
                System.out.print("0" + count[i] + " ");
            }
            System.out.print("\n");

            for (int i = 28; i < 35; i++) {
                gotThePrint = true;
                for (int j = 0; j < towers.length; j++) {
                    if (towers[j] != null && towers[j].getHP() > 0 && towers[j].getPosition() == i) {
                        System.out.print("TT ");
                        gotThePrint = false;
                        break;
                    }
                }
                if (gotThePrint) {
                    System.out.print("-- ");
                }
            }

            System.out.println("0" + count[36]);

            for (int i = 28; i < 36; i++) {
                System.out.print("0" + count[i] + " ");
            }
            System.out.print("\n");

            System.out.print("0" + count[27] + " ");

            for (int i = 25; i > 18; i--) {
                gotThePrint = true;
                for (int j = 0; j < towers.length; j++) {
                    if (towers[j] != null && towers[j].getHP() > 0 && towers[j].getPosition() == i) {
                        System.out.print("TT ");
                        gotThePrint = false;
                        break;
                    }
                }
                if (gotThePrint) {
                    System.out.print("-- ");
                }
            }
            System.out.print("\n");

            for (int i = 26; i > 18; i--) {
                System.out.print("0" + count[i] + " ");
            }
            System.out.print("\n");

            for (int i = 10; i < 17; i++) {
                gotThePrint = true;
                for (int j = 0; j < towers.length; j++) {
                    if (towers[j] != null && towers[j].getHP() > 0 && towers[j].getPosition() == i) {
                        System.out.print("TT ");
                        gotThePrint = false;
                        break;
                    }
                }
                if (gotThePrint) {
                    System.out.print("-- ");
                }
            }    
            System.out.println("0" + count[18]);

            for (int i = 10; i < 18; i++) {
                System.out.print("0" + count[i] + " ");
            }
            System.out.print("\n");

            System.out.print("0" + count[9] + " ");

            for (int i = 7; i > 0; i--) {
                gotThePrint = true;
                for (int j = 0; j < towers.length; j++) {
                    if (towers[j] != null && towers[j].getHP() > 0 && towers[j].getPosition() == i) {
                        System.out.print("TT ");
                        gotThePrint = false;
                        break;
                    }
                }
                if (gotThePrint) {
                    System.out.print("-- ");
                }
            }
            System.out.print("\n");

            for (int i = 8; i > 0; i--) {
                System.out.print("0" + count[i] + " ");
            }
            System.out.print("\n");

            System.out.println("=======================");
        }
    }
}