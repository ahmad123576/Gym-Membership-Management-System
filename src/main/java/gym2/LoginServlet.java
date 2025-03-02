package gym2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String upassword=request.getParameter("upassword");
		
		try {
			GymDAO dao=new GymDAO();
			String role=dao.validateUser(uname, upassword);
			
			if(role!=null) {
				
				HttpSession o_session=request.getSession(false);
				if(o_session!=null) {
					o_session.invalidate();
				}
				HttpSession session=request.getSession(true);
				session.setAttribute("uname", uname);
				session.setAttribute("role", role);
				
				if("admin".equals(role)) {
					RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
					rd.forward(request, response);
				}else if("user".equals(role)){
					RequestDispatcher rd=request.getRequestDispatcher("user.jsp");
					rd.forward(request, response);
				}
			}else {
				response.getWriter().print("<h1>invalid username or password</h1>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}
	}

}
