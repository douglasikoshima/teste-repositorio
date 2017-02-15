package br.com.vivo.fo.ctrls.usuario.manterUsuario;

import javax.ejb.Local;

@Local
public interface ManterUsuarioFacade {

    public br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO listaSelectsUsuario(br.com.vivo.fo.usuario.vo.UsuarioSimplVODocument.UsuarioSimplVO usuarioSimplVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO listaOrganograma(br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO nivelCargoOrg, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaCargoPorNivelVODocument.ListaCargoPorNivelVO listaCargoPorNivel(br.com.vivo.fo.usuario.vo.ListaCargoPorNivelVODocument.ListaCargoPorNivelVO listaCargoPorNivelVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadePorOrganizacaoVODocument.ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacao(br.com.vivo.fo.usuario.vo.ListaUnidadePorOrganizacaoVODocument.ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacaoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUsuariosVODocument.ListaUsuariosVO pesquisaUsuarios(br.com.vivo.fo.usuario.vo.UsuarioSimplVODocument.UsuarioSimplVO queryUsuario, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.UsuarioLDAPVODocument.UsuarioLDAPVO salvaUsuario(br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO usuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvaHierarquiaDeptoPessoa(br.com.vivo.fo.usuario.vo.NivelCargoOrgDeptoRelacionamentoVODocument.NivelCargoOrgDeptoRelacionamentoVO nivelCargoOrgDeptoRelacionamentoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvarUsuarioEditado(br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO usuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoVODocument.RelacionarUsuarioGrupoVO relacionaUsuarioGrupo(br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO usuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoVODocument.RelacionarUsuarioGrupoVO salvaUsuarioGrupoRelacionado(br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoSimplVODocument.RelacionarUsuarioGrupoSimplVO relacionaUsuarioGrupo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilVODocument.RelacionarUsuarioPerfilVO relacionaUsuarioPerfil(br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO usuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilVODocument.RelacionarUsuarioPerfilVO salvaUsuarioPerfilRelacionado(br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilSimplVODocument.RelacionarUsuarioPerfilSimplVO relacionaUsuarioPerfil, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraVODocument.RelacionarUsuarioOperadoraVO relacionaUsuarioOperadora(br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO usuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraVODocument.RelacionarUsuarioOperadoraVO salvaUsuarioOperadoraRelacionado(br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraSimplVODocument.RelacionarUsuarioOperadoraSimplVO relacionaUsuarioOperadoraSimplVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setSupervisor(java.lang.String user, java.lang.String idGrupo, java.lang.String idUsuario, java.lang.String inSupervisor) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
