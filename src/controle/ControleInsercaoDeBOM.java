package controle;

import java.util.*;

import modelo.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class ControleInsercaoDeBOM
{
	// atributos
	private List bom;
	private ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao;
	private TabelaDeNiveis tabela;
	private List materiais;	
	
	public ControleInsercaoDeBOM(ControleGeracaoDeProgramacao ctrGeracaoDeProgramacao)
	{
		this.ctrGeracaoDeProgramacao = ctrGeracaoDeProgramacao;
		bom = new ArrayList();
		tabela = new TabelaDeNiveis();		
		
		// Lista de materiais
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		materiais = session.createQuery("from Material").list();		
	
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	public void inserirBOM()
	{
		Set programacaoBom = new HashSet();
		
		programacaoBom.addAll(bom);	
		ctrGeracaoDeProgramacao.setTabela(tabela);
		
		Set bomSet = new HashSet();
		bomSet.addAll(bom);
		
		ctrGeracaoDeProgramacao.setBom(bomSet);
	}	
	
	public List getMateriais() {
		return materiais;
	}
	
	// inserir árvore
	public void inserirArvore(NoBOM nobom)
	{
		getNiveis(nobom, (short) 0);
		bom.add(nobom);
	}
	
	// método interno recursivo para classificar os níveis por LLC
	private void getNiveis(NoBOM nobom, short nivel)
	{
		tabela.setNivel(nobom.getMaterial(), (short)nivel);
		for (Object objFilho: nobom.getFilhos())
		{
			NoBOM filho = (NoBOM) objFilho;
			getNiveis(filho, (short) (nivel+1));
		}
	}
	
	// ilustrar árvore
	public String ilustrarArvore(int linha)
	{
		NoBOM raiz = (NoBOM) bom.get(linha);		
		return percorrerNo(raiz, (short) 0); 
	}
	
	// método interno recursivo para percorrer a árvore BOM
	private String percorrerNo(NoBOM raiz, short nivel)
	{
		String tab = "\n";
		for (int i=0; i<nivel; i++)
			tab += "--";
		
		String no = tab + " " + raiz.getMaterial().getNome() + "(" + raiz.getQuantidade() + ")";
		if (!raiz.getFilhos().isEmpty()) 
		{
			for (Object objFilho: raiz.getFilhos())
			{
				NoBOM filho = (NoBOM) objFilho;				
				no += percorrerNo(filho, (short) (nivel+1));
			}
		}
		
		return no;
	}
	
	public void excluir(int linha)
	{
		bom.remove(linha);
	}

}
