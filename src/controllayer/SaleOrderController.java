package controllayer;

import java.time.LocalDateTime;
import java.util.List;

import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderController {
	private SaleOrder saleOrder;
	private ProductController productController;
	private CustomerController customerController;
	
	public void createOrder() {
		saleOrder = new SaleOrder(LocalDateTime.now());
	}
	
	public boolean addProduct(int productId, int quantity) throws DataAccessException {
		productController = new ProductController();
		Product product = productController.findProduct(productId);
		
		if(product.getStock() >= quantity) {
			saleOrder.addSaleOrderLine(new SaleOrderLine(product, quantity));
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addCustomer(int cId) throws DataAccessException{
		customerController = new CustomerController();
		saleOrder.setCustomer(customerController.findCustomer(cId));
	}
	
	public void endOrder() {
		System.out.println(saleOrder.getDeliveryNote());
	}
	
	public List<SaleOrderLine> getOrderLineList(){
		return saleOrder.getOrderLineList();
	}
}
