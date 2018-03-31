package io.github.josephmtinangi.controllers.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.josephmtinangi.models.District;
import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.repositories.RegionRepository;
import io.github.josephmtinangi.utilities.Helper;

@RestController
@RequestMapping(path = "/regions")
public class RegionController {

	@Value("${app.url}")
	private String appURL;

	@Autowired
	private RegionRepository regionRepository;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> index() {

		List<Region> regions = regionRepository.findAll();

		ArrayList<Object> regionList = new ArrayList<>();

		for (Region region : regions) {
			HashMap<String, Object> regionMap = new HashMap<>();
			regionMap.put("name", region.getName());
			regionMap.put("url", appURL + "/regions/" + region.getSlug());
			regionMap.put("districts_url", appURL + "/regions/" + region.getSlug() + "/districts");
			regionList.add(regionMap);
		}

		return Helper.createResponse(regionList, HttpStatus.OK);
	}

	@RequestMapping(path = "/{slug}", method = RequestMethod.GET)
	public ResponseEntity<?> show(@PathVariable String slug) {

		Region region = regionRepository.findFirstBySlug(slug);

		if (region == null) {
			HashMap<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", "Not Found");
			return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
		}

		HashMap<String, Object> regionMap = new HashMap<>();
		regionMap.put("id", region.getId());
		regionMap.put("name", region.getName());
		regionMap.put("districts_url", appURL + "/regions/" + region.getSlug() + "/districts");

		return Helper.createResponse(regionMap, HttpStatus.OK);
	}

	@RequestMapping(path = "/{slug}/districts", method = RequestMethod.GET)
	public ResponseEntity<?> districts(@PathVariable String slug) {

		Region region = regionRepository.findFirstBySlug(slug);

		if (region == null) {
			HashMap<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", "Not Found");
			return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
		}

		List<District> districs = region.getDistricts();

		ArrayList<Object> districtsList = new ArrayList<>();

		for (District district : districs) {
			HashMap<String, Object> districtMap = new HashMap<>();
			districtMap.put("id", district.getId());
			districtMap.put("name", district.getName());
			districtMap.put("url",
					appURL + "/regions/" + district.getRegion().getSlug() + "/districts/" + district.getSlug());

			HashMap<String, Object> regionMap = new HashMap<>();
			regionMap.put("id", district.getRegion().getId());
			regionMap.put("name", district.getRegion().getName());
			regionMap.put("url", appURL + "/regions/" + region.getSlug());
			regionMap.put("districts_url", appURL + "/regions/" + region.getSlug() + "/districts");

			districtMap.put("region", regionMap);

			districtsList.add(districtMap);
		}

		return Helper.createResponse(districtsList, HttpStatus.OK);
	}
}
