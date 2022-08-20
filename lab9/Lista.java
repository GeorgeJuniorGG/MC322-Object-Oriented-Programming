package lab9;

public class Lista {
    private No inicio;

    public Lista() {
        this.inicio = null;
    }

    void addInicio(Pessoa pessoa) {
        No novo = new No(pessoa);
        if(this.inicio == null) {
            this.inicio = novo;
        }
        else {
            novo.setProximo(inicio);
            this.inicio = novo;
        }
    }

    void addFim(Pessoa pessoa) {
        No novo = new No(pessoa);
        if(inicio == null) {
            inicio = novo;
        }
        else {
            No auxiliar = inicio.getProximo();
            No auxiliar2 = inicio;
            while(auxiliar != null) {
                auxiliar = auxiliar.getProximo();
                auxiliar2 = auxiliar2.getProximo();
            }
            auxiliar2.setProximo(novo);

        }
    }

    void add(Pessoa pessoa, int posicao) {
        if(posicao == 0) {
            addInicio(pessoa);
        }
        else {
            No novo = new No(pessoa);
            No auxiliar = inicio;
            No auxiliar2 = inicio.getProximo();
            for(int i = 0; i < posicao - 1; i++) {
                auxiliar = auxiliar.getProximo();
                auxiliar2 = auxiliar2.getProximo();
            }
            auxiliar.setProximo(novo);
            novo.setProximo(auxiliar2);
        }
    }

    void removerInicio() {
        if(inicio != null) {
            inicio = inicio.getProximo();
        }
    }

    void removerFim() {
        if(inicio != null) {
            if(inicio.getProximo() == null) {
                inicio = null;
            }
            else {
                No auxiliar = inicio;
                No auxiliar2 = inicio.getProximo();
                while(auxiliar2.getProximo() != null) {
                    auxiliar = auxiliar.getProximo();
                    auxiliar2 = auxiliar2.getProximo();
                }
                auxiliar.setProximo(null);
            } 
        }
    }

    void remover(int posicao) {
        if (posicao == 0) {
            removerInicio();
        }
        else {
            No auxiliar = inicio;
            No auxiliar2 = inicio.getProximo();
            for(int i = 0; i < posicao - 1; i++) {
                auxiliar = auxiliar.getProximo();
                auxiliar2 = auxiliar2.getProximo();
            }
            if(auxiliar2 != null) {
                auxiliar2 = auxiliar2.getProximo();
                auxiliar.setProximo(auxiliar2);
            }
        }
    }

    int getTamanho() {
        No auxiliar = inicio;
        int contador = 0;
        while(auxiliar != null) {
            contador++;
            auxiliar = auxiliar.getProximo();
        }
        return contador;
    }

    void limpar() {
        inicio = null;
    }

    void printLista() {
        No auxiliar = inicio;
        while(auxiliar != null) {
            System.out.print(auxiliar.getPessoa().getNome() + " ");
            auxiliar = auxiliar.getProximo();
        }
        System.out.println("");
    }
    
}