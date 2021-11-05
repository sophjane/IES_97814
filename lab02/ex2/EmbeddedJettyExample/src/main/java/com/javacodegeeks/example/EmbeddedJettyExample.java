package com.javacodegeeks.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.PrintWriter;

public class EmbeddedJettyExample {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8680);

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(HelloServlet.class, "/");

        server.start();
        server.join();

    }

    public static class HelloServlet extends HttpServlet
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            //response.getWriter().println("<h1>New Hello Simple Servlet</h1>");

            PrintWriter out = response.getWriter();
            out.println("<h3>Hello");

            String userName = request.getParameter("username");

            if(userName != null) {
                out.println("" + HTMLFilter.filter(userName) + "</h3>");
            } else {
                out.println("World</h3>");
            }
            out.println("<P>");
            out.print("<form action=\\");
            out.println("method=POST>");
            out.println("Username:");
            out.println("<input type=text size=20 name=username>");
            out.println("<br>");
            out.println("<input type=submit>");
            out.println("</form>");
        }
    }
}