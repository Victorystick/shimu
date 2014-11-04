package vs.shimu.client;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import vs.shimu.Controller;
import vs.shimu.counter.Counter;
import vs.shimu.display.Display;
import vs.shimu.entity.Entity;
import vs.shimu.entity.Player;
import vs.shimu.entity.projectiles.Projectile;
import vs.shimu.server.DataPackage;
import vs.shimu.state.State;
import vs.shimu.state.StateHandler;

public class ClientScreen extends State {
	private DataPackage data;
	private Socket socket;

	public ClientScreen(StateHandler main, Display display, Controller control) {
		super(main, display, control);
		try {
			socket = new Socket(InetAddress.getByName(null), 44444);
		} catch (UnknownHostException e) {
			// TODO Some error message perhaps...
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Some error message perhaps...
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// Abortion check of controls
		boolean[] keys = control.getKeys();
		if (keys[0]) {
			disconnect();
			pop();
			return;
		}
		if (!display.isVisible()) {
			disconnect();
			pop();
			return;
		}
		// send(control.getInputPackage()); //Send controls
		// getDatapackage();
		if (hasReceivedData())
			render();
	}

	private boolean hasReceivedData() {
		// TODO Auto-generated method stub
		return false;
	}

	private void disconnect() {
		// TODO Disconnect from server
	}

	@Override
	public void render() {
		BufferedImage img = new BufferedImage(display.getWidth(), display
				.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		// Actual rendering
		for (Player player : data.getPlayers()) {
			player.render(g);
		}
		for (Entity enemy : data.getEnemies()) {
			enemy.render(g);
		}
		for (Projectile bullet : data.getBullets()) {
			bullet.render(g);
		}
		for (Player player : data.getPlayers()) {
			player.getCrosshair().render(g);
		}
		// TODO Render counters
		g.dispose();
		display.update(img);
	}
}
