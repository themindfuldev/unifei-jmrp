package modelo;

import java.util.*;

public class AgendaMPS {
 
	// Atributos
	private Long id;	 
	private Material material;	 
	private Set pedidos;
	 
	public AgendaMPS() { }
	
	public AgendaMPS(Material material, Set pedidos)
	{
		id = (long) 0;
		this.material = material;
		this.pedidos = pedidos;
	}

	// métodos get/set
	public Set getPedidos()
	{
		return pedidos;
	}

	public void setPedidos(Set lotes)
	{
		this.pedidos = lotes;
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
	
	
}
 
