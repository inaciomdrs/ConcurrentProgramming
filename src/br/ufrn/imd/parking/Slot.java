package br.ufrn.imd.parking;

public class Slot {
	
	private int id;
	private boolean available;	
	private Semaphore semaphore;

	public Slot(int id, Semaphore semaphore) {
		super();
		this.id = id;
		this.available = true;
		this.semaphore = semaphore;
	}
	
	public void arrive(){
		this.semaphore.P();
		this.available = ParkingLot.UNAVAILABLE;
	}
	
	public void depart(){
		this.available = ParkingLot.AVAILABLE;
		this.semaphore.V();
	}
	
	public int getID(){
		return this.id;
	}
	
	public boolean isAvailable(){
		return this.available;
	}
			
}
