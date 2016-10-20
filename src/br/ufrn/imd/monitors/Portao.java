package br.ufrn.imd.monitors;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Portao {
	
	private Lock locker;
	private int id;
	private boolean disponivel;
	
	public Portao(int id){
		this.id = id;
		this.locker = new ReentrantLock();
	}
	
	public Portao(){
		this(((int) Math.random() * 1000));
	}
	
	public boolean estaDisponivel(){
		return this.disponivel;
	}
			
	public boolean entrar(){
		if(this.disponivel){
			this.locker.lock();
			this.disponivel = false;
			return true;
		}
		return false;
	}
	
	public boolean sair(){
		if(!this.disponivel){
			this.locker.unlock();
			this.disponivel = false;
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "Portão "+this.id;
	}

}
