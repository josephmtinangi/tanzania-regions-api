package io.github.josephmtinangi.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.josephmtinangi.utilities.Helper;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

	@Value("${app.url}")
	private String appURL;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> index() {

		HashMap<String, Object> output = new HashMap<>();
		output.put("regions_url", appURL + "/regions");

		return Helper.createResponse(output, HttpStatus.OK);
	}
}
