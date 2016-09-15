package br.ufrn.imd.sincronization_volatile;

public class Main_Sincronization {

	public static void main(String[] args) {
		Coordinate coordinate = new Coordinate();
		
		Incrementor incrementor = new Incrementor(coordinate);
		Printer printer = new Printer(coordinate);
		
		Thread producer = new Thread(incrementor);
		Thread consumer = new Thread(printer);
		
		producer.start();
		consumer.start();
	}

}
