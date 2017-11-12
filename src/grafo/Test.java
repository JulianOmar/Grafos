package grafo;

public class Test
{

	public static void main(String[] args)
	{
		int[][] mat =
		{
				{ 0, 34, 13, 13, 2 },
				{ 0, 0, 0, 0, 13 },
				{ 0, 22, 0, 1, 0 },
				{ 0, 13, 0, 0, 19 },
				{ 0, 0, 6, 0, 0 } };
		Grafo pepito = new Grafo(mat);
		//pepito.imprimir();
		int[] result=  Dijkstra.dijkstra(pepito,1);
		/*for(int i=0; i< pepito.getCantNodos();i++)
			System.out.print(result[i]+" ");*/
		
		int [][] mat2 = {{0,8,5},{0,0,0},{0,2,0}};
		
		Grafo panini = new Grafo(mat2);
		panini.imprimir();
		Grafo res= new Grafo(Floyd.floyd(panini));
		res.imprimir();
	
		
	}

}
