package vs.shimu.state;

public interface StateHandler {
	public void popState();
	public void pushState(State sh);
}
