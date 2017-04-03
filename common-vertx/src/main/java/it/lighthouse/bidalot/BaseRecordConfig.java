package it.lighthouse.bidalot;

public class BaseRecordConfig {

	private String name;
	private String host;
	private int port;
	private String root;
	
	
	
	public BaseRecordConfig(String name, String host, int port, String root) {
		super();
		this.name = name;
		this.host = host;
		this.port = port;
		this.root = root;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	
	
}
