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
	
	public synchronized int concederAcessoPistaPouso(){
		int portaoAcesso = haPortaoDisponivel();
		
		if((portaoAcesso != -1) && pistaPouso.entrar()){
			System.out.println("Torre: Permissão para acesso à pista de pouso concedida!");
			concederAcessoPortao(portaoAcesso);
			pistaPouso.sair();
			return portaoAcesso;
		} else {
			String problem = (portaoAcesso == -1) ? "Portões lotados!" : "Pista congestionada!";
			
			System.out.println("Torre: "+ problem +" Aguarde um pouco!");
			return -1;	
		}
	}
	
	public synchronized boolean liberarPistaPouso(int numeroPortao){
		if(pistaPouso.sair()){
			System.out.println("Torre: Pista de pouso liberada!");
			liberarPortao(numeroPortao);
			return true;
		} else {
			System.out.println("Torre: Pista de pouso ocupada!");
			return false;
		}
	}
	
	private int haPortaoDisponivel(){
		int numPortoes = portoes.size();
		for (int i = 0; i < numPortoes; i++) {
			if(portoes.get(i).estaDisponivel()){
				return i;
			}
		}
		return -1;
	}
	
	private void concederAcessoPortao(int numero){
		portoes.get(numero).entrar();
		System.out.println(mensagemAcessoAPortao(portoes.get(numero)));
	}
	
	public boolean liberarPortao(int numeroPortao){
		if(portoes.get(numeroPortao).sair()){
			System.out.println("Torre: Portão " + numeroPortao + " liberado!");
			return true;
		} else {
			System.out.println("Torre: Portão " + numeroPortao + " NÃO liberado!");
			return false;
		}
	}
	
	private String mensagemAcessoAPortao(Portao portao){
		return "Torre: Dirigir-se ao " + portao;
	}
		
}
