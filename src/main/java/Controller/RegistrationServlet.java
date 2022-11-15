package Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


import com.mysql.jdbc.Connection;

import ContextDB.UserDBUtil;
import Entity.User;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDBUtil userDBUtil;
	
	//JEE Resource injection : Tomcat va injecter l'objet connection pool et l'injecter à la variable datasource
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
		String firstname=request.getParameter("fname");
		String lastname=request.getParameter("lname");
		String loginid=request.getParameter("lid");
		String password=request.getParameter("psw");
		String dateofbirth=request.getParameter("dob");
		String mobileno=request.getParameter("mobile");
		User u = new User(firstname,lastname,loginid,password,dateofbirth,mobileno);
		
		
		try {
			userDBUtil.register(u);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");//-with-scriptlets.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
