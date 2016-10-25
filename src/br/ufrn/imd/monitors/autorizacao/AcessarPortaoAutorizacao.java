package br.ufrn.imd.monitors.autorizacao;

import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class AcessarPortaoAutorizacao implements AutorizacaoCommand<Integer> {

	@Override
	public Integer solicitarAutorizacao(TorreDeControle torreControle) {
		return torreControle.concederAcessoPortao();
	}

}
