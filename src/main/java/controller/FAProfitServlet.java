package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.SalesRecordDao;
import domain.Profit;

/**
 * Servlet implementation class FAProfitServlet
 */
@WebServlet("/FA/Profit")
public class FAProfitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SalesRecordDao salesRecordDao = DaoFactory.createSalesRecordDao();
		ArrayList<Profit> salesRecordList = null;
		try {
			salesRecordList = (ArrayList<Profit>)salesRecordDao.showAllEstimates();
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("profitList", salesRecordList);
		request.getRequestDispatcher("/WEB-INF/view/FA/profit.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
