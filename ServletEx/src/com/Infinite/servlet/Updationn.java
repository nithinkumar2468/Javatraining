package com.Infinite.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Updationn
 */
public class Updationn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection on=null;
    public Updationn() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletConfig cg=getServletConfig();
			Class.forName(cg.getInitParameter("mysql"));
			on = DriverManager.getConnection(cg.getInitParameter("mysqlurl"),cg.getInitParameter("mysqlusername"),
					cg.getInitParameter("mysqlpassword"));
			PreparedStatement ps=on.prepareStatement("update student set stdname=? where stdid=?;");
			ps.setString(1,request.getParameter("stdname"));
			ps.setInt(2, Integer.valueOf(request.getParameter("stdid")));
			ps.executeUpdate();
			ps.close();
			response.sendRedirect("Updated.html");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
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
