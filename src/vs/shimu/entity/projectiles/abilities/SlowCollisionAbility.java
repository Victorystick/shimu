package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.Buffs.SpeedDebuff;
import vs.shimu.entity.projectiles.Projectile;

public class SlowCollisionAbility extends AbstractCollisionAbility {

	public SlowCollisionAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void collision(EntityPlus e) {
		e.addBuff(new SpeedDebuff(10, 5000));
	}
}
