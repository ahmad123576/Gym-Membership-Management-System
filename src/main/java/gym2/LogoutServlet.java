package gym2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session!=null) {
		session.invalidate();
		}
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}


}
