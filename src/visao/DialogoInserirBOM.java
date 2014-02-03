/*
 * DialogoInserirBOM.java
 * 
 * Created on 18 de Novembro de 2006, 16:08
 */

package visao;

import controle.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import modelo.NoBOM;

import java.util.*;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class DialogoInserirBOM extends JDialog
{
	// Variables declaration
	private JButton btnExcluir;
	private JButton btnIlustrar;
	private JButton btnInserir;
	private JButton btnCancelar;
	private JButton btnFechar;
	private JLabel lblArvores;
	private JPanel pnlBotoes;
	private JScrollPane scrArvores;
	private JTable tblArvores;

	private ControleInsercaoDeBOM controle;
	private boolean done;

	/** Creates new form DialogoInserirBOM */
	public DialogoInserirBOM(ControleInsercaoDeBOM controle, Dialog owner)
	{
		super(owner, true);
		this.controle = controle;
		initComponents();
		done = false;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents()
	{
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Inserir BOM");
		setSize(350, 250);
		setResizable(false);
		setLocation(150, 150);

		GridBagConstraints gridBagConstraints;

		lblArvores = new JLabel();
		scrArvores = new JScrollPane();
		tblArvores = new JTable();
		pnlBotoes = new JPanel();
		btnInserir = new JButton();
		btnIlustrar = new JButton();
		btnExcluir = new JButton();
		btnCancelar = new JButton();
		btnFechar = new JButton();

		// Layout
		getContentPane().setLayout(new GridBagLayout());

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Inserir BOM");
		lblArvores.setText("\u00c1rvores:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(lblArvores, gridBagConstraints);

		btnInserir.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnInserir(evt);
				}
			});

		btnIlustrar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnIlustrar(evt);
				}
			});

		btnExcluir.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnExcluir(evt);
				}
			});
		btnCancelar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnCancelar(evt);
				}
			});
		btnFechar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnFechar(evt);
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
		TableModel mdlArvores = new JMRPTableModel(new String[] { "Nó inicial",
				"Níveis" }, new ArrayList<ArrayList<Object>>(), false);

		tblArvores.setModel(mdlArvores);
		tblArvores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblArvores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrArvores.setViewportView(tblArvores);

		redimensionarTabela();

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 200;
		gridBagConstraints.ipady = 130;
		getContentPane().add(scrArvores, gridBagConstraints);

		pnlBotoes.setLayout(new GridLayout(6, 1));

		btnInserir.setText("Inserir");
		pnlBotoes.add(btnInserir);

		btnIlustrar.setText("Ilustrar");
		pnlBotoes.add(btnIlustrar);

		btnExcluir.setText("Excluir");
		pnlBotoes.add(btnExcluir);

		pnlBotoes.add(Box.createGlue());

		btnFechar.setText("Fechar");
		pnlBotoes.add(btnFechar);

		btnCancelar.setText("Cancelar");
		pnlBotoes.add(btnCancelar);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		getContentPane().add(pnlBotoes, gridBagConstraints);
	}

	// redimensionar tabela
	private void redimensionarTabela()
	{
		TableColumn column = null;
		for (int i = 0; i < 2; i++)
		{
			column = tblArvores.getColumnModel().getColumn(i);
			switch (i)
			{
				case 0:
					column.setPreferredWidth(130);
					break;
				case 1:
					column.setPreferredWidth(89);
					break;
			}
		}
	}

	// inserir
	private void handlerBtnInserir(ActionEvent evt)
	{
		ControleInsercaoDeNoBOM ctrInsercaoDeNoBOM = new ControleInsercaoDeNoBOM(
				controle);
		DialogoIncluirNo dlgInserirNo = new DialogoIncluirNo(ctrInsercaoDeNoBOM,
				(Dialog) this);
		dlgInserirNo.setVisible(true);

		if (dlgInserirNo.isDone())
		{
			boolean temFilhos = ctrInsercaoDeNoBOM.isNoTemFilho();
			if (temFilhos == true)
			{
				JDialog dlgIncluirFilhos = new DialogoIncluirFilhos(
						ctrInsercaoDeNoBOM, (Dialog) this);
				dlgIncluirFilhos.setVisible(true);
			}
			int niveis = ctrInsercaoDeNoBOM.constroiHierarquia();

			NoBOM raiz = ctrInsercaoDeNoBOM.getNoBOM();
			controle.inserirArvore(raiz);

			ArrayList<Object> objRaiz = new ArrayList<Object>();
			objRaiz.add(raiz.getMaterial().getNome());
			objRaiz.add(niveis);

			JMRPTableModel mdlTabela = (JMRPTableModel) tblArvores.getModel();
			mdlTabela.addValues(objRaiz);
			redimensionarTabela();
		}
	}

	// ilustrar
	private void handlerBtnIlustrar(ActionEvent evt)
	{
		int linhaAtual = tblArvores.getSelectedRow();
		if (linhaAtual != -1)
		{
			String arvore = controle.ilustrarArvore(linhaAtual);
			JOptionPane.showMessageDialog(this, arvore);
		}
		else JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro",
				JOptionPane.ERROR_MESSAGE);

	}

	// excluir
	private void handlerBtnExcluir(ActionEvent evt)
	{
		int linhaAtual = tblArvores.getSelectedRow();
		if (linhaAtual != -1)
		{
			int escolha = JOptionPane.showConfirmDialog(null,
					"Tem certeza de que deseja excluir?", "Confimação",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (escolha == 0)
			{
				controle.excluir(linhaAtual);

				JMRPTableModel mdlTabela = (JMRPTableModel) tblArvores.getModel();
				mdlTabela.removeRow(linhaAtual);
			}

		}
		else JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	// cancelar
	private void handlerBtnCancelar(ActionEvent evt)
	{
		int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar?",
				"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   	if (escolha == 0)
   		dispose();
	}

	// fechar
	private void handlerBtnFechar(ActionEvent evt)
	{
		int escolha = JOptionPane.showConfirmDialog(null, "Confirma inserção desta BOM?",
				"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   	if (escolha == 0)
   	{
			controle.inserirBOM();
			done = true;
			dispose();
   	}
	}

	public boolean isDone()
	{
		return done;
	}
}
