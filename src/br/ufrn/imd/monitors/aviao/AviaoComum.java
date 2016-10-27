package br.ufrn.imd.monitors.aviao;

import br.ufrn.imd.monitors.autorizacao.AcessarPistaPousoAutorizacao;
import br.ufrn.imd.monitors.autorizacao.AutorizacaoCommand;
import br.ufrn.imd.monitors.autorizacao.LiberarPistaPousoAutorizacao;
import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class AviaoComum extends Aeronave {

	private int portaoAcessado;
	@SuppressWarnings("rawtypes")
	private AutorizacaoCommand autorizacao;

	public AviaoComum(TorreDeControle torre) {
		super(torre);
		portaoAcessado = -1;
	}

	public AviaoComum(int id, TorreDeControle torre) {
		super(id, torre);
		portaoAcessado = -1;
	}

	private void pousar() {
		System.out.println(this + ": solicito permissão para pouso.");
		autorizacao = new AcessarPistaPousoAutorizacao();
		portaoAcessado = (Integer) autorizacao.solicitarAutorizacao(getTorre());
		
		if(portaoAcessado != -1){
			System.out.println(this + ": agradecido torre! Pousando...");
			setLocalizacao(Localizacao.NA_TERRA);
		} else {
			System.out.println(this + ": entendido torre! No aguardo...");
		}
	}

	private void decolar(){
		System.out.println(this + ": solicito permissão para decolagem.");
		autorizacao = new LiberarPistaPousoAutorizacao(portaoAcessado);
		boolean podeDecolar = (Boolean) autorizacao.solicitarAutorizacao(getTorre());
		
		if(podeDecolar){
			System.out.println(this + ": agradecido torre! Decolando...");
			setLocalizacao(Localizacao.NO_AR);
		} else {
			System.out.println(this + ": entendido torre! No aguardo...");
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
