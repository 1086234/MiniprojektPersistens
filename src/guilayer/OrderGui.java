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
import java.awt.Font;

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
	private JTextField textField_Quantity;
	private JLabel lblTotalPrice;

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
		setBounds(100, 100, 1084, 600);
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
					betal();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnCancal = new JButton("Annuller");
		btnCancal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annullerOrdre();
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
				.createSequentialGroup().addContainerGap()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnRemoveOneOrderLine).addGap(18)
								.addComponent(btnCancal).addGap(18).addComponent(btnPay))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(10).addComponent(panel_Order,
								GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(5)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 499, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel_Order, GroupLayout.PREFERRED_SIZE, 458, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnPay)
										.addComponent(btnCancal).addComponent(btnRemoveOneOrderLine))
								.addGap(23)))));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(textFieldAddress,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(textFieldCity,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblAddress))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(textFieldZipCode,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblZipCode))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblCity))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(textFieldNavn,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblCustomerName))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(textFieldCustomerID,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblCustomerID)))
						.addContainerGap(21, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(40).addComponent(lblCustomerID)
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
						.addGap(243)));
		panel.setLayout(gl_panel);

		JLabel lblQuantity = new JLabel("Antal ");
		panel_1.add(lblQuantity);

		textField_Quantity = new JTextField();
		panel_1.add(textField_Quantity);

		textField_Quantity.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProductToOrderLine();
				} catch (NumberFormatException | DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		textField_Quantity.setColumns(10);

		lblTotalPrice = new JLabel("");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 32));

		JLabel lblKR = new JLabel("Kr.");
		lblKR.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GroupLayout gl_panel_Order = new GroupLayout(panel_Order);
		gl_panel_Order.setHorizontalGroup(gl_panel_Order.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane_Order, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
				.addGroup(gl_panel_Order.createSequentialGroup().addContainerGap(707, Short.MAX_VALUE)
						.addComponent(lblTotalPrice).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblKR)
						.addContainerGap()));
		gl_panel_Order.setVerticalGroup(gl_panel_Order.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_Order
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane_Order, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_Order
						.createParallelGroup(Alignment.BASELINE).addComponent(lblTotalPrice).addComponent(lblKR))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
		lblTotalPrice.setText(Integer.toString(saleOrderController.CalcTotalPrice()));
	}

	private void fjernVare() {
		int row = tableOrder.getSelectedRow();

		if (row == -1) {

		} else {
			saleOrderController.removeSaleOrderLine(row);
			fillTable();
		}

	}

	private void annullerOrdre() {
		saleOrderController.clearOrderLineList();
		fillTable();
	}

	private void addProductToOrderLine() throws NumberFormatException, DataAccessException {
		int quantity = 1;
		if (textField_Quantity.getText().equals("")) {

		} else {
			quantity = Integer.parseInt(textField_Quantity.getText());
		}
		int fundet = saleOrderController.addProduct(Integer.parseInt(txtVareNo.getText()), quantity);

		switch (fundet) {
		case 1:
			JOptionPane.showMessageDialog(null, "Varenummeret findes ikke. ", "InfoBox: " + "Ikke i systemet",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Der er ikke nok af den valgte vare på lager",
					"InfoBox: " + "Ikke nok på lager", JOptionPane.INFORMATION_MESSAGE);
			break;
		}

		fillTable();
	}

	private void betal() throws DataAccessException {
		// AddOrdreLine to database
		saleOrderController.addOrder(saleOrderController.getOrder());

		// Remove the numbers of products sold from database

		// Clear the table
	}
}
