package io.github.josephmtinangi.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.josephmtinangi.models.District;
import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.repositories.DistrictRepository;
import io.github.josephmtinangi.repositories.RegionRepository;

@Controller
@RequestMapping(path = "/dashboard/regions/{regionId}/districts/{districtId}/wards")
public class WardsWebController {

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public String store(@PathVariable Long regionId, @PathVariable Long districtId) {

		Region region = regionRepository.findOne(regionId);

		District district = districtRepository.findFirstByIdAndRegionId(districtId, region.getId());

		return "redirect:/dashboard/regions/" + region.getId() + "/districts/" + district.getId();
	}
}
