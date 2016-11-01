package br.ufrn.imd.monitors.autorizacao;

import br.ufrn.imd.monitors.recursos.TorreDeControle;

public interface AutorizacaoCommand<T> {
	
	public abstract T solicitarAutorizacao(String requisitante, TorreDeControle torreControle);

}
