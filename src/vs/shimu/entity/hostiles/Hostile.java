package vs.shimu.entity.hostiles;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import vs.shimu.entity.Entity;
import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.Buffs.Buff;

public class Hostile extends EntityPlus {
	protected EntityPlus target;
	private int health;
	private boolean munching;
	protected Set<EntityPlus> targets;

	public Hostile(int width, int height, int x, int y, int speed, int health) {
		super(width, height, x, y, speed, 0);
		target = null;
		targets = new HashSet<EntityPlus>();
		killer = null;
		color = colorFunction(speed);
		this.health = health;
	}

	protected Color colorFunction(int speed) {
		int red = (50 + speed * 20);
		if (red > 255)
			red = 255;
		int green = (80 + speed * 10);
		if (green > 255)
			green = 255;
		// int blue = (25 + speed * 25);
		// if (blue > 255)
		// blue = 255;
		return new Color(red, green, 25);
	}

	@Deprecated
	private Color chooseColor(int speed) {
		if (speed < 4) {
			return Color.yellow;
		}
		if (speed < 5) {
			return Color.cyan;
		}
		if (speed < 6) {
			return Color.green;
		}
		if (speed < 7) {
			return Color.magenta;
		}
		return Color.white;
	}

	// Unused?
	public void setTarget(EntityPlus newTarget) {
		target = newTarget;
	}
	
	public void setTargets(Set<EntityPlus> newTargets) {
		targets = newTargets;
	}
	
	public void addTarget(EntityPlus newTarget) {
		targets.add(newTarget);
	}

	/**
	 * The method used to update Hostiles in multiplayer games.
	 */
	public void update() {
		for (Buff b : buffs) {
			b.continousBuff();
		}
		removeDoneBuffs();
		
		if (target == null) {
			findTarget(targets);
		} else {
			// if (!target.isAlive()) {
			// target = null;
			// } else {
			adjustAngle(target);
			project();
			// }
		}
	}

	/**
	 * Locates the closest player and targets it.
	 * 
	 * @param players
	 */
	protected void findTarget(Set<EntityPlus> targets) {
		int shortest = 200;
		for (EntityPlus player : targets) {
			if (distanceTo(player) <= shortest) {
				target = player;
				angle = angleFacing(target);
			}
		}
	}

	/**
	 * Adjusts the angle of the Entity to try to face its target.
	 * 
	 * @param target
	 */
	// TODO Fix movement bug
	protected void adjustAngle(EntityPlus target) {
		double delta = angleFacing(target) - angle;
		double maxAngle = Math.PI / 36;
		if (Math.abs(delta) > Math.PI) {
			delta += Math.signum(delta) * 2 * Math.PI;
		} else if (Math.abs(delta) > maxAngle)
			delta = Math.signum(delta) * maxAngle;
		angle += delta;
	}

	protected void munching(Entity corpse) {
		if (corpse != null) {
			munching = true;
			angle = angleFacing(corpse);
			project();
		} else {
			munching = false;
		}
	}

	public boolean isAlive() {
		return health > 0;
	}
	
	public void dealDamage(int damage, EntityPlus dealer) {
		health -= damage;
		killer = dealer;
	}

	public int getPoints() {
		return 10 * speed;
	}

	public int getHealth() {
		return health;
	}
}
