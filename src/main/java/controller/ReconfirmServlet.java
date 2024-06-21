package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.DaoFactory;
import domain.CartItem;
import domain.Users;

/**
 * Servlet implementation class ReconfirmServlet
 */
@WebServlet("/reconfirm")
public class ReconfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDao adminDao = DaoFactory.createAdminDao();
		//前のページからユーザーの情報を受け取る
		Users user = (Users) session.getAttribute("user");
		
		//もし、改めて入力された情報とデータベースの情報に相違がある場合
		if (user != null) {
			String new_user_name = request.getParameter("new_user_name");
			String new_user_address = request.getParameter("new_user_address");
			String new_user_mail = request.getParameter("new_user_mail");
			//新しいユーザー情報をジェネリクスに登録
			user.setUserName(new_user_name);
			user.setUserAddress(new_user_address);
			user.setUserMail(new_user_mail);
			
			//データベースの情報を更新
			try {
				adminDao.update(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//更新されたデータベースを改めて取得し、変数に代入する。
		try {
			user = adminDao.findById(user.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.setAttribute("user", user);
		String strTotalPrice = request.getParameter("total");
		Integer totalPrice = Integer.parseInt(strTotalPrice);

		ArrayList<CartItem> ReconfirmCartItemList = new ArrayList<CartItem>();
		ReconfirmCartItemList = (ArrayList<CartItem>) session.getAttribute("cartItemList");

		request.setAttribute("totalPrice", totalPrice);
		session.setAttribute("ReconfirmCartItemList", ReconfirmCartItemList);
		session.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("/WEB-INF/view/reconfirm.jsp").forward(request, response);
	}
}
