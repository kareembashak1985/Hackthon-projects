package com.oracle.vendingmachine.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.oracle.vendingmachine.apimodel.DispenseCoinResponse;
import com.oracle.vendingmachine.apimodel.ErrorDetails;
import com.oracle.vendingmachine.constant.HttpStatusCodes;
import com.oracle.vendingmachine.constant.VMConstants;

@Service
public class ValidationService {
	
	private static final String dispence_validation_message_input_zero="Bad request, minimum accepted currency 1 pound";
	private static final String BAD_REQUEST="Bad Request";
	
	
	public DispenseCoinResponse validateDispenseCoinRequest(Integer inputValue) {
		DispenseCoinResponse  response=null;
		if(inputValue<=0) {
			response = new DispenseCoinResponse();
			response.setStatus(VMConstants.FAILED);
			response.setMessage(dispence_validation_message_input_zero);
			
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setStatus(HttpStatusCodes.STATUS_400.getHttpCode());
			errorDetails.setDetail(dispence_validation_message_input_zero);
			errorDetails.setMessage(BAD_REQUEST);			
			errorDetails.setTimestamp(new Timestamp(new Date().getTime()).toString());
			response.setErrorDetails(errorDetails);
			
		} 
		return response;
	}

}
