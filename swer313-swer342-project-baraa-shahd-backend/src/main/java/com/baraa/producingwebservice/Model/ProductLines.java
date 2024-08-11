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
//import com.baraa.producingwebservice.Payment;
import javax.persistence.Table;

@Entity
@Table(name = "productlines")
public class ProductLines {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ProductLine")
	private String ProductLine;

	@Column(name = "textDescription")
	private String textDescription;

	@Column(name = "htmlDescription")
	private String htmlDescription;

	@Column(name = "image")
	private String image;

	@OneToMany(targetEntity = Products.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ProductLine")

	private List<Products> products;

	public ProductLines() {
		super();

	}

	public ProductLines(String productLine, String textDescription, String htmlDescription, String image,
			List<Products> products) {
		super();
		ProductLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
		this.products = products;
	}

	public String getProductLine() {
		return ProductLine;
	}

	public void setProductLine(String productLine) {
		ProductLine = productLine;
	}

	public String getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public String getHtmlDescription() {
		return htmlDescription;
	}

	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ProductLine, htmlDescription, image, textDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductLines other = (ProductLines) obj;
		return Objects.equals(ProductLine, other.ProductLine) && Objects.equals(htmlDescription, other.htmlDescription)
				&& Objects.equals(image, other.image) && Objects.equals(textDescription, other.textDescription);
	}

	@Override
	public String toString() {
		return "ProductLines [ProductLine=" + ProductLine + ", textDescription=" + textDescription
				+ ", htmlDescription=" + htmlDescription + ", image=" + image + "]";
	}

}