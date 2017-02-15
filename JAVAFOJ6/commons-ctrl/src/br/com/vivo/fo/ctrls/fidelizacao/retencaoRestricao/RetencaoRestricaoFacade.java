package br.com.vivo.fo.ctrls.fidelizacao.retencaoRestricao;

import javax.ejb.Local;

@Local
public interface RetencaoRestricaoFacade {

    public br.com.vivo.fo.fidelizacao.vo.ConsultaAdimplenciaVODocument.ConsultaAdimplenciaVO validarAdimplencia(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.ConsultaAdimplenciaVODocument.ConsultaAdimplenciaVO consultaAdimplenciaVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO getDadosAnaliseCredito(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO retencaoAnaliseCredito) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void finalizarAnaliseCredito(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO getDadosAnaliseEndereco(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO retencaoAnaliseCredito) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void finalizarAnaliseEndereco(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public java.lang.String getMotivosAlteracaoEndereco(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void executarBaixa(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO retensaoSAPVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;
}
