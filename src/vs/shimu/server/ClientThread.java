package vs.shimu.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientThread extends Thread {

	MainServer server;
	Socket socket;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	Logger logger;
	boolean loggedIn;

	public ClientThread(MainServer server, Socket socket) {
		Logger logger = Logger.getLogger(server.getPlayer(socket)
				+ "_SocketThread");
		this.server = server;
		this.socket = socket;

		loggedIn = false;

		try {
			oout = new ObjectOutputStream(socket.getOutputStream());
			oin = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO What if not connected yet?
			logger
					.severe("IOException when attempting to open output or input-streams to client: "
							+ server.getPlayer(socket));
		}
		start();
	}

	@Override
	public void run() {
		// TODO Code the stuff to do every frame;
	}

	private void sendCode(int i) {
		try {
			oout.writeObject(i);
		} catch (IOException e1) {
			// TODO If something goes wrong
			// logger.severe("IOException when attempting to send " + i +
			// " to client: " + server.getPlayer(socket));
			return;
		}
	}

}
