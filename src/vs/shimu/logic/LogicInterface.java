package vs.shimu.logic;

import java.net.Socket;
import java.util.Collection;

import vs.shimu.entity.Entity;
import vs.shimu.entity.Player;
import vs.shimu.entity.hostiles.Hostile;
import vs.shimu.entity.projectiles.Projectile;

public interface LogicInterface {

	/**
	 * The main update function that makes calls to all others.
	 */
	public abstract void update();
	
	public abstract void join(Socket socket);
	
	public abstract void leave(Socket socket);

	public abstract Collection<Player> getPlayers();

	public abstract Collection<Hostile> getEnemies();

	public abstract Collection<Projectile> getProjectiles();

	//public abstract List<Counter> getCounters();

}