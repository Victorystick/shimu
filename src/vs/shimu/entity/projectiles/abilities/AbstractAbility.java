package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.projectiles.Projectile;

public abstract class AbstractAbility implements ProjectileAbility {
	
	Projectile owner;
	
	public AbstractAbility(Projectile owner) {
		changeContext(owner);
	}
	
	public void changeContext(Projectile owner) {
		this.owner = owner;
	}

	@Override
	public ProjectileAbility duplicate() {
		try {
			Object o = clone();
			if (o instanceof ProjectileAbility) {
				return (ProjectileAbility) o;
			}
			
		} catch (CloneNotSupportedException e) {
			// TODO Some error message perhaps...
			e.printStackTrace();
		}
		return null;
	}

}
