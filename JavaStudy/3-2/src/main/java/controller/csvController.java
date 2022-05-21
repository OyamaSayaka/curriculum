package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.bean;
import dao.csvDao;

@WebServlet("/csvController")
public class csvController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		String error = "";
		String error1 = "";

		String productCSV = request.getParameter("productCSV");
		String sales_date = request.getParameter("month");
		String monthlyCSV = request.getParameter("monthlyCSV");
		final String line_feed_code = "\r\n";
		final String DELIMITER_COMMA = "\",\"";
		StringBuilder StringBuilder = new StringBuilder();

		try {
			// 商品別売上集計CSVが押されたとき
			if (!productCSV.equals(null)) {

				// オブジェクトの生成
				ArrayList<bean> list = new ArrayList<bean>();

				//DAOオブジェクト宣言
				csvDao dao = new csvDao();

				list = dao.selectItem();

				// データベースにデータが入っていないとき
				if (list.isEmpty()) {
					error += "データベースに商品がありません";
					request.setAttribute("error", error);

				} else {
					if (!list.isEmpty()) {
						StringBuilder.append("商品コード");
						StringBuilder.append(",");
						StringBuilder.append("商品名");
						StringBuilder.append(",");
						StringBuilder.append("単価");
						StringBuilder.append(",");
						StringBuilder.append("数量");
						StringBuilder.append(",");
						StringBuilder.append("金額");
						StringBuilder.append(line_feed_code);
						for (bean bean : list) {
							StringBuilder.append("\"");
							StringBuilder.append(bean.getCode());
							StringBuilder.append(DELIMITER_COMMA);
							StringBuilder.append(bean.getName());
							StringBuilder.append(DELIMITER_COMMA);
							StringBuilder.append(bean.getPrice());
							StringBuilder.append(DELIMITER_COMMA);
							StringBuilder.append(bean.getQuantity());
							StringBuilder.append(DELIMITER_COMMA);
							StringBuilder.append(bean.getMoney());
							StringBuilder.append("\"");
							StringBuilder.append(line_feed_code);
						}
					}
					PrintWriter printwriter = new PrintWriter(productCSV);

					// CSV出力
					response.setHeader("Content-Disposition", "attachment; filename = productCSV ");
					response.setHeader("Content-Type", "text/csv; charset = UTF-8");
					response.setContentType("application/msword");

					printwriter = response.getWriter();
					printwriter.print(StringBuilder);
					printwriter.flush();
					printwriter.close();
				}
			}

		} catch (ClassNotFoundException | SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		// 指定年月商品別売上集計CSVが押されたとき
		if (!monthlyCSV.equals(null)) {

			// 年月が入力されていないとき
			if (sales_date.isEmpty()) {
				error1 += "年月を入力してください";
				request.setAttribute("error1", error1);

				// 年月が入力されているとき
			} else {

				// オブジェクトの生成
				ArrayList<bean> list1 = new ArrayList<bean>();

				//DAOオブジェクト宣言
				csvDao dao = new csvDao();

				try {
					list1 = dao.select_month(sales_date);

					// データベースにデータが入っていないとき
					if (list1.isEmpty()) {
						error1 += "データベースに商品がありません";
						request.setAttribute("error1", error1);

						// データベースにデータが入っているとき
					} else {
						if (!list1.isEmpty()) {
							StringBuilder.append("商品コード");
							StringBuilder.append(",");
							StringBuilder.append("商品名");
							StringBuilder.append(",");
							StringBuilder.append("単価");
							StringBuilder.append(",");
							StringBuilder.append("数量");
							StringBuilder.append(",");
							StringBuilder.append("金額");
							StringBuilder.append(line_feed_code);
							for (bean bean : list1) {
								StringBuilder.append("\"");
								StringBuilder.append(bean.getCode());
								StringBuilder.append(DELIMITER_COMMA);
								StringBuilder.append(bean.getName());
								StringBuilder.append(DELIMITER_COMMA);
								StringBuilder.append(bean.getPrice());
								StringBuilder.append(DELIMITER_COMMA);
								StringBuilder.append(bean.getQuantity());
								StringBuilder.append(DELIMITER_COMMA);
								StringBuilder.append(bean.getMoney());
								StringBuilder.append("\"");
								StringBuilder.append(line_feed_code);
							}
						}
						PrintWriter printwriter = new PrintWriter(monthlyCSV);

						// CSV出力
						response.setHeader("Content-Disposition", "attachment; filename = monthlyCSV ");
						response.setHeader("Content-Type", "text/csv; charset = UTF-8");
						response.setContentType("application/msword");

						printwriter = response.getWriter();
						printwriter.print(StringBuilder);
						printwriter.flush();
						printwriter.close();
					}

				} catch (ClassNotFoundException | SQLException | NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		request.getRequestDispatcher("WEB-INF/view/ch13/CSV.jsp").forward(request, response);
	}
}