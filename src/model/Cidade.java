package model;

import java.util.HashMap;
import java.util.Objects;

public class Cidade {
    private String nome;
    private HashMap<Vertice, Integer> arestas;

    public Cidade(String nome, HashMap<Vertice, Integer> vertices, Cor cor) {
        this.nome = nome;
        this.arestas = vertices;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<Vertice, Integer> getArestas() {
        return this.arestas;
    }

    public void setArestas(HashMap<Vertice, Integer> arestas) {
        this.arestas = arestas;
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

