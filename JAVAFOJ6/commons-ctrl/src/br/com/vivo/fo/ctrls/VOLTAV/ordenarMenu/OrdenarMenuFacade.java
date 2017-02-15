package br.com.vivo.fo.ctrls.VOLTAV.ordenarMenu;

import javax.ejb.Local;

@Local
public interface OrdenarMenuFacade {

    public br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO obterComboSubSistema(java.lang.String user, java.lang.String idCanal) throws br.com.vivo.fo.exception.FacadeException;

    public void gravar(java.lang.String user, br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO itens) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO pesquisarMenu(java.lang.String user, java.lang.String idCanal, java.lang.String idItemMenu) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO pesquisarItensMenu(java.lang.String user, java.lang.String idCanal, java.lang.String idItemMenu) throws br.com.vivo.fo.exception.FacadeException;
}
