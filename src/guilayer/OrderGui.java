package guilayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controllayer.SaleOrderController;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.time.LocalDateTime;
import java.util.List;

public class OrderGui extends JFrame {
	private SaleOrderLine saleOrderLine;
	private SaleOrder saleOrder;
	private SaleOrderController saleOrderController;
	private Object data[][];
	private JPanel contentPane;
	private JTextField textFieldNavn;
	private JTextField textFieldAddress;
	private JTextField textFieldZipCode;
	private JTextField textFieldCity;
	private JTextField textFieldCustomerID;
	private JTextField textFieldVareNo;
	private JTable tableOrder;

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
	 */
	public OrderGui() {
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblVareNo = new JLabel("Varer Nummer");

		textFieldVareNo = new JTextField();
		textFieldVareNo.setColumns(10);

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_1.add(lblVareNo);
		panel_1.add(textFieldVareNo);

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
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		contentPane.add(panel);
		contentPane.add(panel_2);
		contentPane.add(panel_1);

		JPanel panel_Order = new JPanel();
		contentPane.add(panel_Order);

		JScrollPane scrollPane_Order = new JScrollPane();
		panel_Order.add(scrollPane_Order);
		String[] columnNames = { "First Name", "Last Name" };

		tableOrder = new JTable(GenerateTable(saleOrder.getOrderLineList()), columnNames);
		panel_Order.add(tableOrder);

		tableOrder.update(getGraphics());

	}

	private void init() {
		saleOrderController = new SaleOrderController();
		saleOrderLine = new SaleOrderLine();
		saleOrder = new SaleOrder(LocalDateTime.now());

	}

	private String[][] GenerateTable(List<SaleOrderLine> orderlineList) {
		int i = 0;
		String[][] o = new String[orderlineList.size()][2];

		for (SaleOrderLine SOL : orderlineList) {
			if (orderlineList.size() == 0) {
				o[i][0] = "test";
				o[i][1] = "test";
				i++;
			} else {
				Product p = SOL.getProduct();
				o[i][0] = p.getName();
				o[i][1] = Integer.toString(SOL.getQuantity());
				i++;
			}

		}

		return o;
	}
}
