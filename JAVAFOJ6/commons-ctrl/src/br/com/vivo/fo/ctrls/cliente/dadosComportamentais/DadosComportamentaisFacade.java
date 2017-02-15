package br.com.vivo.fo.ctrls.cliente.dadosComportamentais;

import javax.ejb.Local;

@Local
public interface DadosComportamentaisFacade {

    public void setSalvarManterDadosComportamentais(java.lang.String user, br.com.vivo.fo.cliente.vo.ManterDadosComportamentaisVODocument.ManterDadosComportamentaisVO dadosComportamentais) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAssunto(java.lang.String user, br.com.vivo.fo.cliente.vo.AssuntoVODocument.AssuntoVO assuntoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlterarAssunto(java.lang.String user, br.com.vivo.fo.cliente.vo.AssuntoVODocument.AssuntoVO assuntoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setGravaSubAssunto(java.lang.String user, br.com.vivo.fo.cliente.vo.SubAssuntoGravacaoVODocument.SubAssuntoGravacaoVO subAssuntoGravacaoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlteraSubAssunto(java.lang.String user, br.com.vivo.fo.cliente.vo.SubAssuntoGravacaoVODocument.SubAssuntoGravacaoVO subAssuntoGravacaoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setGravaDadoComportamental(java.lang.String user, br.com.vivo.fo.cliente.vo.DadoComportamentalDocument.DadoComportamental dc) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlteraDadoComportamental(java.lang.String user, br.com.vivo.fo.cliente.vo.DadoComportamentalDocument.DadoComportamental dc) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlterarValorPossivel(java.lang.String user, br.com.vivo.fo.cliente.vo.ValorPossivelVODocument.ValorPossivelVO valorPossivelVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaConteudoVODocument.ListaConteudoVO getListas(java.lang.String user, java.lang.String inFiltro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.SubAssuntosDocument.SubAssuntos getListaSubAssuntos(java.lang.String user, int codigoAssunto, java.lang.String inFiltro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.DadosComportamentaisDocument.DadosComportamentais findDadosComportamentais(java.lang.String user, int codigoSubAssunto) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.AssuntosDocument.Assuntos getListaAssuntos(java.lang.String user, java.lang.String inFiltro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ConteudosDocument.Conteudos getListaConteudos(java.lang.String user, int codigoSubAssunto, java.lang.String inFiltro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ValoresPossiveisDocument.ValoresPossiveis findListaValoresPossiveis(java.lang.String user, int codigoConteudo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ManterDadosComportamentaisVODocument.ManterDadosComportamentaisVO getManterDadosComportamentais(java.lang.String user, java.lang.String idPessoa, java.lang.String idGrupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ManterDadosComportamentaisVODocument.ManterDadosComportamentaisVO getAtributosDadosComportamentais(java.lang.String user, java.lang.String idPessoa, java.lang.String idSubAssunto, java.lang.String idGrupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setGravarValorPossivel(java.lang.String user, br.com.vivo.fo.cliente.vo.ValorPossivelVODocument.ValorPossivelVO valorPossivelVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
