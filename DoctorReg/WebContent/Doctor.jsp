<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.Doctor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/Home.js"></script>
	<meta charset="ISO-8859-1">

	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Doctor Registration </h1>
				<form id="formDoc" name="formDoc" method="post" action="Doctor.jsp">
					First Name: 
					<input id="firstName" name="firstName" type="text"class="form-control form-control-sm">
					 <br> Last Name:
					<input id="lastName" name="lastName" type="text"class="form-control form-control-sm"> 
					<br> Email: 
					<input id="email" name="email" type="email"class="form-control form-control-sm"> 
					<br> Password: 
					<input id="password" name="password" type="password"class="form-control form-control-sm">
					 <br> Specialty:
					 <input id="specialty" name="specialty" type="text"class="form-control form-control-sm">
					 <br> NIC:
					 <input id="nic" name="nic" type="text"class="form-control form-control-sm">
					 <br> Phone Number:
					 <input id="phoneNo" name="phoneNo" type="text"class="form-control form-control-sm">
					 <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
				<input type="hidden" id="hiddoctorIDSave" name="hiddoctorIDSave" value="">
				</form>


				<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   <div id="divItemsGrid">
   
   <%
   
      Doctor docObj = new Doctor();
      out.print(docObj.readDoc());
   %>
   </div>

			</div>
		</div>
	</div>

</body>
</html>