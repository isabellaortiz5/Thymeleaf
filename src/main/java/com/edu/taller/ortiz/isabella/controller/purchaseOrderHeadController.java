package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderheaderService;

@Controller
@RequestMapping("purchase-order-head")
public class purchaseOrderHeadController {

	private PurchaseorderheaderService purchaseorderheaderService;

	@Autowired
	public purchaseOrderHeadController(PurchaseorderheaderService purchaseorderheaderService) {

		this.purchaseorderheaderService = purchaseorderheaderService;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", purchaseorderheaderService.findAll());
		return "/purchase-order-head/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "purchase-order-head/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "purchase-order-head/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "purchase-order-head/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "purchase-order-head/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "purchase-order-head/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model, @PathVariable("id") Integer id) {

		return "purchase-order-head/information";
	}
}
