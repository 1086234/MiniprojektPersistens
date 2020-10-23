package databaselayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controllayer.DataAccessException;
import modellayer.SaleOrderLine;

public class SaleOrderLineDB implements SaleOrderLineDBIF {
	private static final String INSERT_Q = "INSERT INTO salOrderLine(quantity, productVareId, saleOrderId) VALUES (?,?,?)";
	private PreparedStatement insertSaleOrderLine;

	public void SaleOrderDB() throws DataAccessException {
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

	@Override
	public void insertOrderLine(SaleOrderLine saleOrderLine, int orderId) throws DataAccessException {
		try {
			System.out.println(saleOrderLine.getQuantity());
				insertSaleOrderLine.setInt(1, saleOrderLine.getQuantity());
				insertSaleOrderLine.setInt(2, saleOrderLine.getProduct().getVareNo());
				insertSaleOrderLine.setInt(3, orderId);
				insertSaleOrderLine.executeUpdate();

		} catch (SQLException e) {
			throw new controllayer.DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
	}

	public void createSaleOrderLine(List<SaleOrderLine> saleOrderLineList) {

	}
}