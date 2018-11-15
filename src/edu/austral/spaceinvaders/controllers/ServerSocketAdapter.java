package edu.austral.spaceinvaders.controllers;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServerSocketAdapter {

	@WebMethod
	int handshake(int playerAuthToken, String playerName);

}
