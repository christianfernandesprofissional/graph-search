package model;

import java.util.HashMap;
import java.util.Objects;

public class Cidade {
    private String nome;
    private HashMap<Vertice, Integer> vertices;
    private Cor cor;

    public Cidade(String nome, HashMap<Vertice, Integer> vertices, Cor cor) {
        this.nome = nome;
        this.vertices = vertices;
        this.cor = cor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<Vertice, Integer> getVertices() {
        return this.vertices;
    }

    public void setVertices(HashMap<Vertice, Integer> vertices) {
        this.vertices = vertices;
    }

    public Cor getCor() {
        return this.cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            Cidade cidade = (Cidade)o;
            return Objects.equals(this.nome, cidade.nome);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.nome);
    }
}

