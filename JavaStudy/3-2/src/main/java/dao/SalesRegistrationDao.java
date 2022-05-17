package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesRegistrationDao {// 接続用の情報をフィールドに定数として定義
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/2-1";
	private static String USER = "root";
	private static String PASS = "don4028";

	// データベース接続を行うメソッド
	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	// 一括登録をする為のメソッド
	public int insert(String product_name, String quantity) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;
		String product_code = "";
		String sales_date = "";
		String register_datetime = "";
		String update_datetime = "";
		int count = 0;

		// 登録されている名前からマスタテーブルの商品コードを出してくる
		String sql1 = "SELECT product_code FROM m_product WHERE product_name = ?";

		con = getConnection();
		PreparedStatement code = con.prepareStatement(sql1);
		code.setString(1, product_name);
		ResultSet rcode = code.executeQuery();
		while (rcode.next()) {
			product_code = rcode.getString("product_code");

		}

		// t_salesテーブルのsales_date, register_datetime, update_datetimeのNOW()を変数nowに格納
		String sql2 = "SELECT sales_date, register_datetime, update_datetime FROM t_sales WHERE (product_code = ?);";

		con = getConnection();
		PreparedStatement now = con.prepareStatement(sql2);
		now.setString(1, product_code);
		ResultSet nowtime = now.executeQuery();
		while (nowtime.next()) {
			register_datetime = nowtime.getString("register_datetime");
			update_datetime = nowtime.getString("update_datetime");
		}

		//売上を一括登録
		String sql = "INSERT INTO t_sales(sales_date, product_code, quantity, register_datetime, update_datetime) VALUES(NOW(), ?, ?, ?,? );";

		try {

			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, product_code);
			smt.setString(2, quantity);
			smt.setString(3, register_datetime);
			smt.setString(4, update_datetime);
//			ResultSet rs = smt.executeQuery();
//			//もし登録が出来たら1、出来なかったら0
//			if (rs.next()) {
//				count = 1;
//			}else {
//				count = 0;
//			}
//			
//			
//			
//			
//			

			// SQLをDBへ発行
			smt.executeUpdate();

		} catch (IllegalStateException e1) {
			throw new IllegalStateException(e1);

			// リソースの開放
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

		}return count;

	}

}
