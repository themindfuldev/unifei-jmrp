package modelo;

import java.util.*;

public class NoBOM
{
	// atributos
	private Long id;
	private Short quantidade;
	private Material material;
	private Set filhos;

	public NoBOM()
	{
	}

	public NoBOM(Material material, Short quantidade, Set filhos)
	{
		id = (long) 0;
		this.quantidade = quantidade;
		this.material = material;
		this.filhos = filhos;
	}

	// métodos get/set
	public Set getFilhos()
	{
		return filhos;
	}

	public void setFilhos(Set filhos)
	{
		this.filhos = filhos;
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

	public Short getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(Short quantidade)
	{
		this.quantidade = quantidade;
	}
}
