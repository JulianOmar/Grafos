package grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Grafo
{

	public int[][] matriz;
	protected static int MAXX = 10000;

	public Grafo(int[][] mm)
	{
		this.matriz = mm;
		for (int i = 0; i < matriz.length; i++)
			for (int j = 0; j < matriz.length; j++)
				if (matriz[i][j] == 0)
					matriz[i][j] = MAXX;
	}

	public void Grafo(File archivo) throws FileNotFoundException
	{
		Scanner sc = new Scanner(archivo);
		int orden = sc.nextInt(); // cantidad de nodos
		int cantAristas = sc.nextInt();
		int i;
		for (i = 0; i < orden; i++)
			for (int j = 0; j < orden; j++)
				matriz[i][j] = MAXX;

		for (i = 0; i < orden; i++)
		{
			int origen = sc.nextInt();
			int costo = sc.nextInt();
			int destino = sc.nextInt();
			matriz[origen - 1][destino - 1] = costo;
		}
		sc.close();
	}

	public int getCantNodos()
	{
		return matriz.length;
	}

	public int getPeso(int inicio, int destino)
	{
		return matriz[inicio][destino];
	}

	public void imprimir()
	{
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz.length; j++)
			{
				if (matriz[i][j] == MAXX)
					System.out.print("- ");
				else
					System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
	}

}