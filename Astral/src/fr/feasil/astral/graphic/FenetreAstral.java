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
import javax.swing.JTextPane;

import fr.feasil.astral.data.point.Angle;
import fr.feasil.astral.data.point.Planete;
import fr.feasil.astral.profil.Genre;
import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.theme.instance.TextTheme;
import fr.feasil.utils.Utilitaire;


public class FenetreAstral extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	
	//----------
	private JMenuBar menuBar;
	private JMenu mFichier, mEdition, mAide;
	private JMenuItem miQuitter, miEdition, miAide;
	//----------
	private JLabel lblNomProfil;
	private JTextPane txtTheme;
	private JScrollPane scrollTheme;
	//----------
	
	private ModelAstral model;
	
	public FenetreAstral(String title) {
		super(title); pack();
		initFrame();
		initComponents();
		addComponents();
		
		model = new ModelAstral();
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
		
		miQuitter = new JMenuItem("Quitter");
		miQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitter();
			}
		});
		miEdition = new JMenuItem("Edition?");
		miEdition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				TextTheme theme = new TextTheme("Positions des planètes\r\n" + 
				"Soleil 	13°45' 		Poissons\r\n" + 
				"Lune 	21°37' 		Verseau\r\n" + 
				"Mercure 	29°33' 		Poissons\r\n" + 
				"Vénus 	3°29' 		Verseau\r\n" + 
				"Mars 	12°15' 		Taureau\r\n" + 
				"Jupiter 	22°17' 		Sagittaire\r\n" + 
				"Saturne 	18°03' 		Capricorne\r\n" + 
				"Uranus 	29°55' 		Bélier\r\n" + 
				"Neptune 	16°04' 		Poissons\r\n" + 
				"Pluton 	22°32' 		Capricorne\r\n" + 
				"Chiron 	0°47' 		Bélier\r\n" + 
				"Cérès 	10°27' 		Sagittaire\r\n" + 
				"Pallas 	28°41' 	Я 	Balance\r\n" + 
				"Junon 	8°20' 		Gémeaux\r\n" + 
				"Vesta 	15°26' 		Poissons\r\n" + 
				"Noeud Nord 	25°58' 	Я 	Cancer\r\n" + 
				"Lilith 	20°10' 		Verseau\r\n" + 
				"Fortune 	25°28' 		Cancer\r\n" + 
				"AS 	17°35' 		Lion\r\n" + 
				"MC 	3°41' 		Taureau\r\n" + 
				"Planètes en maisons*\r\n" + 
				"Soleil 	Maison 8\r\n" + 
				"Lune 	Maison 7\r\n" + 
				"Mercure 	Maison 8\r\n" + 
				"Vénus 	Maison 6\r\n" + 
				"Mars 	Maison 10\r\n" + 
				"Jupiter 	Maison 5\r\n" + 
				"Saturne 	Maison 6\r\n" + 
				"Uranus 	Maison 9\r\n" + 
				"Neptune 	Maison 8\r\n" + 
				"Pluton 	Maison 6\r\n" + 
				"Chiron 	Maison 9\r\n" + 
				"Cérès 	Maison 4\r\n" + 
				"Pallas 	Maison 3\r\n" + 
				"Junon 	Maison 10\r\n" + 
				"Vesta 	Maison 8\r\n" + 
				"Noeud Nord 	Maison 12\r\n" + 
				"Lilith 	Maison 7\r\n" + 
				"Fortune 	Maison 12\r\n" + 
				"\r\n" + 
				"* Comme il est d'usage, nous considérons qu'une planète à moins de 1 degré de la maison suivante lui appartient, et nous prenons 2 degrés pour le cas de l'AS et du MC.\r\n" + 
				"Positions des maisons\r\n" + 
				"Maison 1 	17°35' 	Lion\r\n" + 
				"Maison 2 	6°42' 	Vierge\r\n" + 
				"Maison 3 	1°21' 	Balance\r\n" + 
				"Maison 4 	3°41' 	Scorpion\r\n" + 
				"Maison 5 	12°14' 	Sagittaire\r\n" + 
				"Maison 6 	18°27' 	Capricorne\r\n" + 
				"Maison 7 	17°35' 	Verseau\r\n" + 
				"Maison 8 	6°42' 	Poissons\r\n" + 
				"Maison 9 	1°21' 	Bélier\r\n" + 
				"Maison 10 	3°41' 	Taureau\r\n" + 
				"Maison 11 	12°14' 	Gémeaux\r\n" + 
				"Maison 12 	18°27' 	Cancer\r\n" + 
				"Liste des aspects\r\n" + 
				"			Orbe 	\r\n" + 
				"Soleil 	Conjonction 	Neptune 	Orbe 	2°19'\r\n" + 
				"Saturne 	Conjonction 	Pluton 	Orbe 	4°28'\r\n" + 
				"Vénus 	Carré 	Uranus 	Orbe 	3°33'\r\n" + 
				"Jupiter 	Carré 	Neptune 	Orbe 	6°12'\r\n" + 
				"Mercure 	Carré 	Jupiter 	Orbe 	7°16'\r\n" + 
				"Uranus 	Carré 	Pluton 	Orbe 	7°23'\r\n" + 
				"Mars 	Trigone 	Saturne 	Orbe 	5°48'\r\n" + 
				"Jupiter 	Trigone 	Uranus 	Orbe 	7°38'\r\n" + 
				"Lune 	Sextile 	Jupiter 	Orbe 	0°39'\r\n" + 
				"Soleil 	Sextile 	Mars 	Orbe 	1°30'\r\n" + 
				"Saturne 	Sextile 	Neptune 	Orbe 	1°58'\r\n" + 
				"Mars 	Sextile 	Neptune 	Orbe 	3°49'\r\n" + 
				"Mercure 	Sextile 	Vénus 	Orbe 	3°55'\r\n" + 
				"Soleil 	Sextile 	Saturne 	Orbe 	4°18'\r\n" + 
				"Uranus 	SemiCarré 	Neptune 	Orbe 	1°08'\r\n" + 
				"Soleil 	SemiCarré 	Uranus 	Orbe 	1°10'\r\n" + 
				"Mercure 	Quintile 	Saturne 	Orbe 	0°29'\r\n" + 
				"Jupiter 	SemiSextile 	Pluton 	Orbe 	0°14'\r\n" + 
				"Mercure 	SemiSextile 	Uranus 	Orbe 	0°22'\r\n" + 
				"Lune 	SemiSextile 	Pluton 	Orbe 	0°54'");
				Profil profil = new Profil("Ernest Vaudeleau", Genre.MASCULIN, theme);
				model.setProfil(profil);
			}
		});
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
		
		//TODO
	}
	
	private void addComponents() {
		//Menu bar
		setJMenuBar(menuBar);
		menuBar.add(mFichier);
		menuBar.add(mEdition);
		menuBar.add(mAide);
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
	public void update(Observable observable, Object args) {
		if ( observable == model ) {
			if ( args instanceof String ) {
				String arg = (String) args;
				if ( "profil".equals(arg) )
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
				else if ( "evaluate".equals(arg) ) {
					StringBuilder sb = new StringBuilder();
					for ( int i = 0 ; i < model.getThemeEvals().size() ; i++ ) {
						sb.append(model.getThemeEvals().get(i));
						if ( i < model.getThemeEvals().size()-1 )
							sb.append("\n\n");
					}
					txtTheme.setText(sb.toString());
					txtTheme.setCaretPosition(0);
				}
			}
		}
	}

}
