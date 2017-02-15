package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;

@Local
public interface DescontoFacade {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO getListaMatriz(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO buscarListaPorMatriz(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO desconto) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO gravarMatriz(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO desconto) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
