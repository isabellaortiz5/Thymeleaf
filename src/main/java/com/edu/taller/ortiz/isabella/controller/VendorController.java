package com.edu.taller.ortiz.isabella.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.service.interfaces.VendorService;
import com.edu.taller.ortiz.isabella.user.UserEntity;

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
		Iterable<Vendor> vendors = vendorService.findAll();
		
		if(vendors.iterator().hasNext()){
			model.addAttribute("vendors", vendors);
		}
		
		return "/vendors/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editVendor(Model model, @PathVariable("id") Integer id) {
		Optional<Vendor> vendors = vendorService.findById(id);
		Vendor vendor = vendors.get();
		model.addAttribute("vendors", vendor);
		
		return "vendors/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditVendor(Model model, @PathVariable Integer id, @ModelAttribute Vendor vendor) {
		vendorService.edit(vendor);
		return "redirect:/vendors";
	}
	@GetMapping("/add")
	public String addVendor(Model model) {
		model.addAttribute("vendors", new Vendor());
		return "vendors/add";
	}

	@PostMapping("/add")
	public String addVendorPost(Model model, @ModelAttribute Vendor vendor) {
		vendorService.add(vendor);
		return "redirect:/vendors";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model, @PathVariable Integer id) {
		vendorService.delete(id);
		return "vendors/index";
	}
	@GetMapping("/{id}")
	public String getVendor(Model model, @PathVariable("id") Integer id) {
		Optional<Vendor> vendors = vendorService.findById(id);
		Vendor vendor = vendors.get();
		model.addAttribute("vendors", vendor);
		return "vendors/information";
	}
}
