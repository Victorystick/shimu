package vs.shimu;

import java.io.Serializable;

public class Savefile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4591559109634786994L;
	final String name;
	final int maxScore;
	final int bestRatio;
	final String lastServer;
	final long aShivemunts1;
	final long aShivemunts2;

	public Savefile(String pName, int maxScore, int bestRatio,
			String lastServer, long[] ashivemunts) {
		this.name = pName;
		this.maxScore = maxScore;
		this.bestRatio = bestRatio;
		this.lastServer = lastServer;
		if (ashivemunts.length > 0) {
			this.aShivemunts1 = ashivemunts[0];
			if (ashivemunts.length > 1) {
				this.aShivemunts2 = ashivemunts[1];
			} else {
				this.aShivemunts2 = 0L;
			}
		} else {
			this.aShivemunts1 = 0L;
			this.aShivemunts2 = 0L;
		}
	}

	public Savefile(String pName, int maxScore, int bestRatio, long[] ashivemunts) {
		this.name = pName;
		this.maxScore = maxScore;
		this.bestRatio = bestRatio;
		this.lastServer = "";
		if (ashivemunts.length > 0) {
			this.aShivemunts1 = ashivemunts[0];
			if (ashivemunts.length > 1) {
				this.aShivemunts2 = ashivemunts[1];
			} else {
				this.aShivemunts2 = 0L;
			}
		} else {
			this.aShivemunts1 = 0L;
			this.aShivemunts2 = 0L;
		}
	}

	public Savefile(String pName, int maxScore, int bestRatio) {
		this.name = pName;
		this.maxScore = maxScore;
		this.bestRatio = bestRatio;
		this.lastServer = "";
		this.aShivemunts1 = 0L;
		this.aShivemunts2 = 0L;
	}

	// private final void setUp(String pName, int maxScore, int bestRatio,
	// String lastServer, long ashivemunts) {
	// this.name = pName;
	// this.maxScore = maxScore;
	// this.bestRatio = bestRatio;
	// this.lastServer = lastServer;
	// this.aShivemunts = ashivemunts;
	// }
}
