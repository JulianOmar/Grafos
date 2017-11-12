package grafo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Prim
{
	public static int prim(Grafo grafo, int nodoOrigen) throws Exception
	{
		nodoOrigen--;
		Set<Integer> v = new HashSet<Integer>();
		Queue<Arista> cola = new PriorityQueue<Arista>();
		List<Arista> aristasFinales = new LinkedList<Arista>();
		int pesoTotal = 0;

		// saco el nodo de partida 
		v.add(nodoOrigen);
		// pongo en la cola las aristas
		Arista[] aristas = getAristas(grafo,nodoOrigen);

		for (int i = 0; i < aristas.length; i++)
			cola.add(aristas[i]);

		while (!cola.isEmpty())
		{
			Arista arista = cola.poll();
			int nodoDestino = arista.getNodoDestino();
			// pregunto si el nodo destino de la arista ya fue visitado
			if (!v.contains(nodoDestino))
			{
				aristasFinales.add(arista);
				pesoTotal += arista.getPeso();
				v.add(nodoDestino);
				// agrego aristas adyacentes
				aristas = getAristas(grafo,nodoDestino);

				for (int i = 0; i < aristas.length; i++)
					cola.add(aristas[i]);
			}
		}
		return pesoTotal;
	}

	public static Arista[] getAristas(Grafo graf,int nodo) throws Exception
	{
		int tam = graf.matriz.length;
		if (nodo >= tam)
			throw new Exception("nodo no valido");

		List<Arista> aristas = new LinkedList<Arista>();
		Arista[] res;

		for (int i = 0; i < tam; i++)
		{
			if (graf.matriz[nodo][i] != graf.MAXX)
				aristas.add(new Arista(nodo, graf.matriz[nodo][i], i));
		}

		res = new Arista[aristas.size()];
		res = aristas.toArray(res);

		return res;
	}
}
