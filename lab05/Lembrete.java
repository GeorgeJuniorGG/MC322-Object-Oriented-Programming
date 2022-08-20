package lab05;

public class Lembrete {
    protected String description;

    public Lembrete(String description) {
        this.description = description;
    }

    public void imprimirLembrete() {
        System.out.println("Detalhes do Lembrete: ");
        System.out.println(description);
    }
}