package br.ufrn.imd.monitors.recursos;

import java.util.ArrayList;
import java.util.List;

public class TorreDeControle {
	
	private PistaDePouso pistaPouso;
	private List<Portao> portoes;
	
	public TorreDeControle(int numeroDePortoes){
		this.pistaPouso = PistaDePouso.getInstance();
		portoes = new ArrayList<Portao>();
		
		for (int i = 0; i < numeroDePortoes; i++) {
			portoes.add(new Portao());
		}
	}
	
	public void autorizarPouso(){
		if(pistaPouso.entrar()){
			System.out.println("Acesso à pista de pouso concedido!");
		} else {
			System.out.println("Pista de pouso congestionada! Aguarde um pouco!");
		}
	}
	
}
