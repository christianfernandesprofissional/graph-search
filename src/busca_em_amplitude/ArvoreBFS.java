package busca_em_amplitude;

import model.Cor;
import model.Vertice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArvoreBFS {
    private Vertice valor;
    private ArvoreBFS pai;
    private int pesoAresta;
    private final List<ArvoreBFS> filhos = new ArrayList();
    private Cor cor = Cor.BRANCO;

    public ArvoreBFS(Vertice valor) {
        this.valor = valor;
    }

    public ArvoreBFS(Vertice valor, int pesoAresta) {
        this.valor = valor;
        this.pesoAresta = pesoAresta;
    }

    public void addFilho(ArvoreBFS filho) {
        filho.pai = this;
        this.filhos.add(filho);
    }

    public ArvoreBFS getPai() {
        return this.pai;
    }

    public void setPai(ArvoreBFS pai) {
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

    public List<ArvoreBFS> getFilhos() {
        return filhos;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public void print() {
        System.out.println(valor.getCidade().getNome());

        for (int i = 0; i < filhos.size(); i++) {
            filhos.get(i).print("", i == filhos.size() - 1);
        }
    }

    private void print(String prefixo, boolean ultimo) {
        System.out.println(prefixo
                + (ultimo ? "└── " : "├── ")
                + valor.getCidade().getNome()
                + " (" + pesoAresta + ")"
        );

        for (int i = 0; i < filhos.size(); i++) {
            filhos.get(i).print(
                    prefixo + (ultimo ? "    " : "│   "),
                    i == filhos.size() - 1
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArvoreBFS arvoreBFS = (ArvoreBFS) o;
        return Objects.equals(valor.getCidade().getNome(), arvoreBFS.valor.getCidade().getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(valor.getCidade().getNome());
    }
}

