package org.latinoware.geodojo.hsqldb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hsqldb.Server;

/**
 * Listener de aplicacao para iniciar um servidor HSQLDB em memoria
 * @author Rafael Soto
 *
 */
public class DatabaseListener implements ServletContextListener {

	private final Server server;

	
	public DatabaseListener() {
		server = new Server();
		server.setDatabaseName(0, "geodojodb");
		server.setDatabasePath(0, "db/geodojodb");
		server.setPort(9001);
		server.setSilent(false);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		server.stop();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		server.start();
	}

}
