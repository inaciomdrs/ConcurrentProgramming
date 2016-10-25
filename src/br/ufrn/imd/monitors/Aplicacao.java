package br.ufrn.imd.monitors;

import br.ufrn.imd.monitors.aviao.AviaoComum;
import br.ufrn.imd.monitors.recursos.TorreDeControle;

public class Aplicacao {

	public static void main(String[] args) {
		
		int numeroDePortoes   = 1;
		int numeroDeAeronaves = 2;
		
		TorreDeControle torreControle = new TorreDeControle(numeroDePortoes);
		
		for (int i = 0; i < numeroDeAeronaves; i++) {
			new AviaoComum(i,torreControle).start();
		}

	}

}
