package br.com.vivo.fo.ctrls.cliente.prospect;

import javax.ejb.Local;

@Local
public interface ProspectFacade {

    public br.com.vivo.fo.cliente.vo.CadastroProspectVODocument.CadastroProspectVO getListas(java.lang.String user, java.lang.String idTipoPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String gravaProspect(br.com.vivo.fo.cliente.vo.CadastroProspectVODocument.CadastroProspectVO cadastroProspectVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
