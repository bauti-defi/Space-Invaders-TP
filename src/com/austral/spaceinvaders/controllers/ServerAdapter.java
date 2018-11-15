package com.austral.spaceinvaders.controllers;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServerAdapter {

	@WebMethod
	boolean handshake(String playerAuthToken, String playerName);

}
