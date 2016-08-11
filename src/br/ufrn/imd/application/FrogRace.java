package br.ufrn.imd.application;

public class FrogRace {

	final static int FROGS_NUMBER = 5;
	final static int DISTANCE = 500;

	public static void main(String[] args) {
		RunningFrogThread.setRaceTotalDistance(DISTANCE);

		RunningFrogThread[] frogs = new RunningFrogThread[FROGS_NUMBER];

		System.out.print("Starting race... ");
		
		for (int i = 0; i < FROGS_NUMBER; i++) {
			frogs[i] = new RunningFrogThread("FROG_" + i);
			frogs[i].start();
		}
		
		System.out.print("Started!\n");

		System.out.println("RACE STATS");
		int actualPlacing = 1;
		while (!allThreadsFinished(frogs)) {
			System.out.println("=================================================");
			for (int i = 0; i < FROGS_NUMBER; i++) {
				frogs[i].printSituation();
				if(!frogs[i].isAlive()){
					frogs[i].setPlacing(actualPlacing);
					actualPlacing++;
				}
			}
			System.out.println("=================================================");
		}	

		for (int i = 0; i < FROGS_NUMBER; i++) {
			System.out.println(frogs[i].getName() + " finished race in " + (frogs[i].getPlacing()+1));
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
