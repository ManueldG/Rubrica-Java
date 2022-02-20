package it.java.rubrica.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

import it.java.rubrica.business.RubricaBusiness;
import it.java.rubrica.model.Contatto;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

public class Rubrica {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rubrica window = new Rubrica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public Rubrica() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 714, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 700, 417);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Inserisci contatto", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(27, 32, 49, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(79, 29, 96, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cognome");
		lblNewLabel_1_1.setBounds(27, 87, 49, 14);
		panel.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(79, 84, 96, 20);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Telefono");
		lblNewLabel_1_2.setBounds(27, 144, 49, 14);
		panel.add(lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(79, 141, 96, 20);
		panel.add(textField_2);
		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contatto nuovoContatto = new Contatto();
				
				nuovoContatto.setNome(textField.getText());
				nuovoContatto.setCognome(textField_1.getText());
				nuovoContatto.setTelefono(textField_2.getText());
				
				try {
					int id = RubricaBusiness.getInstance().aggiungiContatto(nuovoContatto);
					
					if (id>0) {
						JOptionPane.showMessageDialog(null, "contatto inserito con successo");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					}
						
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(79, 205, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annulla");
		btnNewButton_1.setBounds(178, 205, 89, 23);
		panel.add(btnNewButton_1);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		tabbedPane.addTab("Ricerca contatti", null, scrollPane, null);
		
		scrollPane.add(tabbedPane_1);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Telefono", "Cognome", "Nome"
			}
		));
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
	
		List<Contatto> contatti;
		
		try {
			contatti = RubricaBusiness.getInstance().RicercaContatto();
			
			for(Contatto c : contatti) {
				Vector<Comparable> rowData = new Vector<Comparable>();
				rowData.add(c.getId());
				rowData.add(c.getNome());
				rowData.add(c.getCognome());
				rowData.add(c.getTelefono());
				dtm.addRow(rowData);
			}
			
		}
		catch(SQLException e1){
			e1.printStackTrace();
		}
		
		
		
		scrollPane.setViewportView(table);
	}
}
