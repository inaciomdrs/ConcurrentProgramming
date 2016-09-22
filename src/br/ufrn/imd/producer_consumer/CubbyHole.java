package br.ufrn.imd.producer_consumer;

public class CubbyHole {
	private int number;
	private boolean available;
			
	public synchronized int get(){
		while(available == false){
			try {
				wait();
			} catch(InterruptedException ex){}
		}
		
		available = false;
		notify();

		return number;
	}
	
	public synchronized void put(int value){
		while(available == true){
			try {
				wait();
			} catch(InterruptedException ex){}
		}
		
		number = value;
		available = true;
		
		notify();
	}

}
