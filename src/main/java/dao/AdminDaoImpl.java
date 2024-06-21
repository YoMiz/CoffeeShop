package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.Users;

public class AdminDaoImpl implements AdminDao {
	private DataSource ds;
	private Users user;

	public AdminDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Users> findAll() throws Exception {
		ArrayList<Users> userList = new ArrayList<>();
		
		String sql = "SELECT * FROM users";
		try(Connection con = ds.getConnection()){
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Users user = mapToAdmin(rs);
				userList.add(user);
			}
		}catch(Exception e) {
			throw e;
		}
		return userList;
	}

	@Override
	public Users findById(Integer id) throws Exception {
		String sql = "SELECT users" + " user_name" + " user_address," + " user_mail," + " WHERE (user_id = ?)";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, user.getUserId(), Types.INTEGER);
			stmt.executeQuery();
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	@Override
	public void insert(Users user) throws Exception {
		String sql = "INSERT INTO users(user_name, user_pass, user_address, user_mail, user_created)"
				+ " VALUES(?,?,?,?,CURDATE())";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			String hashed = BCrypt.hashpw(user.getUserPass(), BCrypt.gensalt());
			stmt.setString(2, hashed);
			stmt.setString(3, user.getUserAddress());
			stmt.setString(4, user.getUserMail());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("記録できませんでした");
			throw e;

		}
	}

	@Override
	public void update(Users user) throws Exception {
		String sql = "UPDATE users SET " + " user_name = ?, " + " user_address = ?, " + " user_mail = ? "
				+ " WHERE (user_id = ?)";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserAddress());
			stmt.setString(3, user.getUserMail());
			stmt.setObject(4, user.getUserId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void FAUserDelete(Users user) throws Exception {
		String sql = "UPDATE users SET user_deleted = CURDATE() WHERE user_id = ?";
		try(Connection con = ds.getConnection()){
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, user.getUserId(), Types.INTEGER);
			stmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}

	}

	@Override
	public Users findByUserNameAndUserPass(Users user) throws Exception {
	    Users userInfo = null;
	    try (Connection con = ds.getConnection()) {
	        String sql = "SELECT * FROM users WHERE user_name=?;";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, user.getUserName());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String hashed = rs.getString("user_pass");
	            if (BCrypt.checkpw(user.getUserPass(), hashed)) {
	                userInfo = new Users();
	                userInfo.setUserId((Integer)rs.getObject("user_id"));
	                userInfo.setUserName(rs.getString("user_name"));
	                userInfo.setUserPass(rs.getString("user_pass"));
	                userInfo.setUserAddress(rs.getString("user_address"));
	                userInfo.setUserMail(rs.getString("user_mail"));
	                userInfo.setUserCreated(rs.getDate("user_created"));
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("NO USER FOUND");
	        throw e;
	    }
	    return userInfo;
	}
	
	@Override
	public void FAUserUpdate(Users user) throws Exception {
		String sql = "UPDATE users SET " 
				+ " user_name = ?, " 
				+ " user_pass = ?, " 
				+ " user_address = ?, " 
				+ " user_mail = ?, " 
				+ " user_updated = CURDATE() " 
				+ " WHERE (user_id = ?)";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserPass());
			stmt.setString(3, user.getUserAddress());
			stmt.setString(4, user.getUserMail());
			stmt.setObject(5, user.getUserId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	// ジェネリクスに格納
	private Users mapToAdmin(ResultSet rs) throws Exception {
		user = new Users();
		user.setUserId((Integer) rs.getObject("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setUserPass(rs.getString("user_pass"));
		user.setUserAddress(rs.getString("user_address"));
		user.setUserMail(rs.getString("user_mail"));
		user.setUserCreated(rs.getDate("user_created"));
		user.setUserUpdated(rs.getDate("user_updated"));
		user.setUserDeleted(rs.getDate("user_deleted"));
		
		return user;

	}

}