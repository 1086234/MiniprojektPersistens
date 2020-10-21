package databaselayer;

import controllayer.DataAccessException;
import modellayer.Product;

public interface ProductDBIF {
	 Product findByVareNo(int id) throws DataAccessException;
	 Product updateStock(Product product, int quintity) throws DataAccessException;
}
