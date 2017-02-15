package br.com.vivo.fo.ctrls.VOLTAV.manterTerminal;

import javax.ejb.Local;

@Local
public interface ManterTerminalFacade {

    public br.com.vivo.fo.cliente.vo.ListaMunicipioVODocument.ListaMunicipioVO obterComboMunicipio(java.lang.String user, java.lang.String uf) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaLojaVODocument.ListaLojaVO obterComboLoja(java.lang.String user, java.lang.String idUF, java.lang.String nmMunicipio) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ListaTerminaisVODocument.ListaTerminaisVO obterComboTerminal(java.lang.String user, java.lang.String loja) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaLojaVODocument.ListaLojaVO pesquisarNomeLoja(java.lang.String user, java.lang.String nomeLoja) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO selecionarEnderecoLoja(java.lang.String user, java.lang.String loja) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ManterTerminalVODocument.ManterTerminalVO pesquisarTerminal(java.lang.String user, java.lang.String idTerminal, java.lang.String idPessoaDePara) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ListaUFOperadoraVODocument.ListaUFOperadoraVO obterComboUfOperadora(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void reiniciarSenhaLojista(java.lang.String user, java.lang.String idTerminal) throws br.com.vivo.fo.exception.FacadeException;

    public void incluirTerminal(java.lang.String user, br.com.vivo.fo.voltav.vo.ManterTerminalVODocument.ManterTerminalVO terminalVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaUFVODocument.ListaUFVO obterComboUF(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void excluirTerminal(java.lang.String user, java.lang.String idTerminal, java.lang.String idPessoaDePara, java.lang.String nmMunicipio, java.lang.String nmPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ListaTerminaisVODocument.ListaTerminaisVO pesquisarTerminais(java.lang.String user, java.lang.String uf, java.lang.String municipio, java.lang.String loja, java.lang.String terminal, java.lang.String recarga, java.lang.String pagamento, java.lang.String nrPagina) throws br.com.vivo.fo.exception.FacadeException;

    public void alterarTerminal(java.lang.String user, br.com.vivo.fo.voltav.vo.ManterTerminalVODocument.ManterTerminalVO terminalVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVUFOperadoraVODocument.VOLTAVUFOperadoraVO obterUFOperadora(java.lang.String user, java.lang.String uf) throws br.com.vivo.fo.exception.FacadeException;
}
