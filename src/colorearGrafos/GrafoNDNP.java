package colorearGrafos;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class GrafoNDNP {
	private MatrizSimetrica matriz;
	private int[] grados;
	private int grMax;
	private int grMin;
	private int cantNodos;
	private int cantColores;
	private int cantAristas;
	private double adyacencia;
	private int[] listaColores;
	private ArrayList<Nodo> listaNodos = new ArrayList<Nodo>();
	private int[] frecuencia;
	private int mejorRepeticion;
	private int[] mejorColoreado;

	public GrafoNDNP(String path) throws FileNotFoundException {
		this.matriz = new MatrizSimetrica(path);

		this.cantNodos = matriz.getCantNodos();
		this.cantAristas = matriz.getCantAristas();
		this.adyacencia = matriz.getPorcenAdy();
		this.grMax = matriz.getGradoMax();
		this.grMin = matriz.getGradoMin();
		this.grados = matriz.getGrados();
		this.mejorColoreado = new int[cantNodos];
		this.listaColores = new int[cantNodos];
		this.frecuencia = new int[cantNodos];
		this.cantColores = 1;

		for (int i = 0; i < cantNodos; i++) {
			listaNodos.add(new Nodo(i));
			listaNodos.get(i).setGrado(grados[i]);
		}
	}

	public void mezclar() {
		Collections.shuffle(listaNodos);
	}

	private void ordenar(boolean creciente) {
		if (creciente) {
			Collections.sort(listaNodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n1.getGrado() - n2.getGrado();
				}
			});
		} else {
			Collections.sort(listaNodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n2.getGrado() - n1.getGrado();
				}
			});
		}
	}

	public void colorear() {
		this.cantColores = 1;
		int colorBase = 1;
		int color = colorBase;
		int noColoreado = -1;
		int j;
		for (int i = 0; i < listaColores.length; i++) {
			listaColores[i] = noColoreado;
		}
		// El primero lo pinto por defecto
		listaColores[listaNodos.get(0).getIdNodo()] = color;
		// Ahora empiezo por el que sigue y voy preguntando
		for (int i = 1; i < cantNodos; i++) {
			listaColores[listaNodos.get(i).getIdNodo()] = color;
			j = 0;
			while (j < cantNodos) {
				if (matriz.getValor(listaNodos.get(i).getIdNodo(), j)
						&& listaColores[listaNodos.get(i).getIdNodo()] == listaColores[j]) {
					color++;
					if (color > cantColores) {
						cantColores = color;
					}
					listaColores[listaNodos.get(i).getIdNodo()] = color;
					j = -1;
				}
				j++;
			}
			color = colorBase;
		}
	}

	// a lo guido kaczka
	public void colorearS(int repes) {
		int menorCantColores = 0;
		for (int i = 0; i < repes; i++) {
			mezclar();
			colorear();

			if (menorCantColores == 0 || menorCantColores > cantColores) {
				mejorColoreado = listaColores.clone();
				menorCantColores = cantColores;
				mejorRepeticion = i;
			}

			frecuencia[cantColores - 1]++;
		}

		listaColores = mejorColoreado.clone();
		cantColores = menorCantColores;
	}

	public void colorearM(int repes) {
		int menorCantColores = 0;
		for (int i = 0; i < repes; i++) {
			ordenar(true);
			colorear();

			if (menorCantColores == 0 || menorCantColores > cantColores) {
				mejorColoreado = listaColores.clone();
				menorCantColores = cantColores;
				mejorRepeticion = i;
			}

			frecuencia[cantColores - 1]++;
		}

		listaColores = mejorColoreado.clone();
		cantColores = menorCantColores;
	}

	public void colorearWP(int repes) {
		int menorCantColores = 0;
		for (int i = 0; i < repes; i++) {
			ordenar(false);
			colorear();

			if (menorCantColores == 0 || menorCantColores > cantColores) {
				mejorColoreado = listaColores.clone();
				menorCantColores = cantColores;
				mejorRepeticion = i;
			}

			frecuencia[cantColores - 1]++;
		}

		listaColores = mejorColoreado.clone();
		cantColores = menorCantColores;
	}

	// guarda el archivo out
	public void imprimir() throws IOException {
		int ady = (int) adyacencia;
		String path = new String("Coloreado" + cantNodos + "-" + ady + "Ady.out");
		PrintWriter pr = new PrintWriter(new FileWriter(path));
		pr.println(cantNodos + " " + cantColores + " " + cantAristas + " " + adyacencia + " " + grMax + " " + grMin);
		for (int i = 0; i < cantNodos; i++) {
			pr.println(i + " " + listaColores[i]);
		}
		pr.close();
	}

	public void imprimirFreq(String secuencia) {
		int ady = (int) adyacencia;
		String path = new String(secuencia + cantNodos + "-" + ady + ".txt");

		try {
			PrintWriter pr = new PrintWriter(new FileWriter(path));
			for (int i = 0; i < frecuencia.length; i++) {
				pr.println(i + 1 + "," + frecuencia[i]);
			}
			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
