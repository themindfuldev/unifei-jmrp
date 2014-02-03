/*
 * ControleGerenciamentoDeMateriais.java
 * 
 * Created on 20 de Novembro de 2006, 16:51
 * 
 * To change this template, choose Tools | Template Manager and open the
 * template in the editor.
 */

package controle;

import util.*;
import modelo.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

import javax.resource.spi.IllegalStateException;

/**
 * 
 * @author Tiago
 */
public class ControleGeracaoDeProgramacao
{
	// atributos
	private Programacao programacao;
	private TabelaDeNiveis tabela;
	private Set bom;
	private Set pls;
	private Set mps;
	private Set estoques;
	private Set oas;

	// métodos get/set
	public void setOas(Set oas)
	{
		this.oas = oas;
	}

	public void setBom(Set bom)
	{
		this.bom = bom;
	}	

	public void setEstoques(Set estoques)
	{
		this.estoques = estoques;
	}

	public void setTabela(TabelaDeNiveis tabela)
	{
		this.tabela = tabela;
	}

	public String getProgramacaoDescricao() {
		return programacao.getDescricao();
	}

	public String getProgramacaoUnidadeDeTempo() {
		String unidade = "";
		switch(programacao.getUnidadeDeTempo())
		{			
			case Programacao.DIAS:
				unidade = "dia";
				break;
				
			case Programacao.SEMANAS:
				unidade = "semana";
				break;
		}
		
		return unidade;
	}	
	
	public String getProgramacaoTempoInicial() {
		return programacao.getTempoInicial().toString();
	}
	
	public String getProgramacaoTempoFinal() {
		return programacao.getTempoFinal().toString();
	}
	
	public TabelaDeNiveis getTabela()
	{
		return tabela;
	}

	public void setPls(Set pls)
	{
		this.pls = pls;		
	}
	
	public Programacao getProgramacao()
	{
		return programacao;
	}

	public void setMps(Set mps)
	{
		this.mps = mps;
	}
		
	// criar nova programação
	public void novaProgramacao(String descricao, Short unidadeDeTempo, Short inicio,
			Short fim)
	{
		programacao = new Programacao(descricao, unidadeDeTempo, inicio, fim, new HashSet());
	}
	
	// calcular programação
	public void calcular() throws IllegalStateException
	{
		Queue<Material> filaMateriais = tabela.enqueue();
		
		// Para cada material, em ordem LLC
		for (Material material: filaMateriais)
		{
			short tempo = programacao.getTempoInicial();
			short tempoFinal = programacao.getTempoFinal();
			
			// Para cada unidade de tempo entre tempo inicial e final da programação.
			for (; tempo <= tempoFinal; tempo++)
			{
				// Determinar NB
				int necessidadeBruta = 0;
				AgendaMPS agendaMPS = null;				
				
				for (Object agendaObj: mps)
				{
					AgendaMPS agendaMPSvisitada = (AgendaMPS) agendaObj;
					if (agendaMPSvisitada.getMaterial().equals(material))
					{
						agendaMPS = agendaMPSvisitada;
						break;
					}
				}
				Set pedidos = agendaMPS.getPedidos();
				for (Object loteObj: pedidos)
				{
					Lote lote = (Lote) loteObj;
					if (lote.getTempo().equals(tempo))
					{
						necessidadeBruta = lote.getQuantidade();
						break;
					}
				}
				
				if (necessidadeBruta != 0)
				{
					// Determinar RP
					int recebimentoProgramado = 0;
					OA oa = null;				
					
					for (Object oaObj: oas)
					{
						OA oaVisitada = (OA) oaObj;
						if (oaVisitada.getMaterial().equals(material))
						{
							oa = oaVisitada;
							break;
						}
					}
					Set entregas = oa.getEntregas();
					for (Object loteObj: entregas)
					{
						Lote lote = (Lote) loteObj;
						if (lote.getTempo().equals(tempo))
						{
							recebimentoProgramado = lote.getQuantidade();
							break;
						}
					}
					
					// Determinar EP 
					Estoque estoque = null;				
					
					for (Object estoqueObj: estoques)
					{
						Estoque estoqueVisitado = (Estoque) estoqueObj;
						if (estoqueVisitado.getMaterial().equals(material))
						{
							estoque = estoqueVisitado;
							break;
						}
					}
					int estoqueProjetado = estoque.getEstoqueDisponivel();
					
					int necessidadeLiquida = 0;
					if (necessidadeBruta > recebimentoProgramado)
					{
						// Determinar NL
						necessidadeLiquida = necessidadeBruta - recebimentoProgramado;
						for (Object loteObj: entregas)
						{
							Lote lote = (Lote) loteObj;
							if (lote.getTempo().equals(tempo))
							{
								lote.setQuantidade(recebimentoProgramado);
								break;
							}
						}
						
						if (necessidadeBruta > estoqueProjetado)
						{
							//	Determinar NL
							necessidadeLiquida = necessidadeLiquida - estoqueProjetado;
							for (Object estoqueObj: estoques)
							{
								Estoque estoqueVisitado = (Estoque) estoqueObj;
								if (estoqueVisitado.getMaterial().equals(material))
								{
									estoqueVisitado.alocar(estoqueProjetado);
									break;
								}
							}
						}
						else
						{
							//	Determinar NL
							necessidadeLiquida = 0;
							for (Object estoqueObj: estoques)
							{
								Estoque estoqueVisitado = (Estoque) estoqueObj;
								if (estoqueVisitado.getMaterial().equals(material))
								{
									estoqueVisitado.alocar(necessidadeBruta);
									break;
								}
							}
						}					
					}
					else
					{
						// Determinar NL
						necessidadeLiquida = 0;
						for (Object loteObj: entregas)
						{
							Lote lote = (Lote) loteObj;
							if (lote.getTempo().equals(tempo))
							{
								lote.setQuantidade(necessidadeBruta);
								break;
							}
						}
					}
					
					// Determinar lotePL 					
					PL pl = null;				
					
					for (Object plObj: pls)
					{
						PL plVisitado = (PL) plObj;
						if (plVisitado.getMaterial().equals(material))
						{
							pl = plVisitado;
							break;
						}
					}
					Integer lotePL = pl.getTamanhoDoLote();
					int recebimentoDoPedidoPlanejado = 0;
					
					if (lotePL.equals(PL.LFL))
					{
						recebimentoDoPedidoPlanejado = necessidadeLiquida;
						if (recebimentoDoPedidoPlanejado > 0)
						{
							// Determinar leadTime
							short leadTime = pl.getLeadTime();
							
							// Determinar OL
							OL ol = getOL(material);
							Set listaLotes = ol.getLotes();							
							
							Short tempoLote = (short)(tempo-leadTime);
							if (tempoLote < programacao.getTempoInicial())
								throw new IllegalStateException("Esta programação não pode ser realizada pelo JMRP.");
														
							Lote novoLote = ol.getLote(tempoLote);
							novoLote.addQuantidade(recebimentoDoPedidoPlanejado);
										
							Set materiaisFilhos = getFilhos(material);
							for (Object materialNoFilhoObj: materiaisFilhos)
							{
								NoBOM materialNoFilho = (NoBOM) materialNoFilhoObj;
								Material materialFilho = materialNoFilho.getMaterial();
								Integer recebimentoDoPedidoPlanejadoFilho = materialNoFilho.getQuantidade() * recebimentoDoPedidoPlanejado;
								
								// MPS
								AgendaMPS agendaFilho = null;								
								
								for (Object agendaObj: mps)
								{
									AgendaMPS agendaMPSvisitada = (AgendaMPS) agendaObj;
									if (agendaMPSvisitada.getMaterial().equals(materialFilho))
									{
										agendaFilho = agendaMPSvisitada;
										break;
									}
								}
								Set pedidosFilho = agendaFilho.getPedidos();
								
								for (Object pedidoFilhoObj: pedidosFilho)
								{
									Lote pedidoVisitado = (Lote) pedidoFilhoObj;
									if (pedidoVisitado.getTempo().equals(tempoLote))
									{
										pedidoVisitado.addQuantidade(recebimentoDoPedidoPlanejadoFilho);
										break;
									}
								}								
							}
						}
					}
					else
					{
						int lotes = (int)Math.ceil((double) necessidadeLiquida/lotePL);
						// Determinar RPP
						recebimentoDoPedidoPlanejado = lotes*lotePL;
						if (recebimentoDoPedidoPlanejado > 0)
						{
							// Determinar leadTime
							short leadTime = pl.getLeadTime();
							
							for (Object estoqueObj: estoques)
							{
								Estoque estoqueVisitado = (Estoque) estoqueObj;
								if (estoqueVisitado.getMaterial().equals(material))
								{
									estoqueVisitado.adicionar(recebimentoDoPedidoPlanejado-necessidadeLiquida);
									break;
								}
							}
							
							// Determinar OL
							OL ol = getOL(material);
							Set listaLotes = ol.getLotes();							
							
							for (int lote = 0; lote < lotes; lote++)
							{								
								Short tempoLote = (short)(tempo-lote-leadTime);
								if (tempoLote < programacao.getTempoInicial())
									throw new IllegalStateException("Esta programação não pode ser realizada pelo JMRP.");
							
								Lote novoLote = ol.getLote(tempoLote);
								novoLote.addQuantidade(lotePL);
								
								Set materiaisFilhos = getFilhos(material);
								for (Object materialNoFilhoObj: materiaisFilhos)
								{
									NoBOM materialNoFilho = (NoBOM) materialNoFilhoObj;
									Material materialFilho = materialNoFilho.getMaterial();
									Integer recebimentoDoPedidoPlanejadoFilho = materialNoFilho.getQuantidade() * recebimentoDoPedidoPlanejado;
									
									// Determinar MPS
									AgendaMPS agendaFilho = null;								
									
									for (Object agendaObj: mps)
									{
										AgendaMPS agendaMPSvisitada = (AgendaMPS) agendaObj;
										if (agendaMPSvisitada.getMaterial().equals(materialFilho))
										{
											agendaFilho = agendaMPSvisitada;
											break;
										}
									}
									Set pedidosFilho = agendaFilho.getPedidos();
									
									for (Object pedidoFilhoObj: pedidosFilho)
									{
										Lote pedidoVisitado = (Lote) pedidoFilhoObj;
										if (pedidoVisitado.getTempo().equals(tempoLote))
										{
											pedidoVisitado.addQuantidade(recebimentoDoPedidoPlanejadoFilho);
											break;
										}
									}								
								}															
							}
						}
					}					
				}				
			}
		}
	}
	
	// obter filhos de um material
	public Set getFilhos(Material material)
	{
		Set filhos = new HashSet();
		for (Object noBOMObj: bom)
		{
			NoBOM nobom = (NoBOM) noBOMObj;
			filhos.addAll(getNoBOMFilhos(material, nobom));			
		}
		
		return filhos;
	}
	
	// método recursivo interno à classe
	private Set getNoBOMFilhos(Material material, NoBOM noBom)
	{
		Set filhos = new HashSet();
		Material materialNO = noBom.getMaterial();
		
		for (Object noBOMObj: noBom.getFilhos())
		{
			NoBOM noBomFilho = (NoBOM) noBOMObj;			
			if (materialNO.equals(material))
			{
				filhos.add(noBomFilho);
			}			
			filhos.addAll(getNoBOMFilhos(material, noBomFilho));
		}		
		
		return filhos;
	}
	
	// obter um mesmo OL
	public OL getOL(Material material)
	{
		for (Object olObj: programacao.getOls())
		{
			OL ol = (OL) olObj;
			if (ol.getMaterial().equals(material))
				return ol;
		}
		
		Set lotes = new TreeSet();
		short tempo = programacao.getTempoInicial();
		short tempoFinal = programacao.getTempoFinal();	
	
		OL ol = new OL(material, lotes);
		for (; tempo <= tempoFinal; tempo++)
		{
			Lote novoLote = ol.getLote(tempo);
			lotes.add(novoLote);			
		}
		
		programacao.getOls().add(ol);		
		
		return ol;
	}
	
	// persistir a programação
	public void salvarProgramacao()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		for (Object olObj: programacao.getOls())
		{
			OL ol = (OL) olObj;
			
			for (Object loteObj: ol.getLotes())
			{				
				Lote lote = (Lote) loteObj;
				session.save(lote);
			}
			session.save(ol);
		}

		session.save(programacao);		
		tx.commit();		
		
		HibernateUtil.closeSession();
	}
	
	// obter OLs para a interface
	public ArrayList<ArrayList<Object>> getOLs()
	{
		ArrayList<ArrayList<Object>> lista = new ArrayList<ArrayList<Object>>();
		
		for(Object olo: programacao.getOls())
		{
			OL ol = (OL) olo;			
			ArrayList<Object> linha = new ArrayList<Object>();
			linha.add(ol.getMaterial().getNome());

			Set lotes = ol.getLotes();
			for(Object loto: lotes)
			{
				Lote lot = (Lote) loto;
				linha.add(lot.getQuantidade());				
			}
			
			lista.add(linha);
		}
		
		return lista;
	}	

}
