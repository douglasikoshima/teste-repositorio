package br.com.vivo.fo.ctrls.campanha.configurar;

import javax.ejb.Local;

@Local
public interface ConfigurarCampanha {

    public void setPrioridadeCampanha(java.lang.String user, java.lang.String[] idSubCampanhaHistorico) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getGrupoConfigurarCampanhaVO(java.lang.String user, int iCampanhaID, int iSubCampanhaID, int inNegatividade, int processo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getMotivoId(java.lang.String user, int idCampanha, int idTipoStatusCampanha, int processo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getSubMotivoId(java.lang.String user, int idCampanha, int idStatus, int idMotivo, int processo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void addGrupoConfigurarCampanhaVO(java.lang.String user, int iSubCampanhaID, java.lang.String sStatusID, java.lang.String sMotivoID, java.lang.String sSubMotivoID) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getStatus(java.lang.String user, int idSubCampanhaHistorico, int processo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
