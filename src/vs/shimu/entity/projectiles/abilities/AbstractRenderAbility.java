package vs.shimu.entity.projectiles.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.Projectile;

public abstract class AbstractRenderAbility extends AbstractAbility {

	public AbstractRenderAbility(Projectile owner) {
		super(owner);
	}
	
	@Override
	public void collision(EntityPlus e) {
		return;
	}

	@Override
	public void move() {
		return;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public abstract void render(Graphics2D g);

}
