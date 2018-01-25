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
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   
    public SearchBookServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookId = request.getParameter("bookid");
		// JDBC STEP 4 -- RUNNING THE QUERY
		
		String bookType = request.getParameter("type");
				
		if(bookId!= null){
			// from Home page after clicking on Book Title for detailed display
			
			String SELECTSQL = "SELECT * FROM  BOOKS WHERE ID = ? ";
			try {
				pstmt = con.prepareStatement(SELECTSQL);
				
				pstmt.setInt(1, new Integer(bookId));
				
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String author = rs.getString(3);
					String publisher = rs.getString(4);
					String genre = rs.getString(5);
					String description = rs.getString(6);
					byte[] image = rs.getBytes(9);
					Books book = new Books(id, title, author, genre, publisher, description, image);
					
					String contentType = rs.getString(8);
					
					byte[] pdfcontent = rs.getBytes(7);
					
					book.setPdfContent(pdfcontent);
					book.setContentType(contentType);
					
					request.getSession().setAttribute("PDFBOOK", book);
					
					request.getRequestDispatcher("/showbook.jsp").forward(request, response);
					
				}else{
					response.sendRedirect("Home2.jsp");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		
		
		
		else if(bookType!=null){
			String SELECTSQL = "SELECT * FROM  BOOKS WHERE GENRE = ? ";
			ArrayList<Books> bookList = new ArrayList<Books>();
			try {
					pstmt = con.prepareStatement(SELECTSQL);
					pstmt.setString(1,bookType);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						int id = rs.getInt(1);
						String title = rs.getString(2);
						String author = rs.getString(3);
						String genre = rs.getString(4);
						String publisher = rs.getString(5);
						String description = rs.getString(6);
						byte[] image = rs.getBytes(9);
						Books book = new Books(id, title, author, genre, publisher, description, image);
						bookList.add(book);
					  }
					request.getSession().setAttribute("BOOKS_LIST", bookList);
					request.getRequestDispatcher("/home.jsp").forward(request, response);
				  }catch (SQLException e) {
					  e.printStackTrace();
				  }catch(NullPointerException e){
					  e.printStackTrace();
				  }
				}
    
		else{
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
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
