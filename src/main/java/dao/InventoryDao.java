package dao;

import java.util.ArrayList;

import domain.Inventory;
import domain.ProductBeans;

public interface InventoryDao {
	public ArrayList<Inventory> findAllInventory() throws Exception;
	public void addToInventory(Inventory inventory, ProductBeans product) throws Exception;
}
