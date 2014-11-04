package vs.shimu.entity.projectiles.abilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Set;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.AbilityProjectile;
import vs.shimu.entity.projectiles.Projectile;
//import vs.shimu.logic.GameLogic;

public abstract class ClusterAbility extends AbstractAbility {
	
	int timer;
	int sp;
	
	public ClusterAbility(Projectile owner, int speed, int timerStart) {
		super(owner);
		timer = timerStart;
		sp = speed;
	}

	@Override
	public void collision(EntityPlus e) {
		//detonate();
	}

	@Override
	public void move() {
		if (timer == 0) {
			detonate();
			timer = 500000;
		} else {
			timer--;
		}
	}
	
	private void detonate() {
		System.out.println("DETONATE!");
		float alpha = 0;
		double delta = Math.PI/8;
		AbilityProjectile p = null;
		for (int i = 0; i < 8; i++) {
			p = new AbilityProjectile(owner.getOwner(), sp);
			p.setAbilities(getAbilities());
			p.setAngle(alpha);
			p.setHitable(owner.getHitable());
			p.setPosition(Math.round(owner.getX()), Math.round(owner.getY()));
			
			//TODO Fix this shit...
			//GameLogic.(p);
			
			p.move();
			alpha += delta;
		}
		
	}

	protected abstract Set<ProjectileAbility> getAbilities();

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public void render(Graphics2D g) {

	}

}
