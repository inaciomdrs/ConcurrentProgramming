package br.ufrn.imd.monitors.autorizacao;

import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class AutorizacaoPousoUrgente implements IAutorizacaoCommand {

	@Override
	public boolean solicitarAutorizacao(TorreDeControle torreControle) {
		return torreControle.autorizarPouso();
	}

}
