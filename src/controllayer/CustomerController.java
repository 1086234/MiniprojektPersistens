package controllayer;

import databaselayer.CustomerDB;
import modellayer.Customer;

public class CustomerController {
	private CustomerDB customerDB;
	private Customer customer;
	
	public CustomerController() throws DataAccessException {
		customerDB = new CustomerDB();
	}
	
	public Customer findCustomer(int cId) throws DataAccessException {
		return customerDB.findCustomer(cId);
	}
}
