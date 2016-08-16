package br.ufrn.imd.frog_race;

public class Main {

	final static int FROGS_NUMBER = 5;
	final static int DISTANCE = 500;
	final static int NO_PLACING = 0;

	public static void main(String[] args) {
		RunningFrogThread.setRaceTotalDistance(DISTANCE);

		RunningFrogThread[] frogs = new RunningFrogThread[FROGS_NUMBER];

		System.out.print("Starting race... ");
		
		for (int i = 0; i < FROGS_NUMBER; i++) {
			frogs[i] = new RunningFrogThread("FROG_" + i);
			frogs[i].setTimeElapsed(System.currentTimeMillis());
			frogs[i].start();
		}
				
		System.out.print("Started!\n");

		System.out.println("RACE STATS");
		int actualPlacing = 1;
		
		while (!allThreadsFinished(frogs)) {
			System.out.println("=================================================");
			for (int i = 0; i < FROGS_NUMBER; i++) {
				frogs[i].printSituation();
				if((!frogs[i].isAlive()) && (frogs[i].getPlacing() == NO_PLACING)){
						frogs[i].setPlacing(actualPlacing);
						frogs[i].setTimeElapsed(System.currentTimeMillis() - frogs[i].getTimeElapsed());
						actualPlacing++;	
				}
			}
			System.out.println("=================================================");
		}	

		for (int i = 0; i < FROGS_NUMBER; i++) {
			System.out.println(frogs[i].getName() + " finished race in " + frogs[i].getPlacing() + " (" + frogs[i].getTimeElapsed() + " ms)");
		}
	}

	private static boolean allThreadsFinished(Thread[] threads) {
		for (int i = 0; i < FROGS_NUMBER; i++) {
			if (threads[i].isAlive()) {
				return false;
			}
		}
		return true;
	}

}
