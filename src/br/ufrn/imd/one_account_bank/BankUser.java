package br.ufrn.imd.one_account_bank;

public class BankUser extends Thread {

	private Bank bank;
	
	public BankUser(Bank bank, String name) {
		super();
		setName(name);
		this.bank = bank;
	}
	
	public void run(){
		int NUM_EXECUTIONS = 10;
		int HALF = 2;
		int DOUBLE = HALF;
		double LOWER_LIMIT = 5;
		double UPPER_LIMIT = 10; 
		
		double money;
		
		for (int execution = 0; execution < NUM_EXECUTIONS / HALF; execution++) {
			money = Math.random() * UPPER_LIMIT + LOWER_LIMIT; 
			
			System.out.printf("%s is TRYING TO cash %.2f bucks\n",getName(),money);
			
			try {
				bank.depositar(money);
				System.out.printf("%s GOT to cash %.2f bucks [Balance now is %.2f bucks]\n",getName(),money,bank.getBalance());
			} catch (Exception e) {
				System.out.printf("%s DIDN'T GET to cash %.2f bucks\n",getName(),money);
			}
		}
		
		for (int execution = 0; execution < NUM_EXECUTIONS / HALF; execution++) {
			money = Math.random() * UPPER_LIMIT * DOUBLE + UPPER_LIMIT; 
			
			System.out.printf("%s is TRYING TO draw %.2f bucks\n",getName(),money);
			
			try {
				bank.levantar(money);
				System.out.printf("%s GOT to draw %.2f bucks  [Balance now is %.2f bucks]\n",getName(),money,bank.getBalance());
			} catch (Exception e) {
				System.out.printf("%s DIDN'T GET to draw %.2f bucks\n",getName(),money);
			}
			
			
		}
	}
	
	
	
}
