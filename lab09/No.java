package lab9;

public class No {
    private Pessoa pessoa;
    private No proximo;
    
    public No(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.proximo = null;
    }

    No getProximo() {
        return proximo;
    }

    void setProximo(No no) {
        this.proximo = no;
    }

    Pessoa getPessoa() {
        return pessoa;
    }
}