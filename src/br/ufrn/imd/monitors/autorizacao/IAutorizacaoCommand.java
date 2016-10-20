package br.ufrn.imd.monitors.autorizacao;

import br.ufrn.imd.monitors.recursos.TorreDeControle;

public interface IAutorizacaoCommand {
	
	public void solicitarAutorizacao(TorreDeControle torreControle);

}
