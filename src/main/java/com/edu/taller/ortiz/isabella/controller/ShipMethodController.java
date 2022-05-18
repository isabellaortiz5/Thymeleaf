package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;
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
		model.addAttribute("shipmethod", shipmethodService.findAll());
		return "ship-method/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editShipmethod(Model model, @PathVariable("id") Integer id) {
		Shipmethod s = shipmethodService.findById(id);
		
		if (s == null)
			throw new IllegalArgumentException("Invalid Shipmethod Id:" + id);
		
		model.addAttribute("shipmethod", s);
		
		return "ship-method/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String postEditShipmethod(Model model, @PathVariable Integer id, @ModelAttribute("shipmethod") Shipmethod shipmethod) {
		shipmethodService.edit(shipmethod);
		return "redirect:/ship-method";
		
	}
	
	
	@GetMapping("/add")
	public String addShipmethod(Model model) {
		model.addAttribute("shipmethod", new Shipmethod());
		return "ship-method/add";
	}

	@PostMapping("/add")
	public String addWorkOrderPost(Model model, @ModelAttribute Shipmethod shipmethod) {
		shipmethodService.add(shipmethod);
		return "redirect:/ship-method";
	}

	
	@GetMapping("/delete/{id}")
	public String deleteShipmethod(Model model) {
		return "ship-method/index";
	}
	@GetMapping("/{id}")
	public String getShipmethod(Model model, @PathVariable("id") Integer id) {

		return "ship-method/information";
	}
}
