package br.ufrn.imd.parking;

public class Main {

	public static void main(String[] args) {
		int DOUBLE = 2;
		
		int NUMBER_OF_SLOTS = 5;
		int NUMBER_OF_CARS = NUMBER_OF_SLOTS * DOUBLE;
		
		ParkingLot parkingLot = new ParkingLot(NUMBER_OF_SLOTS);
		
		for (int i = 0; i < NUMBER_OF_CARS; i++) {
			new Car(i, parkingLot).start();
		}
		
	}

}
