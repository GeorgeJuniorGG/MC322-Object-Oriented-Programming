package lab05;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class Agenda{
    private Lembrete[] lembretes;
    private LembreteMensal[] lembretesMensais;
    private LembreteSemana[] lembretesSemanas;
    private Evento[] eventos;
    private Reuniao[] reunioes;

    public Agenda(int numberofLembretes, int numberofLembretesSemanais, int numberofLembretesMensais, int numberofEventos, int numberofReunioes) {
        this.lembretes = new Lembrete[numberofLembretes + 1];
        this.lembretesMensais = new LembreteMensal[numberofLembretesMensais + 1];
        this.lembretesSemanas = new LembreteSemana[numberofLembretesSemanais + 1];
        this.eventos = new Evento[numberofEventos + 1];
        this.reunioes = new Reuniao[numberofReunioes + 1];
    }
    public static void main(String[] args) {
        Agenda agenda = new Agenda(2, 2, 2, 2, 1);
        Lembrete lembrete1 = new Lembrete("Estudar");
        Lembrete lembrete2 = new Lembrete("Fazer listas pendentes");
        LembreteMensal lembrete3 = new LembreteMensal("Prova de Calculo", Month.APRIL);
        LembreteMensal lembrete4 = new LembreteMensal("Ferias", Month.JULY);
        int[] weekDays1 ={Calendar.SATURDAY, Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY};
        LembreteSemana lembrete5 = new LembreteSemana("Lista de calculo", weekDays1, 4);
        int[] weekDays2 = {Calendar.TUESDAY};
        LembreteSemana lembrete6 = new LembreteSemana("Entregar lab de MC322", weekDays2, 1);
        Pessoa pessoa1 = new Pessoa("George", "georgejunior@hotmail.com", 32786534);
        Pessoa pessoa2 = new Pessoa("Buffy", "buffy123@gmail.com", 98762342);
        Pessoa[] pessoas1 = {pessoa1, pessoa2};
        pessoa1.confirmPerson();
        pessoa2.confirmPerson();
        Evento lembrete7 = new Evento("Show online", ZonedDateTime.now());
        Evento lembrete8 = new Evento("Live", ZonedDateTime.of(2020, 5, 4, 3, 24, 0, 0, ZoneId.systemDefault()));
        Reuniao lembrete9 = new Reuniao("Jogar", ZonedDateTime.now(), pessoas1, 2);
        agenda.addEvento(lembrete7);
        agenda.addEvento(lembrete8);
        agenda.addReuniao(lembrete9);
        agenda.addLembrete(lembrete1);
        agenda.addLembrete(lembrete2);
        agenda.addLembreteMensal(lembrete3);
        agenda.addLembreteMensal(lembrete4);
        agenda.addLembreteSemana(lembrete5);
        agenda.addLembreteSemana(lembrete6);
        agenda.printSomeDaysLembretes(ZonedDateTime.now());
        agenda.printBetweenDatesLembretes(ZonedDateTime.now(), ZonedDateTime.of(2020, 4, 26, 3, 24, 0, 0, ZoneId.systemDefault()));
        agenda.removeEvento(lembrete7);
        agenda.removeReuniao(lembrete9);
        agenda.removeLembrete(lembrete2);
        agenda.removeLembreteMensal(lembrete4);
        agenda.removeLembreteSemana(lembrete5);
        agenda.printSomeDaysLembretes(ZonedDateTime.now());
    }

    private void addLembrete(Lembrete lembrete) {
        int position = 0;
        while (lembretes[position] != null) {
            position++;
        }
        lembretes[position] = lembrete;
    }
    private void addLembreteSemana(LembreteSemana lembrete) {
        int position = 0;
        while(lembretesSemanas[position] != null) {
            position++;
        }
        lembretesSemanas[position] = lembrete;
    }
    private void addLembreteMensal(LembreteMensal lembrete) {
        int position = 0;
        while(lembretesMensais[position] != null) {
            position++;
        }
        lembretesMensais[position] = lembrete;
    }
    private void addEvento(Evento lembrete) {
        int position = 0;
        while(eventos[position] != null) {
            position++;
        }
        eventos[position] = lembrete;        
    }
    private void addReuniao(Reuniao lembrete) {
        int position = 0;
        while(reunioes[position] != null) {
            position++;
        }
        reunioes[position] = lembrete;
               
    }
    private void removeLembrete(Lembrete lembrete) {
        int position = 0;
        while(lembretes[position] != lembrete) {
            position++;
        }
        lembretes[position] = null;
    }
    private void removeLembreteSemana(LembreteSemana lembrete) {
        int position = 0;
        while(lembretesSemanas[position] != lembrete) {
            position++;
        }
        lembretesSemanas[position] = null;
    }
    private void removeLembreteMensal(LembreteMensal lembrete) {
        int position = 0;
        while(lembretesMensais[position] != lembrete) {
            position++;
        }
        lembretesMensais[position] = null;
    }
    private void removeEvento(Evento lembrete) {
        int position = 0;
        while(eventos[position] != lembrete) {
            position++;
        }        
        eventos[position] = null;
    }
    private void removeReuniao(Reuniao lembrete) {
        int position = 0;
        while(reunioes[position] != lembrete) {
            position++;
        }
        reunioes[position] = null;        
    }
    private void printSomeDaysLembretes(ZonedDateTime date) {
        int position = 0;
        while(lembretes[position] != null) {
            lembretes[position].imprimirLembrete();
            position++;
        }
        position = 0;
        while(lembretesMensais[position] != null) {
            if(lembretesMensais[position].getMonth() == date.getMonth()) {
                lembretesMensais[position].imprimirLembrete();
            }
            position++;
        }
        position = 0;
        if(lembretesSemanas[0] != null){
            for(int i = 0; i < lembretesSemanas[position].getNumberOfWeekDays(); i++) {
                if(lembretesSemanas[position] != null){
                    if(lembretesSemanas[position].getWeekDays()[i] == Calendar.DAY_OF_WEEK) {
                        lembretesSemanas[position].imprimirLembrete();
                    }
                    position++;
                }
            }
        }
        position = 0;
        while (eventos[position] != null) {
            if(eventos[position].getDate().equals(date)) {
                eventos[position].imprimirLembrete();
            }
            position++;
        }
        position = 0;
        while (reunioes[position] != null) {
            if(reunioes[position].getDate().equals(date)) {
                reunioes[position].imprimirLembrete();
            }
            position++;
        }
    }
    private void printBetweenDatesLembretes(ZonedDateTime date1, ZonedDateTime date2) {
        int dia1 = date1.getDayOfYear();
        int dia2 = date2.getDayOfYear();
        ZonedDateTime date = date1;
        for (int i = dia1; i <= dia2; i++) {
            printSomeDaysLembretes(date);
            date = date.plusDays(1);            
        }
    }
}
