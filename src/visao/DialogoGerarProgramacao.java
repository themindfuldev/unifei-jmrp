/*
 * JanelaGerarProgramacao.java
 *
 * Created on 18 de Novembro de 2006, 15:13
 */

package visao;

import java.awt.*;
import java.awt.event.*;

import javax.resource.spi.IllegalStateException;
import javax.swing.*;

import controle.*;

/**
 *
 * @author  Tiago
 */
@SuppressWarnings("serial")
public class DialogoGerarProgramacao extends JDialog
{
   // Variables declaration
   private JButton btnCancelar;
   private JButton btnProsseguir;
   private JLabel lblDescricao;
   private JLabel lblDescricaoDaProgramacao;
   private JLabel lblEtapa1;
   private JLabel lblEtapa2;
   private JLabel lblEtapa3;
   private JLabel lblEtapa4;
   private JLabel lblEtapa5;
   private JLabel lblEtapas;
   private JLabel lblFim;
   private JLabel lblFimDaProgramacao;
   private JLabel lblInicio;
   private JLabel lblInicioDaProgramacao;
   private JPanel pnlBotoes;
   private JPanel pnlEtapas;
   private Icon icnOK;
   private Icon icnNOK;
   
   private ControleGeracaoDeProgramacao controle;
   private EstadosDoDialogoGerarProgramacao estado;
   
   /** Creates new form JanelaGerarProgramacao */
   public DialogoGerarProgramacao(ControleGeracaoDeProgramacao controle, Dialog owner)
   {
      super(owner, true);  
      this.controle = controle;
      initComponents();
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    */
   private void initComponents()
   {
      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setTitle("Gerar programação");
      setSize(500, 200);
      setResizable(false);
		setLocation(100, 100);
      
      GridBagConstraints gridBagConstraints;
      
      lblDescricao = new JLabel();
      lblDescricaoDaProgramacao = new JLabel();
      lblInicio = new JLabel();
      lblInicioDaProgramacao = new JLabel();
      lblFim = new JLabel();
      lblFimDaProgramacao = new JLabel();
      lblEtapas = new JLabel();
      pnlEtapas = new JPanel();
      lblEtapa1 = new JLabel();
      lblEtapa2 = new JLabel();
      lblEtapa3 = new JLabel();
      lblEtapa4 = new JLabel();
      lblEtapa5 = new JLabel();
      pnlBotoes = new JPanel();
      btnProsseguir = new JButton();
      btnCancelar = new JButton();      
         
      icnNOK = new ImageIcon("lib/nok.gif");
      icnOK = new ImageIcon("lib/ok.gif");
      
      btnProsseguir.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            handlerBtnProsseguir(evt);
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
      
      lblDescricao.setText("Descri\u00e7\u00e3o: ");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.anchor = GridBagConstraints.EAST;
      getContentPane().add(lblDescricao, gridBagConstraints);
      
      lblDescricaoDaProgramacao.setText(controle.getProgramacaoDescricao());
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridwidth = 3;
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      getContentPane().add(lblDescricaoDaProgramacao, gridBagConstraints);
      
      lblInicio.setText("In\u00edcio: ");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.anchor = GridBagConstraints.EAST;
      getContentPane().add(lblInicio, gridBagConstraints);
      
      lblInicioDaProgramacao.setText(controle.getProgramacaoUnidadeDeTempo() + " " + controle.getProgramacaoTempoInicial());
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      gridBagConstraints.insets = new Insets(0, 0, 0, 50);
      getContentPane().add(lblInicioDaProgramacao, gridBagConstraints);
      
      lblFim.setText("Fim: ");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.anchor = GridBagConstraints.EAST;
      getContentPane().add(lblFim, gridBagConstraints);
      
      lblFimDaProgramacao.setText(controle.getProgramacaoUnidadeDeTempo() + " " + controle.getProgramacaoTempoFinal());
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.anchor = GridBagConstraints.WEST;
      getContentPane().add(lblFimDaProgramacao, gridBagConstraints);
      
      lblEtapas.setText("Etapas:");
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.anchor = GridBagConstraints.NORTH;
      gridBagConstraints.insets = new Insets(10, 0, 0, 0);
      getContentPane().add(lblEtapas, gridBagConstraints);
      
      pnlEtapas.setLayout(new BoxLayout(pnlEtapas, BoxLayout.Y_AXIS));
      
      lblEtapa1.setText("Inserir lista de materiais (BOM)");
      pnlEtapas.add(lblEtapa1);
      
      lblEtapa2.setText("Inserir pol\u00edticas de loteamento");
      pnlEtapas.add(lblEtapa2);
      
      lblEtapa3.setText("Inserir plano mestre de produ\u00e7\u00e3o (MPS)");
      pnlEtapas.add(lblEtapa3);
      
      lblEtapa4.setText("Inserir estoques");
      pnlEtapas.add(lblEtapa4);
      
      lblEtapa5.setText("Inserir ordens abertas");
      pnlEtapas.add(lblEtapa5);
      
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.gridwidth = 3;
      gridBagConstraints.ipadx = 50;
      gridBagConstraints.insets = new Insets(10, 0, 0, 0);
      getContentPane().add(pnlEtapas, gridBagConstraints);
      
      pnlBotoes.setLayout(new GridLayout(2, 1));
      
      btnProsseguir.setMnemonic('P');
      btnProsseguir.setText("Prosseguir");
      pnlBotoes.add(btnProsseguir);
      
      btnCancelar.setMnemonic('C');
      btnCancelar.setText("Cancelar");
      pnlBotoes.add(btnCancelar);
      
      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 4;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.anchor = GridBagConstraints.SOUTH;
      getContentPane().add(pnlBotoes, gridBagConstraints);
      
      trocaDeEstado(EstadosDoDialogoGerarProgramacao.VAZIO);
   }
   
   // Troca de estado da interface
   private void trocaDeEstado(EstadosDoDialogoGerarProgramacao novoEstado)
   {
      switch(novoEstado)
      {
         case VAZIO:
            lblEtapa1.setForeground(new Color(0, 0x33, 0xFF));
            lblEtapa1.setIcon(icnNOK);
            lblEtapa2.setIcon(icnNOK);
            lblEtapa3.setIcon(icnNOK);
            lblEtapa4.setIcon(icnNOK);
            lblEtapa5.setIcon(icnNOK);
            estado = EstadosDoDialogoGerarProgramacao.VAZIO;
            break;
            
         case BOM_INSERIDO:
            lblEtapa1.setForeground(new Color(0xCC, 0x99, 0x00));
            lblEtapa1.setIcon(icnOK);
            lblEtapa2.setForeground(new Color(0, 0x33, 0xFF));
            estado = EstadosDoDialogoGerarProgramacao.BOM_INSERIDO;
            break;
            
         case PL_INSERIDO:
            lblEtapa2.setForeground(new Color(0xCC, 0x99, 0x00));
            lblEtapa2.setIcon(icnOK);
            lblEtapa3.setForeground(new Color(0, 0x33, 0xFF));
            estado = EstadosDoDialogoGerarProgramacao.PL_INSERIDO;
            break;
            
         case MPS_INSERIDO:
            lblEtapa3.setForeground(new Color(0xCC, 0x99, 0x00));
            lblEtapa3.setIcon(icnOK);
            lblEtapa4.setForeground(new Color(0, 0x33, 0xFF));
            estado = EstadosDoDialogoGerarProgramacao.MPS_INSERIDO;
            break;
            
         case ESTOQUES_INSERIDO:
            lblEtapa4.setForeground(new Color(0xCC, 0x99, 0x00));
            lblEtapa4.setIcon(icnOK);
            lblEtapa5.setForeground(new Color(0, 0x33, 0xFF));
            estado = EstadosDoDialogoGerarProgramacao.ESTOQUES_INSERIDO;
            break;
            
         case OAS_INSERIDO:
            lblEtapa5.setForeground(new Color(0xCC, 0x99, 0x00));
            lblEtapa5.setIcon(icnOK);            
            btnProsseguir.setText("Calcular");
            estado = EstadosDoDialogoGerarProgramacao.OAS_INSERIDO;
            break;
      }
   }
   
   // botão prosseguir
   private void handlerBtnProsseguir(ActionEvent evt)
   {
      switch(estado)
      {
         case VAZIO:
            // Diálogo
         	ControleInsercaoDeBOM ctrInsercaoDeBOM = new ControleInsercaoDeBOM(controle); 
         	DialogoInserirBOM dlgInserirBOM = new DialogoInserirBOM(ctrInsercaoDeBOM, (Dialog)this);
            dlgInserirBOM.setVisible(true);
            if (dlgInserirBOM.isDone())
            	trocaDeEstado(EstadosDoDialogoGerarProgramacao.BOM_INSERIDO);
            break;
            
         case BOM_INSERIDO:
            // Diálogo
         	ControleInsercaoDePLs ctrInsercaoDePLs = new ControleInsercaoDePLs(controle); 
         	DialogoInserirPLs dlgInserirPLs = new DialogoInserirPLs(ctrInsercaoDePLs, (Dialog)this);
            dlgInserirPLs.setVisible(true);
         	if (dlgInserirPLs.isDone())
         		trocaDeEstado(EstadosDoDialogoGerarProgramacao.PL_INSERIDO);
            break;
            
         case PL_INSERIDO:
            // Diálogo
         	ControleInsercaoDeMPS ctrInsercaoDeMPS = new ControleInsercaoDeMPS(controle); 
         	DialogoInserirMPS dlgInserirMPS = new DialogoInserirMPS(ctrInsercaoDeMPS, (Dialog)this);
         	dlgInserirMPS.setVisible(true);
         	if (dlgInserirMPS.isDone())
         		trocaDeEstado(EstadosDoDialogoGerarProgramacao.MPS_INSERIDO);
            break;
            
         case MPS_INSERIDO:
            // Diálogo
         	ControleInsercaoDeEstoques ctrInsercaoDeEstoques = new ControleInsercaoDeEstoques(controle); 
         	DialogoInserirEstoques dlgInserirEstoques = new DialogoInserirEstoques(ctrInsercaoDeEstoques, (Dialog)this);
         	dlgInserirEstoques.setVisible(true);
         	if (dlgInserirEstoques.isDone())
         		trocaDeEstado(EstadosDoDialogoGerarProgramacao.ESTOQUES_INSERIDO);
            break;
            
         case ESTOQUES_INSERIDO:
            // 
         	ControleInsercaoDeOAs ctrInsercaoDeOAs = new ControleInsercaoDeOAs(controle); 
         	DialogoInserirOAs dlgInserirOAs = new DialogoInserirOAs(ctrInsercaoDeOAs, (Dialog)this);
         	dlgInserirOAs.setVisible(true);
         	if (dlgInserirOAs.isDone())
         		trocaDeEstado(EstadosDoDialogoGerarProgramacao.OAS_INSERIDO);
            break;
            
         case OAS_INSERIDO:
         	try
         	{
         		controle.calcular();
         		dispose();
         		new DialogoRelatorio(controle, (Dialog)this).setVisible(true);
         	}
         	catch (IllegalStateException ex)
         	{
         		JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
         	}         	
            break;
      }
   }
   
   // cancelar
   private void handlerBtnCancelar(ActionEvent evt)
   {
   	int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar?",
				"Confimação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
   	if (escolha == 0)
   		dispose();
   }
   
   private enum EstadosDoDialogoGerarProgramacao
   {
      VAZIO, BOM_INSERIDO, PL_INSERIDO, MPS_INSERIDO, ESTOQUES_INSERIDO, OAS_INSERIDO
   }
}
