package com.sip.ams.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sip.ams.entities.Provider;
import com.sip.ams.entities.Article;
import com.sip.ams.repositories.ProviderRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/provider/")
public class ProviderController {

	public static String uploadDirectory = System.getProperty("user.dir")
			+ "/src/main/resources/static/images/providers";

	private final ProviderRepository providerRepository;

	@Autowired
	public ProviderController(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	@GetMapping("list")

	public String listProviders(Model model) {
		List<Provider> lp = (List<Provider>) providerRepository.findAll();
		if (lp.size() == 0)
			lp = null;
		model.addAttribute("providers", lp);
		return "provider/listProviders";

//System.out.println(lp);
//return "Nombre de fournisseur = " + lp.size();
	}

	@GetMapping("add")
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();// object dont la valeur des attributs par defaut
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("add")
	public String addProvider(@Valid Provider provider, BindingResult result,
			@RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			return "provider/addProvider";
		}
		// UPLOAD
		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		provider.setProviderlogo(fileName.toString());
		//
		providerRepository.save(provider);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		model.addAttribute("provider", provider);
		return "provider/updateProvider";
	}

	@PostMapping("update")
	public String updateProvider(@Valid Provider provider, BindingResult result, Model model,
			@RequestParam("files") MultipartFile[] files) {


	
//Step 2 upload Image
		StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		provider.setProviderlogo(fileName.toString());
		// end step 2
		providerRepository.save(provider);
		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id, Model model) {

		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));

		// DELETE LOGO
		Path fileNameAndPath = Paths.get(uploadDirectory, provider.getProviderlogo());
		try {
			Files.delete(fileNameAndPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//

		providerRepository.delete(provider);

		return "redirect:../list";
	}

	@GetMapping("show/{id}")
	public String showProvider(@PathVariable("id") long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		List<Article> articles = (List<Article>) providerRepository.findArticlesByProvider(id);

		if (articles.size() == 0)
			articles = null;

		model.addAttribute("articles", articles);
		model.addAttribute("provider", provider);
		return "provider/showProvider";
	}

	@GetMapping("search")
	public String findProviders(@RequestParam("search") String name, Model model) {
		List<Provider> providers = (List<Provider>) providerRepository.findProviderByName(name);

		if (providers.size() == 0)
			providers = null;

		model.addAttribute("providers", providers);
		model.addAttribute("name", name);
		return "provider/searchProviders";

	}
}
