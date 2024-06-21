package domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Profit {
	public Profit(Timestamp created2, Integer estNum2, String productName2, Integer productNum2, Integer quantity2,
			BigDecimal salePrice, BigDecimal totalSales, BigDecimal unitCost, BigDecimal totalCost, BigDecimal profit2,
			String userName2, Integer userId2, String estStatus2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	private Date created;
	private Integer estNum;
	private String productName;
	private Integer productNum;
	private Integer quantity;
	private Integer salePrice;
	private Integer totalSales;
	private Integer unitCost;
	private Integer totalCost;
	private Integer diff;
	private String userName;
	private Integer userId;
	private String estStatus;
	public Profit() {}
}
