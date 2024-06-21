package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.InventoryDao;
import dao.ProductBeansDao;
import domain.Inventory;
import domain.ProductBeans;

/**
 * Servlet implementation class FAstock
 */
@WebServlet("/FA/Stock")
public class FAstock extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InventoryDao inventoryDao = DaoFactory.createInventoryDao();
		ArrayList<Inventory> inventoryList = null;
		try {
			inventoryList = (ArrayList<Inventory>) inventoryDao.findAllInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("inventoryList", inventoryList);
		request.getRequestDispatcher("/WEB-INF/view/FA/stock.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductBeansDao productBeansDao = DaoFactory.createProductBeansDao();
		
		String strItemType = request.getParameter("itemType");
		String strItemCost = request.getParameter("itemCost");
		String strItemPrice = request.getParameter("itemPrice");
		String strQuantity = request.getParameter("quantity");
		
		Integer itemType = Integer.parseInt(strItemType);
		String itemName = request.getParameter("itemName");
		Integer itemQuantity = Integer.parseInt(strQuantity);
		Integer itemCost = Integer.parseInt(strItemCost);
		Integer itemPrice = Integer.parseInt(strItemPrice);
		String companyName = request.getParameter("companyName");
		String contact = request.getParameter("contact");
		String description = request.getParameter("description");
		String fileName = request.getParameter("fileName");
		ProductBeans newProduct = new ProductBeans();
		
//		int count = 0;
		newProduct.setProductType(itemType);
		newProduct.setProductName(itemName);
		newProduct.setProductCost(itemCost);
		newProduct.setProductPrice(itemPrice);
		newProduct.setProductCompany(companyName);
		newProduct.setProductContact(contact);
		newProduct.setProductDescription(description);
		newProduct.setProductPict(fileName);
		try {
			productBeansDao.addItem(newProduct);
//			count = productBeansDao.productCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Inventory inventory = new Inventory(itemQuantity);
//		newProduct.setProductId(count);
		
		try {
		    InventoryDao inventoryDao = DaoFactory.createInventoryDao();
		    inventoryDao.addToInventory(inventory, newProduct);
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
		doGet(request,response);
	}

}
