package com.hust.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO: 以下はサンプルです。課題とは無関係の処理です。

		String userID = request.getParameter("userID");
		String password = request.getParameter("password");

		if("".equals(userID) || "".equals(password)) {
			String disp = "/errorLogin.html";
			RequestDispatcher dispatch = request.getRequestDispatcher(disp);
			dispatch.forward(request, response);
			return ;
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>一覧</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("あなたのユーザIDは" + userID + "です。");
		out.println("あなたのパスワードは" + password + "です。");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
