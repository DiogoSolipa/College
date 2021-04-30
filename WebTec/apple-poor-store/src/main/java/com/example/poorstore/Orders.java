package com.example.poorstore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Long productID;


	protected Orders() {}

	public Orders(Long productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return String.format(
				"Orders[id=%d, productID=%d]",
				id, productID);
	}
	
	public Long getId() {
		return id;
	}

	public Long getProductID() {
		return productID;
	}
}
