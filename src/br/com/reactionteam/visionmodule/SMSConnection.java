package br.com.reactionteam.visionmodule;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.content.Context;
import android.util.Log;

public class SMSConnection {
	/* The socket to the server */
	private Socket connection;

	/* Streams for reading and writing the socket */
	private BufferedOutputStream toServer;

	/* application context */
	Context mCtx;

	/*
	 * Create an SMSConnection object. Create the socket and the associated
	 * streams. Initialize SMS connection.
	 */
	public SMSConnection(Context ctx) throws IOException {
		mCtx = ctx;
		this.open("192.168.1.102", 7777);
		toServer = new BufferedOutputStream(connection.getOutputStream());
	}

	public boolean open(String host, int port) {
		try {
			connection = new Socket(host, port);
			return true;
		} catch (IOException e) {
			Log.v("smswifi", "cannot open connection: " + e.toString());
		}
		return false;
	}

	/* Close the connection. */
	public void close() {
		try {
			connection.close();
		} catch (IOException e) {
			Log.v("smswifi", "Unable to close connection: " + e.toString());
		}
	}

	/*
	 * Send an SMS command to the server. Check that the reply code is what is
	 * is supposed to be according to RFC 821.
	 */
	public void sendCommand(byte[] command) throws IOException {
		this.open("192.168.1.102", 7777);
		toServer = new BufferedOutputStream(connection.getOutputStream());
		Log.v("smswifi", "Tamanho buffer: " + command.length);
		this.toServer.write(command);
		this.toServer.flush();
		this.toServer.close();
		this.close();
	}
}