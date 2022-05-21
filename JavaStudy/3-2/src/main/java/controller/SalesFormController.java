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
		String addproduct = request.getParameter("add");
		String name = request.getParameter("name");
		String quantity = request.getParameter("quantity");
		String quantityError = "";

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		// 追加ボタン押下時に出力

		if (addproduct != null) {

			bean bean = new bean();

			// 配列宣言
			ArrayList<bean> list = new ArrayList<bean>();

			// チェック処理
			// 数量必須入力
			if (quantity.isEmpty()) {
				quantityError = "数量が空欄です。";
			} else {
				// 単価が数字のみの表示
				if (!quantity.matches("^[0-9]+$|-[0-9]+$")) {
					quantityError = "数字を入力してください。";
				} else {
					if (Integer.parseInt(quantity) < 1) {
						quantityError = "1以上で入力してください。";
					}
				}
			}

			// エラーがないとき
			if (quantityError == "") {

				bean.setName(request.getParameter("name"));
				bean.setQuantity(request.getParameter("quantity"));

				// listがnullじゃない場合
				if (session.getAttribute("list") != null) {
					// listの値をlist1に入れていく。
					ArrayList<bean> list1 = (ArrayList<bean>) session.getAttribute("list");
					// list1の要素数ループしている間listを追加していく
					for (bean bean1 : list1) {
						list.add(bean1);
					}
					for (bean bean1 : list) {
						// 名前が一緒だったら数量を足す
						if (bean1.getName().equals(bean.getName())) {
							int sum = Integer.parseInt(bean.getQuantity()) + Integer.parseInt(bean1.getQuantity());
							// 数量をセットする
							bean1.setQuantity(Integer.toString(sum));
			
							
//							list.set(sum, bean1);
						} else {
							// 名前が一致しなかったらリストに追加していく
							list.add(bean);
						}
					}

				} else {
					list.add(bean);

				}
				// セッションへのデータの登録
				session.setAttribute("list", list);
				request.getRequestDispatcher("SalesRegistration.jsp").forward(request, response);
				return;

			} else {
				request.setAttribute("quantityError", quantityError);
				request.getRequestDispatcher("SalesRegistration.jsp").forward(request, response);

			}

		}

}}
