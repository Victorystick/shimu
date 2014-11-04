package vs.shimu.entity.projectiles.abilities;

import java.util.HashSet;
import java.util.Set;

import vs.shimu.entity.Entity;
import vs.shimu.entity.projectiles.Projectile;

public class HomingClusterAbility extends ClusterAbility {
	
	Entity target;

	public HomingClusterAbility(Projectile owner, int speed, int timerStart, Entity target) {
		super(owner, speed, timerStart);
		
		this.target = target;
		
	}

	@Override
	protected Set<ProjectileAbility> getAbilities() {
		HashSet<ProjectileAbility> ab = new HashSet<ProjectileAbility>();
		ab.add(new LineRenderAbility(owner));
		ab.add(new LinearMoveAbility(owner));
		ab.add(new DamageCollisionAbility(owner));
		return ab;
	}

}
