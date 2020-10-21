package guilayer;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import modellayer.Product;
import modellayer.SaleOrderLine;


/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class SaleOrderLineListTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<SaleOrderLine> data;
	private static final String[] COL_NAMES = { "First name", "Mid. init", "Last name" };

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

	public SaleOrderLine getEmployeeOfRow(int index) {
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
			return null;
		case 1:
			return null;
		case 2:
			return null;
		default:
			return "UNKNOLWN COL NAME";
		}
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

}
