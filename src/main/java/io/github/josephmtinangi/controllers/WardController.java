package io.github.josephmtinangi.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.josephmtinangi.models.District;
import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.models.Ward;
import io.github.josephmtinangi.repositories.DistrictRepository;
import io.github.josephmtinangi.repositories.RegionRepository;
import io.github.josephmtinangi.repositories.WardRepository;
import io.github.josephmtinangi.utilities.Helper;

@RestController
@RequestMapping(path = "/regions/{regionSlug}/districts/{districtSlug}/wards")
public class WardController {

    @Value("${app.url}")
    private String appURL;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> store(@PathVariable String regionSlug, @PathVariable String districtSlug, @ModelAttribute Ward ward) {

        Region region = regionRepository.findFirstBySlug(regionSlug);
        if (region == null) {
            return Helper.createResponse(Helper.createMessage("Region not found"), HttpStatus.BAD_REQUEST);
        }

        District district = districtRepository.findByRegionIdAndSlug(region.getId(), districtSlug);
        if (district == null) {
            return Helper.createResponse(Helper.createMessage("District not found"), HttpStatus.BAD_REQUEST);
        }

        ward.setDistrict(district);
        Ward newWard = wardRepository.save(ward);

        return Helper.createResponse(newWard, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{wardSlug}")
    public ResponseEntity<?> show(@PathVariable String regionSlug, @PathVariable String districtSlug,
                                  @PathVariable String wardSlug) {

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

        Ward ward = wardRepository.findByDistrictIdAndSlug(district.getId(), wardSlug);

        if (ward == null) {
            HashMap<String, Object> outputMap = new HashMap<>();
            outputMap.put("message", "Not Found");
            return Helper.createResponse(outputMap, HttpStatus.BAD_REQUEST);
        }

        HashMap<String, Object> wardMap = new HashMap<>();
        wardMap.put("id", ward.getId());
        wardMap.put("name", ward.getName());
        wardMap.put("url", appURL + "/regions/" + district.getRegion().getSlug() + "/districts/" + district.getSlug()
                + "/wards/" + ward.getSlug());

        return Helper.createResponse(wardMap, HttpStatus.OK);
    }
}
