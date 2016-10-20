package br.ufrn.imd.monitors.recursos;

import java.util.ArrayList;
import java.util.List;

public class TorreDeControle {
	
	private PistaDePouso pistaPouso;
	private List<Portao> portoes;
	private int IDportaoDisponvel;
	
	public TorreDeControle(int numeroDePortoes){
		this.pistaPouso = PistaDePouso.getInstance();
		portoes = new ArrayList<Portao>();
		
		for (int i = 0; i < numeroDePortoes; i++) {
			portoes.add(new Portao());
		}
		
		this.IDportaoDisponvel = 0;
	}
	
	public void autorizarPouso(){
		if(pistaPouso.entrar()){
			System.out.println("Pouso concedido!");
		} else {
			System.out.println("Pista de pouso congestionada! Aguarde um pouco!");
		}
	}
	
	public void autorizarDecolagem(){
		if(pistaPouso.sair()){
			System.out.println("Decolagem concedida!");
		} else {
			System.out.println("Pista de pouso congestionada! Aguarde um pouco!");
		}
	}
	
	public void autorizarAcessoAPortao(){
		if(haPortoesDisponiveis()){
			acessarPortao(portoes.get(IDportaoDisponvel));
		} else {
			int nextDisponivel = 0;
			for (Portao portao : portoes) {
				if(portao.estaDisponivel()){
					IDportaoDisponvel = nextDisponivel + 1;
					acessarPortao(portao);
				} else {
					nextDisponivel++;
				}
			}
			
			if(nextDisponivel >= portoes.size()){
				System.out.println("Portões congestionados! Aguarde um pouco!");
			}
		}
	}
	
	private void acessarPortao(Portao portao){
		if(portao.entrar()){
			System.out.println(mensagemAcessoAPortao(portao));
			IDportaoDisponvel++;
		} else {
			System.out.println("Portões congestionados! Aguarde um pouco!");
		}
	}
	
	private String mensagemAcessoAPortao(Portao portao){
		return "Dirigir-se ao " + portao;
	}
	
	private boolean haPortoesDisponiveis(){
		return this.IDportaoDisponvel < portoes.size();
	}
	
}
