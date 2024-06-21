package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
	private Integer itemType;
	private Integer itemId;
	private String itemName;
	private Integer itemPrice;
	private Integer itemAmount;
}


