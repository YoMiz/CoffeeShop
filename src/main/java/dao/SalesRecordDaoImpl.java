package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

import domain.CartItem;
import domain.Profit;
import domain.SalesRecord;

public class SalesRecordDaoImpl implements SalesRecordDao {
	private DataSource ds;

	public SalesRecordDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public int insertAndGetId(SalesRecord salesRecord) throws Exception {
		int estId = 0;
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO" + " estimates(user_id, total_price, est_created)" + " VALUES(?,?, NOW())";
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, salesRecord.getUserId(), Types.INTEGER);
			stmt.setObject(2, salesRecord.getTotalPrice(), Types.INTEGER);
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					salesRecord.setEstId(generatedKeys.getInt(1));
					estId = salesRecord.getEstId();
				} else {
					throw new SQLException("Creating A Record failed, no ID obtained.");
				}
			}
		}
		return estId;
	}

	@Override
	public void insertDetails(int estId, CartItem cartItem) {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO "
					+ " est_details(est_id, product_id, sale_quantity, sale_price, est_det_created)"
					+ " VALUES(?,?,?,?,NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			int totalItemPrice = cartItem.getItemAmount() * cartItem.getItemPrice();

			stmt.setObject(1, estId, Types.INTEGER);
			stmt.setObject(2, cartItem.getItemId(), Types.INTEGER);
			stmt.setObject(3, cartItem.getItemAmount(), Types.INTEGER);
			stmt.setObject(4, totalItemPrice);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getInventory(CartItem cartItem) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT inventory_quantity FROM inventory WHERE product_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, cartItem.getItemId());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("inventory_quantity");
			} else {
				throw new Exception("No inventory record found for the given product id");
			}
		}
	}

	@Override
	public void updateInventory(CartItem cartItem, int final_quantity) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE inventory SET inventory_quantity = ?, inventory_updated = NOW() WHERE product_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, final_quantity);
			stmt.setObject(2, cartItem.getItemId());
			stmt.executeUpdate();
		}
	}
	@Override
	public ArrayList<Profit> showAllEstimates() throws Exception{
		ArrayList<Profit> estList = new ArrayList<>();
		try(Connection con = ds.getConnection()){
		    String sql = "SELECT "
		            + "e.est_created,"
		            + "e.est_id,"
		            + "ed.product_id,"
		            + "p.product_name,"
		            + "ed.sale_quantity AS quantity,"
		            + "p.price,"
		            + "(p.price * ed.sale_quantity) AS total_price,"
		            + "p.cost,"
		            + "p.cost * ed.sale_quantity AS total_cost,"
		            + "ed.sale_price - (p.cost * ed.sale_quantity) AS profit,"
		            + "u.user_name,"
		            + "u.user_id,"
		            + "e.est_status "
		            + "FROM "
		            + "est_details ed "
		            + "JOIN "
		            + "estimates e ON ed.est_id = e.est_id "
		            + "JOIN "
		            + "products p ON ed.product_id = p.product_id "
		            + "JOIN "
		            + "users u ON e.user_id = u.user_id "
		            + "ORDER BY "
		            + "e.est_created, e.est_id, p.product_id";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			Profit profit = mapToProfit(rs);
			estList.add(profit);
			
		}
		}catch(Exception e) {
			throw e;
		}
		return estList;
	}
	
	private Profit mapToProfit(ResultSet rs) throws Exception {
	    Profit est = new Profit();
		est.setCreated(rs.getTimestamp("est_created"));
	    est.setEstNum(rs.getInt("est_id"));
	    est.setProductNum(rs.getInt("product_id"));
	    est.setProductName(rs.getString("product_name"));
	    est.setQuantity(rs.getInt("quantity"));
	    est.setSalePrice(rs.getInt("price"));
	    est.setTotalSales(rs.getInt("total_price"));
	    est.setUnitCost(rs.getInt("cost"));
	    est.setTotalCost(rs.getInt("total_cost"));
	    est.setDiff(rs.getInt("profit"));
	    est.setUserName(rs.getString("user_name"));
	    est.setUserId(rs.getInt("user_id"));
	    est.setEstStatus(rs.getString("est_status"));
	    
	    return est;
	}
}
