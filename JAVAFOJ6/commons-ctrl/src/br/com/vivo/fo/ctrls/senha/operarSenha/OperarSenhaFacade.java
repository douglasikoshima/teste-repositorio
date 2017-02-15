package br.com.vivo.fo.ctrls.senha.operarSenha;

import javax.ejb.Local;

@Local
public interface OperarSenhaFacade {

    public void GeraSenha(java.lang.String idPessoa, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.senha.vo.SenhaOperarVODocument.SenhaOperarVO ReiniciaSenha(java.lang.String indFrase, java.lang.String foneArea, java.lang.String foneNumero, java.lang.String idPessoa, java.lang.String idPessoaUsr, java.lang.String titularidadeSenha, java.lang.String senha, java.lang.String idCanal, java.lang.String motivo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void AlteraSenha(java.lang.String foneArea, java.lang.String foneNumero, java.lang.String idPessoa, java.lang.String titularidadeSenha, java.lang.String senha, java.lang.String idCanal, java.lang.String motivo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String ValidaSenha(java.lang.String foneArea, java.lang.String foneNumero, java.lang.String senha, java.lang.String idCanal, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.senha.vo.SenhaOperarVODocument.SenhaOperarVO solicitarSenha(java.lang.String indFrase, java.lang.String foneArea, java.lang.String foneNumero, java.lang.String idPessoa, java.lang.String idPessoaUsr, java.lang.String titularidadeSenha, java.lang.String senha, java.lang.String idCanal, java.lang.String motivo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.WarningException;
}
