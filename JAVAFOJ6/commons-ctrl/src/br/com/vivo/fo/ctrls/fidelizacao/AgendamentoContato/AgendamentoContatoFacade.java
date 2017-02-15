package br.com.vivo.fo.ctrls.fidelizacao.AgendamentoContato;

import javax.ejb.Local;

@Local
public interface AgendamentoContatoFacade {

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO agendarContato2(java.lang.String user, java.lang.String[] dados, br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO ofertasReal, java.lang.String ofertasAceita) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO agendarContato(java.lang.String user, java.lang.String[] dados, java.lang.String[] ofertasReal, java.lang.String ofertasAceita) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ListaHistoricoAgendamentoVODocument.ListaHistoricoAgendamentoVO getHistoricoAgendamento(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
