package modelo;

public class Material
{
	// atributos
	private String nome;
	private String descricao;
	private String observacoes;
	private Long id;

	public Material()
	{
	}

	public Material(String nome, String descricao, String observacoes)
	{
		id = (long) 0;
		this.nome = nome;
		this.descricao = descricao;
		this.observacoes = observacoes;
	}

	// métodos get/set
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getObservacoes()
	{
		return observacoes;
	}

	public void setObservacoes(String observacoes)
	{
		this.observacoes = observacoes;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	// retirar
	public String toString()
	{
		return nome;
	}
}