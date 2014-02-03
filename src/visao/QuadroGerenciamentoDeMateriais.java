/*
 * QuadroGerenciamentoDeMateriais.java
 * 
 * Created on 8 de Novembro de 2006, 02:32
 */

package visao;

import controle.*;
import modelo.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.*;
import java.util.List;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class QuadroGerenciamentoDeMateriais extends JPanel
{
	// Variables declaration - do not modify
	private JButton btnConsultar;
	private JButton btnIncluir;
	private JPanel pnlTitulo;
	private JPanel pnlQuadro;
	private JPanel pnlBotoes;
	private JLabel lblConsulta;
	private JScrollPane scpTabela;
	private JTable tblTabela;
	private JTextField txfConsulta;

	private ControleGerenciamentoDeMateriais controle;

	/** Creates new form QuadroGerenciamentoDeMateriais */
	public QuadroGerenciamentoDeMateriais(
			ControleGerenciamentoDeMateriais controle)
	{
		this.controle = controle;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents()
	{
		GridBagConstraints gridBagConstraints;

		// Instanciação dos componentes
		scpTabela = new JScrollPane();
		tblTabela = new JTable();
		lblConsulta = new JLabel();
		txfConsulta = new JTextField();
		pnlTitulo = new JPanel();
		pnlQuadro = new JPanel();
		pnlBotoes = new JPanel();
		btnConsultar = new JButton();
		btnIncluir = new JButton();

		// Listeners botões
		btnIncluir.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnIncluir(evt);
				}
			});
		btnConsultar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnConsultar(evt);
				}
			});

		// Tabela
		ArrayList<ArrayList<Object>> itens = consultaMateriais("");

		TableModel mdlTabela = new JMRPTableModel(new String[] { "Nome",
				"Descrição" }, itens, false);

		tblTabela.setModel(mdlTabela);

		tblTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scpTabela.setViewportView(tblTabela);

		redimensionarTabela();

		// Layout
		pnlQuadro.setLayout(new GridBagLayout());

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 420;
		gridBagConstraints.ipady = 230;
		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		pnlQuadro.add(scpTabela, gridBagConstraints);

		lblConsulta.setText("Consulta por nome: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		pnlQuadro.add(lblConsulta, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		pnlQuadro.add(txfConsulta, gridBagConstraints);

		pnlBotoes.setLayout(new GridLayout(4, 1));

		btnConsultar.setText("Consultar");
		pnlBotoes.add(btnConsultar);

		btnIncluir.setText("Incluir");
		pnlBotoes.add(btnIncluir);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		pnlQuadro.add(pnlBotoes, gridBagConstraints);

		pnlTitulo.setBackground(new Color(0xCC, 0xCC, 0xCC));
		pnlTitulo.add(new JLabel("Gerenciamento de materiais"));

		// Container
		setLayout(new BorderLayout());
		add(pnlTitulo, BorderLayout.PAGE_START);
		add(pnlQuadro, BorderLayout.CENTER);
	}

	// redimensionar tabela
	private void redimensionarTabela()
	{
		TableColumn column = null;
		for (int i = 0; i < 2; i++)
		{
			column = tblTabela.getColumnModel().getColumn(i);
			switch (i)
			{
				case 0:
					column.setPreferredWidth(100);
					break;
				case 1:
					column.setPreferredWidth(339);
					break;
			}
		}
	}

	// consultar materiais
	private ArrayList<ArrayList<Object>> consultaMateriais(String consulta)
	{
		ArrayList<ArrayList<Object>> lista = new ArrayList<ArrayList<Object>>();
		List itens = controle.consultarMateriais(consulta);
		for (Object item : itens)
		{
			Material m = (Material) item;
			ArrayList<Object> linha = new ArrayList<Object>();
			linha.add(m.getNome());
			linha.add(m.getDescricao());

			lista.add(linha);
		}

		return lista;
	}

	// incluir
	private void handlerBtnIncluir(ActionEvent evt)
	{		
   	JDialog dlgInserirMaterial = new DialogoInserirMaterial(controle, (Frame) this.getParent().getParent().getParent().getParent());   	
      dlgInserirMaterial.setVisible(true);
		ArrayList<ArrayList<Object>> lista = consultaMateriais("");
		JMRPTableModel mdlTabela = (JMRPTableModel) tblTabela.getModel();
		mdlTabela.replaceData(lista);
		redimensionarTabela();
	}

	// consultar
	private void handlerBtnConsultar(ActionEvent evt)
	{
		String consulta = txfConsulta.getText();
		ArrayList<ArrayList<Object>> lista = consultaMateriais(consulta);
		JMRPTableModel mdlTabela = (JMRPTableModel) tblTabela.getModel();
		mdlTabela.replaceData(lista);
		redimensionarTabela();
	}

}
