package grafo;

public class Floyd {

	public static int [][] floyd(Grafo grafo)
	{
		int tam = grafo.matriz.length;
		int[][] matrizActual = grafo.matriz.clone();
		int[][] matrizPrevia = grafo.matriz.clone();
		int k = 0;

		while (k < tam) {
			for (int i = 0; i < tam; i++) {
				for (int j = 0; j < tam; j++) {
					// me aseguro de no modificar la columna y fila que coincide
					// con k
					if (i != k && j != k && i != j && matrizPrevia[i][k] + matrizPrevia[k][j] < matrizActual[i][j])
						matrizActual[i][j] = matrizPrevia[i][k] + matrizPrevia[k][j];
				}
			}

			matrizPrevia = matrizActual.clone();
			k++;
		}
		return matrizActual;
	}

}
