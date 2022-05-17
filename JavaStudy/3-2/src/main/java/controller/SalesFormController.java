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
@WebServlet("/SalesForm")
public class SalesFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

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
			return;

		}
	}



//			// チェック処理
//
//			// 商品名必須入力
//			if (name.isEmpty()) {
//				productNameError = "商品名が空欄です。";
//			}
//
//			// 単価必須入力
//			if (price.isEmpty()) {
//				priceError = "単価が空欄です。";
//			} else {
//				// 単価が数字のみの表示
//				if (!price.matches("^[0-9]+$|-[0-9]+$")) {
//					priceError = "数字を入力してください。";
//				} else {
//					if (Integer.parseInt(price) < 1) {
//						priceError = "1以上で入力してください。";
//					}
//				}
//			}
//
//			// エラーがないとき
//			if (productNameError == "" && priceError == "") {
//				// 1件登録メソッドを呼び出し
//				try {
//					dao.insert(name, price);
//
//				} catch (ClassNotFoundException | SQLException e1) {
//					// スタックトーレスを出力する
//					e1.printStackTrace();
//				}
//
//				request.getRequestDispatcher("InsertComplete.jsp").forward(request, response);
//
//			} else {
//				request.setAttribute("productNameError", productNameError);
//				request.setAttribute("priceError", priceError);
//				request.getRequestDispatcher("InsertForm.jsp").forward(request, response);
//
//			}


}
