<%@page import="com.elibrary.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/menu.css"  />

</head>
<body >
	<div style="background-image:url(images/header.jpg);height:200px">
	<div align="right">
	<%if(session.getAttribute("userloggedin")!=null) {%>
	<%
		User loggedinuser =(User)session.getAttribute("userloggedin");
	%>
	
	
	   <a color="red" href="LogoutServlet" ><font size="5px" style="color:#9FFA09">Log out</font></a>	 &nbsp;&nbsp; 
	   Welcome &nbsp;&nbsp; <a href="useraccount.jsp"><font size="5px" style="color:#9FFA09">  <%=loggedinuser.getUserName() %></font></a>
	<%}else{ %>
		<a  href="login.jsp"> <font size="5px" style="color:#9FFA09">login </font></a>
	
	<%} %>
	</div>
	<!-- <div style="vertical-align: middle;"
		align="center">
		<table style="width:100%" border="0">	
		<td width="50%"> 
		<div align="left">
		<a href="/home.jsp"><img src="icon-elibrary.png"></a>
		</td>
		<tr>	<td width="50%">
		<div align="right">
		 -->
									
</div>					        <!-- THE SEARCHING PART -->
	


	<hr>
<div class="dropdown">
  <a href="TopBookServlet"><button class="dropbtn">Home</button></a>
</div>
<div class="dropdown">
  <a href="about.jsp"><button class="dropbtn">About</button></a>
</div>
 <div class="dropdown">
  <button class="dropbtn">Browse</button>
  <div class="dropdown-content">
    <a href="./SearchBookServlet">All Books</a>
    <a href="./SearchBookServlet?type=Drama" >Drama</a>
    <a href="./SearchBookServlet?type=Fiction">Fiction</a>
    <a href="./SearchBookServlet?type=Romance">Romance</a>
    <a href="./SearchBookServlet?type=Sci-fi Fantasy">Sci-fi Fantasy</a>
    <a href="./SearchBookServlet?type=Horror">Horror</a>
  </div>
</div>
<div class="dropdown">
  <button class="dropbtn">Contribute</button>
  <div class="dropdown-content">
  	<% if(session.getAttribute("userloggedin")!=null) {%>
    <a href="./uploadbook.jsp">Upload Book</a>
   <%}else{ 
	   ///alert( "Please  Login to upload" );%>
   <button style="width:160px" type="button" onclick="alert('Please, Login to Upload!')"><a href="login.jsp">Upload Book</a></button>
   
   <%} %>
  </div>
</div>
<div align="right">
	<form align="right" action="SearchServlet" align=right>
	<input type="text" name="title" placeholder="Book Name">&nbsp;
	<input type="text" name="author" placeholder="Author Name">
	<input type="Submit" value="Search">
	</form>
</div>

<br>


<br>
</body>
</html>