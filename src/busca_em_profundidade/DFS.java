package busca_em_profundidade;

import model.Cor;
import model.NotFoundException;
import model.SearchMethod;
import model.Vertice;

import java.util.*;

public class DFS implements SearchMethod {
    private final Vertice[] graph;
    private final List<ArvoreDFS> caminho;
    private final List<ArvoreDFS>  fila = new ArrayList<>();

    public DFS(Vertice[] graph) {
        this.graph = graph;
        this.caminho = new ArrayList<>();
    }

    @Override
    public void buildTree(String cidadeOrigem, String cidadeDestino) {
        Vertice origem = findVertice(cidadeOrigem);
        ArvoreDFS raiz = new ArvoreDFS(origem, 0);
        fila.add(raiz);
        int tempo = 1, j;
        raiz.getOrdemDePassagem().add(tempo);
        ArvoreDFS pai = fila.getFirst();
        pai.setCor(Cor.CINZA);
        while(fila.getFirst().getOrdemDePassagem().size() < 2){
            List<Vertice> filhos = pai.getValor().getCidade().getArestas().entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();
            ArvoreDFS filho = null;
            for(j = 0; j < filhos.size();j++){
                if(filhos.get(j) != null){
                    filho = new ArvoreDFS(filhos.get(j), pai.getValor().getCidade().getArestas().get(filhos.get(j)));
                    if(!fila.contains(filho)){
                       break;
                    }
                }
            }

            if(j != filhos.size() && filhos.get(j) != null){
                filho.setPai(pai);
                pai.getFilhos().add(filho);
                tempo++;
                filho.getOrdemDePassagem().add(tempo);
                fila.add(filho);
                filho.setCor(Cor.CINZA);
                pai = filho;
                j = 0;
                if(filho.getValor().getCidade().getNome().equals(cidadeDestino)){
                    caminho.add(filho);
                }
            }else{
                tempo++;
                if(pai.getCor().equals(Cor.CINZA)){
                    pai.getOrdemDePassagem().add(tempo);
                    pai.setCor(Cor.PRETO);
                }
                pai = pai.getPai();
            }
        }
        fila.getFirst().print();
        printCaminho();
    }

    private void printCaminho() {
        for(int j = 0; j<caminho.size(); j++){
            if(caminho.get(j).getPai() != null){
                ArvoreDFS p = caminho.get(j).getPai();
                caminho.add(p);
            }
        }
        Collections.reverse(caminho);
        System.out.print("\n Caminho -> |");
        caminho.forEach((a) -> System.out.print(a.getValor().getCidade().getNome() + " |"));
        int distancia = caminho.stream().mapToInt(ArvoreDFS::getPesoAresta).sum();
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

