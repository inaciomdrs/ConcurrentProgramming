package br.ufrn.imd.application;

import java.util.Random;

public class RunningFrogThread extends Thread {

	private static int raceTotalDistance;
	private int position;
	private int placing;
	private Random random;

	public RunningFrogThread(String name) {
		setName(name);
		this.placing = 0;
		this.position = 0;
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
			jump();
			
			if(this.position > RunningFrogThread.raceTotalDistance){
				this.position = RunningFrogThread.raceTotalDistance;
			}
												
			if (random.nextBoolean()) {
				rest();
			}
		}
	}

}
