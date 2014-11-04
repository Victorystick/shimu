package vs.shimu.entity.hostiles;

import java.awt.Color;
import vs.shimu.entity.Shooter;

public class Boss extends ShootingHostile implements Shooter {

	public static final int SIZECONST = 10;
	public static final int SIZECOEFF = 2;

	public static final int HEALTHCONST = 10000;
	public static final int HEALTHCOEFF = 2;
	
	

	public Boss(int x, int y, int speed) {
		super(SIZECONST+speed*SIZECOEFF, SIZECONST+speed*SIZECOEFF, x, y, speed, HEALTHCONST+HEALTHCOEFF*speed*speed);
		color = Color.white;
	}

	
}
