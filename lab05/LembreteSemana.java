package lab05;

import java.util.Calendar;

public class LembreteSemana extends Lembrete{
    private int[] weekDays;
    private int numberOfDays;

    public LembreteSemana(String description, int[] weekDays, int numberOfDays) {
        super(description);
        this.numberOfDays = numberOfDays;
        this.weekDays = weekDays;
    }

    @Override
    public void imprimirLembrete() {
        super.imprimirLembrete();
        System.out.print("Para ");
        for(int i = 0; i < numberOfDays; i++) {
            if(weekDays[i] == Calendar.SATURDAY) {
                System.out.print("Sabado ");
            }
            else if(weekDays[i] == Calendar.SUNDAY) {
                System.out.print("Domingo ");
            }
            else if(weekDays[i] == Calendar.MONDAY) {
                System.out.print("Segunda feira ");
                            }
            else if(weekDays[i] == Calendar.TUESDAY) {
                System.out.print("Terca feira ");
            }
            else if(weekDays[i] == Calendar.WEDNESDAY) {
                System.out.print("Quarta feira ");
            }
            else if(weekDays[i] == Calendar.THURSDAY) {
                System.out.print("Quinta feira ");
            }
            else if(weekDays[i] == Calendar.FRIDAY) {
                System.out.print("Sexta feira ");
            }
        }
        System.out.println('.');
    }

    public int[] getWeekDays() {
        return weekDays;
    }

    public int getNumberOfWeekDays() {
        return numberOfDays;
    }
}