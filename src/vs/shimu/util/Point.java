package vs.shimu.util;

public class Point {
	final int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return x;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			return getX()==p.getX() && getY()==p.getY();
		}
		return false;
	}
	
	public int hashCode() {
		return (getX()^3+getY()) % Integer.MAX_VALUE;
	}

}
