package vs.shimu.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import vs.shimu.util.Dimension;
import vs.shimu.entity.Player;
import vs.shimu.logic.ServerLogic;
public class MainServer {
	private ServerLogic logic;
	private Dimension display;
	private Logger logger;
	private ServerSocket sSocket;
	private List<ClientThread> clientThreads;
	
	private MainServer() {
		try {
			System.out.println(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Eventually remove
			e.printStackTrace();
		}
		display = new Dimension(400, 300);
		logic = new ServerLogic(display);
		logger = Logger.getLogger("ShimuServer");
		clientThreads = new ArrayList<ClientThread>();
		
		new Thread(logic).start();
	}
	
	/** MAIN **/
	public static void main(String[] args) {
		MainServer main = new MainServer();
		main.getConnections();
	}

	public void getConnections() {
		try {
			sSocket = new ServerSocket(44444);
			logger.info("Server started!");
			while (true) {
				Socket nClientSocket = sSocket.accept();
				logger.info("CHALLENGER APPROACHES! GET READY 2 FUMBLE!");
				ClientThread ct = new ClientThread(this, nClientSocket);
				clientThreads.add(ct);
			}
		} catch (IOException e1) {
			logger.warning("Could not bind to port!");
			e1.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	public Player getPlayer(Socket socket) {
		return logic.getPlayer(socket);
	}

	public DataPackage getGameDataPackage() {
		// TODO Add caching to speed up process
		// 		This will probably be too slow
		//		when dealing with several players.
		return new DataPackage(logic.getTime(), logic.getPlayers(), logic.getProjectiles(), logic.getEnemies());
	}

	public void processInput(ClientThread connectionHandelerThread,
			InputPackage input) {
		// TODO Auto-generated method stub
		
	}
}
