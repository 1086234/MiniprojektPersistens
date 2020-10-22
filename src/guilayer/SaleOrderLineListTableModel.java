package guilayer;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import modellayer.Product;
import modellayer.SaleOrderLine;


/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class SaleOrderLineListTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private List<SaleOrderLine> data = new ArrayList<>();
	private static final String[] COL_NAMES = { "Varenummer", "Produkt", "Antal", "Pris pr enhed", "Samlet pris" };

	public SaleOrderLineListTableModel() {
		setData(null);
	}

	public SaleOrderLineListTableModel(List<SaleOrderLine> data) {
		setData(data);
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COL_NAMES[column];
	}

	public void setData(List<SaleOrderLine> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}

	public SaleOrderLine getSaleOrderLineOfRow(int index) {
		if (index < 0 || index >= data.size()) {
			return null;
		}
		return this.data.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		SaleOrderLine e = data.get(row);
		
		switch (column) {
		case 0:
			return e.getProduct().getVareNo();
		case 1:
			return e.getProduct().getName();
		case 2:
			return e.getQuantity();
		case 3:
			return e.getProduct().getSalesPrice();
		case 4:
			return (e.getQuantity()*e.getProduct().getSalesPrice());
		default:
			return "UNKNOLWN COL NAME";
		}
	}

	@Override
	public int getRowCount() {
		return (data == null ? 0:  data.size());
	}

}
