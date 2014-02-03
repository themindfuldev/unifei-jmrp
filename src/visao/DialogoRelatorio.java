/*
 * DialogoInserirPLs.java
 * 
 * Created on 18 de Novembro de 2006, 16:56
 */

package visao;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import controle.*;
import java.util.*;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class DialogoRelatorio extends JDialog
{
	// Variables declaration
	private JButton btnCancelar;
	private JPanel pnlBotoes;
	private JScrollPane scrTabela;
	private JTable tblTabela;

	private ControleGeracaoDeProgramacao controle;
	private boolean done;
	private short instantes;

	public boolean isDone()
	{
		return done;
	}

	/** Creates new form DialogoInserirPLs */
	public DialogoRelatorio(ControleGeracaoDeProgramacao controle, Dialog owner)
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
		setTitle("Relatório de OLs");
		setSize(650, 300);
		setResizable(false);
		setLocation(50, 50);

		GridBagConstraints gridBagConstraints;

		scrTabela = new JScrollPane();
		tblTabela = new JTable();
		pnlBotoes = new JPanel();
		btnCancelar = new JButton();

		btnCancelar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnCancelar(evt);
				}
			});
		this.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent arg0)
				{
					int escolha = JOptionPane.showConfirmDialog(null,
							"Tem certeza de que deseja cancelar?", "Confimação",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (escolha == 0) dispose();
				}
			});

		// Tabela
		ArrayList<ArrayList<Object>> itens = controle.getOLs();

		ArrayList<String> cabecalho = new ArrayList<String>();
		cabecalho.add("Material");
		short tempoInicial = controle.getProgramacao().getTempoInicial();
		short tempoFinal = controle.getProgramacao().getTempoFinal();
		instantes = (short) (tempoFinal - tempoInicial + 1);

		for (short i = tempoInicial; i <= tempoFinal; i++)
			cabecalho.add(Short.toString(i));
		String[] cabecalhoArray = cabecalho.toArray(new String[] {});

		TableModel mdlTabela = new JMRPTableModel(cabecalhoArray, itens, false);

		tblTabela.setModel(mdlTabela);
		tblTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrTabela.setViewportView(tblTabela);
		redimensionarTabela();

		// Layout
		getContentPane().setLayout(new GridBagLayout());
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 500;
		gridBagConstraints.ipady = 200;
		getContentPane().add(scrTabela, gridBagConstraints);

		pnlBotoes.setLayout(new GridLayout(2, 1));

		btnCancelar.setText("Fechar");
		pnlBotoes.add(btnCancelar);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		getContentPane().add(pnlBotoes, gridBagConstraints);
	}

	// redimensionar tabela
	private void redimensionarTabela()
	{
		TableColumn column = null;
		int larguraLote = 400 / instantes;

		for (int i = 0; i <= instantes; i++)
		{
			column = tblTabela.getColumnModel().getColumn(i);
			switch (i)
			{
				case 0:
					column.setPreferredWidth(119);
					break;
				default:
					column.setPreferredWidth(larguraLote);
					break;
			}
		}
	}

	// cancelar
	private void handlerBtnCancelar(ActionEvent evt)
	{
		int escolha = JOptionPane.showConfirmDialog(null,
				"Tem certeza de que deseja fechar?", "Confimação",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (escolha == 0) dispose();
	}
}
