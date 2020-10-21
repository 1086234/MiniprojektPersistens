package databaselayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllayer.DataAccessException;
import modellayer.Customer;


public class CustomerDB implements CustomerDBIF {
	
	private Customer customer;
	
	
	private static final String FIND_BY_ID = "select * from customer where id = ?";
	private PreparedStatement findById;
	
	public CustomerDB() throws DataAccessException {
		
		init();
	}
	
	public void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findById = con.prepareStatement(FIND_BY_ID);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	@Override
	public Customer findCustomer(int cId) throws DataAccessException {
		Customer cus = null;
		try {
			findById.setInt(1, cId);
			ResultSet rs = findById.executeQuery();
			if(rs.next()) {
				cus = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return cus;
	}
	
	private List<Customer> buildObjects(ResultSet rs) throws DataAccessException {
		List<Customer> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Customer currCustomer = buildObject(rs);
				res.add(currCustomer);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer = new Customer();
		try {
			customer.setId(rs.getInt("id"));
			customer.setName(rs.getString("name"));
			customer.setAddress(rs.getString("address"));
			customer.setZipCode(rs.getInt("zipCode"));
			customer.setCity(rs.getString("city"));
			customer.setPhoneNo(rs.getString("phone"));
		

		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);

		}
		return customer;
	}
	
}
