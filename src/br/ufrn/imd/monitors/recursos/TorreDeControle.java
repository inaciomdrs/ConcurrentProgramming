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
	
	public boolean concederAcessoPistaPouso(){
		if(pistaPouso.entrar()){
			System.out.println("Torre: Permissão para acesso à pista de pouso concedida!");
			return true;
		} else {
			System.out.println("Torre: Pista congestionada! Aguarde um pouco!");
			return false;	
		}
	}
	
	public boolean liberarPistaPouso(){
		if(pistaPouso.sair()){
			System.out.println("Torre: Pista de pouso liberada!");
			return true;
		} else {
			System.out.println("Torre: Pista de pouso continua ocupada!");
			return false;	
		}
	}
	
	public int concederAcessoPortao(){
		while(!portoes.get(IDportaoDisponvel).estaDisponivel()){
			IDportaoDisponvel++;
			if(IDportaoDisponvel == portoes.size()){
				break;
			}
		}
		
		if((IDportaoDisponvel >= portoes.size()) || (!portoes.get(IDportaoDisponvel).estaDisponivel())){
			IDportaoDisponvel = 0;
			System.out.println("Torre: Todos os portões estão lotados! Aguarde um pouco");
			return -1;	
		} else {
			System.out.println(mensagemAcessoAPortao(portoes.get(IDportaoDisponvel)));
			liberarPistaPouso();
			IDportaoDisponvel++;
			
			if(IDportaoDisponvel == portoes.size()){
				IDportaoDisponvel = portoes.size() - 1;
				return IDportaoDisponvel; 
			} else {
				return IDportaoDisponvel-1;
			}
		}
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
