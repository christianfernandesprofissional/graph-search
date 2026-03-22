package model;

public class Vertice {
    private Cidade cidade;
    private Integer aresta = 0;

    public Vertice(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Integer getAresta() {
        return this.aresta;
    }

    public void setAresta(Integer aresta) {
        this.aresta = aresta;
    }

    public String toString() {
        return "{ cidade=" + this.cidade.getNome() + " }";
    }
}

