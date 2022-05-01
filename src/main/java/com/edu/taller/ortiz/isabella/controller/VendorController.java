package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.service.interfaces.VendorService;

@Controller
@RequestMapping("vendors")
public class VendorController {

	private VendorService vendorService;

	@Autowired
	public VendorController(VendorService vendorService) {

		this.vendorService = vendorService;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("vendors", vendorService.findAll());
		return "/vendors/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editVendor(Model model, @PathVariable("id") Integer id) {

		return "vendors/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditVendor(Model model) {

		return "vendors/edit";
	}
	@GetMapping("/add")
	public String addVendor(Model model) {
		return "vendors/add";
	}

	@PostMapping("/add")
	public String addVendorPost(Model model) {
		return "vendors/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "vendors/index";
	}
	@GetMapping("/{id}")
	public String getVendor(Model model, @PathVariable("id") Integer id) {

		return "vendors/information";
	}
}
