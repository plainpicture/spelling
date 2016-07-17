
package com.flakks.spelling;

import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpServer {
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