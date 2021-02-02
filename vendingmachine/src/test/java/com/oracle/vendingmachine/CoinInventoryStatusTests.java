package com.oracle.vendingmachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oracle.vendingmachine.apimodel.CoinInventoryStatusResponse;
import com.oracle.vendingmachine.constant.HttpStatusCodes;
import com.oracle.vendingmachine.constant.VMConstants;
import com.oracle.vendingmachine.service.VendingMachineServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CoinInventoryStatusTests {
	
	public static final Logger log = LoggerFactory.getLogger(CoinInventoryStatusTests.class);

	@Autowired
	private VendingMachineServiceImpl venMacSer;
	
	
	//setup()
	@BeforeAll
    public  static void before() throws Exception {	
		
	}
	
	
	//tearDown()
	@AfterEach
	void after() throws Exception {
		
    }
	
	
	/**
	 * 
	 * Test Case : Display  the Coin Inventory status
	 * 
	 */
	@Test
	@Order(1)
	void test_coin_inventory_status() {
	  log.info("Test case to display coin inventory status");
	 CoinInventoryStatusResponse result=	venMacSer.inventoryStatus();
	 assertNotNull(result.getCoinInventory());
	 assertEquals(VMConstants.SUCCESS, result.getStatus());	
		
	}
	
	

}
