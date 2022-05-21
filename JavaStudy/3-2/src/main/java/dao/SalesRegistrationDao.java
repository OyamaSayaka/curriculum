package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import bean.bean;

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

	// 登録されている名前からマスタテーブルの商品コードを抽出するメソッド
	public String serchCode(String name) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;
		String code = null;
		String p_name = null;
//		ArrayList<bean> codelist = new ArrayList<bean>();
		String sql1 = "SELECT product_code FROM m_product WHERE product_name = ?";
		// return用オブジェクトの生成

		try {
			bean bean = new bean();

			con = getConnection();
			smt = con.prepareStatement(sql1);
			smt.setString(1, name);
			ResultSet pcode = smt.executeQuery();
//			bean bean = new bean();
			while (pcode.next()) {

				bean.setCode(pcode.getString("product_code"));
				code = bean.getCode();

			}

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

		}
		return code;

	}

	// 一括登録をする為のメソッド
	public String insert(String code, String quantity) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;
		String count = null;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		String dateTime = sdf.format(timestamp);
		
		

		// 売上を一括登録
		String sql = "INSERT INTO t_sales(sales_date, product_code, quantity, register_datetime, update_datetime) VALUES(?, ?, ?, ?, ?);";

		try {
			bean bean = new bean();
			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, dateTime);
			smt.setString(2, code);
			smt.setString(3, quantity);
			smt.setString(4, dateTime);
			smt.setString(5, dateTime);

//エラーになっちゃう

//			ResultSet rs = smt.executeQuery();
//			while (rs.next()) {
//
//				bean.setSalesdate(rs.getString("sales_date"));
//				bean.setCode(rs.getString("product_code"));
//				bean.setQuantity(rs.getString("quantity"));
//				bean.setRegisterdDate(rs.getString("register_datetime"));
//				bean.setUpdateTime(rs.getString("update_datetime"));
//				count ="1";
//			
//				
//			}
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

		}
		return count;

	}

}
