package vs.shimu.entity.Buffs;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.hostiles.Hostile;

public class SeverePoison extends Poison {
	
	int s;

	public SeverePoison(Hostile enemy, int amount,int severity, EntityPlus origin) {
		super(enemy, amount, origin);
		s = severity;
	}
	
	@Override
	public void continousBuff() {
		super.continousBuff();
		a++;
	}

	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(new Color(200, 25, 20));
		for (int i = 0; i < 2; i++) {
			g.drawOval(
					Math.round(e.getX() + rand.nextInt(e.getWidth() / 2) * getSign()),
					Math.round(e.getY() + rand.nextInt(e.getHeight() / 2) * getSign()),
					3, 3);
		}
	}
	
	public boolean equals(Object o) {
		return (o instanceof SeverePoison);
	}
	
	public int hashCode() {
		return e.hashCode();
		
	}

}
