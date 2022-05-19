package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.bean;

public class SelectDao {// 接続用の情報をフィールドに定数として定義
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

	// データベースから全てのアカウント情報の検索を行うメソッド
	// 戻り値としてArrayList<AccountInfo>型の変数を利用
	public ArrayList<bean> selectAll() {
		// 変数宣言
		Connection con = null;
		Statement smt = null;

		// return用オブジェクトの生成
		ArrayList<bean> list = new ArrayList<bean>();

		// SQL文
		String sql = "SELECT * FROM m_product;";

		try {
			con = getConnection();
			smt = con.createStatement();

			// SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果を配列に格納
			while (rs.next()) {
				bean bean = new bean();
				bean.setCode(rs.getString("product_code"));
				bean.setName(rs.getString("product_name"));
				bean.setPrice(rs.getString("price"));
				bean.setRegisterdDate(rs.getString("register_datetime"));
				bean.setUpdateTime(rs.getString("update_datetime"));
				bean.setDeleteDate(rs.getString("delete_datetime"));
				list.add(bean);

			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// リソースの開放
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

}
