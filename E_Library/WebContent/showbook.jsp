<%@page import="com.elibrary.model.Books"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

	function openImage(val,id){
		
		
		 
		var url = "./PDFServlet?open="+val;
		
		window.open(url,200,200,false);
		
		
	}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Book</title>
</head>
<body style="background-color:#DAF7A6">
<div style="header">
	
		<%@include file="header.jsp" %>
	
    </div>
<br>
		
		<% 
			String displayMessage = (String) request.getAttribute("message");
			
		if(displayMessage!=null){
		
		%>
		<b>  <%=displayMessage %> </b>

	
		<% } %>
		
			<%
			
				Books book = (Books) session.getAttribute("PDFBOOK");
	
			
				
			
			%>
			
<form action="" method="post" enctype="multipart/form-data" >
		<div align="center">
		<input type="hidden" name="bookId" value="<%=book.getId() %>" />
				<table align="center" border="0" >
					
					<tr colspan="2" align="center">
						<img alt="No Image" src="BookImageServlet" width="200" height="300"/>
					</tr><br><br><br>	
					<tr>
						<td>Book Title</td> 
						<td> <%= book.getTitle() %> </td>
					</tr>	
					<tr>
						<td>Author</td>
						<td> <%= book.getAuthor() %> </td>
					</tr>	
					<tr>
						<td>Genre</td>
						<td> <%= book.getGenre() %> </td>
					</tr>	
					<tr>
						<td>Publisher Name</td>
						<td> <%= book.getPublisher() %> </td>
					</tr>					
					<tr>
						<td>Brief Description</td>
						<td> <%= book.getDescription() %> </td>
					</tr>	
					<br><br>
					<tr>
						<td><input type="button" value="View PDF" onclick="openImage('inline',<%=book.getId() %>);"></input> </td>
						<td><input type="button" value="Download PDF" onclick="openImage('saveas',<%=book.getId() %>)"></input> </td>
					</tr>						
						
				</table>
		
		
		</div>
</form>		

<div style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %>
	
</div>	
</body>
</html>