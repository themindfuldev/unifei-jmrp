/*
 * DialogoIncluirFilhos.java
 * 
 * Created on 18 de Novembro de 2006, 16:28
 */

package visao;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import modelo.*;
import controle.*;
import java.util.*;
import java.util.List;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class DialogoIncluirFilhos extends JDialog
{
	// Variables declaration
	private JButton btnExcluirFilho;
	private JButton btnIncluirFilho;
	private JButton btnProximo;
	private JLabel lblFilhos;
	private JLabel lblMaterial;
	private JLabel lblMaterialDoNo;
	private JLabel lblQuantidade;
	private JLabel lblQuantidadeDoNo;
	private JPanel pnlBotoes;
	private JScrollPane scrFilhos;
	private JTable tblFilhos;

	private ControleInsercaoDeNoBOM controle;

	/** Creates new form DialogoIncluirNo */
	public DialogoIncluirFilhos(ControleInsercaoDeNoBOM controle, Dialog owner)
	{
		super(owner, true);
		this.controle = controle;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents()
	{
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Incluir filhos");
		setSize(450, 300);
		setResizable(false);
		setLocation(200, 200);

		GridBagConstraints gridBagConstraints;

		lblMaterial = new JLabel();
		lblMaterialDoNo = new JLabel();
		lblQuantidade = new JLabel();
		lblQuantidadeDoNo = new JLabel();
		lblFilhos = new JLabel();
		scrFilhos = new JScrollPane();
		tblFilhos = new JTable();
		pnlBotoes = new JPanel();
		btnIncluirFilho = new JButton();
		btnExcluirFilho = new JButton();
		btnProximo = new JButton();

		NoBOM noPai = controle.getNoBOM();

		// Layout
		getContentPane().setLayout(new GridBagLayout());

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Incluir filhos");
		lblMaterial.setText("Material: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(lblMaterial, gridBagConstraints);

		lblMaterialDoNo.setText(noPai.getMaterial().getNome());
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(lblMaterialDoNo, gridBagConstraints);

		lblQuantidade.setText("Quantidade: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(lblQuantidade, gridBagConstraints);

		lblQuantidadeDoNo.setText(noPai.getQuantidade().toString());
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(lblQuantidadeDoNo, gridBagConstraints);

		lblFilhos.setText("Filhos:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		getContentPane().add(lblFilhos, gridBagConstraints);

		btnIncluirFilho.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnIncluirFilho(evt);
				}
			});
		btnExcluirFilho.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnExcluirFilho(evt);
				}
			});
		btnProximo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnProximo(evt);
				}
			});
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0)
			{
		   	int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar?",
						"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		   	if (escolha == 0)
		   		dispose();
			}
		});

		// Tabela
		TableModel mdlArvores = new JMRPTableModel(new String[] { "Material",
				"Quantidade", "Possui filhos?" },
				new ArrayList<ArrayList<Object>>(), false);

		tblFilhos.setModel(mdlArvores);
		tblFilhos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblFilhos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrFilhos.setViewportView(tblFilhos);

		redimensionarTabela();

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 300;
		gridBagConstraints.ipady = 100;
		getContentPane().add(scrFilhos, gridBagConstraints);

		pnlBotoes.setLayout(new GridLayout(2, 1));

		btnIncluirFilho.setText("Incluir filho");
		pnlBotoes.add(btnIncluirFilho);

		btnExcluirFilho.setText("Excluir filho");
		pnlBotoes.add(btnExcluirFilho);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		getContentPane().add(pnlBotoes, gridBagConstraints);

		btnProximo.setText("Pr\u00f3ximo");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(20, 0, 0, 0);
		getContentPane().add(btnProximo, gridBagConstraints);
	}

	// redimensionamento de tabela
	private void redimensionarTabela()
	{
		TableColumn column = null;
		for (int i = 0; i < 3; i++)
		{
			column = tblFilhos.getColumnModel().getColumn(i);
			switch (i)
			{
				case 0:
					column.setPreferredWidth(150);
					break;
				case 1:
					column.setPreferredWidth(85);
					break;
				case 2:
					column.setPreferredWidth(84);
					break;
			}
		}
	}

	// incluir filho
	private void handlerBtnIncluirFilho(ActionEvent evt)
	{
		ControleInsercaoDeNoBOM ctrInsercaoDeNoBOM = new ControleInsercaoDeNoBOM(
				controle.getCtrInsercaoDeBOM());
		DialogoIncluirNo dlgInserirNo = new DialogoIncluirNo(ctrInsercaoDeNoBOM,
				(Dialog) this);
		dlgInserirNo.setVisible(true);

		if (dlgInserirNo.isDone())
		{
			controle.addControleFilho(ctrInsercaoDeNoBOM);

			NoBOM filho = ctrInsercaoDeNoBOM.getNoBOM();
			JMRPTableModel mdlTabela = (JMRPTableModel) tblFilhos.getModel();

			ArrayList<Object> objFilho = new ArrayList<Object>();
			objFilho.add(filho.getMaterial().getNome());
			objFilho.add(filho.getQuantidade());
			String temFilho = ctrInsercaoDeNoBOM.isNoTemFilho() ? "sim" : "não";
			objFilho.add(temFilho);

			mdlTabela.addValues(objFilho);
			redimensionarTabela();
		}
	}

	// excluir filho
	private void handlerBtnExcluirFilho(ActionEvent evt)
	{
		int linhaAtual = tblFilhos.getSelectedRow();
		if (linhaAtual != -1)
		{
			int escolha = JOptionPane.showConfirmDialog(null,
					"Tem certeza de que deseja excluir?", "Confimação",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (escolha == 0)
			{
				controle.removeControleFilho(linhaAtual);
	
				JMRPTableModel mdlTabela = (JMRPTableModel) tblFilhos.getModel();
				mdlTabela.removeRow(linhaAtual);
			}

		}
		else JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro",
				JOptionPane.ERROR_MESSAGE);

	}

	// próximo
	private void handlerBtnProximo(ActionEvent evt)
	{
		int escolha = JOptionPane.showConfirmDialog(null, "Confirma inserção dos filhos deste nó?",
				"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   	if (escolha == 0)
   	{
			dispose();
	
			List<ControleInsercaoDeNoBOM> filhos = controle.getFilhos();
			for (ControleInsercaoDeNoBOM filho : filhos)
			{
				if (filho.isNoTemFilho())
				{
					JDialog dlgIncluirFilhos = new DialogoIncluirFilhos(filho,
							(Dialog) this);
					dlgIncluirFilhos.setVisible(true);
				}
			}
   	}
	}

}
