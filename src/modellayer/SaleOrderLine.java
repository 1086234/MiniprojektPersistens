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
	 * @return the quantity
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
