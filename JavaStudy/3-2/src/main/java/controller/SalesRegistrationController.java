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
		String product_code;
		String insert;

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		// JSPから送られた登録ボタンの値を受け取る
		String registration = request.getParameter("registration");

		// 登録ボタンが押下されたとき、一括登録をする
		if (registration != null) {

			// セッションオブジェクトの生成
			HttpSession session = request.getSession();

			// 配列宣言
			// 一括登録された商品と数量をリストに格納

			ArrayList<bean> reglist = (ArrayList<bean>) session.getAttribute("list");

			for (int i = 0; i < reglist.size(); i++) {
				System.out.println(reglist.get(i).getName());
				System.out.println(reglist.get(i).getQuantity());
			}

			// DAOオブジェクト宣言
			SalesRegistrationDao dao = new SalesRegistrationDao();

			try {
				if (reglist != null) {

					// 取得した商品名から商品コードを抽出
					for (int i = 0; i < reglist.size(); i++) {
						product_code = dao.serchCode(reglist.get(i).getName());

						System.out.println(product_code);

						if (product_code != null) {

							insert = dao.insert(product_code, reglist.get(i).getQuantity());
							System.out.println(product_code);

						}

					}
					session.setAttribute("registration", registration);
					request.getRequestDispatcher("SalesRegistrationComplrte.jsp").forward(request, response);
					return;
				} else {
					error = "データの登録が出来ません";

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
