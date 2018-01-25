package com.elibrary.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elibrary.model.User;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	Connection con = null;
	java.sql.PreparedStatement pstmt = null;

	@Override
	public void init() throws ServletException {
		try 
		{
			Class.forName(getServletConfig().getServletContext().getInitParameter("Driver"));
		} 
		catch (ClassNotFoundException e)
		{ 
			e.printStackTrace();
		}
		String dbuserId = getServletConfig().getServletContext().getInitParameter("DB_USER");
		
		String dbpassword = getServletConfig().getServletContext().getInitParameter("DB_PASS");
		
		
		String url = getServletConfig().getServletContext().getInitParameter("DB_URL");

		try {
			con = DriverManager.getConnection(url, dbuserId, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 3. STEP 3 - CREATE THE STATEMENT
		String SELECTSQL = "SELECT NAME,USERID,PASSWORD,GENDER,CITY,ADDRESS,IMAGE" 
								+ " FROM  USERS WHERE USERID= ?";
		try {
			pstmt = con.prepareStatement(SELECTSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = "";
		String userId = req.getParameter("user");
		String password = req.getParameter("password");

		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		/**
		 * Following code should validate the entered data from database only.
		 */

		// 4. STEP 4 - EXECUTE THE QUERY
		if (userId != null && password != null && !"".equals(userId.trim()) && !"".equals(password.trim())) {
			try {
				pstmt.setString(1, userId);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					// user exists
					String passwordFromDB = rs.getString("PASSWORD");

					if (password.equals(passwordFromDB)) {
						// password also match
						
						String username = rs.getString(1);
						String userid = rs.getString(2);
						String passwd = rs.getString(3);
						String address = rs.getString(4);
						String city = rs.getString(5);
						String gender = rs.getString(6);
						
						byte[] image = rs.getBytes(7);
						
						
						HttpSession session = req.getSession();
						
						User userloggedin = new  User(username, userid, passwd, address, gender, city); 
						
						if(image!=null){
							userloggedin.setImage(image);
						}
						session.setAttribute("userloggedin", userloggedin);
						
						rd = req.getRequestDispatcher("TopBookServlet");
					} else {
						// password does not match
						message = "Invalid Password. Please, try again.";
					}

				} else {
					// user does not exists
					message = "Invalid User ID. Please, try again.";
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		} else {
			message = "No inputs received. Please, try again.";
		}

		req.setAttribute("key", message);
		rd.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void destroy() {

		try {
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
