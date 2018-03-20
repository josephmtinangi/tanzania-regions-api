package io.github.josephmtinangi.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Helper {

	public static ResponseEntity<?> createResponse(Object data, HttpStatus code) {

		return new ResponseEntity<>(data, code);
	}

}
