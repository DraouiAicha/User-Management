package ContextDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import java.sql.Connection;
import java.sql.Statement;

import Entity.User;



public class UserDBUtil {
	
	private DataSource dataSource;
	public UserDBUtil(DataSource theDataSource)
	{
		dataSource=theDataSource;
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void register(User u) throws SQLException
	{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO user VALUES(NULL,?,?,?,?,?,?)";
					   
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, u.getFirstName());
			myStmt.setString(2, u.getLastName());
			myStmt.setString(3, u.getLoginId());
			myStmt.setString(4, u.getPassword());
			myStmt.setString(5, u.getDateOfBirth());
			myStmt.setString(6, u.getMobileN());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}	
	}
	public boolean login(User u) throws SQLException
	{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		boolean status=false;
		ResultSet rs=null;
		try 
		{
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "SELECT id,fname FROM user WHERE login=? and password=?";
					   
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the student
			myStmt.setString(1, u.getLoginId());
			myStmt.setString(2, u.getPassword());
			rs=myStmt.executeQuery();
			status=rs.next();

		}
		finally {
			close(myConn,myStmt,rs);
		}
		return status;
		
		
	}
}
