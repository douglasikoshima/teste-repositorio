package br.com.vivo.fo.ctrls.admsistemas.configArvoreBaixa;

import javax.ejb.Local;

@Local
public interface ArvoreBaixa {

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO carregaArvoreBaixa(br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO admidBaixa, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removeItemBaixa(br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO item, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void insereItemBaixa(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO item, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO carregaDadosTelaInserir(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaConsultaVODocument.AdmArvoreBaixaConsultaVO dadosConsultas, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO carregaDadosTelaEditar(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaConsultaVODocument.AdmArvoreBaixaConsultaVO dadosConsultas, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvaItemBaixaEditado(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO item, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO getNome(br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO admIdBaixaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmAtualizacaoArvoreBaixaVODocument.AdmAtualizacaoArvoreBaixaVO habilitaArvoreBaixa(br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO item, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void habilitaArvoreBaixaUpdate(br.com.vivo.fo.admsistemas.vo.AdmAtualizacaoArvoreBaixaVODocument.AdmAtualizacaoArvoreBaixaVO arrayIdBaixa, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
