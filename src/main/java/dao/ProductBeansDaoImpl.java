package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.ProductBeans;

public class ProductBeansDaoImpl implements ProductBeansDao {
	private DataSource ds;

	public ProductBeansDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<ProductBeans> findAll(int productType) throws Exception {
		List<ProductBeans> beansList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT" + " product_id," + " product_type," + " product_name," + " sour_bitter,"
					+ " body_sharp," + " flavor," + " sweet," + " description," + " file," + " cost," + " price,"
					+ " company," + " contact," + " created," + " updated," + " deleted" + " FROM products"
					+ " WHERE product_type =" + productType;
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				beansList.add(mapToProduct(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return beansList;
	}

	@Override
	public ProductBeans findItemInfoById(int productId) throws Exception {
		ProductBeans product = new ProductBeans();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT" + " product_id," + " product_type," + " product_name," + " sour_bitter,"
					+ " body_sharp," + " flavor," + " sweet," + " description," + " file," + " cost," + " price,"
					+ " company," + " contact," + " created," + " updated," + " deleted" + " FROM products"
					+ " WHERE product_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, productId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() == true) {
				product = mapToProduct(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return product;
	}

	private ProductBeans mapToProduct(ResultSet rs) throws Exception {
		Integer productId = (Integer) rs.getObject("product_id");
		Integer productType = (Integer) rs.getObject("product_type");
		String productName = rs.getString("product_name");
		Integer productSourBitter = (Integer) rs.getObject("sour_bitter");
		Integer productBodySharp = (Integer) rs.getObject("body_sharp");
		Integer productFlavor = (Integer) rs.getObject("flavor");
		Integer productSweet = (Integer) rs.getObject("sweet");
		String productDescription = rs.getString("description");
		String productPict = rs.getString("file");
		Integer productCost = (Integer) rs.getObject("cost");
		Integer productPrice = (Integer) rs.getObject("price");
		String productCompany = rs.getString("company");
		String productContact = rs.getString("contact");
		Timestamp productCreated = rs.getTimestamp("created");
		Timestamp productUpdated = rs.getTimestamp("updated");
		Timestamp productDeleted = rs.getTimestamp("deleted");

		return new ProductBeans(productId, productType, productName, productSourBitter, productBodySharp, productFlavor,
				productSweet, productDescription, productPict, productCost, productPrice, productCompany,
				productContact, productCreated, productUpdated, productDeleted);
	}

	@Override
	public void addItem(ProductBeans product) throws Exception {
		String sql = "INSERT INTO products(product_type, product_name, description, file, cost, price, company, contact, created)"
				+ " VALUES(?,?,?,?,?,?,?,?, CURDATE())";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, (Integer) product.getProductType());
			stmt.setString(2, product.getProductName());
			stmt.setString(3, product.getProductDescription());
			stmt.setString(4, product.getProductPict());
			stmt.setObject(5, (Integer) product.getProductCost());
			stmt.setObject(6, (Integer) product.getProductPrice());
			stmt.setString(7, product.getProductCompany());
			stmt.setString(8, product.getProductContact());
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("記録できませんでした");
			throw e;
		}
	}

//	@Override
//	public int productCount() throws Exception {
//		int count=0;
//		String sql = "SELECT(*) COUNT FROM products";
//				try(Connection con = ds.getConnection()){
//					PreparedStatement stmt = con.prepareStatement(sql);
//					stmt.executeQuery();
//				}catch(Exception e) {
//					System.out.println("Count出来ませんでした");
//				}
//		return count;
//	}

}
