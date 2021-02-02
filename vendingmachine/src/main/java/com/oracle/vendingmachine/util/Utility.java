package com.oracle.vendingmachine.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oracle.vendingmachine.apimodel.BaseApiModel;
import com.oracle.vendingmachine.constant.HttpStatusCodes;

/**
 * @author kareem
 *
 */
public class Utility {

	/**
	 * Function  to transform the <T extends BaseApiModel> response to ResponseEntity 
	 * @param <T> - object which extends BaseApiModel
	 * @param response  
	 * @return   - return ResponseEntity
	 */
	public static <T extends BaseApiModel> ResponseEntity<T> tranformResponseEntity(T response) {

		if (HttpStatusCodes.STATUS_500.getHttpCode() == response.getErrorDetails().getStatus()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} else if (HttpStatusCodes.STATUS_400.getHttpCode() == response.getErrorDetails().getStatus()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else if (HttpStatusCodes.STATUS_401.getHttpCode() == response.getErrorDetails().getStatus()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		} else if (HttpStatusCodes.STATUS_404.getHttpCode() == response.getErrorDetails().getStatus()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
