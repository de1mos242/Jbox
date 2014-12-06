package net.de1mos.jbox.media;


/**
 * Configuration object of connecting to Icecast server
 */
public class GstreamerConfig {
	
	public GstreamerConfig(String ip, String port, String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.password = password;
	}

	/** Ip address of icecast server **/
	public String ip;
	
	/** Port of icecast server **/
	public String port;
	
	/** Admin password of icecast server **/
	public String password;
}
