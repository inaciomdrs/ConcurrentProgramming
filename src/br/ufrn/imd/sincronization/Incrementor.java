package br.ufrn.imd.sincronization;

public class Incrementor implements Runnable {

	private Coordinate coordinate;
	
	public Incrementor(Coordinate coordinate) {
		super();
		this.coordinate = coordinate;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Incrementing...");
			coordinate.increment();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}

}
