package databaselayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ctrl.DataAccessException;
import db.DBMessages;
import model.Group;
import modellayer.Product;
import modellayer.SaleOrderLine;

public class SaleOrderLineDB implements SaleOrderLineDBIF{
	private static final String INSERT_Q = "insert into saleorderline(id, quantity, productVareNo, saleOrderId) values(?,?,?,?)";
	private PreparedStatement insert;
	
	public void init() {
		
	}
	
	public void insertOrderLine(Orderline orderline) {
		
	}
	
	private List<SaleOrderLine> buildObjects(ResultSet rs){
	}
	
	private SaleOrderLine buildObject(ResultSet rs) {
	}
	
}
