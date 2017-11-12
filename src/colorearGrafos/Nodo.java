package colorearGrafos;

public class Nodo
{

	private final int idNodo;
	private int grado = 0;

	public Nodo(int idNodo)
	{
		this.idNodo = idNodo;
	}

	public void aumentarGrado(int idNodo)
	{
		this.grado++;
	}

	public void setGrado(int grado)
	{
		this.grado = grado;
	}

	public int getGrado()
	{
		return grado;
	}

	public int getIdNodo()
	{
		return idNodo;
	}
}
