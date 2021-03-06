package com.edu.taller.ortiz.isabella.model.prchasing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the purchaseorderdetail database table.
 *
 */
@Entity
@NamedQuery(name = "Purchaseorderdetail.findAll", query = "SELECT p FROM Purchaseorderdetail p")
public class Purchaseorderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PURCHASEORDERDETAIL_PURCHASEORDERID_GENERATOR",allocationSize = 1, sequenceName="PURCHASEORDERDETAIL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PURCHASEORDERDETAIL_PURCHASEORDERID_GENERATOR")
	private Integer purchaseorderid;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate duedate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	private Integer orderqty;

	private Integer productid;

	private BigDecimal receivedqty;

	private BigDecimal rejectedqty;

	private BigDecimal unitprice;

	// bi-directional many-to-one association to Purchaseorderheader
	@ManyToOne
	@JoinColumn(name = "purchaseorderid", insertable = false, updatable = false)
	private Purchaseorderheader purchaseorderheader;

	public Purchaseorderdetail() {
	}

	public LocalDate getDuedate() {
		return this.duedate;
	}

	public Integer getId() {
		return this.purchaseorderid;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Integer getOrderqty() {
		return this.orderqty;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public Purchaseorderheader getPurchaseorderheader() {
		return this.purchaseorderheader;
	}

	public BigDecimal getReceivedqty() {
		return this.receivedqty;
	}

	public BigDecimal getRejectedqty() {
		return this.rejectedqty;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}

	public void setId(Integer id) {
		this.purchaseorderid = id;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setOrderqty(Integer orderqty) {
		this.orderqty = orderqty;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public void setPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		this.purchaseorderheader = purchaseorderheader;
	}

	public void setReceivedqty(BigDecimal receivedqty) {
		this.receivedqty = receivedqty;
	}

	public void setRejectedqty(BigDecimal rejectedqty) {
		this.rejectedqty = rejectedqty;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

}