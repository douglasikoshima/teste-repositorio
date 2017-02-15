package br.com.vivo.fo.ctrls.cliente.prePago.manutencaoPrePago;

import javax.ejb.Local;

@SuppressWarnings("rawtypes")
@Local
public interface ManutencaoPrePagoFacade {

    public void gravaPrePago(java.lang.String user, br.com.vivo.fo.cliente.vo.ManterPrePagoVODocument.ManterPrePagoVO manterPrePagoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.util.ArrayList pesquisaEmailLegado(java.lang.String user, java.lang.String dsEmail, br.com.vivo.vol.menu.vo.ARVOREDocument.ARVORE[] arvore, java.lang.String serviceRouter) throws br.com.vivo.fo.exception.TuxedoException;

    public void persisteContaEmail(java.lang.String user, java.lang.String foneArea, java.lang.String foneNumero, java.lang.String idTipoLinha, java.lang.String email, java.lang.String serviceRouter) throws br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO getPesquisaPessoas(java.lang.String user, java.lang.String pesquisa, java.lang.String valor, java.lang.String nmMeio, java.lang.String nmSobreNome, java.lang.String inPrePago) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.CNAEVODocument.CNAEVO validaCNAE(java.lang.String user, java.lang.String nrCNAE) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaGrupoOpcoesVODocument.PesquisaGrupoOpcoesVO getIdPessoaGrupo(java.lang.String user, java.lang.String nrGrupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO getApoioParametro(java.lang.String user, br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO inParametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument.PrePagoRetornoValidaLinha validarLinha(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ManterPrePagoVODocument.ManterPrePagoVO pesquisaNumeroLinha(java.lang.String user, br.com.vivo.fo.cliente.vo.PrePagoPesquisaVODocument.PrePagoPesquisaVO manterPrePagoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ManterPrePagoVODocument.ManterPrePagoVO getPessoaPrePago(java.lang.String user, br.com.vivo.fo.cliente.vo.ManterPrePagoVODocument.ManterPrePagoVO manterPrePagoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
