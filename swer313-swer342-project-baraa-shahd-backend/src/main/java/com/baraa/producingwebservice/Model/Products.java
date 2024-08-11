package com.baraa.producingwebservice.Model;


import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productCode")

	private String productCode;

	@Column(name = "productName")

	private String productName;

	@Column(name = "productLine")
	private String productLine;

	@Column(name = "productScale")
	private String productScale;

	@Column(name = "productVendor")
	private String productVendor;

	@Column(name = "productDescription")
	private String productDescription;

	@Column(name = "quantityInStock")
	private int quantityInStock;

	@Column(name = "buyPrice")
	private double buyPrice;

	@Column(name = "MSRP")
	private double MSRP;

	@OneToMany(targetEntity = OrderDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "productCode")
	private List<OrderDetails> OrderDetails;

	public Products() {
		super();
	}

	public Products(String productCode, String productName, String productLine, String productScale,
			String productVendor, String productDescription, int quantityInStock, double buyPrice, double mSRP,
			List<OrderDetails> orderDetails) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		MSRP = mSRP;
		//OrderDetails = orderDetails;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getMSRP() {
		return MSRP;
	}

	public void setMSRP(double mSRP) {
		MSRP = mSRP;
	}

	public List<OrderDetails> getOrderDetails() {
		return OrderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		OrderDetails = orderDetails;
	}
	@Override
	public int hashCode() {
		return Objects.hash(MSRP, OrderDetails, buyPrice, productCode, productDescription, productLine, productName,
				productScale, productVendor, quantityInStock);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Double.doubleToLongBits(MSRP) == Double.doubleToLongBits(other.MSRP)
				&& Objects.equals(OrderDetails, other.OrderDetails)
				&& Double.doubleToLongBits(buyPrice) == Double.doubleToLongBits(other.buyPrice)
				&& Objects.equals(productCode, other.productCode)
				&& Objects.equals(productDescription, other.productDescription)
				&& Objects.equals(productLine, other.productLine) && Objects.equals(productName, other.productName)
				&& Objects.equals(productScale, other.productScale)
				&& Objects.equals(productVendor, other.productVendor) && quantityInStock == other.quantityInStock;
	}

	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescription="
				+ productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", MSRP="
				+ MSRP + "]";
	}

}