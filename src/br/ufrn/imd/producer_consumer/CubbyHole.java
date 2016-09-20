package br.ufrn.imd.producer_consumer;

public class CubbyHole {
	private int number;
	private boolean available = false;
	
	public synchronized int get(){
		while(available == false){
			try {
				wait();
			} catch(InterruptedException ex){}
		}
		
		available = false;
		notifyAll();

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
		
		notifyAll();
	}

}
