package br.ufrn.imd.monitors.aviao;

public abstract class Aeronave extends Thread {
	
	private int id;
	private EstadoDeSaude estadoSaude;
	private Localizacao localizacao;
	
	public Aeronave(int id){
		this.id = id;
		setEstadoSaude(EstadoDeSaude.SAUDAVEL);
		setLocalizacao(Localizacao.NO_AR);
	}
	
	public Aeronave(){
		this(((int) Math.random() * 1000));
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
		
	public String toString(){
		return "Aeronave "+this.id;
	}

}
