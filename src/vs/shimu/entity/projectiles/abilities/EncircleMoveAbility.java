package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.Entity;
import vs.shimu.entity.projectiles.Projectile;

public class EncircleMoveAbility extends AbstractMoveAbility {

	Entity target;
	float alpha;
	int rad;
	
	public EncircleMoveAbility(Projectile owner, Entity target, int radius) {
		super(owner);
		this.target = target;
		rad = radius;
	}

	@Override
	public void move() {
		int it = owner.getSpeed() / 2;
		float d = 0;
		if (it != 0) {
			d = owner.getSpeed() / it;
		}
		for (int i = 0; i < it; i++) {
			alpha+=d;
			owner.setX((float) (target.getX()+rad*Math.cos(alpha)));
			owner.setY((float) (target.getY()+rad*Math.sin(alpha)));
			owner.collide();
		}
	}

}
