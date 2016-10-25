package br.ufrn.imd.monitors.aviao;

import br.ufrn.imd.monitors.autorizacao.AcessarPistaPousoAutorizacao;
import br.ufrn.imd.monitors.autorizacao.AcessarPortaoAutorizacao;
import br.ufrn.imd.monitors.autorizacao.AutorizacaoCommand;
import br.ufrn.imd.monitors.autorizacao.LiberarPistaPousoAutorizacao;
import br.ufrn.imd.monitors.autorizacao.LiberarPortaoAutorizacao;
import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class AviaoComum extends Aeronave {

	private int portaoAcessado;

	public AviaoComum(TorreDeControle torre) {
		super(torre);
		portaoAcessado = -1;
	}

	public AviaoComum(int id, TorreDeControle torre) {
		super(id, torre);
		portaoAcessado = -1;
	}

	private void pousar() {
		System.out.println(this + ": Permissão para pouso!");

		AutorizacaoCommand<Boolean> autorizacao = new AcessarPistaPousoAutorizacao();
		if (autorizacao.solicitarAutorizacao(getTorre())) {
			System.out.println(this + ": pousando na pista...");
			setLocalizacao(Localizacao.NA_TERRA);
		} else {
			System.out.println(this + ": no aguardo então...");
		}
	}

	private void acessarPortao() {
		if (getLocalizacao() == Localizacao.NA_TERRA) {
			System.out.println(this + ": Permissão para entrar nos portões!");
			if (new LiberarPistaPousoAutorizacao().solicitarAutorizacao(getTorre())) {
				AutorizacaoCommand<Integer> autorizacao = new AcessarPortaoAutorizacao();
				portaoAcessado = autorizacao.solicitarAutorizacao(getTorre());
			}
		}
	}

	private void decolar() {
		if ((getLocalizacao() == Localizacao.NA_TERRA) && (portaoAcessado != -1)) {
			if ((new LiberarPortaoAutorizacao(portaoAcessado).solicitarAutorizacao(getTorre()))
					&& (new AcessarPistaPousoAutorizacao().solicitarAutorizacao(getTorre()))) {
				System.out.println(this + ": decolando...");
				if(new LiberarPistaPousoAutorizacao().solicitarAutorizacao(getTorre())){
					setLocalizacao(Localizacao.NO_AR);
				}
			}
		}
	}

	public void run() {
		int NUMERO_ITERACOES = 10;
		double probabilidadeProblemas = Math.random();

		int SEGUNDO_EM_MILESIMOS = 1000;

		for (int i = 0; i < NUMERO_ITERACOES; i++) {
			String local = getLocalizacao() == Localizacao.NA_TERRA ? "em solo" : "no ar";
			String saude = getEstadoSaude() == EstadoDeSaude.SAUDAVEL ? "saudável" : "com problemas";
			String prioridade = getPriority() == MIN_PRIORITY ? "mínima"
					: (getPriority() == NORM_PRIORITY ? "normal" : "máxima");

			System.out.println(this + " está " + local + " e " + saude + " (Prioridade: " + prioridade + ")");
			
			if(getLocalizacao() == Localizacao.NO_AR){
				pousar();
				acessarPortao();
			} else {
				decolar();
			}

			if (Math.random() <= probabilidadeProblemas) {
				setEstadoSaude(EstadoDeSaude.COM_PROBLEMAS);
			} else {
				setEstadoSaude(EstadoDeSaude.SAUDAVEL);
			}

			try {
				Thread.sleep(2 * SEGUNDO_EM_MILESIMOS);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}

}
