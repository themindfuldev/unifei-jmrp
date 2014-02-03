/*
 * DialogoNovaProgramacao.java
 * 
 * Created on 18 de Novembro de 2006, 17:09
 */

package visao;

import controle.*;
import modelo.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class DialogoNovaProgramacao extends JDialog
{
	// Variables declaration
	private JButton btnCancelar;
	private JButton btnInserir;
	private JButton btnLimpar;
	private JComboBox cmbUnidadeDeTempo;
	private JLabel lblFim;
	private JLabel lblInicio;
	private JLabel lblDescricao;
	private JLabel lblUnidadeDeTempo;
	private JPanel pnlBotoes;
	private JTextField txtFim;
	private JTextField txtInicio;
	private JTextArea txtDescricao;
	private JScrollPane scrDescricao;

	private ControleGeracaoDeProgramacao controle;

	/** Creates new form DialogoNovaProgramacao */
	public DialogoNovaProgramacao(ControleGeracaoDeProgramacao controle, Frame owner)
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
		setTitle("Nova programação");
		setSize(400, 180);
		setResizable(false);
		setLocation(50, 50);

		GridBagConstraints gridBagConstraints;

		lblDescricao = new JLabel();
		lblUnidadeDeTempo = new JLabel();
		lblInicio = new JLabel();
		lblFim = new JLabel();
		cmbUnidadeDeTempo = new JComboBox();
		txtDescricao = new JTextArea();
		txtInicio = new JTextField();
		txtFim = new JTextField();
		pnlBotoes = new JPanel();
		btnInserir = new JButton();
		btnLimpar = new JButton();
		btnCancelar = new JButton();
		scrDescricao = new JScrollPane(txtDescricao);

		txtDescricao.setWrapStyleWord(true);
		txtDescricao.setLineWrap(true);

		// Listeners botões
		btnInserir.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerBtnInserir(evt);
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

		// Layout
		getContentPane().setLayout(new GridBagLayout());

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		lblDescricao.setText("Descrição: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblDescricao, gridBagConstraints);

		lblUnidadeDeTempo.setText("Unidade de tempo: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblUnidadeDeTempo, gridBagConstraints);

		lblInicio.setText("Inicio: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblInicio, gridBagConstraints);

		lblFim.setText("Fim: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		getContentPane().add(lblFim, gridBagConstraints);

		cmbUnidadeDeTempo.setModel(new DefaultComboBoxModel(new String[] { "",
				"semanas", "dias" }));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		getContentPane().add(cmbUnidadeDeTempo, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 100;
		gridBagConstraints.ipady = 15;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		getContentPane().add(scrDescricao, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		getContentPane().add(txtInicio, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.ipadx = 50;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		getContentPane().add(txtFim, gridBagConstraints);

		pnlBotoes.setLayout(new GridLayout(3, 1));

		btnInserir.setText("Inserir");
		pnlBotoes.add(btnInserir);

		btnLimpar.setText("Limpar");
		pnlBotoes.add(btnLimpar);

		btnCancelar.setText("Cancelar");
		pnlBotoes.add(btnCancelar);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 4;
		gridBagConstraints.anchor = GridBagConstraints.SOUTH;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		getContentPane().add(pnlBotoes, gridBagConstraints);
	}

	// inserir
	private void handlerBtnInserir(ActionEvent evt)
	{
		// validar
		if (validarInserir())
		{
			String descricao = txtDescricao.getText();
			Short unidadeDeTempo = null;
			switch (cmbUnidadeDeTempo.getSelectedIndex())
			{
				case 1:
					unidadeDeTempo = Programacao.SEMANAS;
					break;

				case 2:
					unidadeDeTempo = Programacao.DIAS;
					break;
			}
			Short inicio = Short.parseShort(txtInicio.getText());
			Short fim = Short.parseShort(txtFim.getText());
			controle.novaProgramacao(descricao, unidadeDeTempo, inicio, fim);
			
			dispose();
			
			DialogoGerarProgramacao dlgGerarProgramacao = new DialogoGerarProgramacao(controle, (Dialog)this);			
			dlgGerarProgramacao.setVisible(true);			
		}
		else JOptionPane.showMessageDialog(null, "Pelo menos entre os seguintes aconteceu:\n\n- Há campo(s) em branco\n-O tempo inicial é maior que o tempo final",
				"Erro", JOptionPane.ERROR_MESSAGE);
	}

	// limpar
	private void handlerBtnLimpar(ActionEvent evt)
	{
		txtDescricao.setText("");
		txtInicio.setText("");
		txtFim.setText("");
		cmbUnidadeDeTempo.setSelectedIndex(0);
		txtDescricao.requestFocusInWindow();
	}
   
   // cancelar
	private void handlerBtnCancelar(ActionEvent evt)
   {
   	int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar?",
				"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   	if (escolha == 0)
   		dispose();
   }
   
	// Valida Inserir disciplina
	private boolean validarInserir()
	{
		boolean[] checado = new boolean[5];
		checado[0] = !txtDescricao.getText().equals("");
		checado[1] = !(cmbUnidadeDeTempo.getSelectedIndex() == 0);
		checado[2] = !txtInicio.getText().equals("");
		checado[3] = !txtFim.getText().equals("");
		if (checado[2] && checado[3])
			checado[4] = !(Short.parseShort(txtInicio.getText()) > Short.parseShort(txtFim.getText()));

		boolean valido = true;
		for (boolean b : checado)
		{
			valido &= b;
		}

		return valido;
	}
}
