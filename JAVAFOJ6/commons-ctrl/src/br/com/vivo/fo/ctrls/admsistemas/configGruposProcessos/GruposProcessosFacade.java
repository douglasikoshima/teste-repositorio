package br.com.vivo.fo.ctrls.admsistemas.configGruposProcessos;

import javax.ejb.Local;

@SuppressWarnings({"rawtypes"})
@Local
public interface GruposProcessosFacade {

    public br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument.GrupoProcessoVO getAllGruposProcessos(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO getGruposProcessos(java.lang.String user, java.lang.String node, java.lang.String filtroGrupos, java.lang.String type) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO getRegrasEncaminhamento(java.lang.String user, java.lang.Integer codGrupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO getGruposTratamentoNiveis(java.lang.String user, int codigoGrupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO setGruposProcessos(java.lang.String user, br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO gruposProcessosVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO setRegrasEncaminhamentoValidaSkill(java.lang.String user, java.util.HashMap gravarElementos, java.lang.String valida) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void setRegrasEncaminhamento(java.lang.String user, java.util.HashMap gravarElementos) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void setGruposTratamentoNiveis(java.lang.String user, int nivelSelecionado, java.util.HashMap gravarElementos) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
