package org.skilledUpProject;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginPage")
public class CheckPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass2 = req.getParameter("repass");
        String pass = req.getParameter("spass");
        String name = req.getParameter("sname");

        System.out.println(pass2);
        System.out.println(pass);
        System.out.println(name);

        PrintWriter out = resp.getWriter();
        RequestDispatcher rd = null;
        resp.setContentType("text/html");
        
        if (!(pass.equals(pass2))) {
            out.print("<h1 style='color:red'>password not match</h1>");
            rd = req.getRequestDispatcher("signup.html");
            rd.include(req, resp);
        } else {
            rd = req.getRequestDispatcher("/loginPage2");
            rd.include(req, resp);
        }
        
        
        
        out.println("<a href='signup.html'>skip</a>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
