package br.ufrn.imd.parking;

public class Car extends Thread {

	private int id;
	private ParkingLot parkingLot;
				
	public Car(int id, ParkingLot parkingLot) {
		super();
		this.id = id;
		this.parkingLot = parkingLot;
	}

	public void run(){
		int slotsQuantity = parkingLot.numberOfSlots();
		
		for (int slot = 0; slot < slotsQuantity; slot++) {
			System.out.println("Car #" + id + " IS TRYING to park at Slot " + slot);
			parkingLot.park(slot);
			System.out.println("Car #" + id + " GOT to park at Slot " + slot);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
			parkingLot.unpark(slot);
			System.out.println("Car #" + id + " LEFT Slot " + slot);
		}
	}
	
}
