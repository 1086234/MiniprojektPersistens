package modellayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
	
	private LocalDate date;
	private int amount;
	private String deliveryStatus;
	private LocalDateTime deliveryDate;
	private String deliveryNote;
	private Customer customer;
	private List<SaleOrderLine> orderLineList;
	
	
	public SaleOrder(LocalDate date) {
		super();
		this.date = date;
		orderLineList = new ArrayList<SaleOrderLine>();
	
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<SaleOrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void addSaleOrderLine(SaleOrderLine saleOrderLine) {
		this.orderLineList.add(saleOrderLine);
	}	
	public void removeSaleOrderLine(int index) {
		
		orderLineList.remove(index);
	}
	
	public void clearList() {
		this.orderLineList.clear();
	}
}
