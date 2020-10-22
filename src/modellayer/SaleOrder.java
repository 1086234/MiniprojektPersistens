package modellayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class SaleOrder {

	private Date date;
	private int amount;
	private String deliveryStatus;
	private LocalDateTime deliveryDate;
	private String deliveryNote;
	private Customer customer;
	private List<SaleOrderLine> orderLineList;

	public SaleOrder(Date date) {
		super();
		this.date = date;
		this.deliveryStatus = "På vej";
		orderLineList = new ArrayList<SaleOrderLine>();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public void setCustomer() {
		this.customer = null;
	}

	public List<SaleOrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void addSaleOrderLine(SaleOrderLine saleOrderLine) {
		boolean foundExisting = false;
		for (SaleOrderLine orderLine : orderLineList) 
		{ 
		    if(orderLine.getProduct().getVareNo() == saleOrderLine.getProduct().getVareNo())
		    {
		    	orderLine.quantity = orderLine.quantity + saleOrderLine.quantity;
		    	foundExisting = true;
		    }
		}

		if(foundExisting == false)
			this.orderLineList.add(saleOrderLine);
	}

	public void removeSaleOrderLine(int index) {

		orderLineList.remove(index);
	}

	public void clearList() {
		this.orderLineList.clear();
	}
}
