package grafo;

public class Dijkstra
{
	public static int[] dijkstra(Grafo grafo, int nodoIni)
	{
		nodoIni--;
		int cantNodos = grafo.getCantNodos(); // cantidad de nodos
		int[] distancia = new int[cantNodos];
		boolean[] visitado = new boolean[cantNodos];
		for (int i = 0; i < cantNodos; i++)
		{
			distancia[i] = grafo.getPeso(nodoIni, i);
			visitado[i] = false;
		}
		visitado[nodoIni] = true;
		int porVisit = cantNodos - 1; // reutilizo la variable para indicar cuantos dodos faltan por visitar

		while (porVisit > 0)
		{
			int nodoMenor = MenorDistancia(distancia, visitado);
			// System.out.println(nodoMenor);
			visitado[nodoMenor] = true;
			for (int i = 0; i < cantNodos; i++)
			{
				if (!visitado[i] && ((distancia[nodoMenor] + grafo.matriz[nodoMenor][i]) < distancia[i]))
				{
					distancia[i] = distancia[nodoMenor] + grafo.matriz[nodoMenor][i];

				}
			}
			porVisit--;
			/*for (int j = 0; j < grafo.getCantNodos(); j++)
				System.out.print(distancia[j] + " ");
			System.out.println();
			*/
		}

		return distancia;

	}

	private static int MenorDistancia(int[] distancia, boolean[] visitado)
	{
		int menor = 0, j = 0;
		boolean noEncontrado = true;
		while (noEncontrado) // encuentra al primer menor
		{
			if (!visitado[j])
			{
				menor = j;
				noEncontrado = false;
			}
			j++;
		}
		for (int i = 0; i < distancia.length; i++)// encuentra al menor de todos
		{
			if (!visitado[i] && (distancia[i] < distancia[menor]))
				menor = i;
		}
		return menor;
	}

}
