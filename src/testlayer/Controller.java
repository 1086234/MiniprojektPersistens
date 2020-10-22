package testlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import controllayer.DataAccessException;
import controllayer.SaleOrderController;


class Controller {
	private SaleOrderController saleOrderController;
	
	@BeforeEach
	void init() {
		saleOrderController = new SaleOrderController();
		saleOrderController.createOrder();
	}

	@AfterEach
	void clear() {
		
	}

	@Test
	void AddProductToOrderLineTest() throws DataAccessException {
		
		assertTrue(saleOrderController.addProduct(1, 1));
	}
	@Test
	void AddProductToOrderLineTestNotTrue() throws DataAccessException {
		
		assertFalse(saleOrderController.addProduct(32124, 1));
	}
	@Test
	void quantityTest() throws DataAccessException {
		saleOrderController.addProduct(1, 1);
		assertTrue(saleOrderController.quantityProduct(0, 10));
	}
	@Test
	void quantityTestNotTrue() throws DataAccessException {
		saleOrderController.addProduct(1, 1);
		assertFalse(saleOrderController.quantityProduct(0, 12220));
	}
}
