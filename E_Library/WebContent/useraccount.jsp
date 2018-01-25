<%@page import="com.elibrary.model.User"%>
<html>
	<head>
		<title>Account Information</title>
		
	<style type="text/css">
	
		.inputbutton {
			background-color: red;
		}
		
		
	</style>	
		
	</head>
	<body>
	<div style="header">
	
		<%@include file="header.jsp" %>
	
	</div>
 
	<DIV align="center" >
	  <% User loggedinuser =(User)session.getAttribute("userloggedin"); %>
			<br> 
			<font size="20">User Account Specific Details</font><br><br><br>
		<FORM action="TopBookServlet" method="POST">
		  <table>
		  		<tr>
		  			<td width="70%">
		  				
		  				<table border=0 width="60%" cellspacing="20">
	
				<tr>
				   <td> User Name </td>
				   <td> <input type="Text" name="username" value="<%=loggedinuser.getUserName() %>" /> </td>
		
				</tr>
				
				<tr>
				   <td> User ID </td>
				   <td> <input type="Text" name="userid" value="<%=loggedinuser.getUserID() %>" readonly="readonly"/> </td>
		
				</tr>

				

				<tr>
				   <td>  Gender</td>
				   <td> 
                <input type="radio" name="gender" value="M"> Male </input>
				<input type="radio" name="gender" value="F"> Female </input>
					 </td>
		
				</tr>


				<tr>
				   <td> City</td>
				   <td> <select name="city"> 
 					<option value="Kolkata">Kolkata</option>
					<option value="Malda">Malda</option> 


					</select>
				</td>
				</tr>


				<tr>
				   <td> Address</td>
				   <td> <TextArea name="address" row="20" > <%=loggedinuser.getAddress() %></textArea>
				</td>
		
				</tr>

				<tr>
					<td colspan="2" align="center">
			
						&nbsp;

					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
			
					<input type="Submit" value="Ok" class="inputbutton" /> &nbsp;&nbsp;&nbsp;&nbsp;
				
					</td>
				</tr>
				
		   	</table>
		  				
		  			</td>
		  			<td width="30%">
		  				<img alt="Profile Image" src="ImageServlet" width="300" height="400">
		  					
		  			
		  			</td>
		  		
		  		 </tr>
		  
		  </table> 
		   
		   			

		<FORM>

	<DIV>




<div style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %>
	
</div>


	</body>
</html>