package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class InsertDao {// 接続用の情報をフィールドに定数として定義
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/2-1";
	private static String USER = "root";
	private static String PASS = "EQLAa0_q";

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

	// データベースへデータを登録するメソッド
	public void insert(String name, String price) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;
		

		// 未使用のproduct_codeを採番
		String sql1 = "SELECT MAX(product_code)+1 AS product_code FROM m_product;";

		con = getConnection();
		smt = con.prepareStatement(sql1);
		ResultSet resultSet = smt.executeQuery(sql1);
		resultSet.next();
		int product_code = resultSet.getInt("product_code");

		// 採番したproduct_codeをキーにproduct_code、product_name、priceregister_datetime、update_datetimeを追加登録
		String sql = "INSERT INTO m_product(product_code, product_name, price, register_datetime, update_datetime) VALUES(?, ?, ?, ?,? );";

		try {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			String dateTime = sdf.format(timestamp);

			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setInt(1, product_code);
			smt.setString(2, name);
			smt.setString(3, price);
			smt.setString(4, dateTime);
			smt.setString(5, dateTime);

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

	}
}
