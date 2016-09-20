package br.ufrn.imd.producer_consumer;

public class Consumer extends Thread {
	private CubbyHole cubbyHole;
	private int number;

	public Consumer(CubbyHole cubbyHole, int number) {
		super();
		this.cubbyHole = cubbyHole;
		this.number = number;
	}
	
	public void run(){
		int value;
		for (int i = 0; i < 10; i++) {
			value = cubbyHole.get();
			System.out.println("Consumer #" + this.number + " got: " + value);
		}
	}
}
