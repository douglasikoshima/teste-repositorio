package br.com.vivo.fo.ctrls.VOLTAV.manterMenu;

import javax.ejb.Local;

@Local
public interface ManterMenuFacade {

    public br.com.vivo.fo.voltav.vo.VOLTAVManterMenuVODocument.VOLTAVManterMenuVO getManterMenu(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
