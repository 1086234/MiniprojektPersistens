package databaselayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import controllayer.DataAccessException;
import modellayer.Product;
import modellayer.SaleOrderLine;

public class SaleOrderLineDB implements SaleOrderLineDBIF{
	private static final String INSERT_Q = "insert into saleorderline(id, quantity, productVareNo, saleOrderId) values(?,?,?,?)";
	private PreparedStatement insert;
	
	public void init() throws controllayer.DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insert = con.prepareStatement(INSERT_Q);
		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	@Override
	public void insertOrderLine(SaleOrderLine saleOrderLine) throws DataAccessException {
		try {
			insert.executeQuery();
		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
	}
}
