package lab05;

import java.time.ZonedDateTime;

public class Reuniao extends Evento {
    Pessoa[] people;
    int numberOfPeople;

    public Reuniao(String description, ZonedDateTime date, Pessoa[] people, int numberOfPeople) {
        super(description, date);
        this.people = people;
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void imprimirLembrete() {
        super.imprimirLembrete();
        System.out.println("Com as seguntes pessoas: ");
        for (int i = 0; i < numberOfPeople - 1; i++) {
            if (people[i].getStatus())
                System.out.print(people[i].getName() + ", ");
        }
        if(people[numberOfPeople - 1].getStatus())
            System.out.println(people[numberOfPeople - 1].getName() + ".");
    }
}