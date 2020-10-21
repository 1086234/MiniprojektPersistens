package controllayer;

import databaselayer.CustomerDBIF;
import modellayer.Customer;

public class CustomerController {
	private CustomerDBIF customerDB;
	private Customer customer;
	
	public CustomerController(CustomerDBIF customerDB, Customer customer) {
		super();
		this.customerDB = customerDB;
		this.customer = customer;
	}
	
	public Customer findCustomer(int cId) {
		return customerDB.findCustomer(cID);
	}
}
