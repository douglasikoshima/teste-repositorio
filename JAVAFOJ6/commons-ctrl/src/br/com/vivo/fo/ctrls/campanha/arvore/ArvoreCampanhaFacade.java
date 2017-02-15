package br.com.vivo.fo.ctrls.campanha.arvore;

import javax.ejb.Local;

@Local
public interface ArvoreCampanhaFacade {
	
    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delArvoreSubCampanha(java.lang.String user, java.lang.String idSubCampanhaFixa, java.lang.String idSubCampanhaHistorico) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delArvoreCanalCampanha(java.lang.String user, java.lang.String idCanalCampanha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delArvorePergunta(java.lang.String user, java.lang.String idCanalCampanha, java.lang.String idPergunta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delArvoreResposta(java.lang.String user, java.lang.String idResposta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delArvoreMotivoCampanha(java.lang.String user, java.lang.String idSubCampanhaHistorico, java.lang.String idTipoStatusCampanha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ItemArvoreVODocument.ItemArvoreVO getArvoreCampanha(java.lang.String user, java.lang.String idCampanha, java.lang.String idSubCampanha, java.lang.String idCanalCampanha, java.lang.String xmlParam) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
