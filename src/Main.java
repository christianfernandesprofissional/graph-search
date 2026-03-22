import busca_em_amplitude.BFS;
import model.Cidade;
import model.Cor;
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
        while(true) {
            init();
        }
    }

    public static void init() {
        Scanner sc = new Scanner(System.in);
        String path = "src/graph/graph.csv";
        Vertice[] graph = construirGrafo(path, 15);
        System.out.println("\nSelecione o número da cidade de origem, e um destino:\n");

        for(int i = 0; i < graph.length; ++i) {
            System.out.println(i + 1 + ". " + graph[i].getCidade().getNome());
        }

        System.out.print("\nOrigem:");
        int origem = sc.nextInt();
        System.out.print("Destino: ");
        int destino = sc.nextInt();
        sc.nextLine();
        BFS buscar = new BFS(graph);
        buscar.buildTree(graph[origem - 1].getCidade().getNome(), graph[destino - 1].getCidade().getNome());
        sc.nextLine();
    }

    public static Vertice[] construirGrafo(String caminho, Integer qtVertices) {
        Vertice[] graph = new Vertice[qtVertices];

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String line = br.readLine();
            String[] newLine = line.split(";");

            for(int i = 1; i < newLine.length; ++i) {
                graph[i - 1] = new Vertice(new Cidade(newLine[i], new HashMap(), Cor.BRANCO));
            }

            line = br.readLine();

            for(int vertice = 0; line != null; line = br.readLine()) {
                newLine = line.split(";");

                for(int aresta = 1; aresta <= newLine.length - 1; ++aresta) {
                    if (Integer.parseInt(newLine[aresta]) != -1) {
                        graph[vertice].getCidade().getVertices().put(graph[aresta - 1], Integer.parseInt(newLine[aresta]));
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