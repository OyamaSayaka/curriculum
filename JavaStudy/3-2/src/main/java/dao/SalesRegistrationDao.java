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

	// 登録ボタンを押下時に追加させるメソッド
	public String insert(String name,int price) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;

		// return用変数
		String count = "";

		String sql = "SELECT update_datetime FROM m_product WHERE product_code = ?;";

		try {
			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, name);
			ResultSet resultSet = smt.executeQuery();
			while (resultSet.next())
				count = resultSet.getString("update_datetime");


		} catch (IllegalStateException e) {
			throw new IllegalStateException(e);

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