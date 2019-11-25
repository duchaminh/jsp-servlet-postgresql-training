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

import org.apache.commons.lang.StringUtils;

import com.hust.dao.BaseDAO;
import com.hust.dao.BaseDAOImpl;
import com.hust.dao.UserDAO;
import com.hust.dao.UserDAOImpl;
import com.hust.dto.AggregateByAuthority;
import com.hust.dto.AuthorityDTO;
import com.hust.dto.GenderDTO;
import com.hust.dto.UserDTO;
import com.hust.dto.UserDTOEdit;
import com.hust.util.UserValidator;
import com.hust.util.UserValidatorImpl;
import com.hust.util.validators.genericvalidator.UserException;

import model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int MIN_AGE = 0;
	private static final int MAX_AGE = 60;
    
	UserDAO userDAO = null;
	BaseDAO baseDAO = null;
	List<AuthorityDTO> listAuthority = null;
	List<GenderDTO> listGender = null;
	UserValidator userValidator = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        userDAO = new UserDAOImpl();
        baseDAO = new BaseDAOImpl();
        userValidator = new UserValidatorImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		if(action.equals("EDIT")) {
			String userid = request.getParameter("id");
			getSingleUser(request,response, userid);
		}
		else if(action.equals("DELETE")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/delete-user.jsp");
			dispatcher.forward(request, response);
		}
		else if(action.equals("DELETE-CONFIRM")){
			System.out.println("delete");
			String userid = request.getParameter("id");
			deleteUser(request, response, userid);
		}
		else if(action.equals("AGGREGATE")) {
			System.out.println("AGGREGATE");
			List<AggregateByAuthority> aggregateByAuthoritys = null;
			
			while(aggregateByAuthoritys == null) {
				aggregateByAuthoritys = userDAO.listAggregateByAuthority();
				userDAO.clusteringUserDtoByAuthorityId(aggregateByAuthoritys);
				userDAO.clusteringComplete(aggregateByAuthoritys);
			}
			
			request.setAttribute("aggregate", aggregateByAuthoritys);
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/aggregate-page.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("LOGOUT")) {
			System.out.println("logout");
			HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.removeAttribute("name");
	             
	            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	            dispatcher.forward(request, response);
	        }
		}
		else {
			//List<AuthorityDTO> listAuthority = baseDAO.getListAuthority();
			goToAddUpdatePage(request, response,"ADD");
		}
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response,String userid) throws ServletException, IOException {
		if(userDAO.delete(userid)) {
			request.setAttribute("success_msg", "Delete success");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/success-page.jsp");
			dispatcher.forward(request, response);		
		}else {
			request.setAttribute("fail_msg", "Delete fail");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/delete-user.jsp");
			dispatcher.forward(request, response);	
		}
		
	}
	
	/**
	 * Get single user with id parameter for update operation
	 */
	
	private void getSingleUser(HttpServletRequest request, HttpServletResponse response, String userid) throws ServletException, IOException {
		UserDTOEdit user = new UserDTOEdit();
		
		user = userDAO.get(userid);
		request.setAttribute("user", user);
		goToAddUpdatePage(request, response, "EDIT");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		//Edit data
		if(action.equals("EDIT")) {
			String userId = request.getParameter("userId");
			System.out.println(userId);
			User user = updateUser(request, response, userId);	
			
			//validate
			try {
				userValidator.validate(user);
				if(userDAO.update(user)) {
					request.setAttribute("success_msg", "Update success");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/success-page.jsp");
					dispatcher.forward(request, response);
				}else {
					getSingleUser(request,response, userId);
				}
			} catch (UserException e) {
				e.printStackTrace();
				request.setAttribute("update_err", e.getMessage());
				getSingleUser(request,response, userId);
			}
		}
		// add data to database
		else {
			String userId = request.getParameter("userId");
			//check user id is overlap
			if(userDAO.isOverLapUserId(userId)) {
				request.setAttribute("isoverlap", "User id is overlapping");
				goToAddUpdatePage(request, response,"ADD");
			}
			//if user id is valid, insert data to DB
			else{
				User user = createUser(request, response, userId);	
				//validate
				try {
					userValidator.validate(user);
					if(userDAO.save(user)) {
						request.setAttribute("success_msg", "Add success");
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/views/success-page.jsp");
						dispatcher.forward(request, response);			
					}else {
						goToAddUpdatePage(request, response,"ADD");
					}
				} catch (UserException e) {
					e.printStackTrace();
					request.setAttribute("add_err", e.getMessage());
					goToAddUpdatePage(request, response,"ADD");
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
		System.out.println("青山さん");
		System.out.println(request.getParameter("familyName"));
		user.setFirstName(request.getParameter("firstName"));
		user.setGenderId(Integer.parseInt(request.getParameter("genderId")));
		user.setAuthorityId(Integer.parseInt(request.getParameter("authorityId")));
		if(!request.getParameter("age").isEmpty()) {
			if(StringUtils.isNumeric(request.getParameter("age"))) {
				if(Integer.parseInt(request.getParameter("age")) >= MIN_AGE && Integer.parseInt(request.getParameter("age")) < MAX_AGE)
					user.setAge(Integer.parseInt(request.getParameter("age")));
				else
					user.setAge(-1);
			}else 
				user.setAge(-1);
			
		}
		//age is null
		else {
			user.setAge(0);
		}
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
		if(!request.getParameter("age").isEmpty()) {
			if(StringUtils.isNumeric(request.getParameter("age"))) {
				if(Integer.parseInt(request.getParameter("age")) >= MIN_AGE && Integer.parseInt(request.getParameter("age")) < MAX_AGE)
					user.setAge(Integer.parseInt(request.getParameter("age")));
				else
					user.setAge(-1);
			}else // aaaaa
				user.setAge(-1);
			
		}
		//age is null
		else {
			user.setAge(0);
		}
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
	
	/**
	 * set attribute and send request to add-update page
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void goToAddUpdatePage(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
		while(listAuthority == null && listGender == null) {
			listAuthority = baseDAO.getListAuthority();
			listGender = baseDAO.getListGender();
		}
		
		request.setAttribute("listAuthority", listAuthority);
		request.setAttribute("listGender", listGender);
		request.setAttribute("action", action);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-edit-user.jsp");
		dispatcher.forward(request, response);
	}

}
