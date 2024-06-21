package dao;

import java.util.List;

import domain.ProductBeans;

public interface ProductBeansDao {
	public List<ProductBeans> findAll(int productType) throws Exception;
	public ProductBeans findItemInfoById (int productId) throws Exception;
	public void addItem(ProductBeans product) throws Exception;
//	public int productCount() throws Exception;
}
