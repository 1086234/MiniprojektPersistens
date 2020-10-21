package modellayer;

public class Product {
	private int vareNo;
	private String name;
	private double salesPrice;
	private String countryOfOrigin;
	private int stock;
	private int minStock;
	private int maxStock;

	/**
	 * @param vareNo
	 * @param name
	 * @param salesPrice
	 * @param countryOfOrigin
	 * @param stock
	 * @param minStock
	 * @param maxStock
	 */
	public Product() {
	}

	public Product(int vareNo) {
		this.vareNo = vareNo;
	}

	public Product(int vareNo, String name, double salesPrice, String countryOfOrigin, int stock, int minStock,
			int maxStock) {

		this.vareNo = vareNo;
		this.name = name;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.stock = stock;
		this.minStock = minStock;
		this.maxStock = maxStock;
	}

	/**
	 * @return the vareNo
	 */
	public int getVareNo() {
		return vareNo;
	}

	/**
	 * @param vareNo the vareNo to set
	 */
	public void setVareNo(int vareNo) {
		this.vareNo = vareNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salesPrice
	 */
	public double getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice the salesPrice to set
	 */
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the countryOfOrigin
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	/**
	 * @param countryOfOrigin the countryOfOrigin to set
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the minStock
	 */
	public int getMinStock() {
		return minStock;
	}

	/**
	 * @param minStock the minStock to set
	 */
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	/**
	 * @return the maxStock
	 */
	public int getMaxStock() {
		return maxStock;
	}

	/**
	 * @param maxStock the maxStock to set
	 */
	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}
}
