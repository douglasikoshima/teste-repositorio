package br.com.vivo.fo.ctrls.workflow.RouterService;

import javax.ejb.Local;

@Local
public interface Router {

    /**
     * Método responsável por montar o xml de chamada do serviço tuxedo SSKlunk
     * -> RouterSvc por script body
     * @author Alexandre Nunes
     * @version 1.0
     * @param user O nome do usuário responsável pela execução do serviço script O Script a ser executado xml O xml-body a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo serviço tuxedo
     * @throws TuxedoException é um erro retornado pelo serviço tuxedo ou montado dinamicamente pelo metódo TrataCodigoExecucao()
     * @see executar
     */
    public java.lang.String executarScriptXMLBody(java.lang.String user, java.lang.String script, java.lang.String xmlBody) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Método responsável por montar o xml de chamada do serviço tuxedo SSKlunk
     * -> RouterSvc pelo script disponivel no banco de dados
     * @author Alexandre Nunes
     * @version 1.0
     * @param user O nome do usuário responsável pela execução do serviço script O Script a ser executado xml O xml-body a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo serviço tuxedo
     * @throws TuxedoException é um erro retornado pelo serviço tuxedo ou montado dinamicamente pelo metódo TrataCodigoExecucao()
     * @see executar
     */
    public java.lang.String executarScriptXMLDB(java.lang.String user, int scriptDBID, java.lang.String xmlBody) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
