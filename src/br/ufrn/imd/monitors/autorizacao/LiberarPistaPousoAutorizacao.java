package br.ufrn.imd.monitors.autorizacao;

import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class LiberarPistaPousoAutorizacao implements AutorizacaoCommand<Boolean> {

	private int numeroPortao;
	
	public LiberarPistaPousoAutorizacao(int numero) {
		this.numeroPortao = numero;
	}
	
	@Override
	public Boolean solicitarAutorizacao(TorreDeControle torreControle) {
		return torreControle.liberarPistaPouso(numeroPortao);
	}

}
