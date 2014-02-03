package modelo;

public class PL {
	// constantes
	public static final Integer LFL = 0;
	
	// atributos	
	private Long id;	 
	private Integer tamanhoDoLote;	 
	private Short leadTime;	 		 
	private Material material;
	
	public PL() { }
	
	public PL(Material material, Integer tamanhoDoLote, Short leadTime)
	{
		id = (long) 0;
		this.tamanhoDoLote = tamanhoDoLote;
		this.leadTime = leadTime;
		this.material = material;
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
	
	public Short getLeadTime()
	{
		return leadTime;
	}
	public void setLeadTime(Short leadTime)
	{
		this.leadTime = leadTime;
	}
	public Material getMaterial()
	{
		return material;
	}
	public void setMaterial(Material material)
	{
		this.material = material;
	}
	public Integer getTamanhoDoLote()
	{
		return tamanhoDoLote;
	}
	public void setTamanhoDoLote(Integer tamanhoDoLote)
	{
		this.tamanhoDoLote = tamanhoDoLote;
	}
	 
}
 
