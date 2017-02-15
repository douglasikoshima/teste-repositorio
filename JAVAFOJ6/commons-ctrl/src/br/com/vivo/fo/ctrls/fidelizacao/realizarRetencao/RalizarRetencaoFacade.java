package br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao;

import javax.ejb.Local;

@Local
public interface RalizarRetencaoFacade {

    public br.com.vivo.fo.fidelizacao.vo.ListaHistoricoRetencaoVODocument.ListaHistoricoRetencaoVO getHistorico(java.lang.String user, java.lang.String idLinha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO getDetalheHistorico(java.lang.String user, java.lang.String idCliente) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO getArvore(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getLigacaoIndevida(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getIntencaoCancelamento(java.lang.String user, java.lang.String IdUF, java.lang.String sgTipoPessoa, java.lang.String idSegmentacao, java.lang.String idGrupo, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getDestinosPrevistos(java.lang.String user, java.lang.String idUfOperadora, java.lang.String idIntencao, java.lang.String sgTipoPessoa, java.lang.String idSegmentacao, java.lang.String idGrupo, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO getLinha(java.lang.String user, java.lang.String idCliente, java.lang.String numero, boolean inPortout, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;
}
