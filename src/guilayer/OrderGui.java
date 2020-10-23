package guilayer;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controllayer.DataAccessException;
import controllayer.SaleOrderController;
import modellayer.SaleOrderLine;

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
	private JLabel lblTotalPrices;

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
		setBounds(100, 100, 1200, 700);
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

		textFieldCustomerID = new JTextField();
		textFieldCustomerID.setText("");
		textFieldCustomerID.setColumns(10);

		textFieldNavn = new JTextField();
		textFieldNavn.setColumns(10);

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);

		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);

		JPanel panel_Order = new JPanel();

		JScrollPane scrollPane_Order = new JScrollPane();

		tableOrder = new JTable();
		tableOrder.getTableHeader().setReorderingAllowed(false);
		scrollPane_Order.setViewportView(tableOrder);

		JButton btnPay = new JButton("Betal");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					confirmOrder();
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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 165, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(715)
							.addComponent(btnRemoveOneOrderLine)
							.addGap(18)
							.addComponent(btnCancal)
							.addGap(18)
							.addComponent(btnPay))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_Order, GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_Order, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPay)
						.addComponent(btnCancal)
						.addComponent(btnRemoveOneOrderLine))
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
					.addGap(16))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(20)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblCustomerID)
										.addComponent(textFieldCustomerID, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCustomerName)
										.addComponent(textFieldNavn, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCity)
										.addComponent(textFieldCity, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAddress)
										.addComponent(textFieldAddress, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblZipCode).addComponent(textFieldZipCode,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(40)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(63).addComponent(lblCustomerID)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldCustomerID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblCustomerName)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldNavn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblCity)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblAddress)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblZipCode)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldZipCode,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(334)));
		panel.setLayout(gl_panel);

		JLabel lblQuantity = new JLabel("Antal ");
		panel_1.add(lblQuantity);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setText("");
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

		JLabel lblkr = new JLabel("Kr.");
		lblkr.setFont(new Font("Tahoma", Font.PLAIN, 30));

		lblTotalPrices = new JLabel("");
		lblTotalPrices.setFont(new Font("Tahoma", Font.PLAIN, 36));
		GroupLayout gl_panel_Order = new GroupLayout(panel_Order);
		gl_panel_Order.setHorizontalGroup(gl_panel_Order.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Order.createSequentialGroup().addContainerGap(10, Short.MAX_VALUE)
						.addGroup(gl_panel_Order.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_Order, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 964,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING,
										gl_panel_Order.createSequentialGroup().addComponent(lblTotalPrices)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblkr)
												.addContainerGap()))));
		gl_panel_Order.setVerticalGroup(gl_panel_Order.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_Order
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane_Order, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE).addGroup(gl_panel_Order
						.createParallelGroup(Alignment.BASELINE).addComponent(lblkr).addComponent(lblTotalPrices))
				.addContainerGap()));
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

		lblTotalPrices.setText(Integer.toString(saleOrderController.CalcTotalPrice()));
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
		if (textFieldQuantity.getText().isEmpty())
			textFieldQuantity.setText("1");

		int fundet = saleOrderController.addProduct(Integer.parseInt(txtVareNo.getText()),
				Integer.parseInt(textFieldQuantity.getText()));
		if (fundet == 1) {
			JOptionPane.showMessageDialog(null, "Der er ikke en vare med det varenummer",
					"InfoBox: " + "Ikke i systemet", JOptionPane.INFORMATION_MESSAGE);
		} else if (fundet == 2) {
			JOptionPane.showMessageDialog(null, "Der er ikke nok varer på laget for at lave denne ordre",
					"InfoBox: " + "Ikke nok på lager", JOptionPane.INFORMATION_MESSAGE);
		}

		fillTable();
	}
	
	private void confirmOrder() throws DataAccessException, SQLException {
		// Add costumer to order
		if (!textFieldCustomerID.getText().isEmpty())
			saleOrderController.addCustomer(Integer.parseInt(textFieldCustomerID.getText()));
		else
			saleOrderController.addCustomer();

		// AddOrdreLine to database
		saleOrderController.addOrder(saleOrderController.getOrder());

		// Remove the numbers of products sold from database
		saleOrderController.updateNumberOfProducts();
		saleOrderController.endOrder();
	
		// Clear the table
		saleOrderController.createOrder();
		fillTable();
		
	}
}