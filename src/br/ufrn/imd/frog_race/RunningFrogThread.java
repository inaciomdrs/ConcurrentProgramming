package br.ufrn.imd.frog_race;

import java.util.Random;

public class RunningFrogThread extends Thread {

	private static int raceTotalDistance;
	private int position;
	private int placing;
	private long timeElapsed;
	private Random random;

	public RunningFrogThread(String name) {
		setName(name);
		this.placing = 0;
		this.position = 0;
		this.timeElapsed = 0;
	}

	public int getPlacing() {
		return placing;
	}

	public static void setRaceTotalDistance(int raceTotalDistance) {
		RunningFrogThread.raceTotalDistance = raceTotalDistance;
	}

	public void setPlacing(int placing) {
		this.placing = placing;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void printSituation() {
		System.out.println(getName() + " is at " + this.position + " meters");
	}
	
	public long getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public void jump() {
		random = new Random();
		this.position += random.nextInt(RunningFrogThread.raceTotalDistance);
	}

	public void rest() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Can't sleep!");
		}
	}

	public void run() {
		random = new Random();
		while (this.position < RunningFrogThread.raceTotalDistance) {
			if (random.nextBoolean()) {
				jump();
			} else {
				rest();
			}
									
			if(this.position > RunningFrogThread.raceTotalDistance){
				this.position = RunningFrogThread.raceTotalDistance;
			}						
		}
	}
}
