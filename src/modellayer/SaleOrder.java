package modellayer;

import java.time.LocalDateTime;
import java.util.List;

public class SaleOrder {
	
	private LocalDateTime date;
	private int amount;
	private String deliveryStatus;
	private LocalDateTime deliveryDate;
	private String deliveryNote;
	private List<SaleOrderLine> orderLineList;
	
	public SaleOrder(LocalDateTime date) {
		super();
		this.date = date;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public List<SaleOrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void addSaleOrderLine(SaleOrderLine saleOrderLine) {
		this.orderLineList.add(saleOrderLine);
	}


	
}
