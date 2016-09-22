package br.ufrn.imd.parking;

public class ParkingLot {

	private Slot[] slots;
	private int slotsNumber;
	
	public static final boolean AVAILABLE = true;
	public static final boolean UNAVAILABLE = false;

	public ParkingLot(int slotsNumber) {
		this.slotsNumber = slotsNumber;
		slots = new Slot[this.slotsNumber];

		for (int i = 0; i < this.slotsNumber; i++) {
			slots[i] = new Slot(i, new Semaphore());
		}
	}

	public boolean park(int slotNumber) {
		if (validSlot(slotNumber)) {
			slots[slotNumber].arrive();
			return true;
		}

		return false;
	}

	public boolean unpark(int slotNumber) {
		if (validSlot(slotNumber) && (!slots[slotNumber].isAvailable())) {
			slots[slotNumber].depart();
			return true;
		}

		return false;
	}

	private boolean validSlot(int slotNumber) {
		return (slotNumber >= 0) && (slotNumber < slotsNumber);
	}
	
	public int numberOfSlots(){
		return this.slotsNumber;
	}
	
	public boolean slotStatus(int slotNumber){
		if(validSlot(slotNumber)){
			return slots[slotNumber].isAvailable();
		}
		return false;
	}

}
