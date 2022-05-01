package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.service.interfaces.ShipmethodService;

@Controller
@RequestMapping("ship-method")
public class ShipMethodController {

	private  ShipmethodService shipmethodService;

	@Autowired
	public ShipMethodController(ShipmethodService shipmethodService) {

		this.shipmethodService = shipmethodService;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", shipmethodService.findAll());
		return "ship-method/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "ship-method/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "ship-method/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "ship-method/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "ship-method/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "ship-method/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model, @PathVariable("id") Integer id) {

		return "ship-method/information";
	}
}
