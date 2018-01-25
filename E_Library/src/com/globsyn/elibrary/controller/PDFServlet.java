package com.globsyn.elibrary.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elibrary.model.Books;

/**
 * Servlet implementation class PDFServlet
 */
@WebServlet("/PDFServlet")
public class PDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String openType = request.getParameter("open");
		HttpSession session = request.getSession();
		
		Books book = (Books) session.getAttribute("PDFBOOK");
		
		if(openType.equalsIgnoreCase("inline")){
			
			response.setContentType(book.getContentType());

			OutputStream outputstream = response.getOutputStream();
			
			outputstream.write(book.getPdfContent());
			outputstream.flush();
			outputstream.close();
			
		}else{
			
			request.setAttribute("open", openType);
			request.getRequestDispatcher("/download.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
