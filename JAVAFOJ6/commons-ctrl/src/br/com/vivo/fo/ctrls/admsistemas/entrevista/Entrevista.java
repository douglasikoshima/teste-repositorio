package br.com.vivo.fo.ctrls.admsistemas.entrevista;

import javax.ejb.Local;

@Local
public interface Entrevista {

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO carregaArvoreBaixaAssociada(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaPesquisaVODocument.AdmArvoreBaixaPesquisaVO dados, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removerItemArvoreBaixa(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaPesquisaVODocument.AdmArvoreBaixaPesquisaVO dados, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void adicionarItemArvoreBaixa(br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaPesquisaVODocument.AdmArvoreBaixaPesquisaVO dados, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
