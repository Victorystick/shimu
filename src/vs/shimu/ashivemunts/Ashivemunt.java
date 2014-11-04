package vs.shimu.ashivemunts;

public abstract class Ashivemunt {
	
	final byte id; //byte för vi är ändå bara intresserad av värden <= 127, så vi sparar plats. Yay!
	//That's right! I just doubled our achivements! Om vi vill, kan vi dubbla en gång till (Gömda achivements bara vi kan få?)
	
	public Ashivemunt(byte id) {
		this.id = id;
	}

	byte getId() {
		return id;
	}
	
	abstract String getDescription();
}
