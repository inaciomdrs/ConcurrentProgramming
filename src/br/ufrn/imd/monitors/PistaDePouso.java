package br.ufrn.imd.monitors;

public class PistaDePouso extends Recurso {

	private static PistaDePouso pistaPouso = new PistaDePouso();
	
	private PistaDePouso(){
		super();
	}
	
	public static PistaDePouso getInstance(){
		return pistaPouso;
	}
	
}
