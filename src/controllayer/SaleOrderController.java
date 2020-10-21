package controllayer;

import java.time.LocalDateTime;

import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderController {
	private SaleOrder saleOrder;
	private SaleOrderLine saleOrderLine;
	private ProductController productController;
	private CustomerController customerController;
	
	public void createOrder() {
		saleOrder = new SaleOrder(LocalDateTime.now());
	}
	
	public void addProduct(int productId, int quantity) throws DataAccessException {
		productController = new ProductController();	
		saleOrder.addSaleOrderLine(new SaleOrderLine(productController.findProduct(productId), quantity));
	}
	
	public void addCustomer(int cId) throws DataAccessException{
		customerController = new CustomerController();
		saleOrder.setCustomer(customerController.findCustomer(cId));
	}
	
	public void endOrder() {
		
		System.out.println(saleOrder.getDeliveryNote());
	}
}
