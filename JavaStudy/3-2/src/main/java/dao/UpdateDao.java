package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {// 接続用の情報をフィールドに定数として定義
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
	public void update(String name, String price,String product_code) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;



		// 変更ボタンを押した後入力された内容に更新をする
		String sql = "UPDATE m_product SET (product_name=?, price=?,)WHERE product_code=?;";
		
		try {

			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, name);
			smt.setString(2, price);
			smt.setString(3, product_code);

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

