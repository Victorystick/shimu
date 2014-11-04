package vs.shimu.util;

/**
 * A Dimension class that returns <code>int</code>-type values when the
 * <code>getWidth()</code> and <code>getHeight()</code> methods are called,
 * which the standard <code>java.awt.util.Dimension</code> lacked.
 * 
 * @author Oskar Segersvärd
 */
public class Dimension {
	private int w, h;

	public Dimension(int width, int height) {
		w = width;
		h = height;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
}
