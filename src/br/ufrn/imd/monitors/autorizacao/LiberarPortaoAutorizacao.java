package br.ufrn.imd.monitors.autorizacao;

import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class LiberarPortaoAutorizacao implements AutorizacaoCommand<Boolean> {

	private int numeroPortao;
	
	public LiberarPortaoAutorizacao(int numero){
		this.numeroPortao = numero;
	}
	
	public void setNumeroPortao(int numero){
		this.numeroPortao = numero;
	}
	
	@Override
	public Boolean solicitarAutorizacao(TorreDeControle torreControle) {
		return torreControle.liberarPortao(numeroPortao);
	}

	

}
