package br.com.vivo.fo.ctrls.campanha.manter;

import javax.ejb.Local;

@Local
public interface CampanhaFacade {

    public void addParametrosCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument.CampanhaParametrosVO paramCampanha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setSubCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.SubCampanhaVODocument.SubCampanhaVO subCampanha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setSubCampanhaVersao(java.lang.String user, br.com.vivo.fo.campanha.vo.SubCampanhaVODocument.SubCampanhaVO subCampanha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO selApoioCampanhaMotivo(java.lang.String user, java.lang.String descricao, java.lang.String sigla, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO selApoioCampanhaSubMotivo(java.lang.String user, java.lang.String descricao, java.lang.String sigla, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO selApoioCampanha(java.lang.String user, java.lang.String descricao, java.lang.String sigla, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void addApoioCampanha(java.lang.String user, java.lang.String descricao, java.lang.String sigla) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void addApoioCampanhaTipoCampanha(java.lang.String user, java.lang.String descricao, java.lang.String sigla) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void addApoioCampanhaSubMotivo(java.lang.String user, java.lang.String descricao, java.lang.String sigla) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setApoioCampanha(java.lang.String user, int iCodigo, java.lang.String descricao, java.lang.String sigla) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setApoioCampanhaTipoCampanha(java.lang.String user, int iCodigo, java.lang.String descricao, java.lang.String sigla) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setApoioCampanhaSubMotivo(java.lang.String user, int iCodigo, java.lang.String descricao, java.lang.String sigla) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delApoioCampanha(java.lang.String user, int iCodigo) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delApoioCampanhaSubMotivo(java.lang.String user, int iCodigo) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delApoioCampanhaMotivo(java.lang.String user, int iCodigo) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delApoioCampanhaTipoCampanha(java.lang.String user, int iCodigo) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoCampanhaVODocument.RetornoCampanhaVO addSubCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.SubCampanhaVODocument.SubCampanhaVO subCampanha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO criarVersao(java.lang.String user, java.lang.String idSubCampanhaHistoricoOrigem) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setParametrosSubCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument.CampanhaParametrosVO campanhaParam) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void addApoioCampanhaMotivo(java.lang.String user, java.lang.String descricao, java.lang.String sigla, java.lang.String aderiu) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setApoioCampanhaMotivo(java.lang.String user, int iCodigo, java.lang.String descricao, java.lang.String sigla, java.lang.String aderiu) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getGrupoCampanhaApoio(java.lang.String user, java.lang.String operacao) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument.CampanhaParametrosVO getParametrosSubCampanha(java.lang.String user, java.lang.String idCanalCampanha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.SubCampanhaVODocument.SubCampanhaVO getSubCampanha(java.lang.String user, int idSubCampanhaFixa, int idSubCampanhaHistorico) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
