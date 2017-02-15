package br.com.vivo.fo.ctrls.usuario.manterLote;

import javax.ejb.Local;

@Local
public interface ManterLoteFacade {

    public br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO getListaIdDescricaoVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
