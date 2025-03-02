package gym2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class deleteRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
				
		try {
			int id= Integer.parseInt(request.getParameter("d"));
			
			GymDAO dao= new GymDAO();
			boolean success=dao.deleteAdmin(id);
			if(success) {
				RequestDispatcher rd=request.getRequestDispatcher("viewRecord.jsp");
				rd.forward(request, response);
			}else {
				out.print("<h2>no record found with this id</h2>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			out.print("<h2>connection ni bna!!!"+e.getMessage()+"</h2>");
		}
	}
}
