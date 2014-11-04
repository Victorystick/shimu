package vs.shimu.entity;

import java.util.Random;

import vs.shimu.entity.projectiles.abilities.ProjectileAbility;

public class AbilityContainer extends Entity {
	
	ProjectileAbility ability;
	
	public AbilityContainer(ProjectileAbility ability, int x, int y) {
		super(10, 20, x, y, 0, 0);
		this.ability = ability;
	}
}
