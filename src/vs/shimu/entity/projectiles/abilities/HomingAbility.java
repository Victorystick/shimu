package vs.shimu.entity.projectiles.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.Entity;
import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.hostiles.Hostile;
import vs.shimu.entity.projectiles.Projectile;

public class HomingAbility extends AbstractAbility {

	Entity target;

	public HomingAbility(Projectile owner, Entity target) {
		super(owner);
		this.target = target;
	}

	@Override
	public void move() {
		
		updateTarget();
		
		int it = owner.getSpeed() / 2;
		float d = 0;
		if (it != 0) {
			d = owner.getSpeed() / it;
		}
		for (int i = 0; i < it; i++) {
			owner.adjustAngle(target);
			owner.project(d);
			owner.collide();
		}
	}

	private void updateTarget() {
		target = owner.getOwner().closest(owner.getHitable());
	}

	@Override
	public void collision(EntityPlus e) {
		return;
	}

	@Override
	public Color getColor() {
		return Color.getColor("#954220");
	}

	@Override
	public void render(Graphics2D g) {
		if (target != null) {
			int width = 10;
			int heigth = 5;
			g.setColor(getColor());

			g.drawLine(
					Math.round(target.getX()) - heigth / 2,
					Math.round(target.getY() - width / 2), 
					Math.round(target.getX()) + heigth / 2,
					Math.round(target.getY() + width / 2));

			g.drawLine(
					Math.round(target.getX()) - heigth / 2,
					Math.round(target.getY() + width / 2), 
					Math.round(target.getX()) + heigth / 2,
					Math.round(target.getY() - width / 2));
		}
	}

}
