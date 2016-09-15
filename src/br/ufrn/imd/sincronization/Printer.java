package br.ufrn.imd.sincronization;

public class Printer implements Runnable {

private Coordinate coordinate;
	
	public Printer(Coordinate coordinate) {
		super();
		this.coordinate = coordinate;
	}

	@Override
	public void run() {
		while(true){
			coordinate.printAttributes();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}
	
}
