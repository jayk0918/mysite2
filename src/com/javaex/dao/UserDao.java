package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

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


	// insert
	public int insert(UserVo userVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " insert into users ";
			query += " values(seq_users_no.nextval, ?, ?, ?, ? ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 등록되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
	
	// login
		public UserVo getUser(UserVo userVo) {
			getConnection();
			
			UserVo authUser = new UserVo();
			
			try {
				String query = "";
				query += " select no ";
				query += "       ,name ";
				query += " from users ";
				query += " where id = ? ";
				query += " and password = ? ";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userVo.getId());
				pstmt.setString(2, userVo.getPassword());

				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("no");
					String name = rs.getString("name");
					
					authUser = new UserVo();
					authUser.setNo(no);
					authUser.setName(name);
				}
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return authUser;
		}
	
	
	
}
