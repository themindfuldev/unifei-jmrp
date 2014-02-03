package modelo;

import java.util.*;

public class OA
{
	// atributos
	private Long id;
	private Material material;
	private Set entregas;

	public OA()
	{
	}

	public OA(Material material, Set entregas)
	{
		id = (long) 0;
		this.material = material;
		this.entregas = entregas;
	}

	// métodos get/set
	public Set getEntregas()
	{
		return entregas;
	}

	public void setEntregas(Set entregas)
	{
		this.entregas = entregas;
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
