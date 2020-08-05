package com.korchagin.java.courses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FirstFormResultsServlet", urlPatterns = "/formResults.html")
public class FirstFormResultsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String middleName = req.getParameter("middleName");
        String birthdate = req.getParameter("birthdate");
        String city = req.getParameter("city");

        out.printf("<table border = \"1\">" +
            "<tr><td>First Name</td><td>" + firstName + "</td></tr>" +
            "<tr><td>Second Name</td><td>" + secondName + "</td></tr>" +
            "<tr><td>Middle Name</td><td>" + middleName +"</td></tr>" +
            "<tr><td>Birthdate</td><td>" + birthdate + "</td></tr>" +
            "<tr><td>City</td><td>" + city + "</td></tr>" +
        "</table>");
        out.close();
    }
}
