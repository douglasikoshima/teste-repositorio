package br.com.vivo.fo.ctrls.admsistemas.configArvoreContato;

import javax.ejb.Local;

@Local
public interface ArvoreContato {

    public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO carregaSelectsContatoFolhaVO(br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaRemoverVODocument.AdmContatoFolhaRemoverVO idContato, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO carregaArvoreContato(br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO admIdContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removeItemArvoreContato(br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaRemoverVODocument.AdmContatoFolhaRemoverVO contatoRemover, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvaItemArvoreContato(br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO contatoSalvar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void habilitaArvoreContato(br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO contatoFolhaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void copiaArvoreContato(br.com.vivo.fo.admsistemas.vo.AdmArvoreContatoCopiaVODocument.AdmArvoreContatoCopiaVO item, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvaItemEditadoArvoreContato(br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO contatoEditar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO getArvoreNome(br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO admIdContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmGrupoContatoContainerVODocument.AdmGrupoContatoContainerVO getListaAssocGrupoContato(java.lang.String idContato, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvaAssocGrupoContato(java.lang.String selecionados, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO getArvoreParametro(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO arvoreParametroVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO getArvoreConsulta(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO arvoreParametroVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmTipoArvoreVODocument.AdmTipoArvoreVO obterTipoArvore(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
