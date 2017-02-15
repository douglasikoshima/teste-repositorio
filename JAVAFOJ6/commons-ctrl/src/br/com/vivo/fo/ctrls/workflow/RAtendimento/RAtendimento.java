package br.com.vivo.fo.ctrls.workflow.RAtendimento;

import javax.ejb.Local;

@Local
public interface RAtendimento {

    public br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument.FormularioProcessoVO getFormularioProcessoVO(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO getPesquisaGruposCRI(java.lang.String user, br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO filtro) throws java.lang.Exception;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO getMsgInboxRC(java.lang.String user, br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO getInboxRC(java.lang.String user, br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public void obtemProximoProcesso(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.FacadeException;

    public void analistaDisponivelRC2(java.lang.String user, java.lang.String inDisponvivel) throws br.com.vivo.fo.exception.FacadeException;

    public void analistaDisponivelRC(java.lang.String user, java.lang.String inDisponvivel) throws br.com.vivo.fo.exception.FacadeException;

    public void analistaDisponivel2(java.lang.String user, java.lang.String inDisponvivel, int tipoInBox) throws br.com.vivo.fo.exception.FacadeException;

    public void analistaDisponivel(java.lang.String user, java.lang.String inDisponvivel, int tipoInBox) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument.EncerramentoVO getEncerramentoVO(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO getDetalheAtendimento(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO getFormularioVO(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVO(java.lang.String user, java.lang.String idAtendimento, java.lang.String idGrupo, java.lang.String inCRI, java.lang.String inRC, java.lang.String inSupervisor) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoAbaRelacionamentoVO(java.lang.String user, java.lang.String idAtendimento, java.lang.String idGrupo, java.lang.String inCRI, java.lang.String inRC, java.lang.String inSupervisor) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.FaseVODocument.FaseVO[] getFasesNiveisAtendimento(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO getDadosPessoa(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.StringComumVODocument.StringComumVO getDescricaoArvoreAtendimento(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.StringComumVODocument.StringComumVO getComentarioHistorico(java.lang.String user, java.lang.String idAndamento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO getDadosContato(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO getInboxCRI(java.lang.String user, br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO getOperacaoNota(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO getWFMotivosVO(java.lang.String user, java.lang.String pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO getWFAtdNotasVO(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO getMsgInboxCRI(java.lang.String user, br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO getRWFAtendimentoVO(java.lang.String user, br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFInboxUserVODocument.RWFInboxUserVO getUsuarioCampanha(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO getDadosContatoEx(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.StringComumVODocument.StringComumVO getDescricaoArvoreAtendimentoEx(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO getDadosPessoaEx(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.FaseVODocument.FaseVO[] getFasesNiveisAtendimentoEx(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoAbaRelacionamentoVOEx(java.lang.String user, java.lang.String idAtendimento, java.lang.String idGrupo, java.lang.String inCRI, java.lang.String inRC, java.lang.String inSupervisor) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVOEx(java.lang.String user, java.lang.String idAtendimento, java.lang.String idGrupo, java.lang.String inCRI, java.lang.String inRC, java.lang.String inSupervisor) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO getFormularioVOEx(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument.FormularioProcessoVO getFormularioProcessoVOEx(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO getDetalheAtendimentoEx(java.lang.String user, java.lang.String idAtendimento, boolean inHistoricoMG) throws br.com.vivo.fo.exception.FacadeException;
}
