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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import io.github.josephmtinangi.models.Region;
import io.github.josephmtinangi.repositories.RegionRepository;
import io.github.josephmtinangi.utilities.Helper;

@Controller
@RequestMapping(path = "/dashboard/regions")
public class RegionsWebController {

	private static final Logger log = LoggerFactory.getLogger(RegionsWebController.class);

	@Value("${appRootStorage}")
	private String rootStorage;

	@Autowired
	private RegionRepository regionRepository;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public String index(Model model) {

		List<Region> regions = regionRepository.findAll();

		model.addAttribute("regions", regions);

		return "regions/index";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public String show(Model model, @PathVariable Long id) {

		Region region = regionRepository.findOne(id);

		model.addAttribute("region", region);

		return "regions/show";
	}

	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String create(Model model) {

		return "regions/create";
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public String store(@ModelAttribute Region region) {

		regionRepository.save(region);

		return "redirect:/dashboard/regions";
	}

	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam MultipartFile file) throws Exception {

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

				List<Region> regionsList = new ArrayList<>();
				while ((nextLine = csvReader.readNext()) != null) {
					log.info(nextLine[0]);
					Region region = new Region();
					region.setName(nextLine[0]);
					region.setSlug(nextLine[0]);

					regionsList.add(region);
				}

				regionRepository.save(regionsList);

				csvReader.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return "redirect:/dashboard/regions";
	}
}
