package io.github.josephmtinangi.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.josephmtinangi.models.District;
import io.github.josephmtinangi.repositories.DistrictRepository;

@Controller
@RequestMapping(path = "/dashboard/districts")
public class DistrictWebController {

	@Autowired
	private DistrictRepository districtRepository;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index(Model model) {

		List<District> districts = districtRepository.findAll();

		model.addAttribute("districts", districts);

		return "districts/index";
	}

}
