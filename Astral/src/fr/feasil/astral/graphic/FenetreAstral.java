package fr.feasil.astral.graphic;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import fr.feasil.utils.Utilitaire;


public class FenetreAstral extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	
	public FenetreAstral(String title) {
		super(title); pack();
		initFrame();
		initComponents();
		addComponents();
		
		setVisible(true);
	}
	
	private void initFrame() {
		getContentPane().setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(900, 600));
		setSize(900, 600);
		setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
		
		setIconImage(Utilitaire.getImageIcon("icone_128_128.png").getImage());
	}
	
	private void initComponents() {
		//TODO
	}
	
	private void addComponents() {
		//TODO
	}
	
	
	
	
	
	
	
	public void quitter()
	{
//		if ( JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment fermer l'application ?", "Attention", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, Utilitaire.ICON_MISH_QUESTION()) == JOptionPane.YES_OPTION )
		{
			System.exit(0);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
