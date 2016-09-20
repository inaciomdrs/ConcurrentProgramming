package br.ufrn.imd.producer_consumer;

public class Main {

	public static void main(String[] args) {
		CubbyHole resource = new CubbyHole();
		
		Producer producer = new Producer(resource, 1);
		
		Consumer consumerA = new Consumer(resource, 1);
		Consumer consumerB = new Consumer(resource, 2);
		
		producer.start();
		consumerA.start();
		consumerB.start();

	}

}
