/*
 * DialogoIncluirNo.java
 * 
 * Created on 18 de Novembro de 2006, 16:37
 */

package visao;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.List;
import controle.*;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class DialogoIncluirNo extends JDialog
{
	// Variables declaration
	private JButton btnCancelar;
	private JButton btnIncluir;
	private JButton btnLimpar;
	private ButtonGroup grpBotoes;
	private JComboBox cmbMaterial;
	private JLabel lblMaterial;
	private JLabel lblPossuiFilhos;
	private JLabel lblQuantidade;
	private JPanel pnlBotoes;
	private JRadioButton rbtNao;
	private JRadioButton rbtSim;
	private JTextField txtQuantidade;

	private boolean isDone;
	private ControleInsercaoDeNoBOM controle;

	/** Creates new form DialogoIncluirNo */
	public DialogoIncluirNo(ControleInsercaoDeNoBOM controle, Dialog owner)
	{
		super(owner, true);
		isDone = false;
		this.controle = controle;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents()
	{
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Incluir nó");
		setSize(350, 200);
		setResizable(false);
		setLocation(250, 250);

		GridBagConstraints gridBagConstraints;

		grpBotoes = new ButtonGroup();
		lblMaterial = new JLabel();
		cmbMaterial = new JComboBox();
		lblQuantidade = new JLabel();
		txtQuantidade = new JTextField();
		lblPossuiFilhos = new JLabel();
		rbtSim = new JRadioButton();
		rbtNao = new JRadioButton();
		pnlBotoes = new JPanel();
		btnIncluir = new JButton();
		btnLimpar = new JButton();
		btnCancelar = new JButton();

		// Listeners botões
		btnIncluir.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnIncluir(evt);
				}
			});

		btnLimpar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnLimpar(evt);
				}
			});

		btnCancelar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnCancelar(evt);
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

		grpBotoes.add(rbtSim);
		grpBotoes.add(rbtNao);

		// Layout
		getContentPane().setLayout(new GridBagLayout());

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Incluir n\u00f3");
		lblMaterial.setText("Material: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblMaterial, gridBagConstraints);

		List materiaisList = controle.getMateriais();
		materiaisList.add(0, "");
		String[] materiais = new String[materiaisList.size()];
		for (int i = 0; i < materiaisList.size(); i++)
		{
			materiais[i] = (String) materiaisList.get(i);
		}

		cmbMaterial.setModel(new DefaultComboBoxModel(materiais));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(cmbMaterial, gridBagConstraints);

		lblQuantidade.setText("Quantidade: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblQuantidade, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 30;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		getContentPane().add(txtQuantidade, gridBagConstraints);

		lblPossuiFilhos.setText("Possui filhos: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblPossuiFilhos, gridBagConstraints);

		rbtSim.setSelected(true);
		rbtSim.setText("Sim");
		rbtSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		rbtSim.setMargin(new Insets(0, 0, 0, 0));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		getContentPane().add(rbtSim, gridBagConstraints);

		rbtNao.setText("N\u00e3o");
		rbtNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		rbtNao.setMargin(new Insets(0, 0, 0, 0));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(rbtNao, gridBagConstraints);

		pnlBotoes.setLayout(new GridLayout(3, 1));

		btnIncluir.setText("Incluir");
		pnlBotoes.add(btnIncluir);

		btnLimpar.setText("Limpar");
		pnlBotoes.add(btnLimpar);

		btnCancelar.setText("Cancelar");
		pnlBotoes.add(btnCancelar);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 4;
		gridBagConstraints.anchor = GridBagConstraints.SOUTH;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		getContentPane().add(pnlBotoes, gridBagConstraints);
	}

	// incluir
	private void handlerBtnIncluir(ActionEvent evt)
	{
		if (validarIncluir())
		{
			int indiceMaterial = cmbMaterial.getSelectedIndex() - 1;
			Short quantidade = Short.parseShort(txtQuantidade.getText());
			boolean temFilhos = rbtSim.isSelected();

			controle.setNoBOM(indiceMaterial, quantidade);
			controle.setNoTemFilho(temFilhos);
			
			isDone = true;
			dispose();
		}
		else JOptionPane.showMessageDialog(null, "Pelo menos entre os seguintes aconteceu:\n\n- Há campo(s) em branco\n-O tempo inicial é maior que o tempo final",
				"Erro", JOptionPane.ERROR_MESSAGE);
	}

	// limpar
	private void handlerBtnLimpar(ActionEvent evt)
	{
		txtQuantidade.setText("");
		rbtNao.setSelected(false);
		rbtSim.setSelected(false);
		cmbMaterial.setSelectedIndex(0);
	}

	private void handlerBtnCancelar(ActionEvent evt)
	{
		int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar?",
				"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   	if (escolha == 0)
		dispose();
	}

	// Valida Inserir disciplina
	private boolean validarIncluir()
	{
		boolean[] checado = new boolean[3];
		checado[0] = !(cmbMaterial.getSelectedIndex() == 0);
		checado[1] = !txtQuantidade.getText().equals("");
		checado[2] = !(rbtSim.isSelected() && rbtNao.isSelected());
		
		boolean valido = true;
		for (boolean b : checado)
		{
			valido &= b;
		}

		return valido;
	}

	public boolean isDone()
	{
		return isDone;
	}
}
