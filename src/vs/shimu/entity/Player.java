package vs.shimu.entity;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;

import vs.shimu.counter.PointsCounter;
import vs.shimu.counter.RatioCounter;
import vs.shimu.drawable.Crosshair;
import vs.shimu.entity.projectiles.AbilityProjectile;
import vs.shimu.entity.projectiles.MainProjectile;
import vs.shimu.entity.projectiles.Projectile;
import vs.shimu.entity.projectiles.abilities.DotRenderAbility;
import vs.shimu.entity.projectiles.abilities.LinearMoveAbility;
import vs.shimu.entity.projectiles.abilities.ProjectileAbility;
import vs.shimu.entity.projectiles.abilities.SlowCollisionAbility;

public class Player extends EntityPlus {
	private Crosshair cross;
	private String name;
	private PointsCounter pc; // TEMPORARY ((2012-01-25): Absolutely not!)
	private RatioCounter rc; // TEMPORARY ((2012-01-25): Absolutely not!)
	private Collection<ProjectileAbility> abilities;

	public Player(Color color, String name) {
		super(6, 6, 50, 50, 5, 0);
		this.color = color;
		cross = new Crosshair(this);
		damage = 2;
		
		abilities = new HashSet<ProjectileAbility>();
		setInitAbilities();
	}

	private void setInitAbilities() {
		abilities.add(new LinearMoveAbility(null));
		abilities.add(new DotRenderAbility(null));
		abilities.add(new SlowCollisionAbility(null));
	}

	public void setCounters(PointsCounter points, RatioCounter ratio) {
		pc = points;
		rc = ratio;
	}

	public Player(Color color) {
		this(color, "Player");
	}

	public void addPoints(int p) {
		float r = rc.getRatio();
		if (r < 1)
			r = 1;
		pc.addTo((int) (p * r));
	}

	@Override
	public String toString() {
		return name;
	}

	public void updateCrosshair() {
		cross.update();
	}

	public Crosshair getCrosshair() {
		return cross;
	}

	public void setName(String nick) {
		name = nick;
	}

	@Override
	public int getSpeed() {
		return (int) (speed + Math.sqrt(Math.sqrt(Math.abs(rc.getRatio()))));
		// return (int) rc.getRatio();
	}

	public int getDamage() {
		float r = rc.getRatio();
		if (r >= 2)
			return (int) (damage * rc.getRatio());
		return damage;
	}

	public Projectile fireWeapon1(Collection<? extends EntityPlus> es) {
		Projectile bullet = new MainProjectile(this, closest(es), 16);
		bullet.setHitable(es);
		return bullet;
	}

	public Projectile fireWeapon2(Collection<? extends EntityPlus> es) {
		AbilityProjectile abullet = new AbilityProjectile(this, 20);
		
		HashSet<ProjectileAbility> bulletAbilities = new HashSet<ProjectileAbility>();
		
		for (ProjectileAbility pa : abilities) {
			ProjectileAbility npa = pa.duplicate();
			npa.changeContext(abullet);
			bulletAbilities.add(npa);
		}
		
		abullet.setAbilities(bulletAbilities);
		abullet.setHitable(es);
		
		return abullet;
	}
}
