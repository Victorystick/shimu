package vs.shimu.entity.hostiles;

import java.awt.Color;
import java.util.Collection;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.Shooter;
import vs.shimu.entity.projectiles.MainProjectile;
import vs.shimu.entity.projectiles.Projectile;

public class ShootingHostile extends Hostile implements Shooter {
	
	public static final boolean CANSHOOT = true;
	protected int timer;

	public ShootingHostile(int width, int height, int x, int y, int speed,
			int health) {
		super(width, height, x, y, speed, health);
		damage = 2;
	}
	
	protected Color colorFunction(int speed) {
		int red = (50 + speed * 20);
		if (red > 255)
			red = 255;
		//int green = (80 + speed * 10);
		//if (green > 255)
		//	green = 255;
		// int blue = (25 + speed * 25);
		// if (blue > 255)
		// blue = 255;
		return new Color(red, 25, 25);
	}
	
	public Projectile shoot() {
		return fireWeapon(targets);
	}
	
	public Projectile fireWeapon(Collection<? extends EntityPlus> es) {
		if (CANSHOOT && target != null && time2Shoot()) {
			Projectile bullet = new MainProjectile(this, target, 24);
			bullet.setHitable(es);
			return bullet;
		}
		return null;
	}
	
	private boolean time2Shoot() {
		int time = timer;
		time += Math.random()*10;
		if (speed < 50) {
			return (time % (50-speed)) == 0;
		} 
		return time == 0;
	}
	
	public void update() {
		super.update();
		timer++;
	}

}
