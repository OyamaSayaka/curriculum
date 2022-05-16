package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.bean;

/**
 * Servlet implementation class SalesRegistration
 */
@WebServlet("/SalesRegistration")
public class SalesRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String error = "";

		// SalesRegistrationから値を受け取る
//		String productName = request.getParameter("productName");
//		String quantity = request.getParameter("quantity");
		String addproduct = request.getParameter("add");

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		// 追加ボタン押下時に出力

		if (addproduct != null) {

			bean bean = new bean();

//				bean.setName(getString("productName"));
//				bean.setCode(getString("quantity"));
			// 配列宣言
			ArrayList<bean> list = new ArrayList<bean>();

			//
			bean.setName(request.getParameter("productName"));
			bean.setQuantity(request.getParameter("quantity"));
			list.add(bean);
			
			// セッションへのデータの登録
			session.setAttribute("list", list);
			request.getRequestDispatcher("SalesRegistration.jsp").forward(request, response);

			
		} else {
			error = "空欄です。入力してください";
			session.setAttribute("error", error);
			request.getRequestDispatcher("SalesRegistration.jsp").forward(request, response);
		}

	}

}
