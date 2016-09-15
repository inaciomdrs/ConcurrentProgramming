package br.ufrn.imd.sincronization;

public class Coordinate {
	
	private int x, y;
	
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

}
