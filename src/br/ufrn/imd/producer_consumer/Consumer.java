package br.ufrn.imd.producer_consumer;

public class Consumer extends Thread {
	private CubbyHole cubbyHole;
	private int number;
	private boolean consumed;

	public Consumer(CubbyHole cubbyHole, int number) {
		super();
		this.cubbyHole = cubbyHole;
		this.number = number;
		this.consumed = false;
	}
	
	public void run(){
		int value;
		for (int i = 0; i < 10; i++) {
			if(!consumed){
				value = cubbyHole.get();
				System.out.println("Consumer #" + this.number + " got: " + value);
				consumed = true;
			} else {
				consumed = false;
			}
		}
	}
}
