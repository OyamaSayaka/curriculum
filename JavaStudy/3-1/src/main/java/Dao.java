
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao {

	static final String URL = "jdbc:mysql://localhost/2-1";
	static final String USERNAME = "root";
	static final String PASSWORD = "EQLAa0_q";
	private static Connection connection;

	// データベース接続を行うメソッド

	public static Connection getConnection() {
		try {
			Class.forName(URL);

			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return connection;

		} catch (Exception e) {
			throw new IllegalStateException(e);

		}

	}

	// データベースから全てのアカウント情報の検索を行うメソッド
	// 戻り値としてArrayList<AccountInfo>型の変数を利用
  public ArrayList<AccountInfo> selectAll(){
	
	        //変数宣言
	
	        Connection con = null;
	        Statement  smt = null;

	        //return用オブジェクトの生成
	
	        ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();

	        //SQL文
	
	        String sql = "SELECT * FROM account";

	        try{
	
	            con = getConnection();
	            smt = con.createStatement();
	
	            //SQLをDBへ発行
	
	            ResultSet rs = smt.executeQuery(sql);
	
	
	            //検索結果を配列に格納
	
	            while(rs.next()){
	   AccountInfo accountinfo =new AccountInfo();
	    accountinfo.setId(rs.getString("id"));
    accountinfo.setName(rs.getString("name"));
	   accountinfo.setEmail(rs.getString("email"));
  accountinfo.setAuthority(rs.getString("authority"));
	            list.add(accountinfo);
	
	            }
	
	        }catch(Exception e){
	
	            throw new IllegalStateException(e);
	
	        }finally{
	
	            //リソースの開放
	 try{smt.close();}catch(SQLException ignore){}
	
	            }
  try{con.close();}catch(SQLException ignore){}
	
	            



return list;

}

}