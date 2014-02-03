package modelo;

public class Lote implements Comparable
{
	// atributos
	private Long id;
	private Short tempo;
	private Integer quantidade;

	public Lote()
	{
	}

	// métodos get/set
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Integer getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(Integer quantidade)
	{
		this.quantidade = quantidade;
	}

	public Short getTempo()
	{
		return tempo;
	}

	public void setTempo(Short tempo)
	{
		this.tempo = tempo;
	}

	public Lote(Short tempo, Integer quantidade)
	{
		id = (long) 0;
		this.tempo = tempo;
		this.quantidade = quantidade;
	}

	public void addQuantidade(Integer quantidade)
	{
		this.quantidade += quantidade;
	}

	public int compareTo(Object arg0)
	{
		return tempo - ((Lote)arg0).getTempo();
	}

}
