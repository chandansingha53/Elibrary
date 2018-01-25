<%@page import="com.elibrary.model.Books"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.
w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplication Name</title>
</head>
<body style="height:100%;background-image: url(images/wallpapr4.jpg);height: 100%;background-position: center;background-repeat: no-repeat;
    background-size: cover;">
	<div style="header">
	
		<%@include file="header.jsp" %> 
	
    </div>
	
	<div align="center">
	
<form action="">	
			<table width="100%" border="3">
					<caption   >
					<font style="color:#25F808" size="20">
						List of Books Available
					</font>
					
					</caption>
				<thead>
					<tr style="background-color:#F808B3;">
							<th  width="30%" ><h3>Title</h3></th>
							<th><h3>Author</h3></th>
							<th><h3>Genre</h3></th>
							<th><h3>Publisher</h3></th>
							<th><h3>Image</h3></th>
					</tr>
					<%
			
					ArrayList<Books> booksList = (ArrayList<Books>)session.getAttribute("BOOKS_LIST");
					int i=0; 
					if(booksList!=null && booksList.size()>0){
						Iterator<Books> iter = booksList.iterator();
						while(iter.hasNext()){
								Books books = iter.next();
								
						if(i%2==0){ 
					%>		
						<tr style="background-color:#FBC8C1" >
						
						<%} else { %>
						<tr style="background-color:#AFD8F2;">
						<%} %> 
							<td width="30%" align="center"><a href="./SearchBookServlet?bookid=<%=books.getId() %>"> 
									<%=books.getTitle() %> 
								</a>
							</td>
							<td width="20%" align="center" > <%=books.getAuthor() %></td>
							<td width="10%" align="center"><%=books.getGenre() %></td> 
							<td width="20%" align="center"><%=books.getPublisher() %></td>
							<td width="20%" align="center">
								<img alt="No Image" src="BookImageHomeServlet?bookid=<%=books.getId()%>" width="120px" height="150px"/>
							</td>
						</tr>
					<%	
						i++; 
						}
					}else{	
					
					%>
					
						<tr align="center"> <td align="center" colspan="4"><font style="font-size:23px; color:#F8F808"> <b> No books found</b></font></td> </tr>
				
					<%
					}
					%>
				</thead>
			
			</table>
</form>	
	</div>
	
	<div style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %> 
	
</div>	
</body>
</html>