package com.globsyn.elibrary.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/UploadBookServlet")
@MultipartConfig( fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	PreparedStatement statement = null;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// gets values of text fields
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String genre = request.getParameter("genre");
		String publisher = request.getParameter("publisher");
		String description = request.getParameter("description");
		Part image = request.getPart("image");
		
		InputStream inputStream = null; // input stream of the upload file

		String insertBookSql = "INSERT INTO BOOKS (TITLE, AUTHOR, GENRE, PUBLISHER, DESCRIPTION,CONTENT ,CONTENTTYPE ) "
				+ " values (?,?,?,?,?,?,?)";
		if(image!=null)
			insertBookSql = "INSERT INTO BOOKS (TITLE, AUTHOR, GENRE, PUBLISHER, DESCRIPTION,CONTENT ,CONTENTTYPE,IMAGE ) "
					+ " values (?,?,?,?,?,?,?,?)";

		String message = null; // message will be sent back to client

		try {

			statement = con.prepareStatement(insertBookSql);
			statement.setString(1, title);
			statement.setString(2, author);
			statement.setString(3, genre);
			statement.setString(4, publisher);
			statement.setString(5, description);
			if(image!=null)
			statement.setBlob(8, image.getInputStream());
			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("bookfile");

			System.out.println(filePart.getName());
			// InputStream fileContent = filePart.getInputStream();
			if (filePart != null) {

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();

				if (inputStream != null) {
					statement.setBlob(6, inputStream);
					statement.setString(7, filePart.getContentType());
				}

			} 
			// sends the statement to the database server
			int row = statement.executeUpdate();
			if (row > 0) {
				message = "Book is now uploaded successfully..";
			}

			// sets the message in request scope
			request.setAttribute("message", message);

			// forwards to the message page
			request.getRequestDispatcher("/uploadbook.jsp").forward(request, response);
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			// sets the message in request scope
			request.setAttribute("message", message);
			request.getRequestDispatcher("/uploadbook.jsp").forward(request, response);
			ex.printStackTrace();
		}

	}

}
