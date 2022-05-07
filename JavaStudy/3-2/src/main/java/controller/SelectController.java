package controller;

import java.io.IOException;
import java.util.ArrayList;

import bean.bean;
import dao.SelectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/SerchServlet")
public class SelectController extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// コンテンツタイプの設定
//		response.setContentType("text/html; charset = UTF8");
	
		
		
		String error = "";

		try {
			// 配列宣言
			ArrayList<bean> list = new ArrayList<bean>();

			// オブジェクト宣言
			SelectDao objDao = new SelectDao();

			// 全検索メソッドを呼び出し
			list = objDao.selectAll();

			// 検索結果を持ってmenu.jspにフォワード
			request.setAttribute("list", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/SelectForm.jsp").forward(request, response);
		}

	}

}
