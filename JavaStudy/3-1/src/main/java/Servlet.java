
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/serchServlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// コンテンツタイプの設定
		response.setContentType("text/html; charset = UTF8");

		// 画面出力
		PrintWriter out = response.getWriter();

		

		// フォワード先の指定

		RequestDispatcher dispatcher = request.getRequestDispatcher("/3-1/forward.jsp");

		// フォワードの実行

		dispatcher.forward(request, response);
		
		// パラメーター取得
				String name = request.getParameter("name");

				out.println("検索した本は" + name + "さんですね");
		
		

		System.out.println("サーブレットの終了");

	}

}
