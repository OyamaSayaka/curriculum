package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import bean.bean;

public class csvDao {
	//接続用の情報をフィールドに定数として定義
	private static String RDB_DRIVE = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/2-1";
	private static String USER = "root";
	private static String PASS = "EQLAa0_q";

	//データベース接続を行うメソッド
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e);
		}
	}

	// 商品ごとの売上集計(売上のない商品も含める)メソッド
	public ArrayList<bean> selectItem() throws SQLException, ClassNotFoundException {
		//変数宣言
		Connection con = null;
		PreparedStatement smt = null;

		//return用オブジェクトの生成
		ArrayList<bean> list = new ArrayList<bean>();

		String sql = "SELECT m_product.product_code, m_product.product_name, m_product.price, SUM(t_sales.quantity) FROM m_product LEFT JOIN t_sales"
				+ " ON m_product.product_code = t_sales.product_code GROUP BY m_product.product_code;";

		try {
			con = getConnection();
			smt = con.prepareStatement(sql);
			ResultSet resultSet = smt.executeQuery();
			while (resultSet.next()) {
				bean bean = new bean();
				bean.setCode(String.format("%03d", resultSet.getInt("m_product.product_code")));
				bean.setName(resultSet.getString("m_product.product_name"));
				bean.setPrice(resultSet.getString("m_product.price"));
				bean.setQuantity(resultSet.getString("SUM(t_sales.quantity)"));
				bean.setMoney(Integer.parseInt(bean.getPrice()) * Integer.parseInt(bean.getQuantity()));
				list.add(bean);
			}

		} catch (IllegalStateException e) {
			throw new IllegalStateException(e);

			//リソースの開放
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;
	}

	// 指定した年月に売上のある商品の売上集計(売上のない商品は含めない)メソッド
	public ArrayList<bean> select_month(String sales_date) throws SQLException, ClassNotFoundException {
		//変数宣言
		Connection con = null;
		PreparedStatement smt = null;

		//return用オブジェクトの生成
		ArrayList<bean> list1 = new ArrayList<bean>();

		// 引数から受け取ったsales_dateをsplitで分割し、sales_date1に格納する
		String[] sales_date1 = sales_date.split("-");

		// それぞれ分割した文字列を格納する
		String sales_date_year = sales_date1[0];
		String sales_date_month = sales_date1[1];

		// 月初日を作成する
		String one = String.valueOf(01);
		String month_one = String.join("-", sales_date_year, sales_date_month, one);

		// カレンダー型に格納し、月末日を取得する
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sales_date_year));
		cal.set(Calendar.MONTH, Integer.parseInt(sales_date_month));
		int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);

		// 月末日を作成する
		String lastday = String.valueOf(lastDayOfMonth);
		String month_last = String.join("-", sales_date_year, sales_date_month, lastday);

		String sql = "SELECT m_product.product_code, m_product.product_name, m_product.price, SUM(t_sales.quantity) "
				+ "FROM 2-1.m_product INNER JOIN 2-1.t_sales ON m_product.product_code = t_sales.product_code "
				+ "WHERE t_sales.sales_date BETWEEN ? AND ? GROUP BY m_product.product_code;";

		try {
			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, month_one);
			smt.setString(2, month_last);
			ResultSet resultSet = smt.executeQuery();
			while (resultSet.next()) {
				bean bean = new bean();
				bean.setCode(String.format("%03d", resultSet.getInt("m_product.product_code")));
				bean.setName(resultSet.getString("m_product.product_name"));
				bean.setPrice(resultSet.getString("m_product.price"));
				bean.setQuantity(resultSet.getString("SUM(t_sales.quantity)"));
				bean.setMoney(Integer.parseInt(bean.getPrice()) * Integer.parseInt(bean.getQuantity()));
				list1.add(bean);
			}

		} catch (IllegalStateException e) {
			throw new IllegalStateException(e);

			//リソースの開放
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list1;
	}
}
