package br.ufrn.imd.producer_consumer;

public class Producer extends Thread {
	private CubbyHole cubbyHole;
	private int number;
	
	public Producer(CubbyHole cubbyHole, int number) {
		super();
		this.cubbyHole = cubbyHole;
		this.number = number;
	}
	
	public void run(){
		for (int i = 0; i < 10; i++) {
			cubbyHole.put(i);
			System.out.println("Producer #" + this.number + " put: " + i);
			
			try {
				sleep((int) (Math.random() * 100));
			} catch(InterruptedException ex){}
		}
	}
}
