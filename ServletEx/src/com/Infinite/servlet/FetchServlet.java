package com.Infinite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displayemploye
 */
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String URL="jdbc:mysql://localhost:3306/training";
	final String USER="root";
	final String PASSWORD="mysql123@";
	final String DRIVER="com.mysql.jdbc.Driver";
	
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() throws ServletException{
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	public FetchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection on = null;

		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<h3>Employee Details<h3>");
			out.println("<table border=1><tr>"+"<td><b>Emp Name></b></td>"+"<td><b>Emp id</b></td>");
			Statement stmt = on.createStatement();
			String sql="select * from employee;";
			ResultSet rs = stmt.executeQuery(sql);
			//System.out.println("fullname:\t idno:");
			while (rs.next()) {
				String empname=rs.getString("empname");
				//int empid=rs.getInt("Empid");
				
				out.println("<tr>"+"<td>"+empname+"</td>"+"</tr>");
			}
			out.println("</table></body></html>");
			rs.close();
			stmt.close();
			out.close();

		    } catch (Exception e1) {
			System.out.println(e1);
		    }
            finally {
			try {
				on.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		    }

	}

}
