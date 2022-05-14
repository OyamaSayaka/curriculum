package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import bean.bean;
import dao.UpdateDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateFormServlet")
public class UpdateFormController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		bean bean =new bean();
		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();

		String error = "";
		
		
		// UpdateForm.jspから入力されたデータを受け取る
		
		String serialcode = request.getParameter("serialcode");
		
		String serialname = request.getParameter("serialname");
		String serialprice = request.getParameter("serialprice");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		try {

			// DAOオブジェクト宣言
			UpdateDao upd = new UpdateDao();
			
			String datetime = upd.select_datetime(serialcode);
			System.out.println(datetime);		
			bean.setUpdateTime(datetime);
			bean.setCode(Integer.parseInt(serialcode));
			bean.setName(serialname);
			bean.setPrice(Integer.parseInt(serialprice));
		
		
			request.setAttribute("editform", bean);

		} catch (IllegalStateException e) {
			error = "エラーの為、一覧表示はできませんでした。";

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		}finally {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/UpdateForm.jsp").forward(request, response);
		}
	}
}

