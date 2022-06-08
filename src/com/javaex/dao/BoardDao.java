package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.javaex.vo.BoardVo;

public class BoardDao {

	private String id = "admin";
	private String pw = "Jayk09180918";
	private String url = "jdbc:oracle:thin:@webdb_high?TNS_ADMIN=/Users/jaykim0918/Dropbox/Wallet_webdb";
	private String driver = "oracle.jdbc.OracleDriver";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// connection
	public void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}
	
	// connection end - close
	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// select
		public List<BoardVo> getList() {
			List<BoardVo> gList = new ArrayList<BoardVo>();
			getConnection();
			
			try {
				String query = "";
				query += " select br.no no ";
				query += "     	 ,br.title  title ";
				query += "       ,br.content content ";
				query += "       ,br.hit hit ";
				query += "       ,to_char(br.reg_date, 'YYYY-MM-DD HH24:MM') reg_date";
				query += "       ,us.name name ";
				query += " from board br, users us ";
				query += " where br.user_no = us.no ";
				query += " order by no desc ";
				
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("no");
					String title = rs.getString("title");
					String name = rs.getString("name");
					String content = rs.getString("content");
					int hit = rs.getInt("hit");
					String date = rs.getString("reg_date");
					
					BoardVo boardVo = new BoardVo(no, title, name, content, hit, date);
					
					gList.add(boardVo);
				}
				
			}catch(SQLException e) {
				System.out.println("error" + e);
			}
			
			close();
			return gList;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
