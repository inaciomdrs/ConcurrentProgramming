package br.ufrn.imd.monitors.aviao;

import br.ufrn.imd.monitors.autorizacao.IAutorizacaoCommand;
import br.ufrn.imd.monitors.recursos.TorreDeControle;

public abstract class Aeronave extends Thread {
	
	private int id;
	private TorreDeControle torre;
	private IAutorizacaoCommand autorizacao;	
	private EstadoDeSaude estadoSaude;
	private Localizacao localizacao;
	
	public Aeronave(int id){
		this.id = id;
		setEstadoSaude(EstadoDeSaude.SAUDAVEL);
		setLocalizacao(Localizacao.NO_AR);
	}
	
	public Aeronave(TorreDeControle torre){
		this(((int) Math.random() * 1000));
		this.torre = torre;
	}
			
	public EstadoDeSaude getEstadoSaude() {
		return estadoSaude;
	}

	public void setEstadoSaude(EstadoDeSaude estadoSaude) {
		this.estadoSaude = estadoSaude;
		
		if(saudavelNaTerra() || comProblemasNoAr()){
			setPriority(MAX_PRIORITY);
		}
	}
	
	private boolean saudavelNaTerra(){
		return ((this.estadoSaude == EstadoDeSaude.SAUDAVEL) && (this.localizacao == Localizacao.NA_TERRA));
	}
	
	private boolean comProblemasNoAr(){
		return ((this.estadoSaude == EstadoDeSaude.COM_PROBLEMAS) && (this.localizacao == Localizacao.NO_AR));
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
		
		if(this.localizacao == Localizacao.NO_AR){
			setPriority(MAX_PRIORITY);
		} else {
			setPriority(MIN_PRIORITY);
		}
	}
	
	public TorreDeControle getTorre() {
		return torre;
	}

	public void setTorre(TorreDeControle torre) {
		this.torre = torre;
	}
		
	public String toString(){
		return "Aeronave "+this.id;
	}

	public IAutorizacaoCommand getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(IAutorizacaoCommand autorizacao) {
		this.autorizacao = autorizacao;
	}

}
