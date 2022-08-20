package lab9;

public class FilaAtendimentoA {
    private Fila filaComum;
    private Fila filaPrioritaria;

    public FilaAtendimentoA() {
        filaComum = new Fila();
        filaPrioritaria = new Fila();
    }

    void add(Pessoa pessoa) {
        filaComum.add(pessoa);
    }

    void addPrioritario(Pessoa pessoa) {
        filaPrioritaria.add(pessoa);
    }

    void remover() {
        if(filaPrioritaria.getTamanho() == 0) {
            filaComum.remover();
        }
        else {
            filaPrioritaria.remover();
        }
    }

    int getTamanho() {
        int tamanho = filaComum.getTamanho() + filaPrioritaria.getTamanho();
        return tamanho;
    }

    void printFilaAtendimento() {
        System.out.println("Pessoas na fila prioritaria: ");
        filaPrioritaria.printFila();
        System.out.println("Pessoas na fila comum: ");
        filaComum.printFila();
    }
    
}