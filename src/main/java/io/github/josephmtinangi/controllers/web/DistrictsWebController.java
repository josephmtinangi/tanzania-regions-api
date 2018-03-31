package io.github.josephmtinangi.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
