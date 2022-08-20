package lab05;

import java.time.ZonedDateTime;

public class Evento extends Lembrete {
    protected ZonedDateTime date;

    public Evento(String description, ZonedDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public void imprimirLembrete() {
        super.imprimirLembrete();;
        System.out.println("Para o dia " + date + ".");
    }

    public ZonedDateTime getDate() {
        return date;
    }
}