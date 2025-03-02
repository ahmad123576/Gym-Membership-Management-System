package gym2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class updateuserRecord2 extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				RequestDispatcher rd=request.getRequestDispatcher("viewuserRecord2.jsp");
				rd.forward(request, response);
			}else {
				out.println("<h3>no record for "+id+" id.</h3>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}

}
