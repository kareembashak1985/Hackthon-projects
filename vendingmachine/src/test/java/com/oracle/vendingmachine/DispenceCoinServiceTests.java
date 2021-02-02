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

import com.oracle.vendingmachine.apimodel.DispenseCoinResponse;
import com.oracle.vendingmachine.constant.HttpStatusCodes;
import com.oracle.vendingmachine.constant.VMConstants;
import com.oracle.vendingmachine.service.VendingMachineServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DispenceCoinServiceTests {
	
	public static final Logger log = LoggerFactory.getLogger(DispenceCoinServiceTests.class);

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
	 * Test Case : if user provided zero(0) as input to the dispenceCoin Service,  
	 * 
	 */
	@Test
	@Order(1)
	void test_dispence_coins_input_0() {
	  log.info("Test case where user provide input £0 ");
	 DispenseCoinResponse result=	venMacSer.dispenceCoins(0);
	 assertNull(result.getDispenseCoinDetails());
	 assertEquals(VMConstants.FAILED, result.getStatus());
	 assertNotNull(result.getErrorDetails());
	 assertEquals(HttpStatusCodes.STATUS_400.getHttpCode(), result.getErrorDetails().getStatus());
	
	}
	
	/**
	 * 
	 * Test Case : if user provided £20 as input to the dispenceCoin Service,  
	 * 
	 */
	@Test	
	@Order(2)    
	void test_dispence_coins_input_20() {
	  log.info("Test case where user provide input £20 ");	
	  DispenseCoinResponse result=	venMacSer.dispenceCoins(20);
	  assertNotNull(result.getDispenseCoinDetails());
	  assertEquals(VMConstants.SUCCESS, result.getStatus());
	
	}
	
	/**
	 * 
	 * Test Case : if user provided £19 as input to the dispenceCoin Service,  
	 * 
	 */
	@Test
	@Order(3)
	void test_dispence_coins_input_19() {
	 log.info("Test case where user provide input £19 ");
	 DispenseCoinResponse result=	venMacSer.dispenceCoins(19);
	 assertNotNull(result.getDispenseCoinDetails());
	 assertEquals(VMConstants.SUCCESS, result.getStatus());
	
	}
	
	/**
	 * 
	 * Test Case : if user provided £100 as input to the dispenceCoin Service,  
	 * 
	 */
	@Test
	@Order(4)
	void test_dispence_coins_input_100() {
	 log.info("Test case where user provide input £100 ");
	 DispenseCoinResponse result=	venMacSer.dispenceCoins(100);
	 assertNotNull(result.getDispenseCoinDetails());
	 assertEquals(VMConstants.SUCCESS, result.getStatus());
	
	}
	
	/**
	 * 
	 * Test Case : if user provided £1000 as input to the dispenceCoin Service,  
	 * 
	 */
	@Test
	@Order(5)
	void test_dispence_coins_input_1000() {
	 log.info("Test case where user provide input £1000 ");
	 DispenseCoinResponse result=	venMacSer.dispenceCoins(1000);
	 assertNotNull(result.getDispenseCoinDetails());
	 assertEquals(VMConstants.SUCCESS, result.getStatus());
	
	}
	
	

}
