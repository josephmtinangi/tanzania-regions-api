package io.github.josephmtinangi.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.repositories.RegionRepository;

@Controller
@RequestMapping(path = "/dashboard/regions")
public class RegionWebController {

	@Autowired
	private RegionRepository regionRepository;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index(Model model) {

		List<Region> regions = regionRepository.findAll();

		model.addAttribute("regions", regions);

		return "regions/index";
	}
}
