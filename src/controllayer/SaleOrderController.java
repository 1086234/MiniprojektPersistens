package controllayer;

import java.util.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

import databaselayer.SaleOrderDB;
import databaselayer.SaleOrderLineDB;
import modellayer.Customer;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderController {
	private SaleOrder saleOrder;
	private SaleOrderDB saleOrderDB;
	private ProductController productController;
	private CustomerController customerController;
	private SaleOrderLineDB saleOrderLineDB;
	
	public void createOrder() throws DataAccessException {
		Date date = new Date(System.currentTimeMillis());
		customerController = new CustomerController();
		saleOrder = new SaleOrder(date);
		saleOrderDB = new SaleOrderDB();
		saleOrderLineDB = new SaleOrderLineDB();
	}

	public int addProduct(int productId, int quantity) throws DataAccessException {
		int fundet = 0;
		boolean stockCheck;
		productController = new ProductController();

		Product product = null;
		if (productController.findProduct(productId) != null) {
			product = productController.findProduct(productId);
			if (product.getStock() >= quantity) {
				stockCheck = saleOrder.addSaleOrderLine(new SaleOrderLine(product, quantity), productController.findProduct(product.getVareNo()));
				if(stockCheck)
					fundet = 0;
				else
					fundet = 2;
			} else {
				fundet = 2;
			}
		} else {
			fundet = 1;
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

	public int CalcTotalPrice() {
		int total = 0;
		for(SaleOrderLine SOL: saleOrder.getOrderLineList()) {
			total += SOL.getProduct().getSalesPrice() * SOL.getQuantity();
		}
		return total;
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

	public void endOrder() throws DataAccessException, SQLException {
		 int id = addOrder(saleOrder);
		
		System.out.println(LocalDateTime.now());
		for (SaleOrderLine orderLine : getOrderLineList() ) {
			System.out.println(orderLine.getProduct().getName());
			System.out.println(orderLine.getQuantity());
			System.out.println(orderLine.getProduct().getSalesPrice());
			double subTotal = 0;
			subTotal = orderLine.getQuantity() * orderLine.getProduct().getSalesPrice();
			System.out.println(subTotal);
			saleOrderLineDB.insertOrderLine(orderLine, id);
		}
		System.out.println(CalcTotalPrice());
		System.out.println(saleOrder.getDeliveryDate());
		System.out.println(saleOrder.getDeliveryNote());
	}

	public void clearOrderLineList() {
		saleOrder.clearList();
	}

	public int addOrder(SaleOrder order) throws DataAccessException, SQLException {
	 return	saleOrderDB.insertOrder(order);
	}
	
	public void updateNumberOfProducts() throws DataAccessException {
		
		for (SaleOrderLine orderLine : saleOrder.getOrderLineList()) 
		{ 
			productController.updateStock(orderLine.getProduct(), orderLine.getQuantity());
		}
	}
}
