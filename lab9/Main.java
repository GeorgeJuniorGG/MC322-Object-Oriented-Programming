package lab9;

public class Main {
    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa("Luke Skywalker", 19, "12372309764");
        Pessoa pessoa2 = new Pessoa("Leia Organa", 19, "12372309865");
        Pessoa pessoa3 = new Pessoa("Obi-Wan Kenobi", 56, "18672647398");
        Pessoa pessoa4 = new Pessoa("Anakin Skywalker", 43, "82637216298");
        Pessoa pessoa5 = new Pessoa("Han Solo", 25, "12632746212");
        Pessoa pessoa6 = new Pessoa("Chewbacca", 358, "71635212832");
        Pessoa pessoa7 = new Pessoa("Lando Calrissian", 28, "812645532212");
        Pessoa pessoa8 = new Pessoa("Yoda", 900, "11108700101");
        Pessoa pessoa9 = new Pessoa("C3po", 100, "00012903178");
        Pessoa pessoa10 = new Pessoa("R2-D2", 68, "12638111167");
        Lista lista1 = new Lista();
        lista1.addInicio(pessoa1);
        lista1.addFim(pessoa2);
        lista1.add(pessoa3, 1);
        System.out.println(lista1.getTamanho());
        lista1.printLista();
        lista1.removerFim();
        lista1.remover(1);
        lista1.printLista();
        lista1.addFim(pessoa4);
        lista1.add(pessoa6, 1);
        lista1.removerInicio();
        lista1.addInicio(pessoa5);
        lista1.addFim(pessoa7);
        lista1.printLista();
        lista1.limpar();
        FilaAtendimentoA fila1 = new FilaAtendimentoA();
        fila1.add(pessoa1);
        fila1.addPrioritario(pessoa8);
        fila1.addPrioritario(pessoa9);
        fila1.add(pessoa10);
        fila1.printFilaAtendimento();
        fila1.remover();
        fila1.remover();
        fila1.printFilaAtendimento();
        FilaAtendimentoB fila2 = new FilaAtendimentoB();
        fila2.add(pessoa1);
        fila2.addPrioritario(pessoa8);
        fila2.addPrioritario(pessoa9);
        fila2.add(pessoa10);
        fila2.printFila();
        fila2.remover();
        fila2.remover();
        fila2.printFila();
    }
}