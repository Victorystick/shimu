package vs.shimu.entity.projectiles;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import vs.shimu.util.Dimension;
import vs.shimu.entity.Entity;
import vs.shimu.entity.EntityPlus;

public class Projectile extends Entity implements Cloneable {
	protected EntityPlus owner;
	protected int damage;
	protected boolean alive;
	protected Collection<EntityPlus> hitable;

	public Projectile(EntityPlus owner, int speed) {
		super(1, 1, owner.getX(), owner.getY(), speed, owner.getAngle());
		color = Color.white;
		damage = owner.getDamage();
		this.owner = owner;
		setAlive(true);
		hitable = new HashSet<EntityPlus>();
	}

	public boolean outsideScreen(Dimension display) {
		if (x > 0 && x < display.getWidth() && y > 0 && y < display.getHeight())
			return false;
		return true;
	}

	public EntityPlus getOwner() {
		return owner;
	}

	public int getDamage() {
		return damage;
	}

	public void setTarget(Entity target) {
		super.angle = angleFacing(target);
	}

	/**
	 * Adjusts the angle of the Entity to try to face its target.
	 * 
	 * @param target
	 */
	public void adjustAngle(Entity target) {
		if (target == null) {
			return;
		}
		
		double angleDiff = angleFacing(target) - angle;
		double delta;
		if (Math.abs(angleDiff) > getMaxAngle()) {
			delta = getMaxAngle();
		} else {
			delta = angleDiff;
		}
		if (Math.abs(angleDiff) > Math.PI) {
			angle += Math.signum(angle) * Math.abs(delta);
		} else {
			angle += delta;
		}
		if (Math.abs(angle) > Math.PI)
			angle -= Math.PI * 2;
	}

	protected double getMaxAngle() {
		return Math.PI/256;
	}
	
	public void move() {
		int it = getSpeed() / 2;
		float d = 0;
		if (it != 0) {
			d = getSpeed() / it;
		}
		for (int i = 0; i < it; i++) {
			project(d);
			collide();
		}
		return;
	}

	public void collide() {
		for (EntityPlus e : hitable) {
			if (e.intersects(this)) { //Check if enemy occupies any point in set
				e.dealDamage(getDamage(), getOwner());
				setAlive(false);
				return;
			}
		}
	}

	public void setAlive(boolean b) {
		alive = b;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	@SuppressWarnings("unchecked") //Det kommer bara saker som är EntityPlus eller mer. Så det är lugnt.
	public void setHitable(Collection<? extends EntityPlus> newHittable) {
		hitable = (Collection<EntityPlus>) newHittable;
	}
	
	public Collection<EntityPlus> getHitable() {
		return hitable;
	}

	public void setDamage(int newDamage) {
		damage = newDamage;
	}
}
