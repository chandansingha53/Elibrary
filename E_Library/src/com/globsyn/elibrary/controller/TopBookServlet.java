package com.globsyn.elibrary.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elibrary.model.Books;

@WebServlet("/TopBookServlet")
public class TopBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TopBookServlet() {
        super();
    }
    Connection con = null;
	PreparedStatement pstmt = null;

	public void init() throws ServletException {
		// STEP 1 - REGISTER THE DRIVER
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

	public void destroy() {
		try {
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3. STEP 3 - CREATE THE STATEMENT
					String SELECTSQL = "SELECT *FROM BOOKS";
					try {
						pstmt = con.prepareStatement(SELECTSQL);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					ArrayList<Books> bookList = new ArrayList<Books>();
					try {
						ResultSet rs = pstmt.executeQuery();
						
						while(rs.next()){
							
							int id = rs.getInt(1);
							String title = rs.getString(2);
							String author = rs.getString(3);
							String genre = rs.getString(4);
							String publisher = rs.getString(5);
							String description = rs.getString(6);
							byte[] image = rs.getBytes(9);
							Books book = new Books(id, title, author, genre, publisher, description,image);
							bookList.add(book);
							
						}
						request.getSession().setAttribute("BOOKS_LIST", bookList);
						request.getRequestDispatcher("/Home2.jsp").forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}catch(NullPointerException e){
						e.printStackTrace();
					}
		}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
