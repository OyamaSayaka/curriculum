package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.UpdateDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String productNameError = "";
		String priceError = "";
		String error = "";

		// UpdateForm.jspから入力されたデータを受け取る
		String serialcode = request.getParameter("serialcode");
		String serialid = request.getParameter("serialid");
		String name = request.getParameter("name");
		String serialprice = request.getParameter("serialprice");
		String price = request.getParameter("price");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String count = request.getParameter("count");

		// DAOオブジェクト宣言
		UpdateDao dao = new UpdateDao();

		// 検索時のupdate_datetimeを取ってきて、countに格納

//	try {
//		int count1 = dao.select_update_datetime(serialcode);
//	} catch (ClassNotFoundException | SQLException e1) {
//		
//		e1.printStackTrace();
//
//	}
//	request.setAttribute("count",count);

		if (update != null) {

			// 売上が発生しているか確認
			try {
				int sales = dao.checkSales(Integer.parseInt(serialid));

			} catch (ClassNotFoundException | SQLException e1) {
				// スタックトーレスを出力する
				e1.printStackTrace();
			}

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
					if (Integer.parseInt(price) <= 1) {
						priceError = "1以上で入力してください。";
					}else {
						if (Integer.parseInt(price) == Integer.parseInt(serialprice)) {

							priceError = "変更前と同じ金額です。";

						}
					}
				}

				

			}
		}

		// 先に誰かが更新したとき
		try

		{
			int count1 = dao.select_update_datetime(serialid);
			if (Integer.parseInt(count) != count1) {
				error = "商品を変更できませんでした。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("WEB-INF/view/UpdateForm.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

		// エラーがないとき
		if (productNameError == "" && priceError == "") {
			// 1件登録メソッドを呼び出し
			try {
				int count2 = dao.checkSales(Integer.parseInt(serialid));

				if (count2 == 0) {
					int count3 = UpdateDao.update(name, price, serialid);
				}

			} catch (ClassNotFoundException | SQLException e1) {
				// スタックトーレスを出力する
				e1.printStackTrace();
			}
			request.setAttribute("update", update);
			request.getRequestDispatcher("UpdateComplete.jsp").forward(request, response);

		} else {
			request.setAttribute("serialcode", serialcode);
			request.setAttribute("serialid", serialid);
			request.setAttribute("productNameError", productNameError);
			request.setAttribute("priceError", priceError);
			request.setAttribute("error", error);
			request.getRequestDispatcher("UpdateForm.jsp").forward(request, response);

		}

		try

		{
			// 削除ボタンを押した後の処理
			if (delete != null) {
				dao.delete(serialid, name, price);
			}

		} catch (ClassNotFoundException | SQLException e1) {
			// スタックトーレスを出力する
			e1.printStackTrace();
		}
		request.setAttribute("delete", delete);
		request.getRequestDispatcher("UpdateComplete.jsp").forward(request, response);
	}

}

//		// 削除ボタンを押したとき
//		if (delete != null) {
//
//			//DAOオブジェクト宣言
//			UpdateDao4 objUpdateDao4 = new UpdateDao4();
//
//			try {
//				int count2 = objUpdateDao4.select1_update_datetime(product_code);
//
//				// 先に誰かが更新したとき
//				if (Integer.parseInt(count1) != count2) {
//					error = "商品を変更できませんでした。";
//				}
//			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
//			}
//
//			// エラーがないとき
//			if (error == "" && error1 == "") {
//				// 削除メソッドを呼び出し
//				try {
//					int count = objUpdateDao4.delete(product_code, id, price);
//
//					//削除件数をupdateReceipt2.jspに登録
//					request.setAttribute("count", count);
//
//				} catch (ClassNotFoundException | SQLException e2) {
//					e2.printStackTrace();
//				}
//				request.getRequestDispatcher("WEB-INF/view/ch13/updateReceipt2.jsp").forward(request, response);
//			} else {
//				request.setAttribute("error", error);
//				request.setAttribute("product_code", product_code);
//				request.getRequestDispatcher("WEB-INF/view/ch13/updateForm.jsp").forward(request, response);
//			}
//		}
//	}
//}
