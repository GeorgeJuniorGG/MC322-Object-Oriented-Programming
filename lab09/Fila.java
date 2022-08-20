package lab9;

public class Fila {
    protected Lista lista;

    public Fila() {
        this.lista = new Lista();
    }

    void add(Pessoa pessoa) {
        lista.addFim(pessoa);
    }

    void remover() {
        lista.removerInicio();
    }

    int getTamanho() {
        return lista.getTamanho();
    }

    void printFila() {
        lista.printLista();
    }
    
}