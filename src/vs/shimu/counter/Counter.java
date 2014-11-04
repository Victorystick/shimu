package vs.shimu.counter;

import vs.shimu.drawable.Drawable;

public interface Counter extends Drawable { //perhaps make abstract in future, implement the drawable methods here
	
	int getCount();
	void setCount(int count);
	void addTo(int add);
	String getName();
}
	
