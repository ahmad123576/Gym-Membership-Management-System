package gym2;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		int id;
		String name, fname, phone;;
		int weight, height;
		
		try {
			id=Integer.parseInt(request.getParameter("id"));
			weight=Integer.parseInt(request.getParameter("weight"));
			height=Integer.parseInt(request.getParameter("height"));
			name=request.getParameter("name");
			fname=request.getParameter("fname");
			phone=request.getParameter("phone");
			
			GymDAO dao=new GymDAO();
			boolean success=dao.registerAdmin(id, name, fname, phone, weight, height);
			
			if(success) {
				RequestDispatcher rd=request.getRequestDispatcher("viewRecord.jsp");
				rd.forward(request, response);
			}else {
				out.print("<h2>failed to register , id is same</h2>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			out.print("<h2>connection ni bna!!!"+e.getMessage()+"</h2>");
		}
	}

}
