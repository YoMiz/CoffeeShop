package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class NewMemberDoneServlet
 */
@WebServlet("/newMemberDone")
public class NewMemberDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<CartItem> cartItemList = (List<CartItem>)session.getAttribute("cartItemList");
		
		String userName = request.getParameter("user_name");
		String userAddress = request.getParameter("user_address");
		String userMail = request.getParameter("E-mail");
		String userPass = request.getParameter("login_pass");
		String reconfirmUserPass = request.getParameter("reconfirmed_login_pass");
		Users user = new Users(null, userName, userPass, userAddress, userMail );
		AdminDao adminDao = DaoFactory.createAdminDao();
		try {
			adminDao.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			user = adminDao.findByUserNameAndUserPass(user);
		} catch (Exception e) {
			System.out.println("ユーザーが見つかりません");
		}
		session.setAttribute("user", user);
		session.setAttribute("user_name", userName);
		session.setAttribute("cartItemList", cartItemList);
		
		request.getRequestDispatcher("/WEB-INF/view/cart_login.jsp").forward(request, response);
		
	}
}
