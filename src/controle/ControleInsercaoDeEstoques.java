package controle;

import java.util.*;
import modelo.*;

public class ControleInsercaoDeEstoques
{
	// atributos
	private ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao;
	private TabelaDeNiveis tabela;
	private List<Material> materiaisList;

	public ControleInsercaoDeEstoques(ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao)
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
			linha.add(new Integer(0));
			linha.add(new Integer(0));
			
			lista.add(linha);
			materiaisList.add(mat);
		}
		
		return lista;
	}
	
	// inserir estoque
	public void inserir(ArrayList<ArrayList<Object>> lista)
	{
		Set estoques = new HashSet();
		
		for (int i=0; i<lista.size(); i++)
		{
			Material mat = materiaisList.get(i);
			ArrayList<Object> linha = lista.get(i); 
			
			Integer estoqueAtual = (Integer) linha.get(1);
			Integer estoqueAlocado = (Integer) linha.get(2);
			
			Estoque estoque = new Estoque(mat, estoqueAtual, estoqueAlocado);
			estoques.add(estoque);
		}
		
		ctrGeracaoDeProgramacao.setEstoques(estoques);
	}
	
}
