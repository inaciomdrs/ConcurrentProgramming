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
	
	public synchronized int concederAcessoPistaPouso(String requisitante){
		System.out.print("[Situação da pista: " + pistaPouso.estaDisponivel() + "] ");
		int portaoAcesso = haPortaoDisponivel();
		boolean resultado;

		if((portaoAcesso != -1)){
			resultado = pistaPouso.entrar(); 
			if(resultado){
				System.out.println("Torre para " + requisitante + ": Permissão para acesso à pista de pouso concedida!");
				concederAcessoPortao(requisitante,portaoAcesso);
				pistaPouso.sair();
				return portaoAcesso;	
			} else {
				System.out.println("Torre para " + requisitante + ": pista lotada! Aguarde um pouco!");
				return -1;
			}
		} else {
			String problem = (portaoAcesso == -1) ? "Portões lotados!" : "Pista congestionada!";
			
			System.out.println("Torre para " + requisitante + ": "+ problem +" Aguarde um pouco!");
			return -1;	
		}
	}
	
	public synchronized boolean liberarPistaPouso(String requisitante, int numeroPortao){
		System.out.print("[Situação da pista: " + pistaPouso.estaDisponivel() + "] ");
		boolean resultado = pistaPouso.sair();
		if(resultado){
			System.out.println("Torre para " + requisitante + ": Pista de pouso liberada!");
			liberarPortao(requisitante,numeroPortao);
			return true;
		} else {
			System.out.println("Torre para " + requisitante + ": Pista de pouso ocupada!");
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
	
	private void concederAcessoPortao(String requisitante, int numero){
		if(portoes.get(numero).entrar()){
			System.out.println(mensagemAcessoAPortao(requisitante,portoes.get(numero)));	
		} else {
			System.out.println("Torre para " + requisitante + ": Portão " + numero + " NÃO liberado!");
		}
		
	}
	
	public boolean liberarPortao(String requisitante, int numeroPortao){
		if(portoes.get(numeroPortao).sair()){
			System.out.println("Torre para " + requisitante + ": Portão " + numeroPortao + " liberado!");
			return true;
		} else {
			System.out.println("Torre para " + requisitante + ": Portão " + numeroPortao + " NÃO liberado!");
			return false;
		}
	}
	
	private String mensagemAcessoAPortao(String requisitante, Portao portao){
		return "Torre para " + requisitante + ": Dirigir-se ao " + portao;
	}
		
}
