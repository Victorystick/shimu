package vs.shimu.entity.projectiles.abilities;

import java.awt.Graphics2D;

import vs.shimu.entity.projectiles.Projectile;

public class DotRenderAbility extends AbstractRenderAbility {
	
	public DotRenderAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(owner.getColor());
		g.fillOval(Math.round(owner.getX()), Math.round(owner.getY()), 3, 3);
		g.setColor(owner.getOwner().getColor());
		g.drawOval(Math.round(owner.getX()), Math.round(owner.getY()), 3, 3);
	}

}
