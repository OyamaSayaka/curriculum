package controller;

import java.io.IOException;
import java.sql.SQLException;

import bean.bean;
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

		bean bean = new bean();

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String error = "";
		String productNameError = "";
		String priceError = "";
		String deleteError = "";
		String editTime = "";
		

		// UpdateForm.jspから入力されたデータを受け取る
		String serialcode = request.getParameter("serialcode");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String serialtime = request.getParameter("serialtime");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
//		String sdt = request.getParameter("selected_datetime");		

//		bean.setCode(Integer.parseInt(serialcode));

		// DAOオブジェクト宣言
		UpdateDao dao = new UpdateDao();

		// 編集ボタン押下時の更新時間を取得
		try {
			editTime = dao.select_datetime(serialcode);
			System.out.println("編集押下時時刻" + editTime);
			System.out.println(serialtime);
		} catch (ClassNotFoundException | SQLException e1) {
			// スタックトーレスを出力する
			e1.printStackTrace();
		}

//		// バリデーション処理
//		// チェック処理
//		// 商品名必須入力
//		if (name.isEmpty()) {
//			productNameError = "商品名が空欄です。";
//		}
//
//		// 単価必須入力
//		if (price.isEmpty()) {
//			priceError = "単価が空欄です。";
//		} else {
//			// 単価が数字のみの表示
//			if (!price.matches("^[0-9]+$|-[0-9]+$")) {
//				priceError = "数字を入力してください。";
//			} else {
//				if (Integer.parseInt(price) <= 1) {
//					priceError = "1以上で入力してください。";
//				}
//			}
//			
//		}

		if (update != null) {
			
			
			try {
				if (serialtime == editTime) {
					// 売上が発生しているか確認 0の場合更新、1の場合更新できない
					int sales = dao.checkSales(Integer.parseInt(serialcode));
					if (sales == 0) {

						// 編集ボタン押下時の登録更新日時と変更前の登録更新日時が一緒か確認
						// 更新前の登録日時を変数dateTimeに格納
						String dateTime = dao.select_datetime(serialcode);
						System.out.println("登録前時刻" + dateTime);

						// 検索表示時点での登録更新日時と更新前の登録更新日時が一緒か確認
						// 変更できます。変更させるメソッド
						dao.update(name, price, serialcode, dateTime);
						System.out.println("ここまで来てます");
						request.getRequestDispatcher("UpdateComplete.jsp").forward(request, response);

					} else {
						error = "計上済の商品です。";

						request.setAttribute("formcodebean", bean);
						request.setAttribute("error", error);
						request.getRequestDispatcher("UpdateForm.jsp").forward(request, response);
					}
				} else {
					error = "履歴が更新されています。";
					request.setAttribute("productNameError", productNameError);
					request.setAttribute("priceError", priceError);
					request.setAttribute("error", error);
					request.getRequestDispatcher("UpdateForm.jsp").forward(request, response);
				}

			} catch (ClassNotFoundException | SQLException e1) {
				// スタックトーレスを出力する
				e1.printStackTrace();

			}

		}

		// 削除ボタンを押した後の処理
		else if (delete != null) {

			try {

				// 削除ボタンを押下時の更新日時を取得
				String datetTime = dao.select_datetime(serialcode);

				if (editTime == datetTime) {
					dao.delete(serialcode, name, price, datetTime);

					request.getRequestDispatcher("UpdateComplete.jsp").forward(request, response);

				} else {
					deleteError = "削除できませんでした。もう一度やり直してください。";
					request.setAttribute("deleteError", deleteError);
					request.getRequestDispatcher("UpdateForm.jsp").forward(request, response);

				}

			} catch (ClassNotFoundException | SQLException e1) {
				// スタックトーレスを出力する
				e1.printStackTrace();

			}

		}

	}
}
