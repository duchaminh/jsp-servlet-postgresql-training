package com.hust.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String action = request.getParameter("action");
		
		if(action.equals("EDIT")) {
			String userid = request.getParameter("id");
			getSingleUser(request,response, userid);
		}
		else if(action.equals("DELETE")) {
			String userId = request.getParameter("id");
			request.setAttribute("userId", userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/delete-user.jsp");
			dispatcher.forward(request, response);
		}
		else if(action.equals("DELETE-CONFIRM")){
			System.out.println("delete");
			String userid = request.getParameter("id");
			deleteUser(request, response, userid);
		}
		else if(action.equals("SEARCH")) {
			System.out.println("SEARCH");
		}
		else {
			request.setAttribute("action", "ADD");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-edit-user.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response,String userid) throws ServletException, IOException {
		if(userDAO.delete(userid)) {
			request.setAttribute("delete_msg", "Delete success");
			
			List<UserDTO> users = userDAO.get();
			request.setAttribute("users", users);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	
	/**
	 * Get single user with id parameter for update operation
	 */
	
	private void getSingleUser(HttpServletRequest request, HttpServletResponse response, String userid) throws ServletException, IOException {
		User user = new User();
		
		user = userDAO.get(userid);
		request.setAttribute("user", user);
		request.setAttribute("action", "EDIT");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-edit-user.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		//Edit data
		if(action.equals("EDIT")) {
			String userId = request.getParameter("userId");
			System.out.println(userId);
			User user = updateUser(request, response, userId);	
			if(userDAO.update(user)) {
				response.sendRedirect(request.getContextPath() +"/logincontroller");
			}
		}
		// add data to database
		else {
			String userId = request.getParameter("userId");
			//check user id is overlap
			if(userDAO.isOverLapUserId(userId)) {
				request.setAttribute("isoverlap", "User id is overlapping");
				request.setAttribute("action", "ADD");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-edit-user.jsp");
				dispatcher.forward(request, response);
			}
			//if user id is valid, insert data to DB
			else{
				User user = createUser(request, response, userId);			
				if(userDAO.save(user)) {
					response.sendRedirect(request.getContextPath() +"/logincontroller");			
				}
			}
		}
		
	}
	
	/**
	 * Create user from form input
	 */
	public User createUser(HttpServletRequest request, HttpServletResponse response, String userId) {
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
		
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		
		System.out.println(Long.parseLong(formatter.format(date)));
		user.setCreateDate(Long.parseLong(formatter.format(date)));
		user.setUpdateDate(Long.parseLong(formatter.format(date)));
		
		return user;
	}
	
	/**
	 * Update user from form input
	 */
	public User updateUser(HttpServletRequest request, HttpServletResponse response, String userId) {
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
		user.setUpdateUserID((String) session.getAttribute("name"));
		
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		
		System.out.println(Long.parseLong(formatter.format(date)));
		user.setUpdateDate(Long.parseLong(formatter.format(date)));
		
		return user;
	}
	
	/**
	 * List user dto for home page
	 */
	public void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDTO> users = userDAO.get();
		request.setAttribute("users", users);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("logincontroller");
		dispatcher.forward(request, response);
	}

}
