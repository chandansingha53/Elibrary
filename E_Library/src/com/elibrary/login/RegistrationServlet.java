package com.elibrary.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.spec.PSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
// @WebServlet("/Registration")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	java.sql.PreparedStatement pstmt = null;
	
	public RegistrationServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {

		 // 1. STEP 1 --- LOAD THE DRIVER try {
		try 
		{
			Class.forName(getServletConfig().getServletContext().getInitParameter("Driver"));
		} 
		catch (ClassNotFoundException e)
		{ 
			e.printStackTrace();
		}

		// 2. STEP 2 - CREATE THE CONNECTION
		String dbuserId = getServletConfig().getServletContext().getInitParameter("DB_USER");
		String dbpassword = getServletConfig().getServletContext().getInitParameter("DB_PASS");
		String url = getServletConfig().getServletContext().getInitParameter("DB_URL");
		try {
			con = DriverManager.getConnection(url, dbuserId, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String address = request.getParameter("address");
		Part image = request.getPart("image");

		// 3. STEP 3 - CREATE THE STATEMENT
		String insertSQL = "INSERT INTO USERS(NAME,USERID,PASSWORD,GENDER,CITY,ADDRESS) VALUES (?,?,?,?,?,?)";
		if(image!=null)
			insertSQL = "INSERT INTO USERS(NAME,USERID,PASSWORD,GENDER,CITY,ADDRESS,IMAGE) VALUES (?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(insertSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 4. STEP 4 - EXECUTE THE QUERY
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, userid);
			pstmt.setString(3, password);
			pstmt.setString(4, gender);
			pstmt.setString(5, city);
			pstmt.setString(6, address);

			if(image!=null)
				pstmt.setBlob(7, image.getInputStream());
			
			pstmt.executeUpdate();

			System.out.println();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		request.setAttribute("key", "User is registered successfully. Please, login now.");
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
