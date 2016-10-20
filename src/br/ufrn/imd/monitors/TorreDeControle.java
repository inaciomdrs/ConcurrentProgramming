package br.ufrn.imd.monitors;

import java.util.ArrayList;
import java.util.List;

public class TorreDeControle {
	
	private PistaDePouso pistaPouso;
	private List<Portao> portoes;
	
	public TorreDeControle(){
		this.pistaPouso = PistaDePouso.getInstance();
		portoes = new ArrayList<Portao>();
	}
		
}
