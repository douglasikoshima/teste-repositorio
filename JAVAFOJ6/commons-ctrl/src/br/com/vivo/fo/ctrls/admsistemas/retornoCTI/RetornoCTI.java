package br.com.vivo.fo.ctrls.admsistemas.retornoCTI;

import javax.ejb.Local;

@Local
public interface RetornoCTI {

    /**
     * M�todo respons�vel por iniciar a montagem da tela de retorno cti com a
     * pesquisa informada
     * @author Alexandre Nunes
     * @version 1.0
     * @param user String com o c�digo do usu�rio logado na aplica��o
     * @return RetornoWFCTIResultadoVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException � um erro retornado pelo servi�o tuxedo
     * @throws AssemblerException � um erro retornado pelo mec�nismo de assembler
     * @throws FacadeException � um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument.RetornoWFCTIResultadoVO pesquisar(java.lang.String user, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * M�todo respons�vel por iniciar a consulta/edi��o de um registro do retorno
     * cti
     * @author Alexandre Nunes
     * @version 1.0
     * @param user String com o c�digo do usu�rio logado na aplica��o
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException � um erro retornado pelo servi�o tuxedo
     * @throws AssemblerException � um erro retornado pelo mec�nismo de assembler
     * @throws FacadeException � um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO consultarRetornoWFCTIVO(java.lang.String user, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * M�todo respons�vel por iniciar a consulta/edi��o de um registro do retorno
     * cti
     * @author Denys Sene
     * @version 1.0
     * @param user String com o c�digo do usu�rio logado na aplica��o
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException � um erro retornado pelo servi�o tuxedo
     * @throws AssemblerException � um erro retornado pelo mec�nismo de assembler
     * @throws FacadeException � um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO carregarGrupos(java.lang.String user, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    /**
     * M�todo respons�vel por gerneciar os processos de:
     * inclus�o/altera��o/exclus�o conforme o metodo informado
     * @author Alexandre Nunes
     * @version 1.0
     * @param user String com o c�digo do usu�rio logado na aplica��o
     * @param operacao int com o codigo da opera�ao a realizar 0=Inclus�o; 1=Altera��o; 2=Exclusao
     * @param retornoWFCTIVO a classe com o conteudo a ser sensibilizado no banco de dados
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException � um erro retornado pelo servi�o tuxedo
     * @throws AssemblerException � um erro retornado pelo mec�nismo de assembler
     * @throws FacadeException � um erro retornado no caso de erros desconhecidos
     */
    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO gravarRetornoWFCTIVO(java.lang.String user, int operacao, br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO retornoWFCTIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
