package com.globsyn.elibrary.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elibrary.model.Books;

/**
 * Servlet implementation class BookImageHomeServlet
 */
@WebServlet("/BookImageHomeServlet")
public class BookImageHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookImageHomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		String idString = request.getParameter("bookid");
		int bid = new Integer(idString);
		ArrayList<Books> list = (ArrayList<Books>)session.getAttribute("BOOKS_LIST");
		byte[] image =null;
		
		for(Books b :list) {
			if(b.getId() == bid) {
				image = b.getImage();
				break;
			}
		}
		
		response.setContentType("image/jpg");
		response.getOutputStream().write(image);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
