package com.java.common;

import java.io.IOException;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

public class RMICustomClientSocketFactory implements RMIClientSocketFactory {
	  private int timeout;

	  public void setTimeout(int timeout)
	  {
	    this.timeout = timeout;
	  }
	  
	  public Socket createSocket(String host, int port) throws IOException {
		    Socket socket = new Socket(host, port);
		    socket.setSoTimeout(this.timeout);
		    return socket;
	 }
}
