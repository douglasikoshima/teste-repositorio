package br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem;

import javax.ejb.Local;

@Local
public interface RelBlindagem {

	public br.com.vivo.fo.admsistemas.vo.RelatorioBlindagemVODocument.RelatorioBlindagemVO getRelatorioBlindagem(java.lang.String user, java.lang.String dtInicial, java.lang.String dtFinal);
}
