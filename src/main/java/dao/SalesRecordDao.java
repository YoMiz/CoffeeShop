package dao;

import java.util.ArrayList;

import domain.CartItem;
import domain.Profit;
import domain.SalesRecord;

public interface SalesRecordDao {
	public int insertAndGetId(SalesRecord salesRecord) throws Exception;
	public void updateInventory(CartItem cartItem, int final_quantity) throws Exception;
	public void insertDetails(int estId, CartItem cartItem) throws Exception;
	public int getInventory(CartItem cartItem) throws Exception;
	ArrayList<Profit> showAllEstimates() throws Exception;
}
