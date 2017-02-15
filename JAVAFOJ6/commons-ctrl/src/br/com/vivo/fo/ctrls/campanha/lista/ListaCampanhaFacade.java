package br.com.vivo.fo.ctrls.campanha.lista;

import javax.ejb.Local;

@Local
public interface ListaCampanhaFacade {

    public br.com.vivo.fo.campanha.vo.ListaCampanhaApoioVODocument.ListaCampanhaApoioVO getListasAssociadasCampanhaVO(java.lang.String user, int idCanalCampanha, int inNegative) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addUploadLista(java.lang.String user, java.lang.String idLista) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO checaUploadLista(java.lang.String user, java.lang.String nmLista, java.lang.String nmArquivoLista) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setListasAssociadasCampanhaVO(java.lang.String user, java.lang.String[] idLista, int idCanalCampanha, int inNegative) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ListaStatusCampanhaVODocument.ListaStatusCampanhaVO obterStatusLista(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ListasCampanhaVODocument.ListasCampanhaVO pesquisarListas(java.lang.String user, java.lang.String nmLista, java.lang.String inStatusCarga) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void delListaCamp(java.lang.String user, java.lang.String idLista) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
