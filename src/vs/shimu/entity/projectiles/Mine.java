package vs.shimu.entity.projectiles;

import java.awt.Graphics2D;

import vs.shimu.entity.EntityPlus;

public class Mine extends Projectile {
	
	int size;
	
	public Mine(EntityPlus owner, int explotion_size) {
		super(owner, 0);
		size = explotion_size;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.drawOval(Math.round(x), Math.round(y), w, h);
	}
	
	public void move() {
		collide();
	}
	
	public void collide() {
		boolean detonate = false;
		for (EntityPlus e : hitable) {
			if (distanceTo(e) < size) {
				detonate = true;
				e.dealDamage(getDamage()*2, getOwner());
			}
		}
		setAlive(!detonate);
	}
}
