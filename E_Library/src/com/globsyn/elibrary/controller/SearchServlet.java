package com.globsyn.elibrary.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.spec.PSource;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elibrary.model.Books;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pstmt = null;

	/**
	 * @param pstmt 
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// STEP 1 - REGISTER THE DRIVER

		try {
			Class.forName(getServletConfig().getServletContext().getInitParameter("Driver"));
		} catch (ClassNotFoundException e) {
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

	public void destroy() {
		try {
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookname = request.getParameter("title");
		String authorname = request.getParameter("author");
		//String category = request.getParameter("category");
		
		
		// JDBC STEP 4 -- RUNNING THE QUERY
		if(!bookname.equals(null) || !authorname.equals(null)){
		String SELECTSQL = "SELECT * FROM  BOOKS WHERE TITLE= ? OR AUTHOR=?";
		ArrayList<Books> bookList = new ArrayList<Books>();
		try {
			pstmt = con.prepareStatement(SELECTSQL);
			pstmt.setString(1,bookname);
			pstmt.setString(2, authorname);
			ResultSet rs = pstmt.executeQuery();
			
				while(rs.next()){
				
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				String publisher = rs.getString(5);
				String genre = rs.getString(4);
				String description = rs.getString(6);
				byte[] image = rs.getBytes(9);
				Books book = new Books(id, title, author, genre, publisher, description,image);
				bookList.add(book);
				
			}
			
			
			request.getSession().setAttribute("BOOKS_LIST", bookList);
			
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	else{
		response.sendRedirect("./home2.jsp");
		
	}	
	
	
}	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
