package vs.shimu.entity.Buffs;

import java.util.Timer;
import java.util.TimerTask;

public abstract class TimedBuff extends AbstractBuff {

	static final Timer timer = new Timer();
	boolean done;

	public TimedBuff(int time) {
		timer.schedule(new BuffTask(this), time);
	}

	public void cancel() {
		done = true;
	}

	public boolean done() {
		return done;
	}

	static class BuffTask extends TimerTask {

		TimedBuff buff;

		public BuffTask(TimedBuff buff) {
			this.buff = buff;
		}

		@Override
		public void run() {
			buff.cancel();
		}

	}

}
