package com.oracle.vendingmachine.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.vendingmachine.apimodel.CoinInventoryStatusResponse;
import com.oracle.vendingmachine.apimodel.DispenseCoinResponse;
import com.oracle.vendingmachine.apimodel.InventorySetupRequest;
import com.oracle.vendingmachine.apimodel.InventorySetupResponse;
import com.oracle.vendingmachine.apimodel.RegisterCoinsRequest;
import com.oracle.vendingmachine.apimodel.RegisterCoinsResponse;
import com.oracle.vendingmachine.constant.HttpStatusCodes;
import com.oracle.vendingmachine.constant.VMConstants;
import com.oracle.vendingmachine.mapper.Mapper;
import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.repository.CoinRepository;

@Service
public class VendingMachineServiceImpl implements VendingMachine {

	public static final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);
	
	
	@Autowired 
	private CoinOperationService coinSerivce;
	@Autowired
	private CoinRepository coinRepo;
	
	@Autowired
	private ValidationService validationService;
	

	@Override
	public CoinInventoryStatusResponse inventoryStatus() {
		CoinInventoryStatusResponse response = null;
		try {
			response = Mapper.mapCoinInvStatusRes(CoinRepository.CoinInventory);
			log.info(response.toString());
		} catch (Exception e) {
			log.error("Exception occured while processing the coin inventory status " + e.getMessage());
			response.setErrorDetails(Mapper.mapErrorDetails(e.getMessage(), VMConstants.COININVENTORYSTATUS));
		}
		return response;
	}
	
	@Override
	public InventorySetupResponse inventorySetup(InventorySetupRequest req) {		
		InventorySetupResponse response=null;
		try {
			CoinRepository.CoinInventory=new ConcurrentHashMap<Coin, Integer>();
			req.getInventorySetup().stream().forEach(coinInv-> {
				CoinRepository.CoinInventory.put(coinInv.getCoin(), coinInv.getQuantity());
			});
			response = Mapper.mapToInitialCoinInvenRes();					
			
		} catch (Exception e) {
			log.error("Exception occured while processing the coin inventory status " + e.getMessage());
			response.setErrorDetails(Mapper.mapErrorDetails(e.getMessage(), VMConstants.INITIALCOININVENTORYSETUP));
		}
		return response;
	}

	@Override
	public RegisterCoinsResponse registerCoins(RegisterCoinsRequest req) {
		RegisterCoinsResponse response = null;
		try {
			Boolean status = coinRepo.registerCoins(Mapper.mapRegisterCointToMap(req));
			if(status) {
				response = Mapper.mapToRegisterCoinResponse();
			} else {
				response= new RegisterCoinsResponse();
				response.setErrorDetails(Mapper.mapErrorDetails(VMConstants.REGISTER_COIN_FAILURE, VMConstants.REGISTERCOINS));
			}
		} catch (Exception e) {
			log.error("Exception occured while processing the coin inventory status " + e.getMessage());
			response.setErrorDetails(Mapper.mapErrorDetails(e.getMessage(), VMConstants.REGISTERCOINS));
		}
		return response;

	}
	
	@Override
	public DispenseCoinResponse dispenceCoins(Integer value) {
		DispenseCoinResponse response = null;
		try {
			response = validationService.validateDispenseCoinRequest(value);
			if (response != null) {
				log.error(" DispenseCoin Request is not valid request");
				return response;
			}
			Map<Coin, Integer> dispenceCoins = coinSerivce.minCoins(value);
			if(dispenceCoins.isEmpty()) {
				response= new DispenseCoinResponse();				
				response.setStatus(VMConstants.FAILED);
				response.setMessage(VMConstants.DISPENCE_INSUFFICIENT_COINS);
				response.setErrorDetails(Mapper.mapErrorDetails(VMConstants.DISPENCE_INSUFFICIENT_COINS, VMConstants.DISPENCECOINS));
			}else {
				response = Mapper.mapToDispenseCoinResponse(dispenceCoins);
			}
			
			
		} catch (Exception e) {
			log.error("Exception occured while processing the coin inventory status " + e.getMessage());
			response.setErrorDetails(Mapper.mapErrorDetails(e.getMessage(), VMConstants.REGISTERCOINS));
		}
		return response;

	}
	
	
	
	
}
