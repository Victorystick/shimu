package vs.shimu.entity.projectiles;

import java.util.HashSet;
import java.util.Set;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.abilities.DamageCollisionAbility;
import vs.shimu.entity.projectiles.abilities.DotRenderAbility;
import vs.shimu.entity.projectiles.abilities.EncircleMoveAbility;
import vs.shimu.entity.projectiles.abilities.ProjectileAbility;

public class ShieldSwarm extends AbilityProjectile {

	public ShieldSwarm(EntityPlus owner, int speed) {
		super(owner, speed);
		abilities = new HashSet<ProjectileAbility>();
		abilities.add(new EncircleMoveAbility(this, owner, 20));
		abilities.add(new DotRenderAbility(this));
		abilities.add(new DamageCollisionAbility(this));
		setAbilities(abilities);
	}
	
	public void move() {
		if (!owner.isAlive()) {
			alive = false;
		} else {
			super.move();
		}
	}

}
