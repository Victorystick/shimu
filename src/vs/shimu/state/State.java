package vs.shimu.state;

import vs.shimu.Controller;
import vs.shimu.display.Display;

public abstract class State {
	protected Display display;
	protected Controller control;
	private StateHandler pop;
	
	public State(StateHandler main, Display display, Controller control){
		pop = main;
		this.display = display;
		this.control = control;
	}
	
	/**
	 * Pops the top gameState.
	 */
	protected void pop(){
		pop.popState();
	}
	
	/**
	 * Updates the state.
	 */
	public abstract void update();
	
	/**
	 * Renders the current state.
	 */
	public abstract void render();
}
