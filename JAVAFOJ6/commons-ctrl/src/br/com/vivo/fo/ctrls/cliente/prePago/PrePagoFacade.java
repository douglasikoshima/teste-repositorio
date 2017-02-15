package br.com.vivo.fo.ctrls.cliente.prePago;

import javax.ejb.Local;

@Local
public interface PrePagoFacade {

    /**
     * Valida se a linha é válida
     */
    public br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument.PrePagoRetornoValidaLinha validarLinha(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Recupera dados de Lista para a Tela
     */
    public br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO getListasVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Busca os dados de Pessoa PrePago atraves do numero do documento
     */
    public br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO getPrePagoVOById(java.lang.String user, java.lang.String idTipoPessoa, java.lang.String idLinhaTelefonica, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.CNAEVODocument.CNAEVO validaCNAE(java.lang.String user, java.lang.String nrCNAE) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Busca os dados de Pessoa PrePago atraves do numero do documento
     */
    public br.com.vivo.fo.atendimento.vo.SETPPTMAVODocument.SETPPTMAVO setPrePagoVO(java.lang.String user, br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO prePagoVO) throws br.com.vivo.fo.exception.FacadeException;

    public boolean inSincronismo(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO getPessoaByDocumento(java.lang.String user, java.lang.String idTipoPessoa, java.lang.String idTipoDocumento, java.lang.String nrDocumento, boolean inPesquisaCliente, int nrPagina) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO getPessoaByDocumentoFiltro(java.lang.String user, java.lang.String idTipoPessoa, java.lang.String idTipoDocumento, java.lang.String nrDocumento, boolean inPesquisaCliente, int nrPagina, java.lang.String nrLinha, java.lang.String nrConta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
