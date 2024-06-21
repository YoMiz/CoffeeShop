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
import domain.Users;

/**
 * Servlet implementation class FAUsersServlet
 */
@WebServlet("/FA/Users")
public class FAUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDao adminDao = DaoFactory.createAdminDao();

		ArrayList<Users> userList = null;
		try {
			userList = (ArrayList<Users>) adminDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int listLen = userList.size();
		request.setAttribute("listLen", listLen);
		session.setAttribute("userList", userList);
		request.getRequestDispatcher("/WEB-INF/view/FA/users.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strUserId = request.getParameter("SelectUserId");
		AdminDao adminDao = DaoFactory.createAdminDao();

		// 入力されたIDがNULLでない場合、
		if (strUserId != null) {
			// ユーザーIDから該当するユーザーの情報を割り出す
			int userId = Integer.parseInt(strUserId);
			String userName = request.getParameter("SelectUserName");
			String userPass = request.getParameter("SelectUserPass");
			String userAddress = request.getParameter("SelectUserAddress");
			String userMail = request.getParameter("SelectUserMail");
			Users user = new Users(userId, userName, userPass, userAddress, userMail, null, null, null);

			String userDeleted = request.getParameter("SelectUserDeleted");
			if (userDeleted != null)
				// 削除の項目にチェックがついていた場合
				try {
					// ユーザーの削除をする。
					System.out.println(user);
					adminDao.FAUserDelete(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			else {
				// ユーザーの情報を更新する。
				try {
					adminDao.FAUserUpdate(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/FA/Users");
	}
}
