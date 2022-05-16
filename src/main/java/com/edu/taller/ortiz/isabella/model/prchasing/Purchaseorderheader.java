package com.edu.taller.ortiz.isabella.model.prchasing;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


/**
 * The persistent class for the purchaseorderheader database table.
 * 
 */
@Entity
@NamedQuery(name="Purchaseorderheader.findAll", query="SELECT p FROM Purchaseorderheader p")
public class Purchaseorderheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PURCHASEORDERHEADER_PURCHASEORDERID_GENERATOR",allocationSize = 1, sequenceName="PURCHASEORDERHEADER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PURCHASEORDERHEADER_PURCHASEORDERID_GENERATOR")
	private Integer purchaseorderid;

	private Integer employeeid;

	private BigDecimal freight;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderdate;

	private Integer revisionnumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate shipdate;

	private Integer status;

	private BigDecimal subtotal;

	private BigDecimal taxamt;

	//bi-directional many-to-one association to Purchaseorderdetail
	@OneToMany(mappedBy="purchaseorderheader")
	private List<Purchaseorderdetail> purchaseorderdetails;

	//bi-directional many-to-one association to Shipmethod
	@ManyToOne
	@JoinColumn(name="shipmethodid")
	private Shipmethod shipmethod;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(name="vendorid")
	private Vendor vendor;

	public Purchaseorderheader() {
	}

	public Integer getPurchaseorderid() {
		return this.purchaseorderid;
	}

	public void setPurchaseorderid(Integer purchaseorderid) {
		this.purchaseorderid = purchaseorderid;
	}

	public Integer getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public BigDecimal getFreight() {
		return this.freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public LocalDate getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public Integer getRevisionnumber() {
		return this.revisionnumber;
	}

	public void setRevisionnumber(Integer revisionnumber) {
		this.revisionnumber = revisionnumber;
	}

	public LocalDate getShipdate() {
		return this.shipdate;
	}

	public void setShipdate(LocalDate shipdate) {
		this.shipdate = shipdate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTaxamt() {
		return this.taxamt;
	}

	public void setTaxamt(BigDecimal taxamt) {
		this.taxamt = taxamt;
	}

	public List<Purchaseorderdetail> getPurchaseorderdetails() {
		return this.purchaseorderdetails;
	}

	public void setPurchaseorderdetails(List<Purchaseorderdetail> purchaseorderdetails) {
		this.purchaseorderdetails = purchaseorderdetails;
	}

	public Purchaseorderdetail addPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail) {
		getPurchaseorderdetails().add(purchaseorderdetail);
		purchaseorderdetail.setPurchaseorderheader(this);

		return purchaseorderdetail;
	}

	public Purchaseorderdetail removePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail) {
		getPurchaseorderdetails().remove(purchaseorderdetail);
		purchaseorderdetail.setPurchaseorderheader(null);

		return purchaseorderdetail;
	}

	public Shipmethod getShipmethod() {
		return this.shipmethod;
	}

	public void setShipmethod(Shipmethod shipmethod) {
		this.shipmethod = shipmethod;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}