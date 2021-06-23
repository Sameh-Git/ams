
package com.sip.ams.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Candidat;

@Controller
public class HomeController {
	static ArrayList<Candidat> lc;
	static {

		lc = new ArrayList<>();
		Candidat c1 = new Candidat(0, "Sameh", "sameh@gmail.com", "11111111");
		Candidat c2 = new Candidat(1, "Amine", "amine@gmail.com", "22222222");
		Candidat c3 = new Candidat(2, "Naouel", "naouel@gmail.com", "33333333");
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
	}

	@RequestMapping("/index")
	// @ResponseBody
	public String home() {

		// return "<h2>Bienvenue au BootCamp</h2>";
		return "candidat/index";
	}

	@RequestMapping("/candidats")
	public String listCandidats(Model m) {

		String libelleFormation = "Spring Boot & Angular";
		m.addAttribute("lf", libelleFormation);
		String formateur = "Mohamed Amine Mezghich";
		m.addAttribute("coach", formateur);
		/*
		 * Candidat c1 = new Candidat(1,"Sameh", "sameh@gmail.com", "11111111");
		 * Candidat c2 = new Candidat(2,"Amine", "amine@gmail.com" , "22222222");
		 * Candidat c3 = new Candidat(3,"Naouel" ,"naouel@gmail.com","33333333");
		 * Candidat tab[] = new Candidat[3]; tab[0] = c1; tab[1] = c2; tab[2] = c3;
		 */
		m.addAttribute("tab", lc);
		return "candidat/Candidats";
	}

	@GetMapping("/add")
	// @ResponseBody
	public String addCandidate() {
		return "candidat/add";
	}

	@PostMapping("/add")
	//@ResponseBody
	public String saveCandidate(@RequestParam("id") int id, @RequestParam("nom") String nom,

			@RequestParam("email") String email, @RequestParam("tel") String tel) {
		// System.out.println(id+nom+email+tel);
		// return "info"+id+nom+email+tel;
		Candidat temp = new Candidat(id, nom, email, tel);
		lc.add(temp);
       return "redirect:candidats";
	}
	
	@GetMapping("/show/{idc}")
	@ResponseBody
	public String show(@PathVariable("idc") int id) {
		return "ID: "+id;
	}

	@GetMapping("/delete/{idc}")
	
	public String delete(@PathVariable("idc") int id) {
		
		lc.remove(id);
		  return "redirect:../candidats";
	}

}
