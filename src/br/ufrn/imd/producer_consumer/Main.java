package br.ufrn.imd.producer_consumer;

public class Main {

	public static void main(String[] args) {
		CubbyHole resource = new CubbyHole();
		
		Producer producerA = new Producer(resource, 1);
		Producer producerB = new Producer(resource, 2);
		
		Consumer consumerA = new Consumer(resource, 1);
		Consumer consumerB = new Consumer(resource, 2);
		Consumer consumerC = new Consumer(resource, 3);
		
		producerA.start();
		producerB.start();
		consumerA.start();
		consumerB.start();
		consumerC.start();

	}

}
