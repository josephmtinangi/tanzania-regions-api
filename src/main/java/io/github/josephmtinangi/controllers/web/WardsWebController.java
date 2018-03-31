package io.github.josephmtinangi.controllers.web;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import io.github.josephmtinangi.models.District;
import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.models.Ward;
import io.github.josephmtinangi.repositories.DistrictRepository;
import io.github.josephmtinangi.repositories.RegionRepository;
import io.github.josephmtinangi.repositories.WardRepository;
import io.github.josephmtinangi.utilities.Helper;

@Controller
@RequestMapping(path = "/dashboard/regions/{regionId}/districts/{districtId}/wards")
public class WardsWebController {

	@Value("${app.rootStorage}")
	private String rootStorage;

	private static final Logger log = LoggerFactory.getLogger(WardsWebController.class);

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private WardRepository wardRepository;

	@RequestMapping(path = "/{wardId}", method = RequestMethod.GET)
	public String show(Model model, @PathVariable Long regionId, @PathVariable Long districtId,
			@PathVariable Long wardId) {

		Region region = regionRepository.findOne(regionId);

		District district = districtRepository.findFirstByIdAndRegionId(districtId, region.getId());

		Ward ward = wardRepository.findFirstByIdAndDistrictId(wardId, district.getId());

		model.addAttribute("ward", ward);

		return "wards/show";
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public String store(@PathVariable Long regionId, @PathVariable Long districtId, @RequestParam MultipartFile file)
			throws Exception {

		Region region = regionRepository.findOne(regionId);

		District district = districtRepository.findFirstByIdAndRegionId(districtId, region.getId());

		if (file != null && !file.isEmpty()) {

			try {

				String filepath = rootStorage + "/" + Helper.saveFile(file, rootStorage);
				log.info("filepath = " + filepath);

				File f = new File(filepath);

				Path path = Paths.get(f.getAbsolutePath());
				log.info("path = " + path);

				Reader reader = Files.newBufferedReader(path);
				CSVReader csvReader = new CSVReader(reader);

				String[] nextLine;

				List<Ward> wardList = new ArrayList<>();
				while ((nextLine = csvReader.readNext()) != null) {
					log.info(nextLine[0]);
					Ward ward = new Ward();
					ward.setName(nextLine[0]);
					ward.setSlug(nextLine[0]);
					ward.setDistrict(district);

					wardList.add(ward);
				}

				wardRepository.save(wardList);

				csvReader.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return "redirect:/dashboard/regions/" + region.getId() + "/districts/" + district.getId();
	}
}
