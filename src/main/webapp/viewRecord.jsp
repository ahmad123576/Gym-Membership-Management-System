<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
    <%@page import="java.io.*" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>records view</title>
  </head>
  <body>
    <nav class="navbar navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand">GYM</a>
    <form class="d-flex">
      <input class="form-control me-2" type="search" name="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="container my-5">
<a href="index.jsp" class="btn btn-primary">Register New Member</a>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">F_NAME</th>
      <th scope="col">PHONE</th>
      <th scope="col">WEIGHT</th>
      <th scope="col">HEIGHT</th>
      <th scope="col">UPDATE</th>
      <th scope="col">DELETE</th>
    </tr>
  </thead>
  <tbody>
    <%
    	try{
    		Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3300/gym2";
			Connection con=DriverManager.getConnection(url,"root","");
			String search=request.getParameter("search");
			String queryString;
			if(search!=null && !search.trim().isEmpty()){
				queryString="Select * from admin where name like ?";
			}else{
				queryString="Select * from admin";
			}
			PreparedStatement pstmt= con.prepareStatement(queryString);
			if(search!=null && !search.trim().isEmpty()){
				pstmt.setString(1, "%"+search+"%");
			}
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){	
    %>
    <tr>
    <td><%=  rs.getInt("id") %></td>
    <td><b><%= rs.getString("name") %></b></td>
    <td><b><%= rs.getString("fname") %></b></td>
    <td><b><%= rs.getString("phone") %></b></td>
    <td><b><%= rs.getInt("weight") %></b></td>
    <td><b><%= rs.getInt("height") %></b></td>
    <td>
    <a href="update_record.jsp?u=<%=rs.getInt("id") %>" class="btn btn-success">Update</a>
    </td>
    <td>
    <form action="deleteRecord?d=<%= rs.getInt("id") %>" method="post">
    <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
    <input type="submit" name="delete" value="Delete" class="btn btn-danger">
    </form>
    </td>
    </tr>
	<% 	}
			rs.close();
			pstmt.close();
    }catch(Exception e){
    	e.printStackTrace();
    }		
    %>
    </tbody>
</table>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>