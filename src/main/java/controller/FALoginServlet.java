package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.DaoFactory;
import domain.Users;

/**
 * Servlet implementation class FALoginServlet
 */
@WebServlet("/FAlogin")
public class FALoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("FAuser", null);

		request.getRequestDispatcher("/WEB-INF/view/FAlogin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDao adminDao = DaoFactory.createAdminDao();
		String FAuserName = request.getParameter("FAloginName");
		String FAuserPass = request.getParameter("FAloginPass");
		Users FAuser = new Users(null, FAuserName, FAuserPass, null, null);
		
		try {
			FAuser = adminDao.findByUserNameAndUserPass(FAuser);

			// 返ってきたFAuserが該当する場合
			if (FAuser != null && FAuser.getUserId() != null && FAuser.getUserId() == 1) {
				session.setAttribute("FAuser", true);
			} else {
				session.setAttribute("FAuser", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/FA/Users");
	}
}