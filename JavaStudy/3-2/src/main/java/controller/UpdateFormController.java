package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateFormServlet")
public class UpdateFormController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String error = "";
		
		// UpdateForm.jspから入力されたデータを受け取る
		
		String serialcode = request.getParameter("serialcode");
		String serialid = request.getParameter("serialid");
		String serialname = request.getParameter("serialname");
		String serialprice = request.getParameter("serialprice");

		try {

			
			request.setAttribute("serialcode", serialcode);
			request.setAttribute("serialid", serialid);
			request.setAttribute("serialname", serialname);
			request.setAttribute("serialprice", serialprice);

		} catch (IllegalStateException e) {
			error = "エラーの為、一覧表示はできませんでした。";

		} finally {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/UpdateForm.jsp").forward(request, response);
		}
	}
}

