package databaselayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controllayer.DataAccessException;

import modellayer.*;

public class ProductDB implements ProductDBIF {
	private Product product;
	private static final String FIND_BY_VARENO = "select productId , name, purchasePrice , countryOfOrigin, minStock, maxStock, stock  from product where productId = ?";
	private PreparedStatement findByVareNo;
	private static final String UPDATE_STOCK_BY_VARENO = "update product stock = ? where vareNo = ?";
	private PreparedStatement updateStockByVareNO;

	public ProductDB() throws DataAccessException {

		init();
	}

	public void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByVareNo = con.prepareStatement(FIND_BY_VARENO);
			updateStockByVareNO = con.prepareStatement(UPDATE_STOCK_BY_VARENO);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);

		}
	}

	@Override
	public Product findByVareNo(int id) throws DataAccessException {
		Product res = null;
		
		try {
			findByVareNo.setInt(1, id);
			ResultSet rs = findByVareNo.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);

		}
		return res;
	}

	@Override
	public Product updateStock(Product product, int quintity) throws DataAccessException{
		Product res = null;
		
		try {
			int vareNo = product.getVareNo();
			updateStockByVareNO.setInt(2, vareNo);
			updateStockByVareNO.setInt(1, quintity);
			ResultSet rs = updateStockByVareNO.executeQuery();
			if(rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
			
		}
		return res;
	}
	
	private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
		List<Product> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Product currPerson = buildObject(rs);
				res.add(currPerson);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;

	}

	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product product = new Product();
		try {
			product.setVareNo(rs.getInt("productId"));
			product.setName(rs.getString("name"));
			product.setSalesPrice(rs.getDouble("purchasePrice"));
			product.setCountryOfOrigin(rs.getString("countryOfOrigin"));
			product.setStock(rs.getInt("stock"));
			product.setMinStock(rs.getInt("minStock"));
			product.setMaxStock(rs.getInt("MaxStock"));

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);

		}
		return product;
	}
}
