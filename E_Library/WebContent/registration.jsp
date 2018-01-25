<html>
	<head>
		<title>Registration Page</title>
		<script type="text/javascript">
			function check_info(){
				var name = document.getElementById('name').value;
				var user_id = document.getElementById('user_id').value;
				var password = document.getElementById('password').value;
				var yes_checked = document.getElementById('male').checked;
				var no_checked = document.getElementById('female').checked;
				var address = document.getElementById('address').value;
				if(name=="" || user_id=="" || password=="" || (yes_checked==false && no_checked==false) || address==""){
					alert("Please fill all the required fields");
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
	<DIV align="center">

			<br> 
			<font size="20">Please, enter the registration details..</font><br><br><br>
		<FORM action="Registration" method="POST" enctype="multipart/form-data" onsubmit="return check_info();">
		   	<table border=0 width="60%" cellspacing="20">
	
				<tr>
				   <td> Enter Name </td>
				   <td> <input type="Text" name="name" id="name"/> </td>
		
				</tr>
				
				<tr>
				   <td> Enter User ID </td>
				   <td> <input type="Text" name="userid" id="user_id"/> </td>
		
				</tr>

				<tr>
				   <td> Enter Password</td>
				   <td> <input type="password" name="password" id="password"/> </td>
		
				</tr>


				<tr>
				   <td> Select Gender</td>
				   <td> 
                                <input type="radio" name="gender" value='m' id="male"> Male </input>
				<input type="radio" name="gender" value='f' id="female"> Female </input>
					 </td>
		
				</tr>


				<tr>
				   <td> Select your City</td>
				   <td> <select name="city"> 
 					<option value="Kolkata">Kolkata</option>
					<option value="Malda">Malda</option> 


					</select>
				</td>
				</tr>


				<tr>
				   <td> Enter Address</td>
				   <td> <TextArea name="address" row="20" id="address"> </textArea>
				</td>
		
				</tr>
				
				<tr>
				   <td> Profile Image(optional)</td>
				   <td> <input type="file" name="image" value="Browse" /> </textArea>
				</td>
		
				</tr>

				<tr>
					<td colspan="2" align="center">
			
						&nbsp;

					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
			
					<input type="Submit" value="SAVE" /> &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="Reset" /> &nbsp;&nbsp;&nbsp;&nbsp;
					<a href="TopBookServlet"><input type="button" value="CANCEL" /></a> &nbsp;&nbsp;&nbsp;&nbsp;

				<br><br> Alredy Registered? Please,<a href="login.jsp" > click here </a> to login.
					</td>
				</tr>
				
		   	</table>		

		<FORM>

	<DIV>




<footer style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %> 
	
</footer>


	</body>
</html>