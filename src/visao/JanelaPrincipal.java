/*
 * JanelaPrincipal.java
 * 
 * Created on 8 de Novembro de 2006, 02:04
 */

package visao;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controle.ControleGeracaoDeProgramacao;

/**
 * 
 * @author Tiago
 */
@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame
{
	// Variables declaration
	private JMenuBar mnbPrincipal;
	private JMenuItem mniGerenciarMateriais;
	private JMenuItem mniGerenciarProgramacoes;
	private JMenuItem mniSair;
	private JMenuItem mniSobre;
	private JSeparator mnsFuncoes;
	private JMenu mnuAjuda;
	private JMenu mnuFuncoes;

	private JPanel pnlGerenciamentoDeMateriais;

	/** Creates new form JanelaPrincipal */
	public JanelaPrincipal(
			QuadroGerenciamentoDeMateriais qdrGerenciamentoDeMateriais)
	{
		this.pnlGerenciamentoDeMateriais = qdrGerenciamentoDeMateriais;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents()
	{
		// Instanciação dos componentes
		mnbPrincipal = new JMenuBar();
		mnuFuncoes = new JMenu();
		mniGerenciarMateriais = new JMenuItem();
		mniGerenciarProgramacoes = new JMenuItem();
		mnsFuncoes = new JSeparator();
		mniSair = new JMenuItem();
		mnuAjuda = new JMenu();
		mniSobre = new JMenuItem();

		// Configuração da janela
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 400);
		setTitle("JMRP");
		setResizable(false);

		// Posicionamento
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int posX = (d.width - this.getWidth()) / 2;
		int posY = (d.height - this.getHeight()) / 2;
		setLocation(posX, posY);

		// Configuração dos componentes
		// Menu
		mnuFuncoes.setMnemonic('f');
		mnuFuncoes.setText("Fun\u00e7\u00f5es");
		mniGerenciarMateriais.setMnemonic('m');
		mniGerenciarMateriais.setText("Gerenciar materiais");
		mniGerenciarMateriais.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerMniGerenciarMateriais(evt);
				}
			});
		mnuFuncoes.add(mniGerenciarMateriais);

		mniGerenciarProgramacoes.setMnemonic('p');
		mniGerenciarProgramacoes.setText("Gerar programação");
		mnuFuncoes.add(mniGerenciarProgramacoes);
		mniGerenciarProgramacoes.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerMniGerenciarProgramacoes(evt);
				}
			});
		mnuFuncoes.add(mnsFuncoes);

		mniSair.setMnemonic('r');
		mniSair.setText("Sair");
		mniSair.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					handlerMniSair(evt);
				}
			});
		mnuFuncoes.add(mniSair);

		mnbPrincipal.add(mnuFuncoes);

		mnuAjuda.setMnemonic('a');
		mnuAjuda.setText("Ajuda");
		mniSobre.setMnemonic('s');
		mniSobre.setText("Sobre...");
		mniSobre.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						JOptionPane.showMessageDialog(null, "JMRP 1.0\n\nTiago Romero Garcia - 12643\nCCO511", "Sobre", JOptionPane.INFORMATION_MESSAGE);
					}
				});
		
		mnuAjuda.add(mniSobre);

		mnbPrincipal.add(mnuAjuda);

		setJMenuBar(mnbPrincipal);

		// Quadros
		pnlGerenciamentoDeMateriais.setSize(400, 300);

		// Container
		Container pane = getContentPane();
		pane.setLayout(new CardLayout());
		pane.add("MATERIAIS", pnlGerenciamentoDeMateriais);
		pane.setVisible(false);

	}

	// Gerenciar materiais
	private void handlerMniGerenciarMateriais(ActionEvent evt)
	{
		Container pane = getContentPane();
		((CardLayout) pane.getLayout()).first(pane);
		pane.setVisible(true);
	}

	// Gerar programação
	private void handlerMniGerenciarProgramacoes(ActionEvent evt)
	{
		ControleGeracaoDeProgramacao controle = new ControleGeracaoDeProgramacao();
		JDialog dlgNovaProgramacao = new DialogoNovaProgramacao(controle, (Frame) this);
		dlgNovaProgramacao.setVisible(true);
	}

	private void handlerMniSair(ActionEvent evt)
	{
		System.exit(0);
	}
}
