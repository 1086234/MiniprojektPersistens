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

import gui.EmployeeListTableModel;
import model.Employee;
import modellayer.SaleOrderLine;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class OrderGui extends JFrame {
	private SaleOrderLineListTableModel saleOrderLineListTableModel;
	private JTable tblSaleOrderLine;
	
	private JPanel contentPane;
	private JTextField textFieldNavn;
	private JTextField textFieldAddress;
	private JTextField textFieldZipCode;
	private JTextField textFieldCity;
	private JTextField textFieldCustomerID;
	private JTextField textFieldVareNo;
	
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
		//init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		
		JPanel pnlEmployeeList = new JPanel();
		contentPane.add(pnlEmployeeList);
		pnlEmployeeList.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlEmployeeList.add(scrollPane, BorderLayout.CENTER);

		tblSaleOrderLine = new JTable();
		scrollPane.setViewportView(tblSaleOrderLine);

	
		
		
		
		
		
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
	}
	private void init() {
		this.saleOrderLineListTableModel = new SaleOrderLineListTableModel();
		this.tblSaleOrderLine.setModel(saleOrderLineListTableModel);

		// listen to selection changes in the Employee table
		
	}
	private Object tblEmployeesSelectionChanged() {
		SaleOrderLine currEmployee = getCurrentSaleOrderLine();
	//	displayEmployeeObject(currEmployee);
		return null;
	}
	private SaleOrderLine getCurrentSaleOrderLine() {
		int selectedRow = this.tblSaleOrderLine.getSelectedRow();
		SaleOrderLine currEmployee = null;
		if (selectedRow > -1) {
			currEmployee = this.saleOrderLineListTableModel.getEmployeeOfRow(selectedRow);
		}
		return currEmployee;
	}
}
