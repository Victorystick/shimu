package vs.shimu.entity.hostiles;

import java.awt.Color;
import java.util.Collection;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.IceBeam;
import vs.shimu.entity.projectiles.MainProjectile;
import vs.shimu.entity.projectiles.Projectile;
import vs.shimu.entity.projectiles.ShieldSwarm;

public class Turret extends ShootingHostile {
	
	int speed;
	
	public Turret(int x, int y, int speed, int health) {
		super(5, 5, x, y, 0, health);
		this.speed = speed;
	}
	
	protected Color colorFunction(int speed) {
		//int red = (50 + speed * 20);
		//if (red > 255)
		//	red = 255;
		//int green = (80 + speed * 10);
		//if (green > 255)
		//	green = 255;
		//int blue = (25 + speed * 25);
		//if (blue > 255)
		//	blue = 255;
		return new Color(150, 125, 255);
	}
	
	private boolean time2Shoot() {
		return timer % (speed+5) < speed-4;
	}
	
	public Projectile fireWeapon(Collection<? extends EntityPlus> es) {
		if (CANSHOOT && target != null && target.isAlive() && time2Shoot()) {
			Projectile bullet = new MainProjectile(this, target, 14);
			bullet.setHitable(es);
			return bullet;
		}
		return null;
	}

}
