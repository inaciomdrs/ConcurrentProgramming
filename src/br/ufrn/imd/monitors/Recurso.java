package br.ufrn.imd.monitors;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Recurso {
	
	private Lock locker;
	private boolean disponivel;
	
	public Recurso(){
		this.locker = new ReentrantLock();
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
			this.disponivel = true;
			return true;
		}
		return false;
	}
	

}
