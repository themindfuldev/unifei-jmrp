package controle;

import java.util.*;
import modelo.*;

public class ControleInsercaoDeNoBOM
{
	// atributos
	private NoBOM noBom;
	private boolean noTemFilho;
	private ControleInsercaoDeBOM ctrInsercaoDeBOM;
	private List<ControleInsercaoDeNoBOM> filhos;
	private List materiais;	
	
	public ControleInsercaoDeNoBOM(ControleInsercaoDeBOM ctrInsercaoDeBOM)
	{
		this.ctrInsercaoDeBOM = ctrInsercaoDeBOM;
		this.materiais = ctrInsercaoDeBOM.getMateriais();
		filhos = new ArrayList<ControleInsercaoDeNoBOM>();
		noTemFilho = false;
	}

	// obter Materiais para a interface
	public List getMateriais() {
		List nomesMateriais = new ArrayList();
		List materiais = ctrInsercaoDeBOM.getMateriais();
		
		for (Object obj: materiais)
		{
			Material mat = (Material) obj;
			String nome = mat.getNome();
			nomesMateriais.add(nome);
		}

		return nomesMateriais;
	}

	// métodos get/set
	public void setNoBOM(int indiceMaterial, Short quantidade)
	{
		Set filhosNoBOM = new HashSet();
		Material material = (Material) materiais.get(indiceMaterial); 
		
		noBom = new NoBOM(material, quantidade, filhosNoBOM);
	}	
	
	public NoBOM getNoBOM()
	{
		return noBom;
	}

	public ControleInsercaoDeBOM getCtrInsercaoDeBOM()
	{
		return ctrInsercaoDeBOM;
	}
	
   // construir hierarquia de árvore BOM
	public int constroiHierarquia()
   {
   	int niveis = 1;
   	for (ControleInsercaoDeNoBOM filho: filhos)
   	{
   		niveis = Math.max(niveis, 1 + filho.constroiHierarquia());
   		noBom.getFilhos().add(filho.getNoBOM());
   	}
   	return niveis;
   }
	
   // controle de filhos
	public void addControleFilho(ControleInsercaoDeNoBOM filho)
   {
   	filhos.add(filho);
   }  

	public boolean isNoTemFilho()
	{
		return noTemFilho;
	}

	public void setNoTemFilho(boolean noTemFilho)
	{
		this.noTemFilho = noTemFilho;
	}

	public List<ControleInsercaoDeNoBOM> getFilhos()
	{
		return filhos;
	}
	
	public void removeControleFilho(int linha)
	{		
		filhos.remove(linha);
	}
	
}
