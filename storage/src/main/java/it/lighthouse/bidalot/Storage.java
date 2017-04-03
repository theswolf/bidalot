package it.lighthouse.bidalot;

import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;

public class Storage {

	OServer server;
	public void startServer() throws Exception {
		server = OServerMain.create();
		server = server.startup(getClass().getClassLoader().getResourceAsStream("db.config.xml"));
		server.activate();
		System.out.println("Server started");
	}
	
	public void stopServer() {
		server.shutdown();
	}

	/*public static void main(String[] args) throws Exception {
		Storage app = new Storage();
		app.startServer();
	}*/
}
