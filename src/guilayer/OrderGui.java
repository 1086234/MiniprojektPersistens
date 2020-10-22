package guilayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controllayer.DataAccessException;
import controllayer.SaleOrderController;
import modellayer.Customer;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class OrderGui extends JFrame {
	private SaleOrderController saleOrderController;
	private SaleOrderLineListTableModel solltm;
	private JTable tableOrder;

	private JPanel contentPane;
	private JTextField textFieldNavn;
	private JTextField textFieldAddress;
	private JTextField textFieldZipCode;
	private JTextField textFieldCity;
	private JTextField textFieldCustomerID;
	private JTextField txtVareNo;
	private JTextField textFieldQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderGui frame = new OrderGui();
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
	public OrderGui() throws DataAccessException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JLabel lblVareNo = new JLabel("Varer Nummer");

		txtVareNo = new JTextField();
		txtVareNo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProductToOrderLine();
				} catch (NumberFormatException | DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtVareNo.setColumns(10);

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_1.add(lblVareNo);
		panel_1.add(txtVareNo);

		JLabel lblCustomerID = new JLabel("kunde ID: ");

		JLabel lblCustomerName = new JLabel("navn : ");

		JLabel lblAddress = new JLabel("adresse :");

		textFieldZipCode = new JTextField();
		textFieldZipCode.setColumns(10);

		JLabel lblZipCode = new JLabel("PostNummer :");

		JLabel lblCity = new JLabel("by :");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(lblCustomerID);

		textFieldCustomerID = new JTextField();
		textFieldCustomerID.setText("");
		textFieldCustomerID.setColumns(10);
		panel.add(textFieldCustomerID);
		panel.add(lblCustomerName);

		textFieldNavn = new JTextField();
		textFieldNavn.setColumns(10);
		panel.add(textFieldNavn);
		panel.add(lblCity);

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		panel.add(textFieldCity);
		panel.add(lblAddress);

		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		panel.add(textFieldAddress);
		panel.add(lblZipCode);
		panel.add(textFieldZipCode);

		JPanel panel_Order = new JPanel();

		JScrollPane scrollPane_Order = new JScrollPane();

		tableOrder = new JTable();
		tableOrder.getTableHeader().setReorderingAllowed(false);
		scrollPane_Order.setViewportView(tableOrder);

		JButton btnPay = new JButton("Betal");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					betal();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnCancal = new JButton("Annuller");
		btnCancal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		JButton btnRemoveOneOrderLine = new JButton("fjern en vare");
		btnRemoveOneOrderLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fjernVare();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnRemoveOneOrderLine).addGap(18)
								.addComponent(btnCancal).addGap(18).addComponent(btnPay))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panel_Order, GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(5)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_Order, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnPay)
										.addComponent(btnCancal).addComponent(btnRemoveOneOrderLine))
								.addGap(23))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE).addGap(16)));

		JLabel lblQuantity = new JLabel("Antal ");
		panel_1.add(lblQuantity);

		textFieldQuantity = new JTextField();
		panel_1.add(textFieldQuantity);
		textFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProductToOrderLine();
				} catch (NumberFormatException | DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		textFieldQuantity.setColumns(10);
		GroupLayout gl_panel_Order = new GroupLayout(panel_Order);
		gl_panel_Order.setHorizontalGroup(gl_panel_Order.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_Order, GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE));
		gl_panel_Order.setVerticalGroup(gl_panel_Order.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Order.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_Order, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(53, Short.MAX_VALUE)));
		panel_Order.setLayout(gl_panel_Order);
		contentPane.setLayout(gl_contentPane);
		init();

	}

	private void init() throws DataAccessException {
		saleOrderController = new SaleOrderController();
		saleOrderController.createOrder();
		fillTable();
	}

	private SaleOrderLine getSelectedProduct() {
		int row = tableOrder.getSelectedRow();
		SaleOrderLine currLine = null;
		if (row >= 0) {
			currLine = solltm.getSaleOrderLineOfRow(row);
		}
		return currLine;
	}

	private void fillTable() {
		solltm = new SaleOrderLineListTableModel();
		this.tableOrder.setModel(solltm);
		List<SaleOrderLine> data = saleOrderController.getOrderLineList();
		solltm.setData(data);
	}

	private void fjernVare() {
		int row = tableOrder.getSelectedRow();

		if (row == -1) {

		} else {
			saleOrderController.removeSaleOrderLine(row);
			fillTable();
		}

	}

	private void clear() {
		saleOrderController.clearOrderLineList();
		fillTable();
	}

	private void addProductToOrderLine() throws NumberFormatException, DataAccessException {

		boolean fundet = saleOrderController.addProduct(Integer.parseInt(txtVareNo.getText()),
				Integer.parseInt(textFieldQuantity.getText()));

		if (!fundet) {
			JOptionPane.showMessageDialog(null, "Den er ikke en vare med det varer nummer ",
					"InfoBox: " + "Ikke i systemet", JOptionPane.INFORMATION_MESSAGE);

		}
		fillTable();
	}

	private void betal() throws DataAccessException, SQLException {
		// Add costumer to order
		if (textFieldCustomerID.getText() == "")
			saleOrderController.addCustomer(Integer.parseInt(textFieldCustomerID.getText()));
		else
			saleOrderController.addCustomer();

		// AddOrdreLine to database
		saleOrderController.addOrder(saleOrderController.getOrder());

		// Remove the numbers of products sold from database
		saleOrderController.updateNumberOfProducts();

		// Clear the table
		clear();
	}
}