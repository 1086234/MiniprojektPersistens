package controllayer;

import databaselayer.ProductDB;
import databaselayer.ProductDBIF;
import modellayer.Product;

public class ProductController {
	private ProductDBIF productDB;

	public ProductController() throws DataAccessException {
		productDB = new ProductDB();
	}
	public Product findProduct(int VareNo) throws DataAccessException {
		return productDB.findByVareNo(VareNo);
	}
	public void updateStock(Product product, int quintity) throws DataAccessException {
		productDB.updateStock(product, quintity);
	}
}
