package model;

public class Vertice {
    private Cidade cidade;

    public Vertice(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String toString() {
        return "{ cidade=" + this.cidade.getNome() + " }";
    }
}

