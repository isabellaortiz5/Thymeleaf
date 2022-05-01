package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderdetailService;

@Controller
@RequestMapping("purchase-order-detail")
public class PurchaseorderDetailController {

	private PurchaseorderdetailService podService;

	@Autowired
	public PurchaseorderDetailController(PurchaseorderdetailService podService) {

		this.podService = podService;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", podService.findAll());
		return "/purchase-order-detail/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "purchase-order-detail/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "purchase-order-detail/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "purchase-order-detail/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "purchase-order-detail/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "purchase-order-detail/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model, @PathVariable("id") Integer id) {

		return "purchase-order-detail/information";
	}
}
