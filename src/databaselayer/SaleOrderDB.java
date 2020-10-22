package databaselayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controllayer.DataAccessException;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderDB {
	private SaleOrder saleOrder;
	private static final String INSERT_Q = "INSERT INTO salOrderLine(quantity, productVareId, saleOrderId) VALUES (?,?,?)";
	private PreparedStatement insertSaleOrderLine;
	
	public SaleOrderDB() throws DataAccessException {
		init();
	}
	
	public void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertSaleOrderLine = con.prepareStatement(INSERT_Q);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);

		}
	}
	
	public void insertOrder(SaleOrder order) {
		
	}
}
