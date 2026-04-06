package busca_em_amplitude;

import model.Cor;
import model.NotFoundException;
import model.SearchMethod;
import model.Vertice;

import java.util.*;

public class BFS implements SearchMethod {
    private final Vertice[] graph;
    private final List<ArvoreBFS> caminho = new ArrayList<>();;
    private final List<ArvoreBFS>  fila = new ArrayList<>();

    public BFS(Vertice[] graph) {
        this.graph = graph;
    }

    @Override
    public void buildTree(String cidadeOrigem, String cidadeDestino) {
        Vertice origem = findVertice(cidadeOrigem);
        ArvoreBFS raiz = new ArvoreBFS(origem, 0);
        raiz.setCor(Cor.CINZA);
        fila.add(raiz);
        int i = 0;
        while(!isAllBlack()){
            ArvoreBFS pai = fila.get(i);
            if(!pai.getCor().equals(Cor.PRETO)){
                List<Vertice> filhos = pai.getValor().getCidade().getArestas().entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();
                for(Vertice v: filhos){
                    ArvoreBFS filho = new ArvoreBFS(v, pai.getValor().getCidade().getArestas().get(v));
                    if(!fila.contains(filho) ){
                        filho.setCor(Cor.CINZA);
                        filho.setPai(pai);
                        pai.getFilhos().add(filho);
                        fila.add(filho);

                        if(filho.getValor().getCidade().getNome().equals(cidadeDestino)){
                            caminho.add(filho);
                        }
                    }
                }
            }
            i++;
            pai.setCor(Cor.PRETO);
        }

        fila.getFirst().print();

        printCaminho();
        System.out.print("Q: {");
        fila.forEach(a -> System.out.print(a.getValor().getCidade().getNome() + ", "));
        System.out.print("}\n");

    }

    private void printCaminho() {
        for(int j = 0; j<caminho.size(); j++){
            if(caminho.get(j).getPai() != null){
                ArvoreBFS p = caminho.get(j).getPai();
                caminho.add(p);
            }
        }
        Collections.reverse(caminho);
        System.out.print("\n Caminho -> |");
        caminho.forEach((a) -> System.out.print(a.getValor().getCidade().getNome() + " |"));
        int distancia = caminho.stream().mapToInt(ArvoreBFS::getPesoAresta).sum();
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

    private boolean isAllBlack(){
        return fila.stream().allMatch(a -> a.getCor().equals(Cor.PRETO));
    }
}

