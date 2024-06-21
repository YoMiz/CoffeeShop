package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.ProductBeansDao;
import dao.SalesRecordDao;
import domain.CartItem;
import domain.ProductBeans;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<ProductBeans> recommendList = new ArrayList<>();
		List<ProductBeans> beansList = new ArrayList<>();
		int listLen = 0;
		try {
			ProductBeansDao productBeansDao = DaoFactory.createProductBeansDao();

			for (int productType = 1; productType <= 2; productType++) {
				if (productType == 1) {
					recommendList = productBeansDao.findAll(productType);

				} else {
					beansList = productBeansDao.findAll(productType);
				}
			}
			//inventoryの数が少なければSoldOutと記述させる機構
			
			//商品全体の数を把握
			for(ProductBeans product: recommendList) {
				listLen += 1;
			}
			for(ProductBeans product: beansList) {
				listLen += 1;
			}
			
			//getInventory(CartItem)に疑似的にIdを入力する。
			SalesRecordDao inventoryListDao = DaoFactory.createSalesRecordDao();
			Map<Integer, Boolean> soldOutMap = new HashMap<>();

			for(int i = 1; i <= listLen; i++) {
				//このままfindAll(productId)を利用して、getInventory(CartItem=BeanList.getProductId())を作動させる。
			    CartItem product = new CartItem(null, i, null, null, null);
			    int restAmount = inventoryListDao.getInventory(product);

			    if(restAmount <= 100) {
			        soldOutMap.put(i, true);
			    } else {
			        soldOutMap.put(i, false);
			    }
			}
			//売り切れMapをJSPに反映
			request.setAttribute("soldOutMap", soldOutMap);
			request.setAttribute("beansList", beansList);
			request.setAttribute("recommendList", recommendList);
			request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
			
		} catch (

		Exception e) {
			throw new ServletException(e);
		}
	}
}