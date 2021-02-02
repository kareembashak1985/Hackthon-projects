package com.oracle.vendingmachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
import com.oracle.vendingmachine.apimodel.InventorySetupRequest;
import com.oracle.vendingmachine.apimodel.InventorySetupResponse;
import com.oracle.vendingmachine.constant.VMConstants;
import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.model.CoinInventory;
import com.oracle.vendingmachine.service.VendingMachineServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InitialCoinInventorySetupTests {
	
	public static final Logger log = LoggerFactory.getLogger(InitialCoinInventorySetupTests.class);

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
	 * Test Case :Register the coins and validate the coin inventory status after registration
	 * 
	 */
	@Test
	@Order(1)
	void test_initial_coin_inventory_setup() {
	  log.info("Test case to display coin inventory status");
	  InventorySetupRequest req = new InventorySetupRequest();
	  List<CoinInventory> coinDetails = new ArrayList<CoinInventory>();
	  CoinInventory coinInv1= new CoinInventory();
	  coinInv1.setCoin(Coin.ONEPOUND);
	  coinInv1.setQuantity(100);
	  CoinInventory coinInv2= new CoinInventory();
	  coinInv2.setCoin(Coin.TWOPOUND);
	  coinInv2.setQuantity(200);
	  coinDetails.add(coinInv1);
	  coinDetails.add(coinInv2);
	  req.setInventorySetup(coinDetails);
	 
	 InventorySetupResponse  result=	venMacSer.inventorySetup(req);
	 assertNotNull(result.getMessage());
	 assertEquals(VMConstants.SUCCESS, result.getStatus());	
	 
	 
	 // After setting the float value/Initial setup validating the coin inventory
	 CoinInventoryStatusResponse  coinInvResponse= venMacSer.inventoryStatus();
	 assertNotNull(coinInvResponse.getCoinInventory());
	 coinInvResponse.getCoinInventory().stream().forEach(e-> {
		 if(Coin.ONEPOUND.getCoinName().equals(e.getCoin().getCoinName())) {
			 assertEquals(100,e.getQuantity());
		 } else if(Coin.TWOPOUND.getCoinName().equals(e.getCoin().getCoinName())) {
			 assertEquals(200, e.getQuantity());
		 } 
	 });
	 
	
	}
	
	

}
