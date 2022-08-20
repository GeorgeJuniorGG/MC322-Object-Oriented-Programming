package lab05;

import java.time.Month;

public class LembreteMensal extends Lembrete {
    private Month month;

    public LembreteMensal (String description, Month month) {
        super(description);
        this.month = month;
    }

    @Override
    public void imprimirLembrete() {
        super.imprimirLembrete();
        System.out.println("Para o mes de " + month + ".");
    }

    public Month getMonth() {
        return month;
    }
}