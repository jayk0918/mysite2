package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;

@WebServlet("/bc")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("list".equals(action)) {
			BoardDao boardDao = new BoardDao();
			List<BoardVo> bList = boardDao.getList();
			
			request.setAttribute("bList", bList);
			
			WebUtil.forward(request, response, "./WEB-INF/views/board/list.jsp");
			
		}else if("writeForm".equals(action)) {
			WebUtil.forward(request, response, "./WEB-INF/views/board/writeForm.jsp");
			
		}else if("insert".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userno = Integer.parseInt(request.getParameter("userno"));
			
			BoardVo boardVo = new BoardVo(title, content, userno);
			BoardDao boardDao = new BoardDao();
			
			boardDao.insert(boardVo);
			
			WebUtil.redirect(request, response, "/mysite2/bc?action=list");
			
		}else if("read".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getInfo(no);
			
			request.setAttribute("boardVo", boardVo);
			
			WebUtil.forward(request, response, "./WEB-INF/views/board/read.jsp");
			
		}else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));

			BoardVo boardVo = new BoardVo(no);
			BoardDao boardDao = new BoardDao();
			boardDao.delete(boardVo);
			
			WebUtil.redirect(request, response, "/mysite2/bc?action=list");
		
		}else if("modifyForm".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getInfo(no);
			
			request.setAttribute("boardVo", boardVo);
			
			WebUtil.forward(request, response, "./WEB-INF/views/board/modifyForm.jsp");
			
		}else if("modify".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardVo boardVo = new BoardVo(no, title, content);
			BoardDao boardDao = new BoardDao();
			boardDao.update(boardVo);
			
			
			WebUtil.redirect(request, response, "/mysite2/bc?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
