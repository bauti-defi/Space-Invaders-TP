package com.austral.spaceinvaders.controllers;

public interface ServerAdapter {

	//@WebMethod
	boolean handshake(String playerAuthToken, String playerName);

}
