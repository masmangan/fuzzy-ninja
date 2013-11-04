package alpro3.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author marco.mangan@pucrs.br
 * 
 */
public class Grafo3 {

	private int[][] adj;

	/**
	 * 
	 * @param nodos
	 */
	public Grafo3(int nodos) {
		if (nodos < 1) {
			throw new IllegalArgumentException(
					"O número de nodos deve ser maior do que zero.");
		}
		adj = new int[nodos + 1][nodos + 1];
	}

	/**
	 * 
	 * @param origem
	 * @param destino
	 */
	public void addEdge(int origem, int destino) {
		if (origem < 1 || origem > adj.length) {
			throw new IllegalArgumentException("Origem inválida!");
		}
		if (destino < 1 || destino > adj.length) {
			throw new IllegalArgumentException("Destino inválido!");
		}
		adj[origem][destino] = 1;

	}

	/**
	 * 
	 */
	public void mostra() {
		for (int i = 1; i < adj.length; i++) {
			for (int j = 1; j < adj[i].length; j++) {
				System.out.printf("%d ", adj[i][j]);
			}
			System.out.println();
		}
	}

	private static int WHITE = 10;
	private static int GRAY = 20;
	private static int BLACK = 30;

	private static int INFINITY = -1;
	private static int NIL = -1;

	int color[];
	int d[];
	int f[];
	int p[];
	List<Integer> caminho;

	public String getUltimaBusca() {
		return Arrays.toString(color) + "\n" + Arrays.toString(d) + "\n"
				+ Arrays.toString(p);
	}

	public List<Integer> largura(int s) {

		reset(s);

		color[s] = GRAY;
		caminho.add(s);
		d[s] = 0;
		p[s] = NIL;

		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(s);
		while (!Q.isEmpty()) {
			int u = Q.peek();
			for (Integer v : getAdjacentes(u)) {
				if (color[v] == WHITE) {
					color[v] = GRAY;
					caminho.add(v);
					d[v] = d[u] + 1;
					p[v] = u;
					Q.add(v);
				}
			}
			Q.poll();
			color[u] = BLACK;
		}
		return caminho;
	}

	private void reset(int s) {
		color = new int[adj.length];
		d = new int[adj.length];
		f = new int[adj.length];
		p = new int[adj.length];
		caminho = new ArrayList<Integer>();

		for (int u = 1; u < adj.length; u++) {
			if (u != s) {
				color[u] = WHITE;
				d[u] = INFINITY;
				f[u] = INFINITY;
				p[u] = NIL;
			}
		}
	}

	private List<Integer> getAdjacentes(int u) {
		List<Integer> ads = new ArrayList<Integer>();

		for (int v = 0; v < adj[u].length; v++) {
			if (adj[u][v] != 0) {
				ads.add(v);
			}
		}

		return ads;
	}

	private List<Integer> getIncidentes(int u) {
		List<Integer> inc = new ArrayList<Integer>();

		for (int v = 0; v < adj[u].length; v++) {
			if (adj[v][u] != 0) {
				inc.add(v);
			}
		}

		return inc;

	}

	private int time;

	/**
	 * Realiza percurso em profundidade a partir do nodo <code>u</code>.
	 * 
	 * Adaptação do código do livro Cormen et alli. Nesta implementação, o
	 * percurso é realizado apenas a partir do nodo <code>u</code>. No livro, o
	 * percurso é realizado a partir de cada nodo do grafo (linhas 5 e 6 do DFS
	 * foram removidas).
	 * 
	 * @param u
	 * @return lista
	 */
	public List<Integer> profundidade(int u) {
		reset(u);
		time = 0;
		dfsVisit(u);
		return caminho;
	}

	/**
	 * 
	 * @param u
	 * @return
	 */
	private void dfsVisit(int u) {
		color[u] = GRAY;
		caminho.add(u);
		time++;
		d[u] = time;
		for (Integer v : getAdjacentes(u)) {
			if (color[v] == WHITE) {
				p[v] = u;
				dfsVisit(v);
			}
		}
		color[u] = BLACK;
		time++;
		f[u] = time;
	}

	public boolean isFonte(int n) {
		return getGrauEntrada(n) == 0 && getGrauSaida(n) > 0;
	}

	public boolean isSumidouro(int n) {
		return getGrauSaida(n) == 0 && getGrauEntrada(n) > 0;
	}

	public int getGrauSaida(int n) {
		return getAdjacentes(n).size();
	}

	public int getGrauEntrada(int n) {
		return getIncidentes(n).size();
	}

	public List<Integer> getFontes() {
		List<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < adj.length; i++) {
			if (isFonte(i)) {
				r.add(i);
			}
		}
		return r;
	}

	public List<Integer> getSumidouros() {
		List<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < adj.length; i++) {
			if (isSumidouro(i)) {
				r.add(i);
			}
		}
		return r;
	}

}
