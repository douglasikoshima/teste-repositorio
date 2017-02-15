package br.com.vivo.fo.ctrls.usuario.organograma;

import javax.ejb.Local;

@Local
public interface Organograma {

    public br.com.vivo.fo.usuario.vo.ListaCargoVODocument.ListaCargoVO getListaCargo(br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO cargo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaCargoVODocument.ListaCargoVO removeCargo(br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO cargo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaCargoVODocument.ListaCargoVO insertCargo(br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO cargo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaCargoVODocument.ListaCargoVO alteraCargo(br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO cargo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument.ListaAtividadeVO getListaAtividade(br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO atividade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument.ListaAtividadeVO removeAtividade(br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO atividade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument.ListaAtividadeVO insertAtividade(br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO atividade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument.ListaAtividadeVO inserirAtividade(br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO atividade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument.ListaAtividadeVO alteraAtividade(br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO atividade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadeVODocument.ListaUnidadeVO getListaUnidade(br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO unidade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadeVODocument.ListaUnidadeVO removeUnidade(br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO unidade, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadeVODocument.ListaUnidadeVO insertUnidade(br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO unidade, java.lang.String operacion, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadeVODocument.ListaUnidadeVO editarUnidade(br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO unidade, java.lang.String operacion, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO editarTipoOrganizacao(br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO tipoOrganizacao, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO getListaTipoOrganizacao(br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO tipoOrganizacao, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO removeTipoOrganizacao(br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO tipoOrganizacao, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO insertTipoOrganizacao(br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO tipoOrganizacao, java.lang.String operacion, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.CargoAtividadeRelacionamentoVODocument.CargoAtividadeRelacionamentoVO listaRelacionarCargoAtividade(br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO sistemaRecuperaAgrupamentos, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.OrganizacaoUnidadeRelacionamentoVODocument.OrganizacaoUnidadeRelacionamentoVO listaRelacionarOrganizacaoUnidade(br.com.vivo.fo.usuario.vo.OrganizacaoVODocument.OrganizacaoVO sistemaRecuperaAgrupamentos, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.NivelCargoRelacionamentoVODocument.NivelCargoRelacionamentoVO listaRelacionarNivelCargo(br.com.vivo.fo.usuario.vo.NivelOrganogramaVODocument.NivelOrganogramaVO sistemaRecuperaAgrupamentos, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.CargoAtividadeRelacionamentoVODocument.CargoAtividadeRelacionamentoVO salvaCargoAtividadeRelacionados(br.com.vivo.fo.usuario.vo.CargoAtividadeRelacionamentoVODocument.CargoAtividadeRelacionamentoVO cargoAtividadeVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.NivelCargoRelacionamentoVODocument.NivelCargoRelacionamentoVO salvaNivelCargoRelacionados(br.com.vivo.fo.usuario.vo.NivelCargoRelacionamentoVODocument.NivelCargoRelacionamentoVO nivelCargoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.OrganizacaoUnidadeRelacionamentoVODocument.OrganizacaoUnidadeRelacionamentoVO salvaOrganizacaoUnidadeRelacionados(br.com.vivo.fo.usuario.vo.OrganizacaoUnidadeRelacionamentoVODocument.OrganizacaoUnidadeRelacionamentoVO organizacaoUnidadeVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument.AdmOrgNivelContainerVO cargaArvoreNivel(br.com.vivo.fo.usuario.vo.NivelOrganogramaVODocument.NivelOrganogramaVO nivelVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void excluiNivel(br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument.AdmOrgNivelContainerVO nivelContainerVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void excluiOrganizacao(br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument.AdmOrgOrganizacaoContainerVO organizacaoContainerVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void gravaNivel(br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument.AdmOrgNivelContainerVO nivelContainarVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void gravaOrganizacao(br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument.AdmOrgOrganizacaoContainerVO organizacaoContainarVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void alteraNivel(br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument.AdmOrgNivelContainerVO nivelContainarVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void alteraOrganizacao(br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument.AdmOrgOrganizacaoContainerVO organizacaoContainarVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument.AdmOrgOrganizacaoContainerVO cargaArvoreOrganizacao(br.com.vivo.fo.usuario.vo.OrganizacaoVODocument.OrganizacaoVO organizacaoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO getListaTipoOrganizacaos(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
