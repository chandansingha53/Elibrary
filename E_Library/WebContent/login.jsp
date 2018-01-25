<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    
<html>
	<head>
		<title> e-Library Application Login Page </title>
		<script type="text/javascript">
			function check_info(){
				var username = document.getElementById('username').value;
				var password = document.getElementById('password').value;
				if(username=="" || password==""){
					alert("Please fill all the fields");
					return false;
				}
				else{
					return true;
				}
			}
		</script>
	</head>
	<body style="background-color:#DAF7A6">
	<header>
	
		<%@include file="header.jsp" %> 
	
    </header>

<%!
	String msg=""; 

%>

<%
	msg = (String)request.getAttribute("key");  

	if(msg == null)
		msg = "";

%>

	<table width="100%" border="0">
		<tr>
		<td width="40%">
	<div align="right" width=100%>		
	
	
	<form action="login" method="POST" onsubmit="return check_info();">
	
	
		<img height="300" src="images/eLibrary_login.jpg" />
	</div>	
		</td>

		<td width="60%">
	<div align="right" width="100%">
	  	<font size="20" color="RED" > <%=msg %>	  </font>
	  	
		<br />
	       <h3>Please, enter your user and password to log in into the system</h3>
		<br />		<br />		<br />
		
		Please enter User Name <input type="text" name="user" id="username"/>

		<br /><br /><br />

		Please enter Password <input type="password" name="password" id="password"/> 

		<br /><br /><br />

<a href="registration.jsp" > <input type="button" value="New User" style="background-color:blue;color:white"/> </a> &nbsp;&nbsp;&nbsp;&nbsp;

		<input type="submit" value="LOG IN" style="background-color:blue;color:white"  />

	</form>
	</div>
		 

	    </div>
		</td>
	   </tr>
	</table>
</div>
<footer style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %> 
	
</footer>
	</body>

</html>