
package com.flakks.spelling;

import org.eclipse.jetty.server.Server;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpServer {
	/*
	class Handler extends AbstractHandler {
	    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        response.setContentType("application/json; charset=utf-8");
	        response.setStatus(HttpServletResponse.SC_OK);
	        baseRequest.setHandled(true);
	        
	        response.getWriter().println(new RequestHandler(request).process());
	    }
	}*/
	
	public void start() throws Exception {
        Server server = new Server(8888);
        
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);
        
        context.addServlet(new ServletHolder(new CorrectServlet()), "/correct");
        context.addServlet(new ServletHolder(new SuggestServlet()), "/suggest");

        server.start();
        server.join();
	}
}