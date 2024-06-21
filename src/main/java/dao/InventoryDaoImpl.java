package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import domain.Inventory;
import domain.ProductBeans;

public class InventoryDaoImpl implements InventoryDao {
	private DataSource ds;
	private Inventory inventory;

	public InventoryDaoImpl(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public ArrayList<Inventory> findAllInventory() throws Exception {
		ArrayList<Inventory> inventoryList = new ArrayList<>();
		
		String sql = "SELECT "
				+ " inventory.product_id, "
				+ " product_name,"
				+ " inventory_quantity,"
				+ " inventory_updated,"
				+ " cost, price,"
				+ " company,"
				+ " contact,"
				+ " products.updated, "
				+ " description, "
				+ " file"
				+ " FROM inventory"
				+ " JOIN products"
				+ " ON products.product_id = inventory.product_id";
		try(Connection con = ds.getConnection()){
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				inventory = mapToInventory(rs);
				inventoryList.add(inventory);
			}
		}
		
		return inventoryList;
	}

	private Inventory mapToInventory(ResultSet rs) throws Exception{
		Inventory inventory = new Inventory();
		inventory.setProductId((Integer)rs.getObject("product_id"));
		inventory.setProductName(rs.getString("product_name"));
		inventory.setQuantity((Integer)rs.getObject("inventory_quantity"));
		inventory.setInventoryUpdated(rs.getTimestamp("inventory_updated"));
		inventory.setItemCost((Integer)rs.getObject("cost"));
		inventory.setItemPrice((Integer)rs.getObject("price"));
		inventory.setCompanyName(rs.getString("company"));
		inventory.setContact(rs.getString("contact"));
		inventory.setDescription(rs.getString("description"));
		inventory.setFile(rs.getString("file"));
		
		inventory.setCostUpdated(rs.getTimestamp("updated"));
		
		return inventory;
		
		
	}

	@Override
	public void addToInventory(Inventory inventory, ProductBeans product) throws Exception {
		String sql = "INSERT INTO inventory(product_type, inventory_quantity, inventory_updated)"
				+ " VALUES(?,?, CURDATE())";
		try(Connection con = ds.getConnection()){
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, (Integer)product.getProductType());
			stmt.setObject(2, (Integer)inventory.getQuantity());
			stmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("DB:Inventory 記録できませんでした");
			throw e;
		}
	}
}
