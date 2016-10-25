package br.ufrn.imd.monitors.recursos;

public class Portao extends Recurso {
	
	private int id;
	
	public Portao(int id){
		super();
		this.id = id;
	}
	
	public Portao(){
		this(((int) Math.random() * 1000));
	}
		
	public String toString(){
		return "Portao "+this.id;
	}

}
