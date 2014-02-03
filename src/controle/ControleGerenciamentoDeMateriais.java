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
import org.hibernate.*;
import java.util.*;

import modelo.*;

/**
 * 
 * @author Tiago
 */
public class ControleGerenciamentoDeMateriais
{
	// atributos
	private String ultimaConsulta = "";

	// consultar material
	public List consultarMateriais(String consultaNome)
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result;
		if (consultaNome == null) result = session.createQuery(
				"from Material as mat where mat.nome like '" + ultimaConsulta
						+ "%' order by mat.nome")
					.list();
		else
		result = session.createQuery(
				"from Material as mat where mat.nome like '" + consultaNome + "%' order by mat.nome")
				.list();

		tx.commit();		
		HibernateUtil.closeSession();

		ultimaConsulta = consultaNome;

		return result;
	}

	// persistir material
	public void salvarMaterial(String nome, String descricao, String observacoes)
	{
		Material material = new Material(nome, descricao, observacoes);

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(material);

		tx.commit();
		
		HibernateUtil.closeSession();
	}

}
