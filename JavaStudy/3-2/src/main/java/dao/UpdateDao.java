package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDao {// 接続用の情報をフィールドに定数として定義
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

	// 変更ボタンを押した時のupdate_datetimeを取ってくるメソッド
	public String select_datetime(String product_code) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;

		// return用変数
		String count = "";

		String sql = "SELECT update_datetime FROM m_product WHERE product_code = ?;";

		try {
			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, product_code);
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

// 売上があるか確認するメソッド
	public int checkSales(int code) throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;
		int count = 0;

		String sql = "SELECT * FROM t_sales WHERE product_code = ?;";

		try {
			con = getConnection();
			smt = con.prepareStatement(sql);
			smt.setInt(1, code);
			ResultSet resultSet = smt.executeQuery();
			// resultSetの中身が入っているかnull以外で書く

			if (resultSet.next()) {
				// ↓falseかtrueだけだからこんな複雑にしなくてもよい
				count = resultSet.getRow();
				if (count == 0) {
					count = 0;
				} else
					count = 1;
			}

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

	// データベースへデータを更新するメソッド
	public void update(String name, String price, String code, String dateTime)
			throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;

		// 変更ボタンを押した後入力された内容に更新をする
		String sql = "UPDATE m_product SET product_name=?, price=?,update_datetime = NOW() WHERE product_code=?;";

		try {

			con = getConnection();
			PreparedStatement cd = con.prepareStatement(sql);

			smt = con.prepareStatement(sql);
			smt.setString(1, name);
			smt.setString(2, price);
			smt.setString(3, code);

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

	// データを論理削除するメソッド
	public void delete(String code, String name, String price, String datetTime)
			throws SQLException, ClassNotFoundException {
		// 変数宣言
		Connection con = null;
		PreparedStatement smt = null;

		// 論理削除を行う
		String deleteSql = "UPDATE m_product SET delete_datetime = NOW() WHERE product_code = ?;";
		String dateSql = "SELECT update_datetime FROM m_product WHERE product_code= ? ;";

		try {
			con = getConnection();
			PreparedStatement cd = con.prepareStatement(dateSql);
			cd.setString(1, code);
			ResultSet dateCheck = cd.executeQuery();
			while (dateCheck.next()) {

				smt = con.prepareStatement(deleteSql);
				smt.setString(1, code);

				// SQLをDBへ発行
				smt.executeUpdate();

			}

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
	}
}
