package modelo;

public class Estoque {
	// atributos
	private Long id;	 
	private Integer estoqueAtual;	 
	private Integer estoqueAlocado;	 
	private Material material;
	 
	public Estoque() { }
	
	public Estoque(Material material, Integer estoqueAtual, Integer estoqueAlocado)
	{
		if (estoqueAlocado > estoqueAtual)
			throw new IllegalArgumentException("Estoque alocado � maior que o atual");
		id = (long) 0;		
		this.estoqueAlocado = estoqueAlocado;
		this.estoqueAtual = estoqueAtual;
		this.material = material;
	}
	
	// m�todos get/set	
	public Integer getEstoqueDisponivel() {
		return estoqueAtual - estoqueAlocado;
	}

	public Integer getEstoqueAlocado()
	{
		return estoqueAlocado;
	}

	public void setEstoqueAlocado(Integer estoqueAlocado)
	{
		if (estoqueAlocado > estoqueAtual)
			throw new IllegalArgumentException("Estoque alocado � maior que o atual");
		this.estoqueAlocado = estoqueAlocado;
	}

	public Integer getEstoqueAtual()
	{
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual)
	{
		if (estoqueAlocado > estoqueAtual)
			throw new IllegalArgumentException("Estoque atual � menor que o alocado");		
		this.estoqueAtual = estoqueAtual;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Material getMaterial()
	{
		return material;
	}

	public void setMaterial(Material material)
	{
		this.material = material;
	}
	
	public void alocar(Integer quantidade)
	{
		if (quantidade > getEstoqueDisponivel())
			throw new IllegalArgumentException("Quantidade a ser alocada � maior que a poss�vel");
		estoqueAlocado = estoqueAlocado + quantidade;
	}
	
	public void adicionar(Integer quantidade)
	{
		estoqueAtual = estoqueAtual + quantidade;
	}
	 
}
 
