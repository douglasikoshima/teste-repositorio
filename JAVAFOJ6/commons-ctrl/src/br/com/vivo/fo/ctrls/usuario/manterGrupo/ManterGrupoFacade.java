package br.com.vivo.fo.ctrls.usuario.manterGrupo;

import javax.ejb.Local;

@SuppressWarnings("rawtypes")
@Local
public interface ManterGrupoFacade {

	public void setRegrasEncaminhamento(java.lang.String user, java.util.HashMap gravarElementos) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO listaGrupo(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO editaGrupo(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO grupoVOedita, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO cadastraGrupo(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO grupoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO removeGrupo(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO grupoVOremove, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO pesquisaGrupo(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO grupoVOpesquisa, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarGrupoCanalVODocument.RelacionarGrupoCanalVO listaRelacionarGrupoCanal(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO grupoRelacionarCanal, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarGrupoPerfilVODocument.RelacionarGrupoPerfilVO listaRelacionarGrupoPerfil(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO grupoRelacionarPerfil, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarGrupoCanalVODocument.RelacionarGrupoCanalVO salvaGrupoCanalRelacionado(br.com.vivo.fo.usuario.vo.GrupoCanalRelacionadoVODocument.GrupoCanalRelacionadoVO grupoCanalRelacionado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO cadastraPerfil(br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO perfilVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO editaPerfil(br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO perfilVOedita, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO listaPerfil(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO removePerfil(br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO perfilVOremove, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO pesquisaPerfil(br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO perfilVOpesquisa, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SistemasUsuarioVODocument.SistemasUsuarioVO listaSistemas(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GrupamentosUsuarioVODocument.GrupamentosUsuarioVO salvaPerfilGrupamentoRelacionados(br.com.vivo.fo.usuario.vo.PerfilGrupamentoUsuarioVODocument.PerfilGrupamentoUsuarioVO perfilGrupamentoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO salvaPerfilUnidadesRelacionadas(br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO perfilUnidadeVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GrupamentosUsuarioVODocument.GrupamentosUsuarioVO listaRelacionarPerfilGrupamento(br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO sistemaRecuperaAgrupamentos, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO listaRelacionarPerfilUnidades(br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO listaUnidades, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO getTiposGrupo(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO getListaSkill(java.lang.String user, br.com.vivo.fo.usuario.vo.PesquisaSkillVODocument.PesquisaSkillVO pesquisaSkill) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removeSkill(java.lang.String user, java.lang.String idSkill) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO inserirAlterarSkill(java.lang.String user, br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO manterSkillVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO getRegrasEncaminhamento(java.lang.String user, java.lang.String idSkill) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarGrupoPerfilVODocument.RelacionarGrupoPerfilVO salvarGrupoPerfilRelacionado(br.com.vivo.fo.usuario.vo.GrupoPerfilRelacionadoVODocument.GrupoPerfilRelacionadoVO grupoPerfilRelacionado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setHistoricoConfigVariaveis(java.lang.String user, java.lang.String dsLogin, java.lang.String idGrupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
