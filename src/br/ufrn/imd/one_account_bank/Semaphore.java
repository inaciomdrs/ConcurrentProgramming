package br.ufrn.imd.one_account_bank;

public class Semaphore {
	private int count;
	
	public Semaphore(){
		count = 1;
	}
	
	public synchronized void P(){
		if(count == 0){
			try {
				wait();
			} catch (InterruptedException e){}
		}
		count = 0;
	}
	
	public synchronized void V(){
		count = 1;
		notify();
	}
	
}
