package controllayer;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import databaselayer.SaleOrderDB;
import modellayer.Product;
import modellayer.SaleOrder;
import modellayer.SaleOrderLine;

public class SaleOrderController {
	private SaleOrder saleOrder;
	private SaleOrderDB saleOrderDB;
	private ProductController productController;
	private CustomerController customerController;

	public void createOrder() throws DataAccessException {

		saleOrder = new SaleOrder(LocalDate.now());
		saleOrderDB = new SaleOrderDB();

	}

	public int addProduct(int productId, int quantity) throws DataAccessException {
		int fundet = 0;
		productController = new ProductController();

		Product product = null;
		if (productController.findProduct(productId) != null) {
			product = productController.findProduct(productId);
			if (product.getStock() >= quantity) {
				boolean doubles = checkOrderLine(product);
				
				if (!doubles) {
					saleOrder.addSaleOrderLine(new SaleOrderLine(product, quantity));
				}

				fundet = 0;
			} else {
				fundet = 2;
			}
		} else {
			fundet = 1;
		}
		return fundet;
	}

	public boolean checkOrderLine(Product product) {
		int size = saleOrder.getOrderLineList().size();
		int i = 0;
		boolean check = false;
		while (!check && i < size) {
			
			Product temp = getOrderLineList().get(i).getProduct();
			if (product.getName().equals(temp.getName())) {
				int quantity = saleOrder.getOrderLineList().get(i).getQuantity();
				
				if (quantity < product.getStock()) {
					saleOrder.getOrderLineList().get(i).setQuantity(quantity + 1);
				}
				check = true;
			}
			i++;
		}
		return check;
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
		customerController = new CustomerController();
		saleOrder.setCustomer(customerController.findCustomer(cId));
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

	public void addOrder(SaleOrder order) throws DataAccessException {

		saleOrderDB.insertOrder(order);
	}
}
