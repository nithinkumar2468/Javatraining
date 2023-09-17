package com.Infinite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchServlet1
 */
public class FetchServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection on = null;

	
	public void init() throws ServletException {

		try {

			ServletConfig cg = getServletConfig();
			Class.forName(cg.getInitParameter("mysql"));
			on = DriverManager.getConnection(cg.getInitParameter("mysqlurl"), cg.getInitParameter("mysqlusername"),
					cg.getInitParameter("mysqlpassword"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public FetchServlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h3>Employee Details<h3>");
			out.println("<table border=1><tr>" + "<td><b>Emp Name</b></td>" + "<td><b>Emp id</b></td>");
			Statement st = on.createStatement();
			ResultSet rs = st.executeQuery("select * from employee");
			while (rs.next()) {
				String name = rs.getString("empname");
				int empid = rs.getInt("Empid");

				out.println("<tr>" + "<td>" + name + "</td>" + "<td>" + empid + "</td>" + "</tr>");
			}
			out.println("</table></body></html>");
			rs.close();
			st.close();
			out.close();
		} catch (Exception e1) {
			System.out.println(e1);
		} finally {
			try {
				on.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
