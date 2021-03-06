package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if("joinForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		}else if("join".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(id, password, name, gender);
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		}else if("loginForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		}else if("login".equals(action)) {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserVo userVo = new UserVo(id, password);
			// 생성자 없이 userVo.setId(), userVo.setPassword(); 사용 가능
			UserDao userDao = new UserDao();
			UserVo authUser = userDao.getUser(userVo);
			
			if(authUser == null) {
				System.out.println("로그인 실패");
				WebUtil.redirect(request, response, "/mysite2/user?action=loginForm");
			}else {
				System.out.println("로그인 성공");
				
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authUser);
				
				WebUtil.redirect(request, response, "/mysite2/main");
			}
			
		}else if("logout".equals(action)) {
			System.out.println("logout");
			
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysite2/main");
			
		}else if("modifyForm".equals(action)) {
			// 로그인 check (로그인하지 않고 들어왔을 때 에러)
			
			
			// 로그인 한 사용자의 no값을 세션으로부터 채용
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int no = authUser.getNo();
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(no);
			
			request.setAttribute("userVo", userVo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
		}else if("modify".equals(action)) {
			// session에서 no를 채용
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			int no = authUser.getNo();
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setNo(no);
			userVo.setName(name);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			UserDao userDao = new UserDao();
			int count = userDao.userUpdate(userVo);
			
			WebUtil.redirect(request, response, "/mysite2/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
