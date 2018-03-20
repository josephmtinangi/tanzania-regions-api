package io.github.josephmtinangi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.repositories.RegionRepository;
import io.github.josephmtinangi.utilities.Helper;

@RestController
@RequestMapping(path = "/regions")
public class RegionController {

	@Autowired
	private RegionRepository regionRepository;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> index() {

		List<Region> regions = regionRepository.findAll();

		return Helper.createResponse(regions, HttpStatus.OK);
	}
}
