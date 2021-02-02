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
import com.oracle.vendingmachine.apimodel.RegisterCoinsRequest;
import com.oracle.vendingmachine.apimodel.RegisterCoinsResponse;
import com.oracle.vendingmachine.constant.VMConstants;
import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.model.CoinInventory;
import com.oracle.vendingmachine.service.VendingMachineServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class RegisterCoinsTests {
	
	public static final Logger log = LoggerFactory.getLogger(RegisterCoinsTests.class);

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
	 * Test Case : Register Coins
	 *  
	 */
	@Test
	@Order(1)
	void test_coin_inventory_status() {
	  log.info("Test case to register the coins");
	  RegisterCoinsRequest req = new RegisterCoinsRequest();
	  List<CoinInventory> coinDetails = new ArrayList<CoinInventory>();
	  CoinInventory coinInv1= new CoinInventory();
	  coinInv1.setCoin(Coin.ONEPOUND);
	  coinInv1.setQuantity(10);
	  CoinInventory coinInv2= new CoinInventory();
	  coinInv2.setCoin(Coin.TWOPOUND);
	  coinInv2.setQuantity(10);
	  coinDetails.add(coinInv1);
	  coinDetails.add(coinInv2);
	  req.setRegisterCoinsDetails(coinDetails);
	 
	 RegisterCoinsResponse  result=	venMacSer.registerCoins(req);
	 assertNotNull(result.getMessage());
	 assertEquals(VMConstants.SUCCESS, result.getStatus());	
	 
	 
	 // After setting the float value/Initial setup validating the coin inventory
	 CoinInventoryStatusResponse  coinInvResponse= venMacSer.inventoryStatus();
	 assertNotNull(coinInvResponse.getCoinInventory()); 
	 
	
	}
	
	

}
