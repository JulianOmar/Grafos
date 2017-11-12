package colorearGrafos;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class MatrizSimetrica {
	private boolean[] vectorMatriz;
	private int cantNodos;
	private int gradoMin;
	private int gradoMax;
	private double porcenAdy;
	private int cantAristas;
	private int[] grados;

	public MatrizSimetrica(int cantNodos) {
		this.cantNodos = cantNodos;
		vectorMatriz = new boolean[calcularAristas()];
	}

	public MatrizSimetrica(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		sc.useLocale(Locale.ENGLISH);
		this.cantNodos = sc.nextInt();
		grados = new int[cantNodos];
		cantAristas = sc.nextInt();
		int fil;
		int col;
		vectorMatriz = new boolean[calcularAristas()];
		porcenAdy = sc.nextFloat();
		gradoMax = sc.nextInt();
		gradoMin = sc.nextInt();

		int finFor = (int) Math.floor((cantAristas * porcenAdy));

		for (int i = 0; i < finFor; i++) {
			fil = sc.nextInt();
			col = sc.nextInt();
			grados[fil]++;
			grados[col]++;
			vectorMatriz[(int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2)] = true;
		}
		sc.close();
	}

	public int[] getGrados() {
		return grados;
	}

	public int getCantAristas() {
		return cantAristas;
	}

	public int getGradoMin() {
		return gradoMin;
	}

	public int getGradoMax() {
		return gradoMax;
	}

	public double getPorcenAdy() {
		return porcenAdy;
	}

	public int calcularAristas() {
		return (cantNodos * (cantNodos - 1)) / 2;
	}

	public int getCantNodos() {
		return cantNodos;
	}

	public boolean[] getVectorMatriz() {
		return vectorMatriz;
	}

	public boolean getValor(int fil, int col) {
		int aux;
		if (fil == col) {
			return false;
		}
		if (col < fil) {
			aux = fil;
			fil = col;
			col = aux;
		}
		return vectorMatriz[(int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2)];
	}

	public void setValor(int fil, int col) {
		int index;
		int aux;
		if (fil == col) {
			return;
		}
		if (fil > col) {
			aux = fil;
			fil = col;
			col = aux;
		}
		index = (int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2);
		vectorMatriz[index] = true;
	}

	public void outValor(int fil, int col) {
		int index;
		int aux;
		if (fil == col) {
			return;
		}
		if (fil > col) {
			aux = fil;
			fil = col;
			col = aux;
		}
		index = (int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2);
		vectorMatriz[index] = false;
	}

	public int contarTrue() {
		int cant = 0;
		for (boolean b : vectorMatriz) {
			if (b == true) {
				cant++;
			}
		}
		return cant;
	}
}
