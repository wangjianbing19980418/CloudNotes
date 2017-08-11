package com.cloudnotes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjb.DAO.UsersDAO;
import com.wjb.md5.MD5Util;

public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UsersDAO usersDao = new UsersDAO();

		if (username == null || password == null) {
			ServletContext context = getServletContext();
			context.log("输入参数为空:", new IllegalStateException("并没有参数"));
		}
		boolean result = usersDao.register(username, MD5Util.getMd5(password));

		PrintWriter pw = resp.getWriter();
		pw.write("" + result);
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
