<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>update kro</title>
  </head>
  <body>
<div class="container my-5">
	<h3>Update you Record</h3>
<%
	String a= request.getParameter("a");
	Integer id= Integer.parseInt(a);
	try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3300/gym2";
	Connection con=DriverManager.getConnection(url,"root","");
	String queryString="Select * from admin where id= ? ";
	PreparedStatement pstmt= con.prepareStatement(queryString);
	pstmt.setInt(1, id);
	ResultSet rs=pstmt.executeQuery();
	if(rs.next()){
%>
	<form action="updateuserRecord2" method="post">
    <input type="hidden" name="id" value="<%= rs.getInt("id")%>">
  <div class="mb-3">
    <label for="name" class="form-label">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="<%= rs.getString("name")%>" readonly>
  </div>
  <div class="mb-3">
    <label for="fname" class="form-label">F_Name</label>
    <input type="text" class="form-control" id="fname" name="fname" value="<%= rs.getString("fname")%>"readonly>
  </div>
  <div class="mb-3">
    <label for="phone" class="form-label">Phone</label>
    <input type="text" class="form-control" id="phone" name="phone" value="<%= rs.getString("phone")%>"readonly>
  </div>
  <div class="mb-3">
    <label for="weight" class="form-label">Weight</label>
    <input type="text" class="form-control" id="weight" name="weight" value="<%= rs.getInt("weight")%>">
  </div>
  <div class="mb-3">
    <label for="height" class="form-label">Height</label>
    <input type="text" class="form-control" id="height" name="height" value="<%= rs.getInt("height")%>">
  </div>
  <button type="submit" class="btn btn-primary" name="edit">Update</button>
</form>
<% 
	}else{
		out.println("<h3>no record for "+id+" id.</h3>");
	}
	pstmt.close();
	con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
%>
</div>


    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
  </body>
</html>
