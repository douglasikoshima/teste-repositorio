package br.com.vivo.fo.ctrls.workflow.atendimentoDetalheACS;

import javax.ejb.Local;

@Local
public interface AtendimentoDetalheACSFacade {

    public br.com.vivo.fo.workflow.vo.AtendimentoDetalheACSMigracaoVODocument.AtendimentoDetalheACSMigracaoVO obtemDetalheACS(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
