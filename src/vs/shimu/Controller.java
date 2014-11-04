package vs.shimu;

import java.awt.event.*;

import vs.shimu.server.InputPackage;

public class Controller implements KeyListener, MouseListener,
MouseMotionListener {
	private boolean[] keys;
	private int[] mouse;

	public Controller() {
		keys = new boolean[8];
		mouse = new int[4];
	}

	public boolean[] getKeys() {
		return keys;
	}

	public int[] getMouse() {
		return mouse;
	}

	public InputPackage getInputPackage(){
		return new InputPackage(keys, mouse);
	}

	/**
	 * Converts a keyCode into that keys number in the <code>keyPressed</code>
	 * boolean array.
	 * 
	 * @param keyCode
	 *            int of the pressed key
	 * @return the number of the key being pressed
	 */
	private int convertKey(KeyEvent key) {
		// System.out.println(key.getKeyCode());
		switch (key.getKeyCode()) {
		case 27:
			return 0; // ESC menu
		case 65:
			return 1; // A left
		case 68:
			return 2; // D right
		case 87:
			return 3; // W up
		case 83:
			return 4; // S down
		case 82:
			return 5; // R reset
		case 76:
			return 6; // L ?
		case 32:
			return 7; // SPACE ?
		default:
			return -1;// UNKNOWN KEY
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		int num = convertKey(key);
		if (num != -1) {
			keys[num] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		int num = convertKey(key);
		if (num != -1) {
			keys[num] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// Do nothing
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse[0] = e.getX();
		mouse[1] = e.getY()-27;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse[0] = e.getX();
		mouse[1] = e.getY()-27;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Do nothing

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Do nothing

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Do nothing

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		switch (arg0.getButton()) {
		case MouseEvent.BUTTON1: mouse[2] = 1; 
		break;
		case MouseEvent.BUTTON3: mouse[3] = 1; 
		break;
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		switch (arg0.getButton()) {
		case MouseEvent.BUTTON1: mouse[2] = 0; break;
		case MouseEvent.BUTTON3: mouse[3] = 0; break;
		}
	}
}
