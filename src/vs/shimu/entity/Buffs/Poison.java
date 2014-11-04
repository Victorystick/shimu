package vs.shimu.entity.Buffs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.hostiles.Hostile;

public class Poison extends TimedBuff {

	protected Hostile e;
	protected int a;
	protected EntityPlus o;
	protected Random rand;

	public Poison(Hostile enemy, int amount, EntityPlus origin) {
		super((amount % origin.getDamage()*enemy.getDamage())+1000);
		e = enemy;
		a = amount;
		o = origin;
		rand = new Random();
	}

	@Override
	public void continousBuff() {
		e.dealDamage(rand.nextInt(a), o);
	}

	public void render(Graphics2D g) {

		g.setColor(new Color(0, 50, 0));
		for (int i = 0; i < 3; i++) {
			g.drawOval(
					Math.round(e.getX() + rand.nextInt(e.getWidth() / 2) * getSign()),
					Math.round(e.getY() + rand.nextInt(e.getHeight() / 2) * getSign()),
					3, 3);
		}
		g.setColor(new Color(0, 100, 0));
		for (int i = 0; i < 2; i++) {
			g.drawOval(
					Math.round(e.getX() + rand.nextInt(e.getWidth() / 2) * getSign()),
					Math.round(e.getY() + rand.nextInt(e.getHeight() / 2) * getSign()),
					3, 3);
		}
	}

	protected int getSign() {
		return (rand.nextInt() % 2 == 0) ? 1 : -1;
	}

	public boolean equals(Object o) {
		return (o instanceof Poison);
	}

	public int hashCode() {
		return e.hashCode();
	}
	
	@Override
	public void cancel() {
		a = 0;
		super.cancel();
	}
}
