package controle;

import java.util.*;
import modelo.*;

public class ControleInsercaoDeMPS
{
	// atributos
	private ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao;
	private TabelaDeNiveis tabela;
	private List<Material> materiaisList;

	public ControleInsercaoDeMPS(ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao)
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
	      short tempoInicial = getTempoInicial();
	      short tempoFinal = getTempoFinal();
	      
	      for (short i=tempoInicial; i <= tempoFinal; i++)
				linha.add(new Integer(0));
			
			lista.add(linha);
			materiaisList.add(mat);
		}
		
		return lista;
	}
	
	// inserir MPS
	public void inserir(ArrayList<ArrayList<Object>> lista)
	{
		Set mps = new HashSet();
		
		for (int i=0; i<lista.size(); i++)
		{
			Material mat = materiaisList.get(i);
			ArrayList<Object> linha = lista.get(i);
			
			Set pedidos = new TreeSet();
			
			short tempoInicial = getTempoInicial();
	      short tempoFinal = getTempoFinal();
	      short instantes = (short)(tempoFinal - tempoInicial + 1);
	      
	      for (short j=1; j <= instantes; j++)
	      {	      	
	      	Short tempo = (short)(tempoInicial+j-1);
				Integer quantidade = (Integer) linha.get(j);
				
				Lote lote = new Lote(tempo, quantidade);
				pedidos.add(lote);				
			}
									
			AgendaMPS agenda = new AgendaMPS(mat, pedidos);
			mps.add(agenda);
		}
		
		ctrGeracaoDeProgramacao.setMps(mps);
	}
	
	// métodos get
	public short getTempoInicial() {
		return ctrGeracaoDeProgramacao.getProgramacao().getTempoInicial();
	}
	
	public short getTempoFinal() {
		return ctrGeracaoDeProgramacao.getProgramacao().getTempoFinal();
	}
	
}
