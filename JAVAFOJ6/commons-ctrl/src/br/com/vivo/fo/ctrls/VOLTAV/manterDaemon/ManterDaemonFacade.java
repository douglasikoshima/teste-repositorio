package br.com.vivo.fo.ctrls.VOLTAV.manterDaemon;

import javax.ejb.Local;

import br.com.vivo.fo.cliente.vo.FilaPrePagoVODocument.FilaPrePagoVO;

@Local
public interface ManterDaemonFacade {

    public FilaPrePagoVO manterDaemon(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
