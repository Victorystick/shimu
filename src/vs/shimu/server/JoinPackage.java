package vs.shimu.server;

import java.io.Serializable;

public class JoinPackage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2595685031181389028L;
	public final String Name;

	
	public JoinPackage(String name) {
		Name = name;
	}
	
	public String getName() {
		return Name;
	}
}
