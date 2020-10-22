package databaselayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controllayer.DataAccessException;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderDB {
	private SaleOrder saleOrder;
	private static final String INSERT_Q = "INSERT INTO SaleOrder(date, amount, deliveryStatus, deleveryDate, customerId) VALUES (?,?,?,?,?)";
	private PreparedStatement insertSaleOrder;

	public SaleOrderDB() throws DataAccessException {
		init();
	}

	public void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertSaleOrder = con.prepareStatement(INSERT_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);

		}
	}

	public void insertOrder(SaleOrder order) throws DataAccessException {
		try {
			insertSaleOrder.setDate(1, Date.valueOf(order.getDate()));
			insertSaleOrder.setInt(2, order.getAmount());
			insertSaleOrder.setString(3, order.getDeliveryStatus());
			insertSaleOrder.setDate(4, Date.valueOf(order.getDate()));
			insertSaleOrder.setInt(5,order.getCustomer().getId());
			
			insertSaleOrder.executeQuery();

		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}

	}
}
