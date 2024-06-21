package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {
	public static AdminDao createAdminDao() {
		return new AdminDaoImpl(getDataSource());
	}
	public static ProductBeansDao createProductBeansDao() {
		return new ProductBeansDaoImpl(getDataSource());	
	}
	public static SalesRecordDao createSalesRecordDao() {
		return new SalesRecordDaoImpl(getDataSource());
	}
	public static InventoryDao createInventoryDao() {
		return new InventoryDaoImpl(getDataSource());
	}
	public static DataSource getDataSource() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/coffee_shop");
		} catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					throw new RuntimeException(e1);
				}
			}
			throw new RuntimeException(e);
		}
		return ds;

	}

}