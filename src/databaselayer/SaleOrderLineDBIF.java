package databaselayer;

import controllayer.DataAccessException;
import modellayer.SaleOrderLine;

public interface SaleOrderLineDBIF {
	void insertOrderLine(SaleOrderLine saleOrderLine, int orderId) throws DataAccessException;
}
