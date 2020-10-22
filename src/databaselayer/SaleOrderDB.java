package databaselayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controllayer.DataAccessException;
import modellayer.SaleOrder;

public class SaleOrderDB {
	private SaleOrder saleOrder;
	
	private static final String INSERT_Q = "INSERT INTO SaleOrder(id, date, amount, deliveryStatus, deleveryDate, invoiceNo, customerId) VALUES (?,?,?,?,?,?,?)";
	private static final String INSERT_INVOICE_Q = "INSERT INTO Invoice(paymentDate, amount) VALUES (?,?)";
	
	private PreparedStatement insertSaleOrder;
	private PreparedStatement insertInvoice;

	public SaleOrderDB() throws DataAccessException {
		init();
	}

	public void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertSaleOrder = con.prepareStatement(INSERT_Q);
			insertInvoice = con.prepareStatement(INSERT_INVOICE_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);

		}
	}

	public void insertOrder(SaleOrder order) throws DataAccessException {
		try {
			insertInvoice(order);
			insertSaleOrder.setInt(1, 1);
			insertSaleOrder.setDate(2, Date.valueOf(order.getDate()));
			insertSaleOrder.setInt(3, order.getAmount());
			insertSaleOrder.setString(4, order.getDeliveryStatus());
			insertSaleOrder.setDate(5, Date.valueOf(order.getDate()));
			insertSaleOrder.setInt(6,1);
			insertSaleOrder.setInt(7,1);
			
			insertSaleOrder.executeQuery();

		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
	}
	
	public void insertInvoice(SaleOrder order) throws DataAccessException {
		try {
			
			insertInvoice.setDate(1, Date.valueOf(order.getDate()));
			insertInvoice.setInt(2, order.getAmount());
			
			insertSaleOrder.executeQuery();
		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
	}
}
