package modellayer;

public class SaleOrderLine {
	int quantity;
	Product product;
	
	/**
	 * @param quantity
	 * @param product
	 */
	public SaleOrderLine(Product product, int quantity) {
		
		this.quantity = quantity;
		this.product = product;
	}
	
	public SaleOrderLine() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the product
	 */
	public Product getProduct(){
		return product;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
