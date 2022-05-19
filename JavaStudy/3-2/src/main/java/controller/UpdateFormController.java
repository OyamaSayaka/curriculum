package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.bean;
import dao.UpdateDao;

@WebServlet("/UpdateFormServlet")
public class UpdateFormController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		bean bean = new bean();
		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String error = "";
		String serialdatetime = "";

		// UpdateForm.jspから入力されたデータを受け取る

		String serialcode = request.getParameter("serialcode");

		String serialname = request.getParameter("serialname");
		String serialprice = request.getParameter("serialprice");

		try {

			// DAOオブジェクト宣言
			UpdateDao upd = new UpdateDao();

			serialdatetime = upd.select_datetime(serialcode);

		} catch (IllegalStateException e) {
			error = "エラーの為、一覧表示はできませんでした。";

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		} finally {
			request.setAttribute("serialcode", serialcode);
			request.setAttribute("serialname", serialname);
			request.setAttribute("serialprice", serialprice);
			request.setAttribute("serialdatetime", serialdatetime);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/UpdateForm.jsp").forward(request, response);
		}
	}
}
