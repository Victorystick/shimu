package vs.shimu.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import vs.shimu.Controller;
import vs.shimu.display.Display;
import vs.shimu.state.State;
import vs.shimu.state.StateHandler;

public class ClientMenu extends State {
	private JPanel panel;
	private JTextField username;
	private JTextField serverIP;
	private Socket socket;
	
	public ClientMenu(StateHandler main, Display display, Controller control) {
		super(main, display, control);
		panel = display.getScreen();
		panel.setLayout(new BorderLayout());
		username = new JTextField(15);
		// JPanel player = new JPanel();
		// JPanel server = new JPanel();
		// player.setBorder(BorderFactory.createTitledBorder(
		// BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Player"));
		// player.setPreferredSize(new Dimension(display.getWidth()/2, 0));
		// server.setBorder(BorderFactory.createTitledBorder(
		// BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Server"));
		// server.setPreferredSize(new Dimension(display.getWidth()/2, 0));
		// player.add(username);
		//		
		// panel.add(player, BorderLayout.WEST);
		// panel.add(server, BorderLayout.EAST);
		// panel.add(BorderLayout.PAGE_END);
		display.pack();
	}

	@Override
	public void update() {
		boolean[] keys = control.getKeys();
		if (keys[0]) {
			pop();
			System.out.println("Escape pressed: Popping");
			return;
		}
		if (!display.isVisible())
			pop();
		render();
	}

	@Override
	public void render() {
	}

	private boolean connectTo(String serverName){
		try {
			socket = new Socket("orange-18.csc.kth.se",44444);
			return true;
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
}
