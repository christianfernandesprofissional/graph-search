package busca_em_amplitude;

import model.Arvore;
import model.Cor;
import model.NotFoundException;
import model.Vertice;

import java.util.*;

public class BFS {
    private final Vertice[] graph;
    private final List<Vertice> tree;
    private final Map<Vertice, Arvore> nos;

    public BFS(Vertice[] graph) {
        this.graph = graph;
        this.tree = new ArrayList();
        this.nos = new HashMap();
    }

    public void buildTree(String cidadeOrigem, String cidadeDestino) {
        List<Arvore> caminho = new ArrayList();
        Vertice origem = this.findVertice(cidadeOrigem);
        this.tree.add(origem);
        Arvore raiz = new Arvore(origem);
        this.nos.put(origem, raiz);
        origem.getCidade().setCor(Cor.CINZA);

        for(int i = 0; i < this.graph.length; ++i) {
            new PriorityQueue();
            Vertice proximo = (Vertice)this.tree.get(i);
            if (!proximo.getCidade().getCor().equals(Cor.PRETO)) {
                List<Vertice> filhos = proximo.getCidade().getVertices().entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();
                filhos.forEach((c) -> c.getCidade().setCor(Cor.CINZA));
                filhos.forEach((v) -> {
                    if (!this.tree.contains(v)) {
                        this.tree.add(v);
                        int peso = (Integer)proximo.getCidade().getVertices().get(v);
                        Arvore filho = new Arvore(v, peso);
                        Arvore pai = (Arvore)this.nos.get(proximo);
                        if (pai != null) {
                            pai.addFilho(filho);
                            filho.setPai(pai);
                        }

                        this.nos.put(v, filho);
                        if (((Arvore)this.nos.get(v)).getValor().getCidade().getNome().equals(cidadeDestino)) {
                            for(Arvore c = (Arvore)this.nos.get(v); c.hasFather(); c = c.getPai()) {
                                caminho.add(c);
                            }

                            caminho.add((Arvore)this.nos.get(origem));
                        }
                    }

                });
                proximo.getCidade().setCor(Cor.PRETO);
            }
        }

        raiz.print();
        Collections.reverse(caminho);
        System.out.print("\n|");
        caminho.forEach((a) -> System.out.print(a.getValor().getCidade().getNome() + " |"));
        int distancia = caminho.stream().mapToInt(Arvore::getPesoAresta).sum();
        System.out.println("Distância total: " + distancia + "Km");
        System.out.println();
    }

    private Vertice findVertice(String cidade) {
        for(Vertice v : this.graph) {
            if (v.getCidade().getNome().equals(cidade)) {
                return v;
            }
        }

        throw new NotFoundException("Cidade não encontrada!");
    }
}

