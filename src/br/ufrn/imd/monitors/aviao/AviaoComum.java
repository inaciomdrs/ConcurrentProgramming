package br.ufrn.imd.monitors.aviao;

import br.ufrn.imd.monitors.autorizacao.AutorizacaoAcessarPortao;
import br.ufrn.imd.monitors.autorizacao.AutorizacaoDecolar;
import br.ufrn.imd.monitors.autorizacao.AutorizacaoPouso;
import br.ufrn.imd.monitors.autorizacao.AutorizacaoPousoUrgente;
import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class AviaoComum extends Aeronave {

	public AviaoComum(TorreDeControle torre) {
		super(torre);
	}
	
	private enum TIPO_REQUISICAO { POUSO, ACESSO_PORTAO, DECOLAGEM };
	
	public void run(){
		int NUMERO_ITERACOES = 10;
		
		TIPO_REQUISICAO requisicao = TIPO_REQUISICAO.POUSO;
		
		for (int i = 0; i < NUMERO_ITERACOES; i++) {
			System.out.println(this + " está " + getLocalizacao());
			if(getLocalizacao() == Localizacao.NO_AR){
				System.out.println(this + ": permissão para pousar");
				requisicao = TIPO_REQUISICAO.POUSO;
				realizarPouso();
			} else if (requisicao != TIPO_REQUISICAO.ACESSO_PORTAO) {
				System.out.println(this + ": permissão para acessar o portão");
				setAutorizacao(new AutorizacaoAcessarPortao());
				requisicao = TIPO_REQUISICAO.ACESSO_PORTAO;
			} else {
				System.out.println(this + ": permissão para decolar");
				setAutorizacao(new AutorizacaoDecolar());
				requisicao = TIPO_REQUISICAO.DECOLAGEM;
			}
			
			boolean sucessoNaRequisicao = getAutorizacao().solicitarAutorizacao(getTorre());
			
			
			
			System.out.println("Entendido Torre...");
		}
	}
	
	private void realizarPouso(){
		if(getEstadoSaude() == EstadoDeSaude.SAUDAVEL){
			setAutorizacao(new AutorizacaoPouso());	
		} else {
			setAutorizacao(new AutorizacaoPousoUrgente());
		}
	}
	
}
