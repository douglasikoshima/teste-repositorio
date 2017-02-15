package br.com.vivo.fo.ctrls.admsistemas.retornoCTI;

import javax.ejb.Local;

@Local
public interface RetornoCTI {

    /**
     * Método responsável por iniciar a montagem da tela de retorno cti com a
     * pesquisa informada
     * @author Alexandre Nunes
     * @version 1.0
     * @param user String com o código do usuário logado na aplicação
     * @return RetornoWFCTIResultadoVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument.RetornoWFCTIResultadoVO pesquisar(java.lang.String user, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Método responsável por iniciar a consulta/edição de um registro do retorno
     * cti
     * @author Alexandre Nunes
     * @version 1.0
     * @param user String com o código do usuário logado na aplicação
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO consultarRetornoWFCTIVO(java.lang.String user, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Método responsável por iniciar a consulta/edição de um registro do retorno
     * cti
     * @author Denys Sene
     * @version 1.0
     * @param user String com o código do usuário logado na aplicação
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO carregarGrupos(java.lang.String user, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * Método responsável por gerneciar os processos de:
     * inclusão/alteração/exclusão conforme o metodo informado
     * @author Alexandre Nunes
     * @version 1.0
     * @param user String com o código do usuário logado na aplicação
     * @param operacao int com o codigo da operaçao a realizar 0=Inclusão; 1=Alteração; 2=Exclusao
     * @param retornoWFCTIVO a classe com o conteudo a ser sensibilizado no banco de dados
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO gravarRetornoWFCTIVO(java.lang.String user, int operacao, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
