package br.com.vivo.fo.ctrls.VOLTAV.campanhas;

import javax.ejb.Local;

@Local
public interface CampanhasFacade {

    public void setCampanhaVO(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaVODocument.CampanhaVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.CampanhaVODocument.CampanhaVO getCampanhaVO(java.lang.String user, java.lang.String serviceName, br.com.vivo.fo.campanha.vo.CampanhaVODocument.CampanhaVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
