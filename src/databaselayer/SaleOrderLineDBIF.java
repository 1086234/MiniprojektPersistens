package databaselayer;

import controllayer.DataAccessException;
import modellayer.SaleOrderLine;

public interface SaleOrderLineDBIF {
	void insertOrderLine(SaleOrderLine saleOrderLine) throws DataAccessException;
}
