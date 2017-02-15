package br.com.vivo.fo.ctrls.admsistemas.link;

import javax.ejb.Local;

@Local
public interface Link {

    public br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO montaTelaLink(br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO idContato, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO removeLink(br.com.vivo.fo.admsistemas.vo.AdmObjetoLinkVODocument.AdmObjetoLinkVO link, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO salvaLinkEditado(br.com.vivo.fo.admsistemas.vo.AdmObjetoLinkVODocument.AdmObjetoLinkVO link, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO inserirLink(br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO link, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
