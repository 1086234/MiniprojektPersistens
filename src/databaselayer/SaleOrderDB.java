package databaselayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import controllayer.DataAccessException;
import modellayer.Product;
import modellayer.SaleOrder;

public class SaleOrderDB {
	private SaleOrder saleOrder;

	private static final String INSERT_Q = "INSERT INTO SaleOrder(date, amount, deliveryStatus, deleveryDate, invoiceNo, customerId) VALUES (?,?,?,?,?,?)";
	private static final String INSERT_INVOICE_Q = "INSERT INTO Invoice(paymentDate, amount) VALUES (?,?)";
	private static final String GET_MAX_INVIOCE_Q = "SELECT MAX(invoiceNo) FROM Invoice";
	private static final String GET_ID_Q = "select ID FROM SaleOrder WHERE invoiceNo = ?";
	private PreparedStatement insertSaleOrderQ;
	private PreparedStatement insertInvoiceQ;
	private PreparedStatement getMaxInvoiceQ;
	private PreparedStatement getIDQ;
	public SaleOrderDB() throws DataAccessException {
		init();
	}

	public void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			getIDQ = con.prepareStatement(GET_ID_Q);
			insertSaleOrderQ = con.prepareStatement(INSERT_Q);
			insertInvoiceQ = con.prepareStatement(INSERT_INVOICE_Q);
			getMaxInvoiceQ = con.prepareStatement(GET_MAX_INVIOCE_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);

		}
	}

	public int insertOrder(SaleOrder order) throws DataAccessException, SQLException {
		insertInvoice(order);
		 int findId = getMaxInvoice();
		 int id = 0;
		try {
			insertSaleOrderQ.setDate(1, order.getDate());
			insertSaleOrderQ.setInt(2, order.getAmount());
			insertSaleOrderQ.setString(3, order.getDeliveryStatus());
			insertSaleOrderQ.setDate(4, order.getDate());
			insertSaleOrderQ.setInt(5, findId);// invoiceNo
			if (order.getCustomer() == null)
				insertSaleOrderQ.setNull(6, java.sql.Types.INTEGER);// customerId
			else
				insertSaleOrderQ.setInt(6, order.getCustomer().getId());

			insertSaleOrderQ.executeUpdate();
			 id = getId(findId);
		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return id;
	}

	public void insertInvoice(SaleOrder order) throws DataAccessException {
		try {

			insertInvoiceQ.setDate(1, order.getDate());
			insertInvoiceQ.setInt(2, order.getAmount());

			insertInvoiceQ.executeUpdate();
		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
	}
	public int getId(int id) throws SQLException, DataAccessException {

		int res = 0;
		try {
			getIDQ.setInt(1, id);
			ResultSet rs = getIDQ.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);

		}
		return res;
	}
	public int getMaxInvoice() throws SQLException {
		String sql = "SELECT MAX(invoiceNo) FROM SaleOrder";
		ResultSet rs = getMaxInvoiceQ.executeQuery();
		int maxId = 0;
		if (rs.next())
			maxId = rs.getInt(1);

		return maxId;
	}
}
