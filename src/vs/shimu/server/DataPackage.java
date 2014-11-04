package vs.shimu.server;

import java.io.Serializable;
import java.util.List;

import vs.shimu.entity.Player;
import vs.shimu.entity.hostiles.Hostile;
import vs.shimu.entity.projectiles.Projectile;

public class DataPackage implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4851042491891645517L;
	public static final double version = 0.1;
	public final int time;
	public final List<Player> players;
	public final List<Projectile> bullets;
	public final List<Hostile> enemies;
	
	public DataPackage(int time, List<Player> players, List<Projectile> bullets, 
			List<Hostile> enemies) {
		this.time = time;
		this.players = players;
		this.bullets = bullets;
		this.enemies =  enemies;
		
		//TODO Test speed of delivery
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Projectile> getBullets() {
		return bullets;
	}

	public List<Hostile> getEnemies() {
		return enemies;
	}

}
