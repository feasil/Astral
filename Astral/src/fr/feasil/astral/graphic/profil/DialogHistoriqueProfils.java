package fr.feasil.astral.graphic.profil;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.feasil.astral.data.point.Angle;
import fr.feasil.astral.data.point.Planete;
import fr.feasil.astral.profil.Genre;
import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.profil.ProfilManager;

public class DialogHistoriqueProfils extends JDialog implements Observer {
	private static final long serialVersionUID = 1L;
	
	//----------
	private JLabel lblImport;
	private JTextArea txtImport;
	private JScrollPane scrollImport;
	//----------
	private JLabel lblProfils;
	private JPanel panBtnProfils;
	private List<JButton> btnProfils;
	//----------
	private JButton btnGenerer;
	//----------
	private JPanel panProfil, panBtnTheme;
	private JLabel lblNom, lblGenre;
	private JTextField txtNom;
	private JComboBox<Genre> cbGenre;
	private List<JButton> btnTheme;
	//----------
	
	
	private ModelHistoriqueProfils model;
	private List<ProfilSelectedListener> listeners = new ArrayList<>();
	
	public DialogHistoriqueProfils(JFrame parent, ProfilManager<Profil> profilManager) {
		super(parent, "Profils", true);
		model = new ModelHistoriqueProfils(profilManager);
		model.addObserver(this);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	model.deleteObserver(DialogHistoriqueProfils.this);
		    	listeners.clear();
		    }
		});
		initComponents();
		addComponents();
		
		setPreferredSize(new Dimension(800, 500));
		setText();
	}
	private void initComponents() {
		getContentPane().setLayout(new GridBagLayout());
		
		//Composants
		lblImport = new JLabel("Importer Données");
		lblImport.setFont(lblImport.getFont().deriveFont(20f));
		lblImport.setPreferredSize(new Dimension(10, 50));
		txtImport = new JTextArea();
		scrollImport = new JScrollPane(txtImport, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//----------
		lblProfils = new JLabel("Derniers Profils");
		lblProfils.setFont(lblProfils.getFont().deriveFont(20f));
		lblProfils.setPreferredSize(new Dimension(10, 50));
		panBtnProfils = new JPanel(new GridBagLayout());
		ActionListener alBtnProfil = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.selectProfil(Integer.parseInt(e.getActionCommand()));
			}
		};
		btnProfils = new ArrayList<>();
		for ( int i = 0 ; i < 9 ; i++ ) {
			JButton btn = new JButton();
			btn.setMargin(new Insets(1, 1, 1, 1));
			if ( i < model.getLastProfils().size() ) {
				btn.setText(model.getLastProfils().get(i).getNom());
				btn.setActionCommand(Integer.toString(i));
				btn.addActionListener(alBtnProfil);
			}
			else
				btn.setEnabled(false);
			
			btnProfils.add(btn);
		}
		//----------
		btnGenerer = new JButton("Générer le thème");
		btnGenerer.setActionCommand("genererTheme");
		btnGenerer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( "genererTheme".equals(e.getActionCommand()) ) {
					model.createProfil(txtImport.getText());
				}
				else if ( "finishProfil".equals(e.getActionCommand()) ) {
					if ( txtNom.getText() == null 
							|| txtNom.getText().trim().length() == 0 ) {
						JOptionPane.showMessageDialog(DialogHistoriqueProfils.this, "Le nom doit être renseigné", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if ( cbGenre.getSelectedItem() == null ) {
						JOptionPane.showMessageDialog(DialogHistoriqueProfils.this, "Le genre doit être renseigné", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
					model.finishProfil(txtNom.getText(), (Genre) cbGenre.getSelectedItem());
				}
					
			}
		});
		//----------
		
		panProfil = new JPanel(new GridBagLayout());
		panProfil.setVisible(false);
		panBtnTheme = new JPanel(new GridBagLayout());
		panBtnTheme.setVisible(false);
		lblNom = new JLabel("Nom : ");
		lblGenre = new JLabel("Genre : ");
		txtNom = new JTextField();
		cbGenre = new JComboBox<>();
		cbGenre.addItem(null); for ( Genre g : Genre.values() ) cbGenre.addItem(g);
		cbGenre.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				String str;
				if ( value instanceof Genre )
					str = ((Genre)value).getSymbole() + " " + ((Genre)value).getLibelle();
				else 
					str = " ";
				return super.getListCellRendererComponent(list, str, index, isSelected, cellHasFocus);
			}
		});
		btnTheme = new ArrayList<>();
		for ( int i = 0 ; i < 3 ; i++ ) {
			JButton btn = new JButton();
			btn.setMargin(new Insets(1, 1, 1, 1));
			btn.setEnabled(false);
			btnTheme.add(btn);
		}
		//----------
		
	}
	private void addComponents() {
		JPanel panB = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//panBtnProfils
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		for ( int i = 0 ; i< btnProfils.size() ; i++  ) {
			gbc.gridx = i%3; gbc.gridy = (int) i/3;
			panBtnProfils.add(btnProfils.get(i), gbc);
		}
		//----------
		//panBtnTheme
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.weightx = 1; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		for ( int i = 0 ; i< btnTheme.size() ; i++  ) {
			gbc.gridx = i%3; gbc.gridy = (int) i/3;
			panBtnTheme.add(btnTheme.get(i), gbc);
		}
		gbc.gridwidth = 3;
		gbc.weightx = 3; gbc.weighty = 1;
		gbc.gridx = 0; gbc.gridy ++;
		panBtnTheme.add(new JPanel(), gbc);
		gbc.gridwidth = 1;
		//----------
		//panProfil
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.weightx = 0; gbc.weighty = 0;
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		panProfil.add(lblNom, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		panProfil.add(lblGenre, gbc);
		gbc.weightx = 1; gbc.weighty = 0;
		gbc.gridx = 1; gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panProfil.add(txtNom, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		panProfil.add(cbGenre, gbc);
		
		gbc.gridwidth = 2;
		gbc.weightx = 2; gbc.weighty = 1;
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		panProfil.add(new JPanel(), gbc);
		gbc.gridwidth = 1;
		//----------
		
		
		//panB
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.weightx = 0; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		panB.add(btnGenerer, gbc);
		//----------
		
		//----------
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.insets = new Insets(4, 2, 0, 2);
		gbc.weightx = 1; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(lblImport, gbc);
		
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(scrollImport, gbc);
		getContentPane().add(panProfil, gbc);
		
		gbc.gridheight = 2;
		gbc.gridx = 1; gbc.gridy = 0;
		gbc.weightx = 0; gbc.weighty = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		getContentPane().add(new JSeparator(JSeparator.VERTICAL), gbc);
		
		gbc.gridheight = 1;
		gbc.gridx = 2; gbc.gridy = 0;
		gbc.insets = new Insets(4, 2, 0, 2);
		gbc.weightx = 1; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(lblProfils, gbc);
		
		gbc.gridx = 2; gbc.gridy = 1;
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(panBtnProfils, gbc);
		getContentPane().add(panBtnTheme, gbc);
		//----------
		
		gbc.gridwidth = 3;
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.weightx = 3; gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(panB, gbc);
		//----------
	}
	
	
	
	public void afficher() {
		Runnable doRun = new Runnable() {
            @Override
            public void run() {
                setVisible(false);
                pack();
                setLocationRelativeTo(getParent());
                setVisible(true);
            }
        };
        SwingUtilities.invokeLater(doRun);
	}
	
	
//	public JRootPane createRootPane() {
//		JRootPane rootPane = new JRootPane();
//		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
//		Action action = new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				dispose();
//			}
//		};
//		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//		inputMap.put(stroke, "ESCAPE");
//		rootPane.getActionMap().put("ESCAPE", action);
//		return rootPane;
//	}
	
	private void setText() {
		txtImport.setText("Positions des planètes\r\n" + 
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
		txtImport.setCaretPosition(0);
	}
	
	
	
	public void addProfilSelectedListener(ProfilSelectedListener listener) {
		listeners.add(listener);
	}
	public void deleteProfilSelectedListener(ProfilSelectedListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	public void update(Observable o, Object args) {
		if ( o == model ) {
			if ( args instanceof String ) {
				if ( "createProfil".equals(args) ){
					btnTheme.get(0).setText("<html><center><div style='font-size:12;'>" + Planete.SOLEIL.getNom() + "</div><br><div style='font-size:10;'>" + model.getProfil().getTheme().getSigne(Planete.SOLEIL) + "</div></center></html>");
					btnTheme.get(1).setText("<html><center><div style='font-size:12;'>" + Angle.ASCENDANT.getNom() + "</div><br><div style='font-size:10;'>" + model.getProfil().getTheme().getSigne(Angle.ASCENDANT) + "</div></center></html>");
					btnTheme.get(2).setText("<html><center><div style='font-size:12;'>" + Planete.LUNE.getNom() + "</div><br><div style='font-size:10;'>" + model.getProfil().getTheme().getSigne(Planete.LUNE) + "</div></center></html>");
					
					btnGenerer.setText("Valider");
					btnGenerer.setActionCommand("finishProfil");
					lblImport.setText("Création du profil");
					lblProfils.setText("");
					scrollImport.setVisible(false);
					panProfil.setVisible(true);
					panBtnProfils.setVisible(false);
					panBtnTheme.setVisible(true);
				}
				else if ( "finishProfil".equals(args) ){
					for ( ProfilSelectedListener l : listeners )
						l.profilSelected(model.getProfil());
					dispose();
				}
			}
		}
	}
}
