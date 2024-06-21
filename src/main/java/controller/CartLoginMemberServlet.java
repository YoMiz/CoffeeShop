package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.DaoFactory;
import dao.ProductBeansDao;
import domain.CartItem;
import domain.ProductBeans;
import domain.Users;

/**
 * Servlet implementation class CartLoginMemberServlet
 */
@WebServlet("/cart_login_member")
public class CartLoginMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<Integer, Integer> itemMap;
	AdminDao adminDao;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName;
		String userPass;
		// ログイン記述
		Users user = (Users) session.getAttribute("user");

		session.setAttribute("error", null);
		try {
			// 記入されたデータの取得(通常ログイン）
			if (session.getAttribute("user_name") == null) {
				userName = request.getParameter("user_name");
				userPass = request.getParameter("user_pass");
			} else {
				//新規登録からのログイン
				userName = user.getUserName();
				userPass = user.getUserPass();
			}
			
			user = new Users(null, userName, userPass, null, null);
			adminDao = DaoFactory.createAdminDao();

			// ユーザーの情報と照らし合わせる
			user = adminDao.findByUserNameAndUserPass(user);
			// 返ってきたUserが該当する場合
			if (user != null) {

				// データベースの情報を出力させる。
				session.setAttribute("user_name", user.getUserName());
				session.setAttribute("user_address", user.getUserAddress());
				session.setAttribute("user_mail", user.getUserMail());
				session.setAttribute("error", null);
			} else {
				// ログインエラーの出力
				session.setAttribute("user_name", null);
				session.setAttribute("error", true); // エラー情報をセッションに保存
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

		// 再度更新されたJSPからリストを作成する
		// カートアイテムリストを取得
		List<CartItem> cartItemList = (List<CartItem>) session.getAttribute("cartItemList");

		// 各カートアイテムの数量を取得
		for (CartItem cartItem : cartItemList) {
			String stritemId = String.valueOf(cartItem.getItemId());
			String amount = request.getParameter(stritemId);
			if (request.getParameter(stritemId) != null) {
				cartItem.setItemAmount(Integer.parseInt(amount));
			}
		}

		// カート情報を配列に変換
		ArrayList<CartItem> ReconfirmCartItemList = new ArrayList<CartItem>();
		// カートの情報を確認用カートに入れなおす。
		for (CartItem cartItem : cartItemList) {
			int productId = cartItem.getItemId();
			try {
				ProductBeansDao productBeansDao = DaoFactory.createProductBeansDao();
				ProductBeans selectedItem = productBeansDao.findItemInfoById(productId);
				Integer cartItemType = selectedItem.getProductType();
				Integer cartItemId = selectedItem.getProductId();
				String cartItemName = selectedItem.getProductName();
				Integer cartItemPrice = selectedItem.getProductPrice();
				Integer cartItemAmount = cartItem.getItemAmount();
				cartItem = new CartItem(cartItemType, cartItemId, cartItemName, cartItemPrice, cartItemAmount);
				ReconfirmCartItemList.add(cartItem);

			} catch (Exception e) {

			}
		}
		cartItemList = ReconfirmCartItemList;
		// 更新されたカートの情報をセッションに記述。
		session.setAttribute("user", user);
		session.setAttribute("cartItemList", cartItemList);
		session.setAttribute("ReconfirmCartItemList", ReconfirmCartItemList);
		request.getRequestDispatcher("/WEB-INF/view/cart_login.jsp").forward(request, response);

	}
}
