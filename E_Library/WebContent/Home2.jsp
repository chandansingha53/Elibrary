<%@page import="com.elibrary.model.Books"%>
<%@page import="java.util.Iterator"%>


<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" name="viewport" content="text/html,width=device-width, initial-scale=1; charset=ISO-8859-1">
<title>e-Lirary Web</title>
 <link rel="stylesheet" type="text/css" href="./css/menu.css"  />
 
<style>
* {box-sizing:border-box}
body {font-family: Verdana,sans-serif;}
.mySlides {display:none}

div a:hover{
  opacity: 0.6;
}

.top a{
	float:left;
	margin:20px;
}


/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 13px;
  width: 13px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}
</style>
 
 
</head>
<body style="background-color:#DAF7A6"><!--style="background-image:url(./images/home.jpg);height: 100%;background-position: center;background-repeat: no-repeat;
background-size: cover;height:100%"-->

<header>
	
		<%@include file="header.jsp" %>
	
</header>

<div class="slideshow-container">

<div class="mySlides fade">
  <div class="numbertext">1 / 3</div>
  <img src="images/wall11.jpg" style="width:100%">
  <div class="text">BAD LIBRARIES BUILD COLLECTIONS, GOOD LIBRARIES BUILD SERVICES, GREAT LIBRARIES BUILD COMMUNITIES.</div>
</div>

<div class="mySlides fade">
  <div class="numbertext">2 / 3</div>
  <img src="images/wallpaper1.jpg" style="width:100%">
  <div class="text">BOOKS ALLOW YOU TO FULLY EXPLORE A TOPIC AND IMMERSE YOURSELF IN A DEEPER WAY THAN MOST MEDIA TODAY.</div>
</div>

<div class="mySlides fade">
  <div class="numbertext">3 / 3</div>
  <img src="images/wall7.jpg" style="width:100%">
  <div class="text">WE NOTICE EBOOK READERS, WE DON'T NOTICE BOOKS.</div>
</div>

</div>
<br>

<div style="text-align:center">
  <span class="dot"></span> 
  <span class="dot"></span> 
  <span class="dot"></span> 
</div>

<script>
var slideIndex = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
       slides[i].style.display = "none";  
    }
    slideIndex++;
    if (slideIndex> slides.length) {slideIndex = 1}    
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";  
    dots[slideIndex-1].className += " active";
    setTimeout(showSlides, 5000); // Change image every 2 seconds
}
</script>


<br><br><br><br><br><br>
<div style="width:100%;height:500px;background-repeat:no-repeat;position:relative" class="top"> 
	<%  
		String rated1 = (getServletConfig().getServletContext().getInitParameter("rated1")); 
		String rated2 = (getServletConfig().getServletContext().getInitParameter("rated2")); 
		String rated3 = (getServletConfig().getServletContext().getInitParameter("rated3")); 
		String rated4 = (getServletConfig().getServletContext().getInitParameter("rated4")); 
		String rated5 = (getServletConfig().getServletContext().getInitParameter("rated5")); 
	%>
	<%
		ArrayList<Books> booksList = (ArrayList<Books>)session.getAttribute("BOOKS_LIST");
		if(booksList!=null && booksList.size()>0){
			Iterator<Books> iter = booksList.iterator();
	%>
	
	<h4 align="center">BEST RATED</h4>
	
	<% 		
			while(iter.hasNext()){
				Books books = iter.next();
				if(books.getId()==Integer.parseInt(rated1)){					
	%>
					<a href="./SearchBookServlet?bookid=<%= rated1 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= rated1 %>" width="180" height="250"></a>
	<% 
				continue;}
				if(books.getId()==Integer.parseInt(rated2)){		
	%>
					<a href="./SearchBookServlet?bookid=<%= rated2 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= rated2 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(rated3)){	
	%>
					<a href="./SearchBookServlet?bookid=<%= rated3 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= rated3 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(rated4)){	
	%>
					<a href="./SearchBookServlet?bookid=<%= rated4 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= rated4 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(rated5)){	
	%>
					<a href="./SearchBookServlet?bookid=<%= rated5 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= rated5 %>" width="180" height="250"></a>
	<%
				continue;}
			}
		}
	%>
</div>

<div style="width:100%;height:500px;background-repeat:no-repeat;position:relative" class="top"> 
	<%  
		String popular1 = (getServletConfig().getServletContext().getInitParameter("popular1")); 
		String popular2 = (getServletConfig().getServletContext().getInitParameter("popular2")); 
		String popular3 = (getServletConfig().getServletContext().getInitParameter("popular3")); 
		String popular4 = (getServletConfig().getServletContext().getInitParameter("popular4")); 
		String popular5 = (getServletConfig().getServletContext().getInitParameter("popular5")); 
	%>
	<%
		ArrayList<Books> bookList = (ArrayList<Books>)session.getAttribute("BOOKS_LIST");
		if(booksList!=null && booksList.size()>0){
			Iterator<Books> iter = bookList.iterator();
	%>
	
	<h4 align="center">POPULAR</h4>
	
	<% 		
			while(iter.hasNext()){
				Books books = iter.next();
				if(books.getId()==Integer.parseInt(popular1)){	
	%>
					<a href="./SearchBookServlet?bookid=<%= popular1 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= popular1 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(popular2)){
	%>
					<a href="./SearchBookServlet?bookid=<%= popular2 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= popular2 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(popular3)){
	%>
					<a href="./SearchBookServlet?bookid=<%= popular3 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= popular3 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(popular4)){
	%>
					<a href="./SearchBookServlet?bookid=<%= popular4 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= popular4 %>" width="180" height="250"></a>
	<%
				continue;}
				if(books.getId()==Integer.parseInt(popular5)){
	%>
					<a href="./SearchBookServlet?bookid=<%= popular5 %>"><img alt="Book Not Available..!" src="BookImageHomeServlet?bookid=<%= popular5 %>" width="180" height="250"></a>
	<%
				continue;}
			}
		}
	%>
</div>



<div style="position: relative;vertical-align: bottom;" >
	
		<%@include file="footer.jsp" %>
	
</div>
</body>
</html>