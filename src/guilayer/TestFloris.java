package guilayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllayer.DataAccessException;
import controllayer.SaleOrderController;
import modellayer.Product;
import modellayer.SaleOrderLine;

public class TestFloris extends JFrame {
	private SaleOrderController saleOrderController;
	private String data[][];

	private JPanel contentPane;
	private JTextField txtFornavn;
	private JTextField txtEfterNavn;
	private JTextField txtPostNr;
	private JTextField txtBy;
	private JTextField txtTelefon;
	private JButton btnBetal;
	private JButton btnAnnuller;
	private JTable jt;

	final String columnNames[] = { "ID", "NAME", "QUANTITY" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFloris frame = new TestFloris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws DataAccessException
	 */
	public TestFloris() throws DataAccessException {
		init();
		saleOrderController.createOrder();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFornavn = new JLabel("Fornavn:");
		lblFornavn.setBounds(30, 70, 60, 16);
		contentPane.add(lblFornavn);

		JLabel lblEfternavn = new JLabel("Efternavn:");
		lblEfternavn.setBounds(30, 100, 60, 16);
		contentPane.add(lblEfternavn);

		JLabel lblPostNr = new JLabel("PostNr:");
		lblPostNr.setBounds(30, 130, 60, 16);
		contentPane.add(lblPostNr);

		JLabel lblBy = new JLabel("By:");
		lblBy.setBounds(30, 160, 60, 16);
		contentPane.add(lblBy);

		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(30, 192, 60, 16);
		contentPane.add(lblTelefon);

		txtFornavn = new JTextField();
		txtFornavn.setBounds(110, 70, 116, 22);
		contentPane.add(txtFornavn);
		txtFornavn.setColumns(10);

		txtEfterNavn = new JTextField();
		txtEfterNavn.setColumns(10);
		txtEfterNavn.setBounds(110, 100, 116, 22);
		contentPane.add(txtEfterNavn);

		txtPostNr = new JTextField();
		txtPostNr.setColumns(10);
		txtPostNr.setBounds(110, 130, 116, 22);
		contentPane.add(txtPostNr);

		txtBy = new JTextField();
		txtBy.setColumns(10);
		txtBy.setBounds(110, 160, 116, 22);
		contentPane.add(txtBy);

		txtTelefon = new JTextField();
		txtTelefon.setColumns(10);
		txtTelefon.setBounds(110, 190, 116, 22);
		contentPane.add(txtTelefon);

		jt = new JTable(GenerateTable(saleOrderController.getOrderLineList()), columnNames);
		jt.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(jt);
		contentPane.add(sp);
		sp.setBounds(250, 70, 550, 250);

		btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				annuller();
			}
		});

		btnBetal = new JButton("Betal");
		btnBetal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				betal();
			}
		});
		btnBetal.setBounds(850, 400, 95, 30);
		contentPane.add(btnBetal);

		btnAnnuller.setBounds(750, 400, 95, 30);
		contentPane.add(btnAnnuller);

	}

	private void init() throws DataAccessException {
		saleOrderController = new SaleOrderController();
		saleOrderController.createOrder();
	}

	private String[][] GenerateTable(List<SaleOrderLine> orderlineList) {
		int i = 0;

		data = new String[orderlineList.size()][3];

		for (SaleOrderLine SOL : orderlineList) {
			Product p = SOL.getProduct();
			data[i][0] = Integer.toString(p.getVareNo());
			data[i][1] = p.getName();
			data[i][2] = Integer.toString(SOL.getQuantity());
			;
			i++;
		}
		return data;
	}

	private void betal() {
		// Alt i starten

		// Cleanup
		jt.setModel(new DefaultTableModel(null, columnNames));

		// Set up
		saleOrderController.createOrder();
		// jt.setModel(new
		// DefaultTableModel(GenerateTable(saleOrderController.getOrderLineList()),
		// columnNames));
		clearPersonData();
	}

	private void annuller() {
		clearTableData();
		clearPersonData();
	}

	private void clearTableData() {
		saleOrderController.clearOrderLineList();
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "";
			data[i][1] = "";
			data[i][2] = "";
		}
	}
	
	private void clearPersonData() {
		txtFornavn.setText("");
		txtEfterNavn.setText("");
		txtPostNr.setText("");
		txtBy.setText("");
		txtTelefon.setText("");
	}

}
