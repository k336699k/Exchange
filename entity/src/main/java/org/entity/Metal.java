package org.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class Metal implements Serializable {

	private static final long serialVersionUID = 1L;
	private int iD;
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$" , message="Wrong name, must be with no spaces")
	private String title;
	@Pattern(regexp="^[0-9а-яА-ЯёЁa-zA-Z]+$" , message="Wrong quantity, must be with no spaces")
	private String quantity;
	
	private int price;
	
	@Pattern(regexp="^[1-9]{1}\\d*$" , message="Wrong number, must be with no spaces")
	private String priceString;

	public Metal() {

	}

	public Metal( String title, String quantity, int price) {
		super();
		
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

	public String getPriceString() {
		return priceString;
	}

	public void setPriceString(String priceString) {
		this.priceString = priceString;
	}

	@Override
	public String toString() {
		return "Metal [iD=" + iD + ", title=" + title + ", " + "quantity=" + quantity + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iD;
		result = prime * result + price;
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metal other = (Metal) obj;
		if (iD != other.iD)
			return false;
		if (price != other.price)
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
