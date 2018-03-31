package io.github.josephmtinangi.controllers.api;

import java.util.ArrayList;
import java.util.HashMap;

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
import io.github.josephmtinangi.models.Ward;
import io.github.josephmtinangi.repositories.DistrictRepository;
import io.github.josephmtinangi.repositories.RegionRepository;
import io.github.josephmtinangi.utilities.Helper;

@RestController
@RequestMapping(path = "/regions/{regionSlug}/districts")
public class DistrictController {

	@Value("${app.url}")
	private String appURL;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(path = "/{slug}")
	public ResponseEntity<?> show(@PathVariable String regionSlug, @PathVariable String slug) {

		Region region = regionRepository.findFirstBySlug(regionSlug);

		if (region == null) {
			HashMap<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", "Not Found");
			return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
		}

		District district = districtRepository.findByRegionIdAndSlug(region.getId(), slug);

		if (district == null) {
			HashMap<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", "Not Found");
			return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
		}

		HashMap<String, Object> districtMap = new HashMap<>();
		districtMap.put("id", district.getId());
		districtMap.put("name", district.getName());
		districtMap.put("url",
				appURL + "/regions/" + district.getRegion().getSlug() + "/districts/" + district.getSlug());
		districtMap.put("wards_url",
				appURL + "/regions/" + district.getRegion().getSlug() + "/districts/" + district.getSlug() + "/wards");

		HashMap<String, Object> regionMap = new HashMap<>();
		regionMap.put("id", district.getRegion().getId());
		regionMap.put("name", district.getRegion().getName());
		regionMap.put("url", appURL + "/regions/" + region.getSlug());
		regionMap.put("districts_url", appURL + "/regions/" + region.getSlug() + "/districts");

		districtMap.put("region", regionMap);

		return Helper.createResponse(districtMap, HttpStatus.OK);
	}

	@RequestMapping(path = "/{districtSlug}/wards", method = RequestMethod.GET)
	public ResponseEntity<?> wards(@PathVariable String regionSlug, @PathVariable String districtSlug) {

		Region region = regionRepository.findFirstBySlug(regionSlug);

		if (region == null) {
			HashMap<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", "Not Found");
			return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
		}

		District district = districtRepository.findByRegionIdAndSlug(region.getId(), districtSlug);

		if (district == null) {
			HashMap<String, Object> outputMap = new HashMap<>();
			outputMap.put("message", "Not Found");
			return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
		}

		ArrayList<Object> wardsList = new ArrayList<>();

		for (Ward ward : district.getWards()) {
			HashMap<String, Object> wardMap = new HashMap<>();
			wardMap.put("id", ward.getId());
			wardMap.put("name", ward.getName());
			wardMap.put("url", appURL + "/regions/" + district.getRegion().getSlug() + "/districts/"
					+ district.getSlug() + "/wards/" + ward.getSlug());

			wardsList.add(wardMap);
		}

		return Helper.createResponse(wardsList, HttpStatus.OK);
	}
}
