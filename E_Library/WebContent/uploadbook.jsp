<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<form action="UploadBookServlet" method="post" enctype="multipart/form-data" >
		<div align="center">
				<table align="center" border="0" >
					<tr>
						<td>Book Title</td>
						<td> <input type="text" name="title"  /> </td>
					</tr>	
					<tr>
						<td>Author</td>
						<td> <input type="text" name="author"  /> </td>
					</tr>	
					<tr>
						<td>Genre</td>
						<td> <select name="genre" >
								<option value="0">Select</option>
								<option value="Drama">Drama</option>
								<option value="Romance">Romance </option>
								<option value="Horror">Horror</option>
								<option value="Sci-fi Fantasy">Sci-fi Fantasy</option>
								<option value="Fiction">Fiction</option> 
						
							</select>
						 </td>
					</tr>	
					<tr>
						<td>Publisher Name</td>
						<td><input type="text" name="publisher" /></td>
					</tr>					
					<tr>
						<td>Brief Description</td>
						<td><textarea name="description" ></textarea></td>
					</tr>	
					
					<tr>
						<td>Upload PDF</td>
						<td><input type="file" name="bookfile"></input> </td>
					</tr>	
					<tr>
						<td>Upload image</td>
						<td><input type="file" name="image"></input> </td>
					</tr>						
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="Upload Book" />
							
						</td>
					</tr>						
						
				</table>
		
		
		</div>
</form>		
   <div style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %>
	
</div>
</body>
</html>