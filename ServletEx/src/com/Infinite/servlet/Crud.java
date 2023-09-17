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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Crud
 */
public class Crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection on = null;

       
    
    public Crud() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ServletConfig cg = getServletConfig();
			Class.forName(cg.getInitParameter("mysql"));
			on = DriverManager.getConnection(cg.getInitParameter("mysqlurl"), cg.getInitParameter("mysqlusername"),
					cg.getInitParameter("mysqlpassword"));
			//Statement st=on.createStatement();
			//Scanner s=new Scanner(System.in);
			PreparedStatement ps=on.prepareStatement("Insert into student value(?,?);");
			ps.setString(1,request.getParameter("stdname"));
			ps.setInt(2,Integer.valueOf(request.getParameter("stdid")));
			ps.executeUpdate();
			ps.close();
			response.sendRedirect("insert.html");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try{
				on.close();
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
	}
}
