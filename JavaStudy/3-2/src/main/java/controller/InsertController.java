package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InsertDao;

@WebServlet("/InsertServlet")
public class InsertController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productNameError = "";
		String priceError = "";		

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String price = request.getParameter("price");

		// DAOオブジェクト宣言
		InsertDao dao = new InsertDao();
		
		// チェック処理

		// 商品名必須入力
		if (name.isEmpty()) {
			productNameError = "商品名が空欄です。";
		}
		
		// 単価必須入力
		if (price.isEmpty()) {
			priceError = "単価が空欄です。";
		} else {
			// 単価が数字のみの表示
			if (!price.matches("^[0-9]+$|-[0-9]+$")) {
				priceError = "数字を入力してください。";
			} else {
				if (Integer.parseInt(price) < 1) {
					priceError = "1以上で入力してください。";
				}
			}
		}
		
		// エラーがないとき
		if (productNameError == "" && priceError == "") {
			// 1件登録メソッドを呼び出し
			try {
				dao.insert(name, price);
	
			} catch (ClassNotFoundException | SQLException e1) {
				// スタックトーレスを出力する
				e1.printStackTrace();
			}
	
			request.getRequestDispatcher("InsertComplete.jsp").forward(request, response);
			
		} else {
			request.setAttribute("productNameError", productNameError);
			request.setAttribute("priceError", priceError);
			request.getRequestDispatcher("InsertForm.jsp").forward(request, response);
	
		}
	}
}
