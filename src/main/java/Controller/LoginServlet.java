package Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import ContextDB.UserDBUtil;
import Entity.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDBUtil userDBUtil;
       
	@Resource(name="jdbc/tp1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			userDBUtil = new UserDBUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String loginId=request.getParameter("lid");
		String password=request.getParameter("psw");
		User u=new User();
		u.setLoginId(loginId);
		u.setPassword(password);
		
		try {
			if(userDBUtil.login(u))
			{
				session.setAttribute("lid", loginId);
				response.sendRedirect("welcome.jsp");   
			}
			else
			{
				response.sendRedirect("errorpage.jsp");    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
