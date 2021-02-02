package com.oracle.vendingmachine.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.oracle.vendingmachine.apimodel.CoinInventoryStatusResponse;
import com.oracle.vendingmachine.apimodel.DispenseCoinResponse;
import com.oracle.vendingmachine.apimodel.ErrorDetails;
import com.oracle.vendingmachine.apimodel.InventorySetupResponse;
import com.oracle.vendingmachine.apimodel.RegisterCoinsRequest;
import com.oracle.vendingmachine.apimodel.RegisterCoinsResponse;
import com.oracle.vendingmachine.constant.HttpStatusCodes;
import com.oracle.vendingmachine.constant.VMConstants;
import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.model.CoinInventory;

public class Mapper {
	
	public static final String INVENTORY_PROCESSING_ERROR="coin inventory display error";
	public static final String INVENTORY_INITIAL_SETUP_ERROR="coin inventory initialization error";
	public static final String INVENTORY_STATUS_COIN_AVAILABLE="Coins available in container";
	public static final String INVENTORY_STATUS_COIN_NOT_AVAILABLE="Coins doest not exist in container";
	public static final String REGISTER_COINS_ERROR="Failed to register the user coins";
	public static final String DISPENCE_COINS_ERROR="Failed to dispence the coins";
	
	
	/**
	 * Function to map coin repository to CoinInventoryStatusResponse
	 * @param coinRepo
	 * @return
	 */
	public static CoinInventoryStatusResponse  mapCoinInvStatusRes(Map<Coin, Integer> coinRepo) {
		CoinInventoryStatusResponse response= new CoinInventoryStatusResponse();
		List<CoinInventory> result =	coinRepo.entrySet().stream().map( e  -> {
			CoinInventory coinInv= new CoinInventory();
			coinInv.setCoin(e.getKey());
			coinInv.setQuantity(e.getValue());			
			return coinInv;
		}).collect(Collectors.toList());
		response.setCoinInventory(result);
		response.setStatus(VMConstants.SUCCESS);
		if(result !=null && !result.isEmpty()) {
			response.setMessage(INVENTORY_STATUS_COIN_AVAILABLE);
		} else {
			response.setMessage(INVENTORY_STATUS_COIN_NOT_AVAILABLE);
		}
		return response;
	}
	
	/**
	 * Function to map error message to ErrorDetails
	 * @param errorMsg
	 * @param caller
	 * @return
	 */
	public static ErrorDetails mapErrorDetails(String errorMsg, String caller) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(HttpStatusCodes.STATUS_500.getHttpCode());
		errorDetails.setDetail(errorMsg);
		if(VMConstants.COININVENTORYSTATUS.equals(caller)) {
			errorDetails.setMessage(INVENTORY_PROCESSING_ERROR);			
		} else if(VMConstants.INITIALCOININVENTORYSETUP.equals(caller)) {
			errorDetails.setMessage(INVENTORY_INITIAL_SETUP_ERROR);			
		} else if(VMConstants.REGISTERCOINS.equals(caller)) {
			errorDetails.setMessage(REGISTER_COINS_ERROR);			
		} else if(VMConstants.DISPENCECOINS.equals(caller) && VMConstants.DISPENCE_INSUFFICIENT_COINS.equals(errorMsg)) {
			errorDetails.setMessage(VMConstants.DISPENCE_INSUFFICIENT_COINS);	
			errorDetails.setStatus(HttpStatusCodes.STATUS_404.getHttpCode());
		} else if(VMConstants.DISPENCECOINS.equals(caller)) {
			errorDetails.setMessage(DISPENCE_COINS_ERROR);			
		}
		errorDetails.setTimestamp(new Timestamp(new Date().getTime()).toString());
		return errorDetails;
		
	}
	
	public static InventorySetupResponse mapToInitialCoinInvenRes() {
		InventorySetupResponse response = new InventorySetupResponse();
		response.setStatus(VMConstants.SUCCESS);
		response.setMessage(VMConstants.INITIAL_SETUP_SUCCESS);
		return response;
	}
	
	public static RegisterCoinsResponse mapToRegisterCoinResponse() {
		RegisterCoinsResponse response = new RegisterCoinsResponse();
		response.setStatus(VMConstants.SUCCESS);
		response.setMessage(VMConstants.REGISTER_COIN_SUCCESS);
		return response;
	}
	public static DispenseCoinResponse mapToDispenseCoinResponse(Map<Coin,Integer> dispenceCoins) {
		DispenseCoinResponse response = new DispenseCoinResponse();
		List<CoinInventory> result =	dispenceCoins.entrySet().stream().map( e  -> {
			CoinInventory coinInv= new CoinInventory();
			coinInv.setCoin(e.getKey());
			coinInv.setQuantity(e.getValue());			
			return coinInv;
		}).collect(Collectors.toList());
		response.setDispenseCoinDetails(result);
		response.setStatus(VMConstants.SUCCESS);
		response.setMessage(VMConstants.DISPENCE_COIN_SUCCESS);
		return response;
	}
	
	public static Map<Coin,Integer> mapRegisterCointToMap(RegisterCoinsRequest req){
		Map<Coin,Integer> registerCoinMap = new HashMap<Coin, Integer>();
		req.getRegisterCoinsDetails().stream().forEach(e -> {
			registerCoinMap.put(e.getCoin(),e.getQuantity());
		});
		return registerCoinMap;
	}
	

}
