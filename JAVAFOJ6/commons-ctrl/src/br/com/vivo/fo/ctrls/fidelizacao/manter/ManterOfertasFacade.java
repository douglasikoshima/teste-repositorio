package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;


@Local
public interface ManterOfertasFacade {

    public br.com.vivo.fo.fidelizacao.vo.ListaOfertaVODocument.ListaOfertaVO getOfertas(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void delOferta(java.lang.String user, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void setOferta(java.lang.String user, java.lang.String[] dados) throws br.com.vivo.fo.exception.TuxedoException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.FacadeException;

    public void addOferta(java.lang.String user, java.lang.String[] dados) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.GrupoApoioVODocument.GrupoApoioVO getDadosIniciais(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
