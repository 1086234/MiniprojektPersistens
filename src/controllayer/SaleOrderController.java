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
	
	public void addProduct(Product product, int quantity) {
		saleOrder.addSaleOrderLine(new SaleOrderLine(product, quantity));
	}
	
	public void addCustomer(int cId){
		
	}
	
	public void endOrder() {
		
	}
}
