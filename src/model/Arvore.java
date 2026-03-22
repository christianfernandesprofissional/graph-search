package model;

import java.util.ArrayList;
import java.util.List;

public class Arvore {
    private Vertice valor;
    private Arvore pai;
    private int pesoAresta;
    private List<Arvore> filhos = new ArrayList();

    public Arvore(Vertice valor) {
        this.valor = valor;
    }

    public Arvore(Vertice valor, int pesoAresta) {
        this.valor = valor;
        this.pesoAresta = pesoAresta;
    }

    public void addFilho(Arvore filho) {
        filho.pai = this;
        this.filhos.add(filho);
    }

    public Arvore getPai() {
        return this.pai;
    }

    public void setPai(Arvore pai) {
        this.pai = pai;
    }

    public Vertice getValor() {
        return this.valor;
    }

    public int getPesoAresta() {
        return this.pesoAresta;
    }

    public boolean hasFather() {
        return this.pai != null;
    }

    public void print() {
        this.print("", true);
    }

    private void print(String prefixo, boolean ultimo) {
        if (this.pai == null) {
            System.out.println(this.valor.getCidade().getNome());
        } else {
            System.out.println(prefixo + (ultimo ? "└── " : "├── ") + this.valor.getCidade().getNome() + " (" + this.pesoAresta + ")");
        }

        for(int i = 0; i < this.filhos.size(); ++i) {
            ((Arvore)this.filhos.get(i)).print(prefixo + (this.pai == null ? "" : (ultimo ? "    " : "│   ")), i == this.filhos.size() - 1);
        }

    }
}

