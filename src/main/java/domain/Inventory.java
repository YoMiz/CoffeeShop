package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inventory {
	private int productId;
	private String productName;
	private int quantity;
	private int itemCost;
	private int itemPrice;
	private String companyName;
	private String contact;
	private String description;
	private String file;
	private Timestamp costUpdated;
	private Timestamp inventoryUpdated;

	public Inventory() {
	}

	public Inventory(int quantity) {
		this.quantity = quantity;
	}
}