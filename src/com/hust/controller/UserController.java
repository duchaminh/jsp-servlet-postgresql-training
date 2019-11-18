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
		String action = request.getParameter("action");
		
		if(action.equals("EDIT")) {
			getSingleUser(request,response);
		}
		else if(action.equals("DELETE")) {
			String userId = request.getParameter("id");
			request.setAttribute("userId", userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/delete-user.jsp");
			dispatcher.forward(request, response);
		}
		else if(action.equals("DELETE-CONFIRM")){
			System.out.println("delete");
			deleteUser(request, response);
		}
		else {
			request.setAttribute("action", "ADD");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-edit-user.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("id");
		if(userDAO.delete(userid)) {
			request.setAttribute("delete_msg", "Delete success");
			
			List<UserDTO> users = userDAO.get();
			request.setAttribute("users", users);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

	private void getSingleUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("id");
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
		//System.out.print(action);
		if(action.equals("EDIT")) {
			String userId = request.getParameter("userId");
			System.out.println(userId);
			User user = createUser(request, response, userId);	
			if(userDAO.update(user)) {
				response.sendRedirect(request.getContextPath() +"/logincontroller");
			}
		}
		else {
			String userId = request.getParameter("userId");
			
			if(userDAO.isOverLapUserId(userId)) {
				System.out.println(":(((((((((((((((");
				request.setAttribute("isoverlap", "User id is overlapping");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-edit-user.jsp");
				dispatcher.forward(request, response);
			}else{
				User user = createUser(request, response, userId);			
				if(userDAO.save(user)) {
					response.sendRedirect(request.getContextPath() +"/logincontroller");			
				}
			}
		}
		
	}
	
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
		user.setCreateDate(20191113);
		user.setUpdateDate(20191113);
		
		return user;
	}
	
	public void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDTO> users = userDAO.get();
		request.setAttribute("users", users);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("logincontroller");
		dispatcher.forward(request, response);
	}

}
