package org.training.kafka.spring.advanced.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/v1/servlet/myservlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) throws ServletException, IOException {
        String nameLoc = req.getParameter("name");
        resp.addHeader("Content-Type", "text/plain;charset=UTF-8");
        resp.getWriter().println("Hello " + nameLoc);
    }
}
