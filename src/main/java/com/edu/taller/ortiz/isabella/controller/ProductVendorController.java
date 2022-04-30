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
@RequestMapping("product-vendors")
public class ProductVendorController {

	private VendorService vendorService;

	@Autowired
	public ProductVendorController(VendorService vendorService) {

		this.vendorService = vendorService;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", vendorService.findAll());
		return "/product-vendors/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "product-vendors/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "product-vendors/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "product-vendors/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "product-vendors/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "product-vendors/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model, @PathVariable("id") Integer id) {

		return "product-vendors/information";
	}
}
