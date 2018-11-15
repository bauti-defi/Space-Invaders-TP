package com.austral.spaceinvaders.input;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface GameClientSocketAdapter {

	@WebMethod
	void keyPressReceived(String playerAuthToken, char key);

	@WebMethod
	void keyReleaseReceived(String playerAuthToken, char key);

}
