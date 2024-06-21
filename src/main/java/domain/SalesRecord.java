package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesRecord {
	private Integer estId;
	private Integer userId;
	private Integer totalPrice;
	private Date est_created;

	public SalesRecord() {}
}
