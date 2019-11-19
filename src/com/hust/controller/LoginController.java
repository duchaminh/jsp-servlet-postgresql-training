package com.hust.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hust.dao.BaseDAO;
import com.hust.dao.BaseDAOImpl;
import com.hust.dao.UserDAO;
import com.hust.dao.UserDAOImpl;
import com.hust.dto.AuthorityDTO;
import com.hust.dto.UserDTO;

/**
 * Servlet implementation class usercontroller
 */
@WebServlet("/logincontroller")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserDAO userDAO = null;
    BaseDAO baseDAO = null;
    
    public LoginController() {
        userDAO = new UserDAOImpl();
        baseDAO = new BaseDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("name") != null) {
			listUser(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("not-authenticated.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userID");
		String password = request.getParameter("password");
		
		if(userDAO.check(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("name", username);
			
			listUser(request, response);
		}else {
			request.setAttribute("msg", "ログインに失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDTO> users = userDAO.get();
		List<AuthorityDTO> listAuthority = baseDAO.getListAuthority();
		request.setAttribute("users", users);
		request.setAttribute("listAuthority", listAuthority);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

}
