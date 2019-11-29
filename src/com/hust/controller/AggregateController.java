package com.hust.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hust.dao.AggregateDAO;
import com.hust.dao.AggregateDAOImpl;
import com.hust.dto.AggregateByAuthority;

/**
 * Servlet implementation class AggregateController
 */
@WebServlet("/AggregateController")
public class AggregateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String AGGREGATE_WITH_AUTHORITY = "authority_id";
    
	AggregateDAO aggregateDAO = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggregateController() {
        super();
        aggregateDAO = new AggregateDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AggregateByAuthority> results = null;
		results = aggregateDAO.aggregateFor(AGGREGATE_WITH_AUTHORITY);
		
		request.setAttribute("aggregate", results);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/aggregate-page.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
