package vs.shimu.entity.projectiles;

import java.util.HashSet;

import vs.shimu.entity.Entity;
import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.abilities.DamageCollisionAbility;
import vs.shimu.entity.projectiles.abilities.HomingAbility;
import vs.shimu.entity.projectiles.abilities.LineRenderAbility;
import vs.shimu.entity.projectiles.abilities.LinearMoveAbility;
import vs.shimu.entity.projectiles.abilities.ProjectileAbility;


public class MainProjectile extends AbilityProjectile {

	
	public MainProjectile(EntityPlus owner, Entity target, int speed) {
		super(owner, speed);
		HashSet<ProjectileAbility> ab = new HashSet<ProjectileAbility>();
		ab.add(new LineRenderAbility(this));
		ab.add(new LinearMoveAbility(this));
		ab.add(new DamageCollisionAbility(this));
		setAbilities(ab);
	}
}
