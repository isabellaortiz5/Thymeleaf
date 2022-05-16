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

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
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
		Iterable<Purchaseorderheader> pohead= purchaseorderheaderService.findAll();
		
		if(pohead.iterator().hasNext()){
			model.addAttribute("purchaseorderhead", pohead);
		}
		
		return "/purchase-order-head/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editPoHead(Model model, @PathVariable("id") Integer id) {
		Optional<Purchaseorderheader> poHead = purchaseorderheaderService.findById(id);
		Purchaseorderheader purchaseorderhead = poHead.get();
		model.addAttribute("purchaseorderheader", purchaseorderhead);
		
		return "purchase-order-head/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditPoHead(Model model, @PathVariable Integer id, @ModelAttribute Purchaseorderheader purchaseorderhead) {
		purchaseorderheaderService.edit(purchaseorderhead);
		return "redirect:/purchase-order-head";
	}
	@GetMapping("/add")
	public String addPoHead(Model model) {
		model.addAttribute("purchaseorderheader", new Purchaseorderheader());
		return "purchase-order-head/add";
	}

	@PostMapping("/add")
	public String addPoHeadPost(Model model, @ModelAttribute Purchaseorderheader purchaseorderhead) {
		purchaseorderheaderService.add(purchaseorderhead);
		return "redirect:/purchase-order-head";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePoHead(Model model, @PathVariable Integer id) {
		purchaseorderheaderService.delete(id);
		return "purchase-order-head/index";
	}
	@GetMapping("/{id}")
	public String getPoHead(Model model, @PathVariable("id") Integer id) {
		Optional<Purchaseorderheader> poHead = purchaseorderheaderService.findById(id);
		Purchaseorderheader purchaseorderhead = poHead.get();
		model.addAttribute("purchaseorderheader", purchaseorderhead);
		return "purchase-order-head/information";
	}
}
