package modelo;

import java.util.*;

public class OL
{
	// atributos
	private Long id;
	private Material material;
	private Set lotes;

	public OL()
	{
	}

	public OL(Material material, Set lotes)
	{
		id = (long) 0;
		this.material = material;
		this.lotes = lotes;
	}

	// métodos get/set
	public Set getLotes()
	{
		return lotes;
	}

	public void setLotes(Set lotes)
	{
		this.lotes = lotes;
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

	public Lote getLote(Short tempo)
	{
		for (Object loteObj : lotes)
		{
			Lote lote = (Lote) loteObj;
			if (lote.getTempo().equals(tempo)) return lote;
		}

		return new Lote(tempo, 0);
	}

}
