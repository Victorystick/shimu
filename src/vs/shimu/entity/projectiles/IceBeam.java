/**
 * 
 */
package vs.shimu.entity.projectiles;

import java.util.HashSet;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.abilities.LineRenderAbility;
import vs.shimu.entity.projectiles.abilities.LinearMoveAbility;
import vs.shimu.entity.projectiles.abilities.ProjectileAbility;
import vs.shimu.entity.projectiles.abilities.SlowCollisionAbility;

/**
 * @author johan
 *
 */
public class IceBeam extends AbilityProjectile {

	/**
	 * @param owner
	 * @param speed
	 */
	public IceBeam(EntityPlus owner, int speed) {
		super(owner, speed);
		abilities = new HashSet<ProjectileAbility>();
		abilities.add(new LinearMoveAbility(this));
		abilities.add(new LineRenderAbility(this));
		abilities.add(new SlowCollisionAbility(this));
		setAbilities(abilities);
	}

}
