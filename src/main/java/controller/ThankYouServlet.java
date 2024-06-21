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
import domain.CartItem;
import domain.SalesRecord;
import domain.Users;

/**
 * Servlet implementation class ThankYouServlet
 */
@WebServlet("/thankyou")
public class ThankYouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		Integer totalPrice =(Integer) session.getAttribute("totalPrice");
		ArrayList<CartItem> ReconfirmCartItemList = (ArrayList<CartItem>)session.getAttribute("ReconfirmCartItemList");
		
		//DBに保存していく		
		//estimates, est_details, inventory
		
		
		//inventoryにUPDATE product_idを軸に inventory_quantityをマイナス, inventory_updatedを更新。
		SalesRecordDao salesRecordDao = DaoFactory.createSalesRecordDao();
		Integer userId = user.getUserId();
		SalesRecord salesRec = new SalesRecord(null, userId, totalPrice, null);
		int estId = 0;
		try {
			//estimatesにINSERT user_id, est_created, total_price, est_created。
			//出力させたest_idを代入させる
			estId = salesRecordDao.insertAndGetId(salesRec);
			
			for(CartItem cartItem: ReconfirmCartItemList) {				
				//est_detailsにINSERT est_id, product_id, sale_quantity, sale_price, est_det_createdを記載。
				salesRecordDao.insertDetails(estId, cartItem);
				//inventoryにアクセスして在庫状況を確認させる
				int quantity = salesRecordDao.getInventory(cartItem);
				//inventoryにアクセスして、在庫の数を確認
				int final_quantity = quantity - cartItem.getItemAmount();
				//inventoryにアクセスして、在庫の数を更新
				salesRecordDao.updateInventory(cartItem, final_quantity);
			}	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("estimate_num", estId);
		request.setAttribute("totalPrice", session.getAttribute("totalPrice"));
		
		
		session.invalidate();
		request.getRequestDispatcher("/WEB-INF/view/thx.jsp").forward(request, response);
	}

}
