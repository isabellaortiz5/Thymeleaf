package com.edu.taller.ortiz.isabella.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
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
		Iterable<Purchaseorderdetail> purchaseOrderDetail = podService.findAll();
		if(purchaseOrderDetail.iterator().hasNext()){
			model.addAttribute("purchaseOrderDetail", purchaseOrderDetail);
		}
		return "/purchase-order-detail/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editPurchaseOrderDetail(Model model, @PathVariable("id") Integer id) {
		Purchaseorderdetail purchaseOrderDetail = podService.findById(id);
		model.addAttribute("purchaseOrderDetail", purchaseOrderDetail);
	
		return "purchase-order-detail/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditPod(Model model, @PathVariable Integer id, @ModelAttribute Purchaseorderdetail purchaseorderdetail) {
		podService.edit(purchaseorderdetail);

		return "redirect:/purchase-order-detail";
	}
	@GetMapping("/add")
	public String addPurchaseOrderDetail(Model model) {
		model.addAttribute("pruchaseOrderDetail", new Purchaseorderdetail());
		return "purchase-order-detail/add";
	}

	@PostMapping("/add")
	public String addPurchaseOrderDetailPost(Model model, @ModelAttribute Purchaseorderdetail purchaseorderdetail) {
		podService.add(purchaseorderdetail);
		return "purchase-order-detail/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePurchaseOrderDetail(Model model, @PathVariable Integer id) {
		podService.delete(id);
		return "purchase-order-detail/index";
	}
	@GetMapping("/{id}")
	public String getPurchaseOrderDetail(Model model, @PathVariable("id") Integer id) {
		Purchaseorderdetail purchaseOrderDetail = podService.findById(id);
		model.addAttribute("purchaseOrderDetail", purchaseOrderDetail);
	
		return "purchase-order-detail/information";
	}
}
