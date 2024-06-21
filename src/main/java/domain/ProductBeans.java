package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductBeans {
	private Integer productId;
	private Integer productType;
	private String productName;
	private Integer productSourBitter;
	private Integer productBodySharp;
	private Integer productFlavor;
	private Integer productSweet;
	private String productDescription;
	private String productPict;
	private Integer productCost;
	private Integer productPrice;
	private String productCompany;
	private String productContact;
	private Timestamp productCreated;
	private Timestamp productUpdated;
	private Timestamp productDeleted;

public ProductBeans() {}

}