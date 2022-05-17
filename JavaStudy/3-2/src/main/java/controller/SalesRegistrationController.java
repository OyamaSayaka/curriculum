package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.bean;
import dao.SalesRegistrationDao;

@WebServlet("/SalesRegistrationController")
public class SalesRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		bean bean = new bean();

		String error = "";
		int count = 0;

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		// JSPから送られた登録ボタンの値を受け取る
		String registration = request.getParameter("registration");
		String name = request.getParameter("name");
		String quantity = request.getParameter("quantity");

		// 登録ボタンが押下されたとき、一括登録をする
		if (registration != null) {

			// セッションオブジェクトの生成
			HttpSession session = request.getSession();
			// 配列宣言
			ArrayList<bean> reglist = new ArrayList<bean>();

			// 一括登録された商品と数量をリストに格納

			bean.setName(request.getParameter("name"));
			bean.setQuantity(request.getParameter("quantity"));
			reglist.add(bean);

			// DAOオブジェクト宣言
			SalesRegistrationDao dao = new SalesRegistrationDao();

			try {
				dao.insert(bean.getName(), bean.getQuantity());

				if(count != 0) {
				session.setAttribute("registration", registration);
				request.getRequestDispatcher("SalesRegistrationComplrte.jsp").forward(request, response);

				}else {
					error ="本日の売上登録は既に完了してます。";
					
					session.setAttribute("error", error);
					request.getRequestDispatcher("SalesRegistration.jsp").forward(request, response);
					
				}

			} catch (ClassNotFoundException | SQLException e1) {
				// スタックトーレスを出力する
				e1.printStackTrace();
			}

		}
	}
}
