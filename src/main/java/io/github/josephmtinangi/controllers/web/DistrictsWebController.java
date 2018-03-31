package io.github.josephmtinangi.controllers.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.josephmtinangi.models.District;
import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.repositories.DistrictRepository;
import io.github.josephmtinangi.repositories.RegionRepository;

@Controller
@RequestMapping(path = "/dashboard/regions")
public class DistrictsWebController {

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(path = "/{regionId}/districts/{districtId}", method = RequestMethod.GET)
	public String show(Model model, @PathVariable Long regionId, @PathVariable Long districtId) {

		Region region = regionRepository.findOne(regionId);

		District district = districtRepository.findFirstByIdAndRegionId(districtId, region.getId());

		model.addAttribute("district", district);

		return "districts/show";
	}

	@RequestMapping(path = "/{regionId}/districts", method = RequestMethod.POST)
	public String store(@PathVariable Long regionId, @RequestParam String districtsString) {

		Region region = regionRepository.findOne(regionId);

		String[] districts = districtsString.split(",");

		List<District> districtList = new ArrayList<>();
		if (districts != null && districts.length > 0) {
			for (String n : districts) {
				String name = n.replaceAll("\\p{C}", "").trim();
				District district = new District();
				district.setName(name);
				district.setSlug(name);
				district.setRegion(region);

				districtList.add(district);
			}
		}

		districtRepository.save(districtList);

		return "redirect:/dashboard/regions/" + region.getId();
	}

}
