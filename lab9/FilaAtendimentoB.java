package lab9;

public class FilaAtendimentoB extends Fila{
    private Fila filaPrioritaria;

    public FilaAtendimentoB() {
        super();
        this.filaPrioritaria = new Fila(); //elemento dummy que separa prioritarios de nao prioritarios
    }

    void addPrioritario(Pessoa pessoa) {
        filaPrioritaria.add(pessoa);
    }
    
    @Override
    void remover() {
        if(filaPrioritaria.getTamanho() == 0) {
            super.remover();
        }
        else {
            filaPrioritaria.remover();
        }
    }

    @Override
    int getTamanho() {
        int tamanho = super.getTamanho() + filaPrioritaria.getTamanho();
        return tamanho;
    }

    @Override
    void printFila() {
        System.out.println("Pessoas na fila prioritaria: ");
        filaPrioritaria.printFila();
        System.out.println("Pessoas na fila comum: ");
        super.printFila();
    }
}