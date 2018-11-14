package com.austral.spaceinvaders.input;

//@WebService
//@SOAPBinding(style = Style.RPC)
public interface GameClientSocketAdapter {

	//@WebMethod
	void keyPressReceived(String playerAuthToken, char key);

	//@WebMethod
	void keyReleaseReceived(String playerAuthToken, char key);

}
