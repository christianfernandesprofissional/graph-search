package busca_em_profundidade;

import model.Cor;
import model.Vertice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArvoreDFS {
    private final Vertice valor;
    private ArvoreDFS pai;
    private int pesoAresta;
    private Cor cor;
    private final List<ArvoreDFS> filhos = new ArrayList<>();
    private final List<Integer> ordemDePassagem =  new ArrayList<>();

    public ArvoreDFS(Vertice valor) {
        this.valor = valor;
    }

    public ArvoreDFS(Vertice valor, int pesoAresta) {
        this.valor = valor;
        this.pesoAresta = pesoAresta;
    }

    public void addFilho(ArvoreDFS filho) {
        filho.pai = this;
        this.filhos.add(filho);
    }

    public ArvoreDFS getPai() {
        return this.pai;
    }

    public void setPai(ArvoreDFS pai) {
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

    public List<ArvoreDFS> getFilhos() {
        return filhos;
    }

    public List<Integer> getOrdemDePassagem() {
        return ordemDePassagem;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public void print() {
        System.out.println(
                valor.getCidade().getNome()
                        + " [ordem: " + formatOrdem() + "]"
        );

        for (int i = 0; i < filhos.size(); i++) {
            filhos.get(i).print("", i == filhos.size() - 1);
        }
    }

    private void print(String prefixo, boolean ultimo) {
        System.out.println(prefixo
                + (ultimo ? "└── " : "├── ")
                + valor.getCidade().getNome()
                + " (" + pesoAresta + ")"
                + " [ordem: " + formatOrdem() + "]"
        );

        for (int i = 0; i < filhos.size(); i++) {
            filhos.get(i).print(
                    prefixo + (ultimo ? "    " : "│   "),
                    i == filhos.size() - 1
            );
        }
    }

    private String formatOrdem() {
        if (ordemDePassagem.isEmpty()) {
            return "-";
        }
        if (ordemDePassagem.size() == 1) {
            return String.valueOf(ordemDePassagem.get(0));
        }
        return ordemDePassagem.get(0) + ", " + ordemDePassagem.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArvoreDFS arvoreBFS = (ArvoreDFS) o;
        return Objects.equals(valor.getCidade().getNome(), arvoreBFS.valor.getCidade().getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(valor.getCidade().getNome());
    }
}

