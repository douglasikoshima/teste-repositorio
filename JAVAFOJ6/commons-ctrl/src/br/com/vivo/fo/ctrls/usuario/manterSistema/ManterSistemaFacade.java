package br.com.vivo.fo.ctrls.usuario.manterSistema;

import javax.ejb.Local;

@Local
public interface ManterSistemaFacade {

    public br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO adicionaSistema(br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO sistemaAdicionar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO listaSistemas(br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO sistemaManterUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO pesquisaSistema(br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO sistemaPesquisar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO salvaSistemaEditado(br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO sistemaEditado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO removeSistema(br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO sistemaRemove, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaSubSistemasPaginasVODocument.ListaSubSistemasPaginasVO listaSubSistemasPaginas(br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO idSistema, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadesPesquisaVODocument.ListaUnidadesPesquisaVO pesquisaUnidades(br.com.vivo.fo.usuario.vo.UnidadeSalvaVODocument.UnidadeSalvaVO unidadePesquisa, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadesPesquisaVODocument.ListaUnidadesPesquisaVO removeUnidade(br.com.vivo.fo.usuario.vo.UnidadeSalvaVODocument.UnidadeSalvaVO unidadeRemover, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadesPesquisaVODocument.ListaUnidadesPesquisaVO salvaUnidade(br.com.vivo.fo.usuario.vo.UnidadeSalvaVODocument.UnidadeSalvaVO salvaUnidade, java.lang.String tipo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaGrupamentosUsuarioVODocument.ListaGrupamentosUsuarioVO listaTodosGrupamentos(br.com.vivo.fo.usuario.vo.GrupamentosExistentesUsuarioVODocument.GrupamentosExistentesUsuarioVO grupamentosExistentesUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaGrupamentosUsuarioVODocument.ListaGrupamentosUsuarioVO removeGrupamento(br.com.vivo.fo.usuario.vo.GrupamentosExistentesUsuarioVODocument.GrupamentosExistentesUsuarioVO grupamentoRemove, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaGrupamentosUsuarioVODocument.ListaGrupamentosUsuarioVO salvaGrupamento(br.com.vivo.fo.usuario.vo.GrupamentosExistentesUsuarioVODocument.GrupamentosExistentesUsuarioVO grupamentoSalvo, java.lang.String tipo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaServidoresVODocument.ListaServidoresVO salvaServidor(br.com.vivo.fo.usuario.vo.SistemaServidorExistenteUsuarioVODocument.SistemaServidorExistenteUsuarioVO salvaServidor, java.lang.String tipo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaServidoresVODocument.ListaServidoresVO listaServidores(br.com.vivo.fo.usuario.vo.SistemaServidorExistenteUsuarioVODocument.SistemaServidorExistenteUsuarioVO sistemaServidorExistenteUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaServidoresVODocument.ListaServidoresVO removeServidor(br.com.vivo.fo.usuario.vo.SistemaServidorExistenteUsuarioVODocument.SistemaServidorExistenteUsuarioVO servidorRemov, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarSistemaServidorVODocument.RelacionarSistemaServidorVO listaServidoresRelacionadosSistema(br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO sistemaIdListar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.RelacionarSistemaServidorVODocument.RelacionarSistemaServidorVO salvarServidoresRelacionadosSistema(br.com.vivo.fo.usuario.vo.RelacionarSistemaServidorVODocument.RelacionarSistemaServidorVO servidoresRelacionados, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void moverItemMenu(br.com.vivo.fo.usuario.vo.ItemMenuMoverVODocument.ItemMenuMoverVO itemMover, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void inserirItemMenu(br.com.vivo.fo.usuario.vo.ItemMenuInserirVODocument.ItemMenuInserirVO itemInserir, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.MenuVODocument.MenuVO carregaMenu(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ItemMenuVODocument.ItemMenuVO carregaArvoreMenu(br.com.vivo.fo.usuario.vo.SistemaIDArvoreVODocument.SistemaIDArvoreVO idSistemaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removeItemArvore(br.com.vivo.fo.usuario.vo.ItemMenuRemoverVODocument.ItemMenuRemoverVO itemRemover, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvarItemEditado(br.com.vivo.fo.usuario.vo.ItemMenuEditarVODocument.ItemMenuEditarVO itemEditado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaItensMenuVODocument.ListaItensMenuVO listaItensMenu(br.com.vivo.fo.usuario.vo.MenuParamPesquisaItensVODocument.MenuParamPesquisaItensVO param, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaItensMenuVODocument.ListaItensMenuVO salvaItensRelacionados(br.com.vivo.fo.usuario.vo.SalvarItensMenuRelacionadosVODocument.SalvarItensMenuRelacionadosVO param, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument.ListaPaginasUsuarioVO salvaPagina(br.com.vivo.fo.usuario.vo.PaginaUsuarioVODocument.PaginaUsuarioVO paginaSalvar, java.lang.String tipo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument.ListaPaginasUsuarioVO pesquisaPaginas(br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument.ListaPaginasUsuarioVO idSistSubSist, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO listaSubSistemas(br.com.vivo.fo.usuario.vo.SistemaUsuarioVODocument.SistemaUsuarioVO sistemaPerfil, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO listaSubSistemasPorSistema(br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO sistemaIdListar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO listaSubSistemasPorSistemaPorSubSistemaPar(br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO subSistemaUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO salvaSubSistema(br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO subSistemaSalvar, java.lang.String tipo, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO removeSubSistema(br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO subSistemaRemove, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaParamGrupamUnidVODocument.ListaParamGrupamUnidVO listaParamRelGrupamUnidades(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument.SubSistemaPaginasVO listaPaginasPorIdSubSistema(br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO subSistema, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadesGrupamentoVODocument.ListaUnidadesGrupamentoVO listaRelGrupamUnidades(br.com.vivo.fo.usuario.vo.SalvaUnidadesGrupamentoVODocument.SalvaUnidadesGrupamentoVO filtroUnidades, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaUnidadesGrupamentoVODocument.ListaUnidadesGrupamentoVO salvaRelGrupamUnidades(br.com.vivo.fo.usuario.vo.SalvaUnidadesGrupamentoVODocument.SalvaUnidadesGrupamentoVO salvaUnidades, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument.ListaPaginasUsuarioVO removePagina(br.com.vivo.fo.usuario.vo.PaginaUsuarioVODocument.PaginaUsuarioVO paginaRemover, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
