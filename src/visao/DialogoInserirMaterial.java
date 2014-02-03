/*
 * DialogoInserirMaterial.java
 *
 * Created on 18 de Novembro de 2006, 16:59
 */

package visao;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controle.ControleGerenciamentoDeMateriais;

/**
 *
 * @author  Tiago
 */
@SuppressWarnings("serial")
public class DialogoInserirMaterial extends JDialog
{
   // Variables declaration 
   private JButton btnCancelar;
   private JButton btnInserir;
   private JButton btnLimpar;
   private JLabel lblDescricao;
   private JLabel lblNome;
   private JLabel lblObservacoes;
   private JPanel pnlBotoes;
   private JTextField txtNome;
   private JTextArea txtDescricao;   
   private JTextArea txtObservacoes;
   private JScrollPane scrDescricao;
   private JScrollPane scrObservacoes;
   
   private ControleGerenciamentoDeMateriais controle;
   
   /** Creates new form DialogoInserirMaterial */
   public DialogoInserirMaterial(ControleGerenciamentoDeMateriais controle, Frame owner)
   {
      super((Frame)null, true);
      this.controle = controle;
      initComponents();
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    */
   private void initComponents()
   {
      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setTitle("Inserir material");
      setSize(400, 180);
      setLocation(50, 50);
      setResizable(false);
      
      lblNome = new JLabel();
      lblDescricao = new JLabel();
      lblObservacoes = new JLabel();
      txtNome = new JTextField();
      txtDescricao = new JTextArea();
      txtObservacoes = new JTextArea();
      pnlBotoes = new JPanel();
      btnInserir = new JButton();
      btnLimpar = new JButton();
      btnCancelar = new JButton();
      scrDescricao = new JScrollPane(txtDescricao);
      scrObservacoes = new JScrollPane(txtObservacoes);

      txtDescricao.setWrapStyleWord(true);
      txtDescricao.setLineWrap(true);
      
      txtObservacoes.setWrapStyleWord(true);
      txtObservacoes.setLineWrap(true);
      
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
      GridBagConstraints gridBagConstraints;      

      lblNome.setText("Nome: ");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.anchor = GridBagConstraints.EAST;
      getContentPane().add(lblNome, gridBagConstraints);

      lblDescricao.setText("Descri\u00e7\u00e3o: ");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.anchor = GridBagConstraints.EAST;
      getContentPane().add(lblDescricao, gridBagConstraints);

      lblObservacoes.setText("Observa\u00e7\u00f5es: ");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.anchor = GridBagConstraints.EAST;
      getContentPane().add(lblObservacoes, gridBagConstraints);

      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.ipadx = 180;
      getContentPane().add(txtNome, gridBagConstraints);

      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.ipadx = 160;
      gridBagConstraints.ipady = 15;
      //gridBagConstraints.fill = gridBagConstraints.BOTH;
      gridBagConstraints.insets = new Insets(5, 0, 0, 0);
      getContentPane().add(scrDescricao, gridBagConstraints);

      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.ipadx = 160;
      gridBagConstraints.ipady = 30;
      gridBagConstraints.insets = new Insets(5, 0, 0, 0);
      getContentPane().add(scrObservacoes, gridBagConstraints);

      pnlBotoes.setLayout(new GridLayout(3, 1));

      btnInserir.setText("Inserir");
      pnlBotoes.add(btnInserir);

      btnLimpar.setText("Limpar");
      pnlBotoes.add(btnLimpar);

      btnCancelar.setText("Cancelar");
      pnlBotoes.add(btnCancelar);

      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridheight = 3;
      gridBagConstraints.anchor = GridBagConstraints.SOUTH;
      gridBagConstraints.insets = new Insets(0, 10, 0, 0);
      getContentPane().add(pnlBotoes, gridBagConstraints);
      
      txtNome.requestFocusInWindow();
   }   
   
   // inserir
   private void handlerBtnInserir(ActionEvent evt)
   {
   	// validar
   	if (validarInserir())
   	{
	      String nome = txtNome.getText();
	      String descricao = txtDescricao.getText();
	      String observacoes = txtObservacoes.getText();     
	      controle.salvarMaterial(nome, descricao, observacoes);
	      dispose();
   	}
   	else
   		JOptionPane.showMessageDialog(null, "Há campo(s) em branco!",
					"Erro", JOptionPane.ERROR_MESSAGE);
   }
   
   // limpar
   private void handlerBtnLimpar(ActionEvent evt)
   {
      txtNome.setText("");
      txtDescricao.setText("");      
      txtObservacoes.setText("");
      txtNome.requestFocusInWindow();
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
		boolean[] checado = new boolean[2];
		checado[0] = !txtNome.getText().equals("");
		checado[1] = !txtDescricao.getText().equals("");

		boolean valido = true;
		for (boolean b : checado)
		{
			valido &= b;
		}

		return valido;
	}
}
