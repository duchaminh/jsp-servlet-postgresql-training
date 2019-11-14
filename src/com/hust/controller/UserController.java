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

import com.hust.dao.UserDAO;
import com.hust.dao.UserDAOImpl;
import com.hust.dto.UserDTO;

import model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserDAO userDAO = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        userDAO = new UserDAOImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		
		if(userDAO.isOverLapUserId(userId)) {
			System.out.println(":(((((((((((((((");
			request.setAttribute("isoverlap", "User id is overlapping");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-user.jsp");
			dispatcher.forward(request, response);
		}else{
			User user = new User();
			HttpSession session = request.getSession();
			user.setUserId(userId);
			user.setPassword(request.getParameter("password"));
			user.setFamilyName(request.getParameter("familyName"));
			user.setFirstName(request.getParameter("firstName"));
			user.setGenderId(Integer.parseInt(request.getParameter("genderId")));
			user.setAuthorityId(Integer.parseInt(request.getParameter("authorityId")));
			user.setAge(Integer.parseInt(request.getParameter("age")));
			if(request.getParameter("admin") == null)
				user.setAdmin(0);
			else
				user.setAdmin(1);
			user.setCreateUserId((String) session.getAttribute("name"));
			user.setUpdateUserID((String) session.getAttribute("name"));
			user.setCreateDate(20191113);
			user.setUpdateDate(20191113);
			
			if(userDAO.save(user)) {
				response.sendRedirect(request.getContextPath() +"/logincontroller");			
			}
			
			
		}
	}
	
	public void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDTO> users = userDAO.get();
		request.setAttribute("users", users);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("logincontroller");
		dispatcher.forward(request, response);
	}

}
