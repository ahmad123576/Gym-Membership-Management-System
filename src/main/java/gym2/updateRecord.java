package gym2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class updateRecord extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		int id;
		String name, fname, phone;
		int weight, height;
		try {
		id=Integer.parseInt(request.getParameter("id"));
		name=request.getParameter("name");
		fname=request.getParameter("fname");
		phone=request.getParameter("phone");
		weight=Integer.parseInt(request.getParameter("weight"));
		height=Integer.parseInt(request.getParameter("height"));
		
		GymDAO dao=new GymDAO();
		boolean success=dao.updateAdmin(id, name, fname, phone, weight, height);
		
		if(success) {
			RequestDispatcher rd=request.getRequestDispatcher("viewRecord.jsp");
			rd.forward(request, response);
		}else {
			out.print("<h3>no record found for this id</h3>");
		}
		}catch(Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}
	}

}
