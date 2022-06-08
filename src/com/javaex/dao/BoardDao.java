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
			query += "       ,us.name name ";
			query += "       ,br.hit hit ";
			query += "       ,to_char(br.reg_date, 'YYYY-MM-DD HH24:MM') reg_date";
			query += "       ,br.user_no userno ";
			query += " from board br, users us ";
			query += " where br.user_no = us.no ";
			query += " order by br.no desc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				int hit = rs.getInt("hit");
				String date = rs.getString("reg_date");
				String name = rs.getString("name");
				int userno = rs.getInt("userno");

				BoardVo boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setName(name);
				boardVo.setHit(hit);
				boardVo.setDate(date);
				boardVo.setUserNo(userno);

				gList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		}

		close();
		return gList;
	}

	// insert

	public int insert(BoardVo boardVo) {
		int count = -1;
		getConnection();

		try {
			String query = "";
			query += " insert into board ";
			query += " values(seq_board_no.nextval, ?, ?, 0, sysdate, ? ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getUserNo());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 등록되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// select_individual
	public BoardVo getInfo(int no) {
		BoardVo boardVo = null;

		getConnection();

		try {
			String query = "";
			query += " select us.name name ";
			query += "     	 ,br.no no ";
			query += "     	 ,br.hit hit ";
			query += "       ,br.reg_date reg_date ";
			query += "       ,br.title title ";
			query += "       ,br.content content ";
			query += "       ,br.user_no userno ";
			query += " from board br, users us ";
			query += " where br.user_no = us.no ";
			query += " and br.no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				int num = rs.getInt("no");
				int hit = rs.getInt("hit");
				String date = rs.getString("reg_date");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int userNo = rs.getInt("userno");

				boardVo = new BoardVo();
				boardVo.setName(name);
				boardVo.setNo(num);
				boardVo.setHit(hit);
				boardVo.setDate(date);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		close();
		return boardVo;
	}

	// delete
	public int delete(BoardVo boardVo) {
		int count = -1;
		getConnection();

		try {
			String query = "";
			query += " delete from board ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardVo.getNo());

			count = pstmt.executeUpdate();
			System.out.println("[" + count + "건 삭제되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// update
	public int update(BoardVo boardVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " update board ";
			query += " set title = ? ";
			query += "    ,content = ? ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNo());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 수정되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}

}
