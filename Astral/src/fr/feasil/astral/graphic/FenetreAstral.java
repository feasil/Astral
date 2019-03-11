package fr.feasil.astral.graphic;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import fr.feasil.astral.data.point.Angle;
import fr.feasil.astral.data.point.Planete;
import fr.feasil.astral.graphic.datamissing.DataMissingListener;
import fr.feasil.astral.graphic.datamissing.DialogDataMissing;
import fr.feasil.astral.graphic.profil.DialogHistoriqueProfils;
import fr.feasil.astral.graphic.profil.ProfilSelectedListener;
import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.profil.ProfilManager;
import fr.feasil.utils.Utilitaire;


public class FenetreAstral extends JFrame implements Observer, ProfilSelectedListener, DataMissingListener {
	private static final long serialVersionUID = 1L;
	
	//----------
	private JMenuBar menuBar;
	private JMenu mFichier, mEdition, mAide;
	private JMenuItem miQuitter, miTheme, miEdition, miAide;
	//----------
	private JLabel lblNomProfil;
	private JTextPane txtTheme;
	private JScrollPane scrollTheme;
	//----------
	
	private ModelAstral model;
	
	public FenetreAstral(String title, String sqLiteFile) {
		super(title); pack();
		initFrame();
		initComponents();
		addComponents();
		
		model = new ModelAstral(sqLiteFile);
		model.addObserver(this);
		
		setVisible(true);
	}
	
	private void initFrame() {
		getContentPane().setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(900, 600));
		setSize(900, 600);
		setLocationRelativeTo(null);
		//setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
		setIconImage(Utilitaire.getImageIcon("icone_128_128.png").getImage());
	}
	
	private void initComponents() {
		//Menu bar
		menuBar = new JMenuBar();
		mFichier = new JMenu("Fichier"); 
		mEdition = new JMenu("Edition"); 
		mAide = new JMenu("Aide"); 
		
		miTheme = new JMenuItem("Générer un thème");
		miTheme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				genererTheme();
			}
		});
		
		miQuitter = new JMenuItem("Quitter");
		miQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitter();
			}
		});
		miEdition = new JMenuItem("Edition?");
		
		miAide = new JMenuItem("Aide?");
		//----------
		
		//Composants
		lblNomProfil = new JLabel();
		lblNomProfil.setFont(lblNomProfil.getFont().deriveFont(20f));
		lblNomProfil.setPreferredSize(new Dimension(10, 50));
		txtTheme = new JTextPane();
		txtTheme.setEditable(false);
		scrollTheme = new JScrollPane(txtTheme, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//----------
		
	}
	
	private void addComponents() {
		//Menu bar
		setJMenuBar(menuBar);
		menuBar.add(mFichier);
		menuBar.add(mEdition);
		menuBar.add(mAide);
		mFichier.add(miTheme);
		mFichier.add(new JSeparator());
		mFichier.add(miQuitter);
		mEdition.add(miEdition);
		mAide.add(miAide);
		//----------
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.insets = new Insets(4, 2, 0, 2);
		gbc.weightx = 1; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(lblNomProfil, gbc);
		
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(scrollTheme, gbc);
		//----------
	}
	
	
	
	private void genererTheme() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		DialogHistoriqueProfils dialog = new DialogHistoriqueProfils(this, (ProfilManager)model.getProfilManager());
		dialog.addProfilSelectedListener(this);
		dialog.afficher();
	}
	
	
	
	public void quitter()
	{
//		if ( JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment fermer l'application ?", "Attention", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, Utilitaire.ICON_MISH_QUESTION()) == JOptionPane.YES_OPTION )
		{
			System.exit(0);
		}
	}
	
	@Override
	public void update(Observable observable, Object args) {
		if ( observable == model ) {
			if ( args instanceof String ) {
				if ( "profil".equals(args) )
					if ( model.getProfil() != null && model.getProfil().getNom() != null ) {
						lblNomProfil.setText(model.getProfil().getNom() + (model.getProfil().getGenre()==null?"":" " + model.getProfil().getGenre().getSymbole() + ""));
						if ( model.getProfil().getTheme() != null 
								&& model.getProfil().getTheme().getSigne(Planete.SOLEIL) != null 
								&& model.getProfil().getTheme().getSigne(Angle.ASCENDANT) != null 
								&& model.getProfil().getTheme().getSigne(Planete.LUNE) != null ) {
							String enPlus = " - Soleil : " + model.getProfil().getTheme().getSigne(Planete.SOLEIL).getSymbole() + " / ";
							enPlus += "Asc : " + model.getProfil().getTheme().getSigne(Angle.ASCENDANT).getSymbole() + " / ";
							enPlus += "Lune : " + model.getProfil().getTheme().getSigne(Planete.LUNE).getSymbole();
							lblNomProfil.setText(lblNomProfil.getText() + enPlus);
						}
					}
					else
						lblNomProfil.setText("");
				else if ( "evaluate".equals(args) ) {
					StringBuilder sb = new StringBuilder();
					for ( int i = 0 ; i < model.getThemeEvals().size() ; i++ ) {
						sb.append(model.getThemeEvals().get(i));
						if ( i < model.getThemeEvals().size()-1 )
							sb.append("\n\n");
					}
					txtTheme.setText(sb.toString());
					txtTheme.setCaretPosition(0);
					
					//TODO
					DialogDataMissing dialog = new DialogDataMissing(this, model.getDataRules(), model.getProfil());
					dialog.addDataMissingListener(this);
					dialog.afficher();
				}
			}
		}
	}

	@Override
	public void profilSelected(Profil profil) {
		model.setProfil(profil);
	}

	@Override
	public void dataMissingSuivant() {
		// TODO Auto-generated method stub
	}
	@Override
	public void dataMissingPrecedant() {
		// TODO Auto-generated method stub
	}
	@Override
	public void dataMissingTerminer(boolean isChanged) {
		// TODO Auto-generated method stub
	}
	
}
