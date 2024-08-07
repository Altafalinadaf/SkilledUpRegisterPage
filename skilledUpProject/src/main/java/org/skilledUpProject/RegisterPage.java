package org.skilledUpProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginPage2")

public class RegisterPage extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("sname");
		String email = req.getParameter("semail");
		String phone = req.getParameter("sphone");
		String address = req.getParameter("sadd");
		String password = req.getParameter("spass");

		PrintWriter out = resp.getWriter();

		PreparedStatement ps = null;
		Connection con = null;

		resp.setContentType("text/html");
		
		 RequestDispatcher rd = null;
	    resp.setContentType("text/html");

		try {
			// loading and registering the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// connecting with the database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skilledUp", "root", "admin");

			// prepared SQL statement or platform
			ps = con.prepareStatement("insert into student(sName,sEmail,sPhone,sAdd,sPass)values(?,?,?,?,?)");

			// setting all the values to the table
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, password);

			// executing the query return type is Int (counts how many rows are effected )
			int count = ps.executeUpdate();
			if (count > 0) {
				out.println("<h1 style='color:green'>Register Successfully</h1>");
				rd=req.getRequestDispatcher("signup.html");
				rd.include(req, resp);
			} else {
				out.printf("<h2 style=color:'red'>Not Register Successfully</h2>");
				rd=req.getRequestDispatcher("signup.html");
				rd.include(req, resp);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			out.print("<h2 style='color:red'>exception occure " + e.getMessage() + "</h2>");
		} finally {
			try {
				if (con != null && ps != null && out != null) {
					con.close();
					ps.close();
					out.close();
				}
			} catch (Exception e) {
				out.print("<h2 style='color:red'>exception occure " + e.getMessage() + "</h2>");
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
