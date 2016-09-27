package br.ufrn.imd.one_account_bank;

public class Bank {
	
	private double balance; // saldo
	private Semaphore semaphore;
	
	public Bank(double balance, Semaphore semaphore) {
		super();
		this.balance = balance;
		this.semaphore = semaphore;
	}
	
	public Bank(Semaphore semaphore) {
		super();
		this.balance = 0;
		this.semaphore = semaphore;
	}
	
	public void depositar(double value) throws Exception {
		if(value > 0.0){
			semaphore.P();
			balance += value;
			semaphore.V();
		} else {
			throw new Exception("Valor negativo inserido!");
		}
	}
	
	public void levantar(double value) throws Exception {
		if(value < balance){
			semaphore.P();
			balance -= value;
			semaphore.V();
		} else {
			throw new Exception("Valor inserido é maior que o saldo!");
		}
	}
	
	public synchronized double getBalance(){
		 return balance;
	}

}
