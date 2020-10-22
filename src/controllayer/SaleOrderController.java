package controllayer;

import java.util.*;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import databaselayer.SaleOrderDB;
import modellayer.Customer;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderController {
	private SaleOrder saleOrder;
	private SaleOrderDB saleOrderDB;
	private ProductController productController;
	private CustomerController customerController;

	public void createOrder() throws DataAccessException {
		Date date = new Date(System.currentTimeMillis());
		customerController = new CustomerController();
		saleOrder = new SaleOrder(date);
		saleOrderDB = new SaleOrderDB();

	}

	public boolean addProduct(int productId, int quantity) throws DataAccessException {
		boolean fundet = false;
		productController = new ProductController();

		Product product = null;
		if (productController.findProduct(productId) != null) {
			product = productController.findProduct(productId);
			if (product.getStock() >= quantity) {

				saleOrder.addSaleOrderLine(new SaleOrderLine(product, quantity));

				fundet = true;
			} else {
				fundet = false;
			}
		} else {
			fundet = false;
		}
		return fundet;
	}

	public boolean quantityProduct(int index, int quantity) {
		boolean inStock = false;
		
		Product product = saleOrder.getOrderLineList().get(index).getProduct();
		if (product.getStock() >= quantity) {
			saleOrder.getOrderLineList().get(index).setQuantity(quantity);
			inStock = true;
		}

		return inStock;
	}

	public List<SaleOrderLine> getOrderLineList() {
		return saleOrder.getOrderLineList();
	}

	public SaleOrder getOrder() {
		return this.saleOrder;
	}

	public void addCustomer(int cId) throws DataAccessException {
		saleOrder.setCustomer(customerController.findCustomer(cId));
	}
	
	public void addCustomer() throws DataAccessException {
		saleOrder.setCustomer();
	}


	public void removeSaleOrderLine(int index) {
		saleOrder.removeSaleOrderLine(index);
	}

	public void endOrder() {
		System.out.println(saleOrder.getDeliveryNote());
	}

	public void clearOrderLineList() {
		saleOrder.clearList();
	}
	
	public void addOrder(SaleOrder order) throws DataAccessException, SQLException {
		saleOrderDB.insertOrder(order);
	}
	
	public void updateNumberOfProducts() throws DataAccessException {
		
		for (SaleOrderLine orderLine : saleOrder.getOrderLineList()) 
		{ 
			productController.updateStock(orderLine.getProduct(), orderLine.getQuantity());
		}
	}
}
