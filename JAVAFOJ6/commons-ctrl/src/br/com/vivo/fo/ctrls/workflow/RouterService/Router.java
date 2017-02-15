package br.com.vivo.fo.ctrls.workflow.RouterService;

import javax.ejb.Local;

@Local
public interface Router {

    /**
     * M�todo respons�vel por montar o xml de chamada do servi�o tuxedo SSKlunk
     * -> RouterSvc por script body
     * @author Alexandre Nunes
     * @version 1.0
     * @param user O nome do usu�rio respons�vel pela execu��o do servi�o script O Script a ser executado xml O xml-body a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo servi�o tuxedo
     * @throws TuxedoException � um erro retornado pelo servi�o tuxedo ou montado dinamicamente pelo met�do TrataCodigoExecucao()
     * @see executar
     */
    public java.lang.String executarScriptXMLBody(java.lang.String user, java.lang.String script, java.lang.String xmlBody) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * M�todo respons�vel por montar o xml de chamada do servi�o tuxedo SSKlunk
     * -> RouterSvc pelo script disponivel no banco de dados
     * @author Alexandre Nunes
     * @version 1.0
     * @param user O nome do usu�rio respons�vel pela execu��o do servi�o script O Script a ser executado xml O xml-body a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo servi�o tuxedo
     * @throws TuxedoException � um erro retornado pelo servi�o tuxedo ou montado dinamicamente pelo met�do TrataCodigoExecucao()
     * @see executar
     */
    public java.lang.String executarScriptXMLDB(java.lang.String user, int scriptDBID, java.lang.String xmlBody) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
