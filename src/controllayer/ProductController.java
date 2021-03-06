package controllayer;

import databaselayer.ProductDB;
import databaselayer.ProductDBIF;
import modellayer.Product;

public class ProductController {
	private ProductDB productDB;

	public ProductController() throws DataAccessException {
		productDB = new ProductDB();
	}
	public Product findProduct(int VareNo) throws DataAccessException {
		return productDB.findByVareNo(VareNo);
	}
	public void updateStock(Product product, int quantity) throws DataAccessException {
		productDB.updateStock(product, productDB.findByVareNo(product.getVareNo()).getStock()-quantity);
	}
}
