package br.com.vivo.fo.ctrls.VOLTAV.manterDaemonDesativacaoComprovanteEmail;

import javax.ejb.Local;

@Local
public interface manterDaemonDesativacaoComprovanteEmail {

    public br.com.vivo.fo.cliente.vo.FilaComprovanteEmailVODocument.FilaComprovanteEmailVO manterDaemon(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
