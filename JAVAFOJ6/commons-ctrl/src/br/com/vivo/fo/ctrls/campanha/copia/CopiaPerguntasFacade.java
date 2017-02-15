package br.com.vivo.fo.ctrls.campanha.copia;

import javax.ejb.Local;

@Local
public interface CopiaPerguntasFacade {

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addCopia(java.lang.String user, int idCanalOrigem, int idCanalDestino) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;
}
