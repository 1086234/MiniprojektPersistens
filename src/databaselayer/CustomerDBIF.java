package databaselayer;

import controllayer.DataAccessException;
import modellayer.Customer;

public interface CustomerDBIF {
	
	Customer findCustomer(int cId) throws DataAccessException;

}
