package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.Projectile;

public class DamageCollisionAbility extends AbstractCollisionAbility {
	
	public DamageCollisionAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void collision(EntityPlus e) {
		e.dealDamage(owner.getDamage(), owner.getOwner());
		owner.setAlive(false);
		return;
	}

}
