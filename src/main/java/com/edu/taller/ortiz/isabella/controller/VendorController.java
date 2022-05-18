package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.service.implementations.BusinessentityServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.VendorServiceImp;

@Controller
@RequestMapping("vendors")
public class VendorController {

	private VendorServiceImp vendorService;
	private BusinessentityServiceImp bs;
	
	@Autowired
	public VendorController(VendorServiceImp vendorService, BusinessentityServiceImp bs) {
		this.vendorService = vendorService;
		this.bs = bs;
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
		Vendor vendor = vendorService.findById(id);
		model.addAttribute("vendors", vendor);
		model.addAttribute("businessentity", bs.findAll());
		
		return "vendors/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditVendor(Model model, @PathVariable Integer id, @ModelAttribute Vendor vendor) {
		vendor.setBusinessentityid(id);
		vendorService.edit(vendor);
		return "redirect:/vendors";
	}
	@GetMapping("/add")
	public String addVendor(Model model) {
		model.addAttribute("vendors", new Vendor());
		model.addAttribute("businessentity", bs.findAll());
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
		Vendor vendor = vendorService.findById(id);
		model.addAttribute("vendors", vendor);
		return "vendors/information";
	}
}
