package fr.feasil.astral.graphic.datamissing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import fr.feasil.astral.profil.Profil;
import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.theme.ruleengine.Expression;

public class DialogDataMissing extends JDialog implements Observer {
	private static final long serialVersionUID = 1L;
	
	//----------
	private JComboBox<Expression> cbExpression;
	private JTextArea txtArgument;
	private JScrollPane scrollArgument;
	private JButton btnPrecedent, btnSuivant, btnTerminer;
	//----------
	
	
	private ModelDataMissing model;
	private List<DataMissingListener> listeners = new ArrayList<>();
	
	public DialogDataMissing(JFrame parent, DataRules dataRules, Profil profil) {
		super(parent, "Informations manquantes", true);
		model = new ModelDataMissing(dataRules, profil);
		model.addObserver(this);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	model.deleteObserver(DialogDataMissing.this);
		    	listeners.clear();
		    }
		});
		
		initComponents();
		addComponents();
		
		model.loadDataMissing();
		
		setPreferredSize(new Dimension(800, 500));
	}
	
	private void initComponents() {
		getContentPane().setLayout(new GridBagLayout());
		
		//----------
		cbExpression = new JComboBox<>();
		cbExpression.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				setText(((Expression)value).getStringValue());
				return this;
			}
		});
		txtArgument = new JTextArea();
		scrollArgument = new JScrollPane(txtArgument, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//----------
		btnPrecedent = new JButton("< Précédent");
		btnSuivant = new JButton("Suivant >");
		btnTerminer = new JButton("Terminer");
		//----------
		//TODO actionListeners
	}
	
	private void addComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(4, 2, 0, 2);
		gbc.weightx = 1; gbc.weighty = 0;
		gbc.gridwidth = 4;
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(cbExpression, gbc);
		
//		gbc.insets = new Insets(4, 2, 0, 2);
		gbc.weightx = 1; gbc.weighty = 1;
//		gbc.gridwidth = 3;
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(scrollArgument, gbc);
		
		gbc.insets = new Insets(4, 2, 4, 0);
		gbc.weightx = 0; gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		getContentPane().add(btnPrecedent, gbc);
		
		gbc.insets = new Insets(4, 2, 4, 0);
//		gbc.weightx = 0; gbc.weighty = 0;
//		gbc.gridwidth = 1;
		gbc.gridx = 1; gbc.gridy = 2;
//		gbc.fill = GridBagConstraints.NONE;
		getContentPane().add(btnSuivant, gbc);
		
//		gbc.insets = new Insets(4, 2, 4, 0);
		gbc.weightx = 1; gbc.weighty = 0;
//		gbc.gridwidth = 1;
		gbc.gridx = 2; gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(new JPanel(), gbc);
		
		gbc.insets = new Insets(4, 0, 4, 2);
		gbc.weightx = 0; gbc.weighty = 0;
//		gbc.gridwidth = 1;
		gbc.gridx = 3; gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		getContentPane().add(btnTerminer, gbc);
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
	
	
	public void addDataMissingListener(DataMissingListener listener) {
		listeners.add(listener);
	}
	public void deleteDataMissingListener(DataMissingListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	public void update(Observable observable, Object args) {
		if ( observable == model ) {
			if ( args instanceof String ) {
				if ( "missingReady".equals(args) ) {
					//TODO
					for ( Expression e : model.getListMissing() )
						cbExpression.addItem(e);
				}
			}
		}
	}

}
