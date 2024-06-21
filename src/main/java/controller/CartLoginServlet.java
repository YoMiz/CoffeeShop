package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.ProductBeansDao;
import domain.CartItem;
import domain.ProductBeans;

/**
 * Servlet implementation class CartLoginServlet
 */
@WebServlet("/cart_login")
public class CartLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// ログインエラー
		request.setAttribute("error", null);

		Map<Integer, Integer> itemMap = new HashMap<>();

		String[] itemList;
		String[] productType = { "recommend", "beans" };
		// recommend, beansそれぞれの商品IDを取得してくる。
		for (int i = 0; i <= 1; i++) {
			// JSP上の"item"の目印をitemListに代入
			itemList = request.getParameterValues(productType[i]);
			if (itemList != null) {
				for (String strItems : itemList) {
					String[] strItem = strItems.split("-");
					itemMap.put(Integer.parseInt(strItem[0]), Integer.parseInt(strItem[1]));
				}
			}
		}

		ArrayList<CartItem> cartItemList = new ArrayList<CartItem>();
		// JSPから引き出した情報を基にデータベースから情報を抽出
		for (Entry<Integer, Integer> item : itemMap.entrySet()) {
			int productId = item.getKey();
			try {
				ProductBeansDao productBeansDao = DaoFactory.createProductBeansDao();
				ProductBeans selectedItem = productBeansDao.findItemInfoById(productId);

				Integer cartItemType = selectedItem.getProductType();
				Integer cartItemId = selectedItem.getProductId();
				String cartItemName = selectedItem.getProductName();
				Integer cartItemPrice = selectedItem.getProductPrice();
				Integer cartItemAmount = item.getValue();
				CartItem cartItem = new CartItem(cartItemType, cartItemId, cartItemName, cartItemPrice, cartItemAmount);
				cartItemList.add(cartItem);

			} catch (Exception e) {

			}
		}
		if (cartItemList.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/main");
		} else {

			session.setAttribute("cartItemList", cartItemList);
			session.setAttribute("error", null);
			request.getRequestDispatcher("/WEB-INF/view/cart_login.jsp").forward(request, response);
		}
	}
}