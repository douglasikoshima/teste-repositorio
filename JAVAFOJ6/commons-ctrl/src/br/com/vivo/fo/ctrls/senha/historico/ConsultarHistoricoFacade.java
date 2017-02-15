package br.com.vivo.fo.ctrls.senha.historico;

import javax.ejb.Local;

@Local
public interface ConsultarHistoricoFacade {

    public br.com.vivo.fo.senha.vo.SenhaMovimentosVODocument.SenhaMovimentosVO ConsHistorico(java.lang.String inPesquisa, java.lang.String foneArea, java.lang.String foneNumero, java.lang.String idPessoa, java.lang.String titularidadeSenha, java.lang.String idPessoaCli, java.lang.String idPessoaUsr, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
