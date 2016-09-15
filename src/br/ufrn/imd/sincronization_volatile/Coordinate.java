package br.ufrn.imd.sincronization_volatile;

public class Coordinate {
	
	private volatile int x, y;
	
	public Coordinate(){
		x = 0;
		y = 0;
	}
	
	public void increment(){
		x++;
		y++;
	}
	
	public void printAttributes(){
		System.out.println(x + ", " + y);
	}
	
	public void zeroAttributes(){
		x = 0;
		y = 0;
	}

}
