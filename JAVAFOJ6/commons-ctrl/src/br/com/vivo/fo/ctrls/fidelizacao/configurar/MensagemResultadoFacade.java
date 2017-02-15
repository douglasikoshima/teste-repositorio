package br.com.vivo.fo.ctrls.fidelizacao.configurar;

import javax.ejb.Local;

@Local
public interface MensagemResultadoFacade {

    public void delMensagemEncerramento(java.lang.String user, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void setMensagemResultado(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.MensagemResultadoVODocument.MensagemResultadoVO mensagemResultado) throws br.com.vivo.fo.exception.TuxedoException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getTpEncerramento(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getRegional(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addMensagemResultado(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.MensajePesquisaInVODocument.MensajePesquisaInVO msgPesqVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ListaMensagemResultadoVODocument.ListaMensagemResultadoVO getMensagemResultado(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.MensajePesquisaVODocument.MensajePesquisaVO msjPesVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getIntencaoCancelamento(java.lang.String user, java.lang.String idPerg, java.lang.String parametro, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
