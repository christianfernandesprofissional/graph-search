import busca_em_amplitude.BFS;
import busca_em_profundidade.DFS;
import model.Cidade;
import model.Cor;
import model.SearchMethod;
import model.Vertice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = args.length > 0 ? args[0] : "src/graph/graph.csv";
        Vertice[] graph = construirGrafo(path, 15);
        int sair = 0;
        while(sair != 2) {

            System.out.println("Selecione o número da cidade de origem, e um destino:\n");
            for(int i = 0; i < graph.length; ++i) {
                System.out.println(i + 1 + ". " + graph[i].getCidade().getNome());
            }

            System.out.print("\nOrigem:");
            int origem = sc.nextInt();
            System.out.print("Destino: ");
            int destino = sc.nextInt();
            sc.nextLine();

            if(origem > 15 || origem < 1 || destino > 15 || destino < 1 ){
                System.out.println("\nCidade inválida! Tente novamente! ");
                continue;
            }

            System.out.println("Escolha um metodo de busca:");
            System.out.println("1.BFS");
            System.out.println("2.DFS");
            SearchMethod buscar = null;

            while(buscar == null) {
                int metodo = sc.nextInt();
                sc.nextLine();
                if(metodo == 1) {
                    buscar = new BFS(graph);
                }else if(metodo == 2){
                    buscar = new DFS(graph);
                }else{
                    System.out.println("Metodo de busca inválido");
                }
            }
            buscar.buildTree(graph[origem - 1].getCidade().getNome(), graph[destino - 1].getCidade().getNome());
            sc.nextLine();

            System.out.println("Deseja executar novamente?");
            System.out.println("1.SIM");
            System.out.println("2.NÃO");
            sair = sc.nextInt();
        }
    }



    public static Vertice[] construirGrafo(String caminho, Integer qtVertices) {
        Vertice[] graph = new Vertice[qtVertices];

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String line = br.readLine();
            String[] newLine = line.split(";");

            for(int i = 1; i < newLine.length; ++i) {
                graph[i - 1] = new Vertice(new Cidade(newLine[i], new HashMap<>(), Cor.BRANCO));
            }

            line = br.readLine();

            for(int vertice = 0; line != null; line = br.readLine()) {
                newLine = line.split(";");

                for(int aresta = 1; aresta <= newLine.length - 1; ++aresta) {
                    if (Integer.parseInt(newLine[aresta]) != -1) {
                        graph[vertice].getCidade().getArestas().put(graph[aresta - 1], Integer.parseInt(newLine[aresta]));
                    }
                }

                ++vertice;
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return graph;
    }
}