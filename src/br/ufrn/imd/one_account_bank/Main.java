package br.ufrn.imd.one_account_bank;

public class Main {

	public static void main(String[] args) {
		Bank bank = new Bank(new Semaphore());
		
		BankUser handersonUser = new BankUser(bank, "Handerson");
		BankUser villarUser = new BankUser(bank, "Villar");
		BankUser ramonUser = new BankUser(bank, "Ramon");
		
		handersonUser.start();
		ramonUser.start();
		villarUser.start();

	}

}
