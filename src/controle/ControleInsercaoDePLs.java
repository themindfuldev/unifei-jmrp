package controle;

import java.util.*;
import modelo.*;

public class ControleInsercaoDePLs
{
	// atributos
	private ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao;
	private TabelaDeNiveis tabela;
	private List<Material> materiaisList;

	public ControleInsercaoDePLs(ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao)
	{
		this.ctrGeracaoDeProgramacao = ctrGeracaoDeProgramacao;
		materiaisList = new ArrayList<Material>();
		this.tabela = this.ctrGeracaoDeProgramacao.getTabela();		
	}
	
	// obter Materiais para a interface
	public ArrayList<ArrayList<Object>> getMateriais()
	{
		ArrayList<ArrayList<Object>> lista = new ArrayList<ArrayList<Object>>();
		Set<Material> materiais = tabela.getMateriais();
		
		for(Material mat: materiais)
		{
			ArrayList<Object> linha = new ArrayList<Object>();
			linha.add(mat.getNome());
			linha.add("LFL");
			linha.add(new Short((short)0));
			
			lista.add(linha);
			materiaisList.add(mat);
		}
		
		return lista;
	}
	
	// inserir PL
	public void inserir(ArrayList<ArrayList<Object>> lista)
	{
		Set pls = new HashSet();
		
		for (int i=0; i<lista.size(); i++)
		{
			Material mat = materiaisList.get(i);
			ArrayList<Object> linha = lista.get(i); 
			
			String tamanhoDoLoteString = (String) linha.get(1);
			Integer tamanhoDoLote;
			
			if (tamanhoDoLoteString.equalsIgnoreCase("LFL"))
				tamanhoDoLote = 0;
			else
				tamanhoDoLote = Integer.parseInt(tamanhoDoLoteString);
			
			Short leadTime = (Short) linha.get(2);
			
			PL pl = new PL(mat, tamanhoDoLote, leadTime);
			pls.add(pl);
		}
		
		ctrGeracaoDeProgramacao.setPls(pls);
	}
	
}
