/*
 * GestorDoUsuario.java
 * 
 * Created on 8 de Novembro de 2006, 02:05
 * 
 * To change this template, choose Tools | Template Manager and open the
 * template in the editor.
 */

import javax.swing.JOptionPane;
import org.hibernate.exception.*;

import visao.JanelaPrincipal;
import visao.*;
import controle.*;

/**
 * 
 * @author Tiago
 */
public class GestorDoUsuario
{

	/**
	 * @param args
	 *           the command line arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			// Inicialização dos objetos primários
			ControleGerenciamentoDeMateriais ctrGerenciamentoDeMateriais = new ControleGerenciamentoDeMateriais();
			QuadroGerenciamentoDeMateriais qdrGerenciamentoDeMateriais = new QuadroGerenciamentoDeMateriais(
					ctrGerenciamentoDeMateriais);

			JanelaPrincipal jnlPrincipal = new JanelaPrincipal(
					qdrGerenciamentoDeMateriais);
			jnlPrincipal.setVisible(true);
		}
		catch (GenericJDBCException ex)
		{
			JOptionPane.showMessageDialog(null,
					"Não foi possível a conexão com o Banco de Dados!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
