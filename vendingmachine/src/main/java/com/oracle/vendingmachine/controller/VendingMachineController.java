package com.oracle.vendingmachine.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.vendingmachine.apimodel.CoinInventoryStatusResponse;
import com.oracle.vendingmachine.apimodel.DispenseCoinResponse;
import com.oracle.vendingmachine.apimodel.ErrorDetails;
import com.oracle.vendingmachine.apimodel.InventorySetupRequest;
import com.oracle.vendingmachine.apimodel.InventorySetupResponse;
import com.oracle.vendingmachine.apimodel.RegisterCoinsRequest;
import com.oracle.vendingmachine.apimodel.RegisterCoinsResponse;
import com.oracle.vendingmachine.constant.HttpStatusCodes;
import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.service.VendingMachineServiceImpl;
import com.oracle.vendingmachine.util.Utility;

@RestController
@RequestMapping("/api/v1/")
public class VendingMachineController {
	
	
	//API URL's
	public static final String  COIN_INVENTORY_STATUS="/inventorystatus";
	public static final String  INITIAL_COIN_INVENTORY_SETUP="/inventorysetup";
	public static final String  REGISTER_COINS="/registercoins";
	public static final String  DISPENCE_COINS="/dispensecoins";
	public static final String  INVALID_COIN_VALUE="Invalid coin value";
	public static final String  VALIDATION_ERROR_MSG_1="Not supporing the give input coin value, supporting currencies are ";
	
	@Autowired
	private VendingMachineServiceImpl  vmService;
	
	
	/**
	 * The Api to  display coin inventory status
	 * 
	 * @return  - return the coin inventory 
	 */
	@GetMapping(value=COIN_INVENTORY_STATUS,produces = "application/json")
	public ResponseEntity<CoinInventoryStatusResponse>  coinInventoryStatus() {
		CoinInventoryStatusResponse response=null;
		try {
			response = vmService.inventoryStatus();
			if (response.getErrorDetails() != null) {
				return Utility.tranformResponseEntity(response);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * The Api to  reset/initial float coin inventory 
	 * 
	 * @param req -  request to send required coin and quantity 
	 * @return    -  return success or failure status
	 */
	@PostMapping(value=INITIAL_COIN_INVENTORY_SETUP, consumes = "application/json", produces = "application/json")
	public ResponseEntity<InventorySetupResponse>  initialCoinInventorySetup(@Valid @RequestBody InventorySetupRequest req) {
		InventorySetupResponse response = null;
		try {
			response = vmService.inventorySetup(req);
			if (response.getErrorDetails() != null) {
				return Utility.tranformResponseEntity(response);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * The Api to  register coin and update the coin inventory 
	 * 
	 * @param req -  request to register the  coin and quantity 
	 * @return    -  return success or failure status
	 */
	@PostMapping(value=REGISTER_COINS, consumes = "application/json", produces = "application/json")
	public  ResponseEntity<RegisterCoinsResponse>  registerCoins(@Valid @RequestBody RegisterCoinsRequest req) {
		RegisterCoinsResponse response = null;
		try {
			response = vmService.registerCoins(req);
			if (response.getErrorDetails() != null) {
				return Utility.tranformResponseEntity(response);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	
	}

	/**
	 * The Api to  dispense the  coin as user requested amount.
	 * 
	 * @return  - return the coin inventory 
	 */
	@GetMapping(value=DISPENCE_COINS+"/{useramount}",produces = "application/json")
	public ResponseEntity<DispenseCoinResponse>  dispenceCoins(@Valid @Min(1L) @Positive @PathVariable Integer useramount) {
		DispenseCoinResponse response=null;
		try {
			response = vmService.dispenceCoins(useramount);
			if (response.getErrorDetails() != null) {
			 return Utility.tranformResponseEntity(response);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * Handle validation errors.
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	/**
	 * Handle invalid coin type.
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorDetails handleCoinValidationExceptions(HttpMessageNotReadableException ex) {
		System.out.println(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setDetail(ex.getMessage());
		errorDetails.setStatus(HttpStatusCodes.STATUS_400.getHttpCode());
		errorDetails.setError(INVALID_COIN_VALUE);
		errorDetails.setMessage(VALIDATION_ERROR_MSG_1 +  Coin.ONEPOUND +","+ Coin.TWOPOUND+","+ Coin.FIVEPOUND);
		errorDetails.setTimestamp(new Timestamp(new Date().getTime()).toString());  
	    return errorDetails;
	}
}
