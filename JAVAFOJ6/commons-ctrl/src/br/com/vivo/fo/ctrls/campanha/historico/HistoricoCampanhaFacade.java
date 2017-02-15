package br.com.vivo.fo.ctrls.campanha.historico;

import javax.ejb.Local;

@Local
public interface HistoricoCampanhaFacade {

    public br.com.vivo.fo.campanha.vo.ListaCampanhaHistoricoVODocument.ListaCampanhaHistoricoVO getListaCampanhaHistorico(java.lang.String user, java.lang.String[] dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ItemArvoreVODocument.ItemArvoreVO getArvoreHistorico(java.lang.String user, java.lang.String idAtendimentoCampanha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
