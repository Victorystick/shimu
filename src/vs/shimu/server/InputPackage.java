package vs.shimu.server;

import java.io.Serializable;

public class InputPackage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8857206714604677876L;
	final boolean[] keys;
	final int[] mouse;
	
	public InputPackage(boolean[] keys, int[] mouse) {
		this.keys = keys;
		this.mouse = mouse;
	}
	
	public int getMouse(int i) {
		return mouse[i];
	}
	
	public boolean getKey(int i) {
		return keys[i];
	}

}
