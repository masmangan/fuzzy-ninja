package alpro3.grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppGrafo0 {

	public static void main(String[] argc) {
		Grafo3 grafo = null;

		if (argc.length < 1)
			System.out.println("uso: java Grafo0 <arquivo>");
		else {
			grafo = loadFile(argc[0]);
			grafo.mostra();
			/*
			 * System.out.printf("largura(%d): %s\n", 2, grafo.largura(2));
			 * System.out.println(grafo.getUltimaBusca());
			 * 
			 * 
			 * System.out.printf("largura(%d): %s\n", 3, grafo.largura(3));
			 * System.out.printf("largura(%d): %s\n", 5, grafo.largura(5));
			 */
			System.out.printf("\nprofundidade(%d): %s\n", 1,
					grafo.profundidade(1));
			System.out.println(grafo.getUltimaBusca());

			/*
			 * System.out.printf("profundidade(%d): %s\n", 3,
			 * grafo.profundidade(3));
			 * System.out.printf("profundidade(%d): %s\n", 5,
			 * grafo.profundidade(5));
			 */
		}
	}

	public static Grafo3 loadFile(String filename) {
		Grafo3 g = null;
		String linha;

		try {
			BufferedReader buff = new BufferedReader(new FileReader(filename));
			try {
				linha = buff.readLine();
				g = new Grafo3(Integer.parseInt(linha));
				linha = buff.readLine();
				while (linha != null) {
					String[] dados = linha.split(" ");
					g.addEdge(Integer.parseInt(dados[0]),
							Integer.parseInt(dados[1]));
					linha = buff.readLine();
				}
			} finally {
				buff.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
}