package cliente.TelaInicial.DetalheUsuario;

import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO;
import br.com.vivo.fo.cliente.vo.CarregarNovoEnderecoVODocument.CarregarNovoEnderecoVO;
import br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO;
import br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO;
import br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado;
import br.com.vivo.fo.cliente.vo.DocumentoVODocument.DocumentoVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ImpedimentoVODocument.ImpedimentoVO;
import br.com.vivo.fo.cliente.vo.LinhasPorIdVODocument.LinhasPorIdVO;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO.DadosAbaLupaCliente.ContasPontuacao;
import br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO;
import br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO.ListaEnderecos;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.prePago.manutencaoPrePago.ManutencaoPrePagoFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO;
import br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes;
import br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO;
import br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados;
import br.com.vivo.vol.menu.vo.ARVOREDocument.ARVORE;
import cliente.URLErro;
import cliente.TelaInicial.TelaInicialController.TIForm;
import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.enderecoservice.EnderecoSOAPProxy;
import com.indracompany.ws.enderecoservice.ParametrosBuscarListaEnderecos;
import com.indracompany.ws.enderecoservice.SecurityHeaderHelper;
import com.indracompany.ws.enderecoservice.to.Endereco;
import com.indracompany.ws.enderecoservice.to.ErroInfo;

import commons.errors.FormError;

@SuppressWarnings({ "unused", "rawtypes" })
public class DetalheUsuarioController extends AbstractAction {

    private static final long       serialVersionUID                    = 2425811389651337792L;

    @EJB
    private ManutencaoPrePagoFacade prePagoFacade;

    @EJB
    private TelaInicialFacade       telaInicialFacade;

    protected global.Global         globalApp                           = new global.Global();

    private Boolean                 pesquisaEnderecoWebService          = null;

    private LupaClienteVO           lupaCliente                         = null;
    private DocumentoVO[]           documento                           = null;
    private ComunicacaoVO[]         contato                             = null;
    private EnderecoVO[]            endereco                            = null;
    private ImpedimentoVO[]         impedimento                         = null;
    private ContasPontuacao[]       contasPontuacao                     = null;
    private ARVORE[]                arvore;

    private CanaisExistentesVO      canaisExistentesVO;
    private CanaisRelacionadosVO    canaisRelacionadosVO;
    private MeioContatoVO           meioContatoVO;
    private ComunicacoesRecusadasVO comunicacoesRecusadasVO;

    private PermissoesForm          permissoesForm;

    private String                  user                                = null;
    private String                  idPessoa                            = null;
    private String                  nrLinha                             = null;
    private String                  idContaSistemaOrigem                = null;

    private static Logger           logLupaUsuario                      = new Logger("lupausuario");

    private final String            PARAMETRO_PESQUISA_ENDERECO_NA_BASE = "IN_PESQUISA_ENDERECO_BASE";
    private final String            PARAMETRO_ENDPOINT_PESQUISAENDERECO = "BUSCAR_LISTA_ENDERECOS_ENDPOINT";

    private TIForm                  tiForm;
    private FormID                  formID;
    private AbaEndereco             abaEndereco;
    private AbaContato              abaContato;

    public AbaEndereco getAbaEndereco() {
        if (abaEndereco == null) {
            abaEndereco = new AbaEndereco();
        }
        return this.abaEndereco;
    }

    public void setAbaEndereco(AbaEndereco abaEndereco) {
        this.abaEndereco = abaEndereco;
    }

    public FormID getFormID() {
        return this.formID;
    }

    public void setFormID(FormID formID) {
        this.formID = formID;
    }

    public AbaContato getAbaContato() {
        if (abaContato == null) {
            abaContato = new AbaContato();
        }
        return this.abaContato;
    }

    public void setAbaContato(AbaContato abaContato) {
        this.abaContato = abaContato;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("done".equals(mapping.getParameter())) {
            return done(mapping, form, request, response);
        } else if ("salvar".equals(mapping.getParameter())) {
            return salvar(mapping, form, request, response);
        } else if ("voltar".equals(mapping.getParameter())) {
            return voltar(mapping, form, request, response);
        } else if ("loadEndereco".equals(mapping.getParameter())) {
            return loadEndereco(mapping, form, request, response);
        } else if ("loadPermissoes".equals(mapping.getParameter())) {
            return loadPermissoes(mapping, form, request, response);
        } else if ("loadDocumento".equals(mapping.getParameter())) {
            return loadDocumento(mapping, form, request, response);
        } else if ("loadContato".equals(mapping.getParameter())) {
            return loadContato(mapping, form, request, response);
        } else if ("DetalheCliente".equals(mapping.getParameter())) {
            return DetalheCliente(mapping, form, request, response);
        } else if ("loadDadosProfiler".equals(mapping.getParameter())) {
            return loadDadosProfiler(mapping, form, request, response);
        } else if ("custemerDone".equals(mapping.getParameter())) {
            return custemerDone(mapping, form, request, response);
        } else if ("salvarEndereco".equals(mapping.getParameter())) {
            return salvarEndereco(mapping, form, request, response);
        } else if ("controlarEndereco".equals(mapping.getParameter())) {
            return controlarEndereco(mapping, form, request, response);
        } else if ("salvarContato".equals(mapping.getParameter())) {
            return salvarContato(mapping, form, request, response);
        } else if ("pesquisaEndereco".equals(mapping.getParameter())) {
            return pesquisaEndereco(mapping, form, request, response);
        } else if ("buscaEndereco".equals(mapping.getParameter())) {
            return buscaEndereco(mapping, form, request, response);
        } else if ("alterarEmailLegado".equals(mapping.getParameter())) {
            return alterarEmailLegado(mapping, form, request, response);
        } else if ("controlaSequencia".equals(mapping.getParameter())) {
            return controlaSequencia(mapping, form, request, response);
        }else if("controlarContato".equals(mapping.getParameter())){
        	return controlarContato(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="DetalheCliente.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:begin(" + user + ") >> INICIALIZADO");
            idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaUsuario();
            if (ConstantesCRM.SVAZIO.equals(idPessoa)) {
                idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
            }
            nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
            idContaSistemaOrigem = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdContaSistemaOrigem();
            logLupaUsuario.debug("DetalheUsuarioController:begin(" + user + ") >> idPessoa: " + idPessoa);
            logLupaUsuario.debug("DetalheUsuarioController:begin(" + user + ") >> nrLinha: " + nrLinha);
            logLupaUsuario.debug("DetalheUsuarioController:begin(" + user + ") >> idContaSistemaOrigem: " + idContaSistemaOrigem);
            abaContato = new AbaContato();
            logLupaUsuario.debug("DetalheUsuarioController:begin(" + user + ") >> FINALIZADO");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="done" return-action="ClienteDone"
     */
    public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("done");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="permissoes.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            PermissoesForm form = (PermissoesForm) formParam;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:salvar(" + user + ") >> INICIALIZADO");
            canaisRelacionadosVO = CanaisRelacionadosVO.Factory.newInstance();
            canaisRelacionadosVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());

            comunicacoesRecusadasVO = ComunicacoesRecusadasVO.Factory.newInstance();
            comunicacoesRecusadasVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());

            // Percorre o array de ids de formCanaisRelacionados e grava no canaisRelacionadosVO
            for (int i = 0; i < form.formCanaisRelacionados.length; i++) {
                canaisRelacionadosVO.insertNewCanaisRelacionados(i);
                CanaisRelacionados idCanais = CanaisRelacionados.Factory.newInstance();
                idCanais.setIdCanal(form.formCanaisRelacionados[i]);
                for (int x = 0; x < permissoesForm.formCanaisExistentesVO.length; x++) {
                    if (permissoesForm.formCanaisExistentesVO[x].getIdCanal().equalsIgnoreCase(form.formCanaisRelacionados[i])) {
                        idCanais.setNmCanal(permissoesForm.formCanaisExistentesVO[x].getNmCanal());
                        x = permissoesForm.formCanaisExistentesVO.length + 1;
                    }
                }

                canaisRelacionadosVO.setCanaisRelacionadosArray(i, idCanais);
            }

            // Percorre o array de ids de formCanaisRelacionados e grava no canaisRelacionadosVO
            for (int i = 0; i < form.formMeioContatoRecusado.length; i++) {
                comunicacoesRecusadasVO.insertNewMeioContatoRecusado(i);
                MeioContatoRecusado idMeio = MeioContatoRecusado.Factory.newInstance();
                idMeio.setIdMeioContato(form.formMeioContatoRecusado[i]);
                for (int x = 0; x < permissoesForm.formMeioContatoVO.length; x++) {
                    if (permissoesForm.formMeioContatoVO[x].getIdMeioContato().equalsIgnoreCase(form.formMeioContatoRecusado[i])) {
                        idMeio.setDsMeioContato(permissoesForm.formMeioContatoVO[x].getDsMeioContato());
                        x = permissoesForm.formMeioContatoVO.length + 1;
                    }
                }
                comunicacoesRecusadasVO.setMeioContatoRecusadoArray(i, idMeio);
            }

            telaInicialFacade.salvarCanaisRelacionados(canaisRelacionadosVO, user);
            telaInicialFacade.salvarComunicacoesRecusadas(comunicacoesRecusadasVO, user);

            permissoesForm.setFormMeioContatoRecusado(form.getFormMeioContatoRecusado());
            permissoesForm.setFormMeioContatoRecusadoVO(comunicacoesRecusadasVO.getMeioContatoRecusadoArray());

            permissoesForm.setFormCanaisRelacionados(form.getFormCanaisRelacionados());
            permissoesForm.setFormCanaisRelacionadosVO(canaisRelacionadosVO.getCanaisRelacionadosArray());
            logLupaUsuario.debug("DetalheUsuarioController:salvar(" + user + ") >> FINALIZADO");

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:salvar(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="done.do"
     */
    public ActionForward voltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="abaEndereco.jsp"
     * @jpf:forward name="controlar" path="controlarEndereco.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaEndereco form = (AbaEndereco) formParam;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:loadEndereco(" + user + ") >> INICIALIZADO");
            String destino = new String();
            String idPessoa = new String();

            if (!form.getInReload().equals(ConstantesCRM.STRUE) && abaEndereco != null && abaEndereco.getInReload() != null) {
                form.setInReload(abaEndereco.getInReload());
                abaEndereco.setInReload(ConstantesCRM.SVAZIO);
            }

            if (request.getParameter("idPessoaCliente") == null) {
                idPessoa = form.getIdPessoa();
            } else {
                idPessoa = request.getParameter("idPessoaCliente");
            }

            if (idPessoa != null) {
                getAbaEndereco().setAbaEndereco(telaInicialFacade.getEndereco(user, idPessoa).getDadosAbaLupaCliente().getEnderecoVOArray());
                getAbaEndereco().setIdPessoa(idPessoa);
            }

            String param = request.getParameter("tipo");

            if (request.getParameter("reload") == "ok") {
                lupaCliente = telaInicialFacade.getEndereco(user, request.getParameter("idPessoa"));
                endereco = lupaCliente.getDadosAbaLupaCliente().getEnderecoVOArray();
                getAbaEndereco().setAbaEndereco(endereco);
                destino = ConstantesCRM.SUCCESS;
            }

            if (request.getAttribute("reload") == "ok") {
                lupaCliente = telaInicialFacade.getEndereco(user, request.getParameter("idPessoa"));
                endereco = lupaCliente.getDadosAbaLupaCliente().getEnderecoVOArray();
                getAbaEndereco().setAbaEndereco(endereco);
                destino = ConstantesCRM.SUCCESS;

            } else {
                if (param == null) {
                    destino = ConstantesCRM.SUCCESS;
                } else {
                    form.setAcao(request.getParameter("tipo"));
                    form.setIdArray(request.getParameter("idArray"));
                    form.setIdEndereco(request.getParameter("idEndereco"));
                    form.getEndereco().setInSincronismo(request.getParameter("sincronizado"));
                    getAbaEndereco().setAcao(form.getAcao());
                    getAbaEndereco().setIdArray(form.getIdArray());
                    getAbaEndereco().setIdEndereco(form.getIdEndereco());
                    getAbaEndereco().getEndereco().setInSincronismo(form.getEndereco().getInSincronismo());
                    destino = "controlar";
                }
            }

            logLupaUsuario.debug("DetalheUsuarioController:loadEndereco(" + user + ") >> FINALIZADO");
            request.setAttribute("abaEndereco", abaEndereco);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(destino);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:loadEndereco(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="permissoes.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadPermissoes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            // Monta o log da operação se possível
            ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);
            permissoesForm = new PermissoesForm();
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:loadPermissoes(" + user + ") >> INICIALIZADO");
            canaisExistentesVO = telaInicialFacade.getCanaisExistentesVO(user);
            permissoesForm.setFormCanaisExistentesVO(canaisExistentesVO.getCanaisExistentesArray());

            canaisRelacionadosVO = CanaisRelacionadosVO.Factory.newInstance();
            canaisRelacionadosVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());
            canaisRelacionadosVO = telaInicialFacade.getCanaisRelacionadosVO(canaisRelacionadosVO, user);
            permissoesForm.setFormCanaisRelacionadosVO(canaisRelacionadosVO.getCanaisRelacionadosArray());

            meioContatoVO = telaInicialFacade.getMeioContatoVO(user);
            permissoesForm.setFormMeioContatoVO(meioContatoVO.getMeioContatoArray());

            comunicacoesRecusadasVO = ComunicacoesRecusadasVO.Factory.newInstance();
            comunicacoesRecusadasVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());
            comunicacoesRecusadasVO = telaInicialFacade.getComunicacoesRecusadasVO(comunicacoesRecusadasVO, user);
            permissoesForm.setFormMeioContatoRecusadoVO(comunicacoesRecusadasVO.getMeioContatoRecusadoArray());
            logLupaUsuario.debug("DetalheUsuarioController:loadPermissoes(" + user + ") >> FINALIZADO");
            return forward;
        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:loadPermissoes(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="abaDocumento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadDocumento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            user = ConstantesCRM.getCurrentUser(request.getSession());

            logLupaUsuario.debug("DetalheUsuarioController:loadDocumento(" + user + ") >> INICIALIZADO");
            ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);

            if (documento == null) {
                documento = (DocumentoVO[]) request.getSession().getAttribute("documentos");
                request.getSession().removeAttribute("documentos");
            }

            request.setAttribute("piDocumento", documento);
            logLupaUsuario.debug("DetalheUsuarioController:loadDocumento(" + user + ") >> FINALIZADO");

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return forward;

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:loadDocumento(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="abaContato.jsp"
     * @jpf:forward name="controlar" path="controlarContato.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaContato form = (AbaContato) formParam;
            String destino = new String();
            String idPessoa = new String();

            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:loadContato(" + user + ") >> INICIALIZADO");
            if (!form.getInReload().equals(ConstantesCRM.STRUE) && abaContato != null && abaContato.getInReload() != null) {
                form.setInReload(abaContato.getInReload());
                abaContato.setInReload(ConstantesCRM.SVAZIO);
            }

            if (request.getParameter("idPessoaCliente") == null) {
                idPessoa = form.getIdPessoa();
            } else {
                idPessoa = request.getParameter("idPessoaCliente");
            }

            if (idPessoa != null) {
                form.setAbaContato(telaInicialFacade.getContato(user, idPessoa).getDadosAbaLupaCliente().getComunicacaoVOArray());
                if (abaContato == null) {
                    abaContato = new AbaContato();
                }
                abaContato.setAbaContato(form.getAbaContato());
                form.setIdPessoa(idPessoa);
                abaContato.setIdPessoa(idPessoa);
            }

            String param = request.getParameter("tipo");

            if (request.getAttribute("reload") == "ok") {
                lupaCliente = telaInicialFacade.getContato(user, request.getParameter("idPessoa"));
                contato = lupaCliente.getDadosAbaLupaCliente().getComunicacaoVOArray();
                form.setAbaContato(contato);
                abaContato.setAbaContato(form.getAbaContato());
                destino = ConstantesCRM.SUCCESS;

            } else {
                if (param == null) {
                    destino = ConstantesCRM.SUCCESS;

                } else {
                    form.setAcao(request.getParameter("tipo"));
                    form.setIdArray(request.getParameter("idArray"));
                    form.setIdComunicacao(request.getParameter("idComunicacao"));
                    abaContato.setAcao(form.getAcao());
                    abaContato.setIdArray(form.getIdArray());
                    abaContato.setIdComunicacao(form.getIdComunicacao());
                    destino = "controlar";
                }
            }

            logLupaUsuario.debug("DetalheUsuarioController:loadContato(" + user + ") >> FINALIZADO");
            request.setAttribute("abaContato", abaContato);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            
            return mapping.findForward(destino);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:loadContato(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="lupaUsuario.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward DetalheCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            FormID form = (FormID) formParam;
            int idTipoLinha = 0;
            idTipoLinha = ConstantesCRM.SVAZIO.equals(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha()) ? 0 : Integer.parseInt(((ParametrosVO) request
                    .getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
            formID = new FormID();

            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:DetalheCliente(" + user + ") >> INICIALIZADO");

            lupaCliente = telaInicialFacade.getLupaClienteVO(user, idPessoa, nrLinha, idContaSistemaOrigem, idTipoLinha);
            lupaCliente.getDadosLupaCliente().setIdPessoa(idPessoa);

            documento = lupaCliente.getDadosAbaLupaCliente().getDocumentoVOArray();
            formID.setIdPessoaCliente(lupaCliente.getDadosLupaCliente().getIdPessoa());
            request.setAttribute("piLupaCliente", lupaCliente);
            logLupaUsuario.debug("DetalheUsuarioController:DetalheCliente(" + user + ") >> FINALIZADO");

            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:DetalheCliente(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="/cliente/TelaInicial/DetalheUsuario/profile/CustomerController.jpf"
     */
    public ActionForward loadDadosProfiler(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("documentos", documento);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     */
    public ActionForward custemerDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="loadEndereco.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward salvarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaEndereco form = (AbaEndereco) formParam;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
            logLupaUsuario.debug("DetalheUsuarioController:salvarEndereco(" + user + ") >> INICIALIZADO");
            LupaClienteVO enderecoAlterado = LupaClienteVO.Factory.newInstance();
            enderecoAlterado.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
            enderecoAlterado.addNewDadosLupaCliente().setNrLinha(nrLinha);
            enderecoAlterado.getDadosAbaLupaCliente().addNewEnderecoVO();
            String resposta = new String();

            if ("alteracao".equals(request.getParameter("tipo"))) {
                form.endereco.setDsEnderecoComplemento(form.endereco.getDsEnderecoComplemento().trim());
                form.endereco.setNmBairro(form.endereco.getNmBairro().trim());
                form.endereco.setNmLogradouro(form.endereco.getNmLogradouro().trim());
                form.endereco.setNmMunicipio(form.endereco.getNmMunicipio().trim());
                form.endereco.setNmTipoLogradouro(form.endereco.getNmTipoLogradouro().trim());
                form.endereco.setNmTituloLogradouro(form.endereco.getNmTituloLogradouro().trim());
                form.endereco.setNrEndereco(form.endereco.getNrEndereco().trim());

                enderecoAlterado.getDadosAbaLupaCliente().setEnderecoVOArray(0, form.endereco);
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewPaisVO();
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getPaisVO().setIdPais(form.idPaisSelecionado);
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewUFVO();
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getUFVO().setIdUF(form.idUFSelecionado);
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewTipoEnderecoVO();
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getTipoEnderecoVO().setIdTipoEndereco(form.getIdTipoSelecionado());
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).setIdEndereco(form.getIdEndereco());

                resposta = telaInicialFacade.setSalvarAlterarEndereco(user, enderecoAlterado);

                if (abaEndereco == null) {
                    abaEndereco = new AbaEndereco();
                }
                abaEndereco.setInReload(ConstantesCRM.STRUE);

            } else {
                form.endereco.setDsEnderecoComplemento(form.endereco.getDsEnderecoComplemento().trim());
                form.endereco.setNmBairro(form.endereco.getNmBairro().trim());
                form.endereco.setNmLogradouro(form.endereco.getNmLogradouro().trim());
                form.endereco.setNmMunicipio(form.endereco.getNmMunicipio().trim());
                form.endereco.setNmTipoLogradouro(form.endereco.getNmTipoLogradouro().trim());
                form.endereco.setNmTituloLogradouro(form.endereco.getNmTituloLogradouro().trim());
                form.endereco.setNrEndereco(form.endereco.getNrEndereco().trim());
                enderecoAlterado.getDadosLupaCliente().setIdPessoa(form.getIdPessoa());
                enderecoAlterado.getDadosAbaLupaCliente().setEnderecoVOArray(0, form.endereco);
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewPaisVO();
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getPaisVO().setIdPais(form.idPaisSelecionado);
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewUFVO();
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getUFVO().setIdUF(form.idUFSelecionado);
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewTipoEnderecoVO();
                enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getTipoEnderecoVO().setIdTipoEndereco(form.getIdTipoSelecionado());
                resposta = telaInicialFacade.setSalvarNovoEndereco(user, enderecoAlterado);

                if (abaEndereco == null) {
                    abaEndereco = new AbaEndereco();
                }
                abaEndereco.setInReload(ConstantesCRM.STRUE);
            }

            if (resposta.length() != 0) {
                form.setInMsgRetorno("true");
                String msgErro = resposta.indexOf("]") > -1 ? resposta.substring(resposta.indexOf("]") + 1) : resposta;
                form.setDsMsgRetorno(msgErro);
            } else {
                form.setInMsgRetorno("false");
            }

            request.setAttribute("reload", "ok");
            logLupaUsuario.debug("DetalheUsuarioController:salvarEndereco(" + user + ") >> FINALIZADO");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:salvarEndereco(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="excluir" path="loadEndereco.do"
     * @jpf:forward name="novo" path="incluiEndereco.jsp"
     * @jpf:forward name="alterar" path="alteraEndereco.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward controlarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaEndereco form = (AbaEndereco) formParam;
            String destino = form.getAcao();
            String sincrozinado = form.getEndereco().getInSincronismo();
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:controlarEndereco(" + user + ") >> INICIALIZADO");
            // abaEndereco = form;
            abaEndereco.setInMsgRetorno("false");
            try {
                if (destino.equalsIgnoreCase("excluir")) {
                    LupaClienteVO deleteEndereco = LupaClienteVO.Factory.newInstance();
                    deleteEndereco.addNewDadosAbaLupaCliente();
                    deleteEndereco.getDadosAbaLupaCliente().addNewEnderecoVO();
                    deleteEndereco.getDadosAbaLupaCliente().getEnderecoVOArray(0).setIdEndereco(form.getIdEndereco());
                    request.setAttribute("reload", "ok");
                    telaInicialFacade.setExcluirEndereco(user, deleteEndereco);
                    abaEndereco.setInReload(ConstantesCRM.STRUE);
                    logLupaUsuario.debug("DetalheUsuarioController:controlarEndereco(" + user + ") >> FINALIZADO (EXCLUSAO)");

                } else if (destino.equalsIgnoreCase("alterar")) {
                    EnderecoVO alterarEndereco = EnderecoVO.Factory.newInstance();
                    alterarEndereco.addNewPaisVO();
                    alterarEndereco.addNewTipoEnderecoVO();
                    alterarEndereco.addNewUFVO();
                    alterarEndereco = abaEndereco.abaEndereco[Integer.parseInt(form.getIdArray())];
                    abaEndereco.setEndereco(alterarEndereco);
                    abaEndereco.setListas(telaInicialFacade.getIncluirEndereco(user));
                    abaEndereco.setIdPaisSelecionado(abaEndereco.endereco.getPaisVO().getIdPais());
                    abaEndereco.setIdTipoSelecionado(abaEndereco.endereco.getTipoEnderecoVO().getIdTipoEndereco());
                    abaEndereco.setIdUFSelecionado(abaEndereco.endereco.getUFVO().getIdUF());
                    abaEndereco.setIdEndereco(form.getIdEndereco());
                    abaEndereco.setIdPessoa(form.getIdPessoa());
                    abaEndereco.getEndereco().setInSincronismo(sincrozinado);
                    logLupaUsuario.debug("DetalheUsuarioController:controlarEndereco(" + user + ") >> FINALIZADO (ALTERACAO)");

                } else {
                    EnderecoVO incluirEnderecoVO = EnderecoVO.Factory.newInstance();
                    incluirEnderecoVO.addNewPaisVO();
                    incluirEnderecoVO.addNewTipoEnderecoVO();
                    incluirEnderecoVO.addNewUFVO();
                    abaEndereco.setEndereco(incluirEnderecoVO);
                    abaEndereco.setListas(telaInicialFacade.getIncluirEndereco(user));
                    abaEndereco.setIdPessoa(form.getIdPessoa());
                    logLupaUsuario.debug("DetalheUsuarioController:controlarEndereco(" + user + ") >> FINALIZADO (INCLUSAO)");
                }

            } catch (Exception e) {
                abaEndereco.setInMsgRetorno("true");
                String msgErro = e.getMessage().indexOf("]") > -1 ? e.getMessage().substring(e.getMessage().indexOf("]") + 1) : e.getMessage();
                abaEndereco.setDsMsgRetorno(msgErro);
            }

            request.setAttribute("abaEndereco", abaEndereco);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(destino);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:controlarEndereco(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="novo" path="incluiContato.jsp"
     * @jpf:forward name="alterar" path="alteraContato.jsp"
     * @jpf:forward name="excluir" path="loadContato.do"
     * @jpf:forward name="executa" path="executa.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward controlarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaContato form = (AbaContato) formParam;
            String destino = form.getAcao();
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> INICIALIZADO");
            if (destino.equalsIgnoreCase("excluir")) {
                abaContato = new AbaContato();

                if (form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM PART") == 0
                        || form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM COM") == 0) {
                    abaContato.setDsOldEmail(form.abaContato[Integer.parseInt(form.getIdArray())].getDsContato());
                    abaContato.setInEmail(true);
                } else {
                    abaContato.setDsOldEmail(ConstantesCRM.SVAZIO);
                    abaContato.setInEmail(false);
                }
                if (abaContato.inEmail) { // Executa para exclusao de e-mail
                    logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> Exclusao de E-mail");
                    String tmpEmail = form.abaContato[Integer.parseInt(form.getIdArray())].getDsContato();
                    if (checaEmail(tmpEmail, "delete", ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, request)) { // nao encontrou e-mail no legado
                        logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> E-mail nao existente no legado");
                        LupaClienteVO deleteComunicacao = LupaClienteVO.Factory.newInstance();
                        deleteComunicacao.addNewDadosAbaLupaCliente();
                        deleteComunicacao.getDadosAbaLupaCliente().addNewComunicacaoVO();
                        deleteComunicacao.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
                        request.setAttribute("reload", "ok");
                        telaInicialFacade.setExcluirComunicacao(user, deleteComunicacao);
                    } else { // encontrou e-mail no legado
                        logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> E-mail existente no legado");
                        abaContato.setIdPessoa(form.getIdPessoa());
                        destino = "executa";
                    }
                } else { // Executa para exclusao de outros tipos de contato
                    logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> Exclusao de contato nao e-mail");
                    LupaClienteVO deleteComunicacao = LupaClienteVO.Factory.newInstance();
                    deleteComunicacao.addNewDadosAbaLupaCliente();
                    deleteComunicacao.getDadosAbaLupaCliente().addNewComunicacaoVO();
                    deleteComunicacao.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
                    request.setAttribute("reload", "ok");
                    telaInicialFacade.setExcluirComunicacao(user, deleteComunicacao);
                }
                abaContato.setInReload(ConstantesCRM.STRUE);
                logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> FINALIZADO (EXCLUSAO)");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(destino);
            } else if (destino.equalsIgnoreCase("alterar")) {
                // AbaContato abaContato = new AbaContato();
                logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> Alteracao de Contato");
                if (form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM PART") == 0
                        || form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM COM") == 0) {
                    abaContato.setDsOldEmail(form.abaContato[Integer.parseInt(form.getIdArray())].getDsContato());
                    abaContato.setInEmail(true);
                } else {
                    abaContato.setDsOldEmail(ConstantesCRM.SVAZIO);
                    abaContato.setInEmail(false);
                }
                ComunicacaoVO alterarComunicacao = ComunicacaoVO.Factory.newInstance();
                alterarComunicacao.addNewTipoComunicacaoVO();
                alterarComunicacao = form.abaContato[Integer.parseInt(form.getIdArray())];
                abaContato.setContato(alterarComunicacao);
                abaContato.setIdComunicacao(form.getIdComunicacao());
                abaContato.setIdPessoa(form.getIdPessoa());
                request.setAttribute("abaContato", abaContato);
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(destino);
            } else {
                logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> Inclusao de contato");
                AbaContato abaContato = new AbaContato();
                ComunicacaoVO incluirComunicacao = ComunicacaoVO.Factory.newInstance();
                incluirComunicacao.addNewTipoComunicacaoVO();
                String nrSeqLast = "";
                // Pega o numero de sequencia do ultimo item do Array
                if (this.abaContato.getAbaContato() != null && this.abaContato.getAbaContato().length > 0) {
                    nrSeqLast = this.abaContato.getAbaContato()[this.abaContato.getAbaContato().length - 1].getNrSequencia();
                }
                // Se numero de sequencia vier vazio, setar como numero alto para não acontecer duplicação (tratamento
                // sera feito quando alterar-se sequencia, no metodo controlarSequencia())
                if (nrSeqLast == ConstantesCRM.SVAZIO) {
                    nrSeqLast = "999";
                }
                // Seta com nrSequencia do ultimo item do Array + 1 (para entrar no final da lista)
                incluirComunicacao.setNrSequencia(Integer.toString(Integer.parseInt(nrSeqLast) + 1));
                abaContato.setContato(incluirComunicacao);
                abaContato.setListaTipos(telaInicialFacade.getIncluirComunicacao(user));
                abaContato.setIdPessoa(form.getIdPessoa());
                logLupaUsuario.debug("DetalheUsuarioController:controlarContato(" + user + ") >> FINALIZADO");
                request.setAttribute("abaContato", abaContato);
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(destino);
            }
        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:controlarContato(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="loadContato.do"
     * @jpf:forward name="executa" path="executa.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward salvarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaContato form = (AbaContato) formParam;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:salvarContato(" + user + ") >> INICIALIZADO");
            LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
            comunicacaoAlterada.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
            comunicacaoAlterada.addNewDadosLupaCliente();
            String resposta = ConstantesCRM.SVAZIO;

            if (request.getParameter("tipo").equalsIgnoreCase("alteracao")) {
                String tmpEmail = form.contato.getDsContato().trim();
                if (form.inEmail == true) {
                    if (checaEmail(tmpEmail, "update", form.getIdComunicacao(), form.contato.getDsContato().trim(), form.contato.getNrSequencia().trim(), request)) {
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
                        resposta = telaInicialFacade.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());
                        form.setInReload(ConstantesCRM.STRUE);
                    } else {
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward("executa");
                    }
                } else {

                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
                    resposta = telaInicialFacade.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());
                    form.setInReload(ConstantesCRM.STRUE);
                }
            } else {
                comunicacaoAlterada.getDadosLupaCliente().setIdPessoa(form.getIdPessoa());
                comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
                comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
                comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).addNewTipoComunicacaoVO();
                comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).getTipoComunicacaoVO().setIdTipoComunicacao(form.getIdTipoSelecionado());
                resposta = telaInicialFacade.setSalvarNovaComunicacao(user, comunicacaoAlterada);
                form.setInReload(ConstantesCRM.STRUE);
            }

            abaContato = new AbaContato();
            abaContato.setIdPessoa(form.getIdPessoa());
            abaContato.setInReload(form.getInReload());

            if (resposta.indexOf("DUPLICATE KEY") > 0) {
                abaContato.setInMsgRetorno("true");
                abaContato.setMsgAlerta("Contato não pôde ser incluído/alterado pois já existe um contato do mesmo tipo com essa descrição ou uma seqüencia de contato com este valor!");
            } else {
                request.setAttribute("reload", "ok");
                abaContato.setInMsgRetorno("false");
            }
            logLupaUsuario.debug("DetalheUsuarioController:salvarContato(" + user + ") >> FINALIZADO");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("executa");
        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:salvarContato(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="cepinclui" path="incluiEndereco.jsp"
     * @jpf:forward name="cepaltera" path="alteraEndereco.jsp"
     * @jpf:forward name="pesquisar" path="pesquisaEndereco.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward pesquisaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaEndereco form = (AbaEndereco) formParam;
            String destino = new String();
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:pesquisaEndereco(" + user + ") >> INICIALIZADO");

            if (request.getParameter("pagina").equalsIgnoreCase("inicioPesquisa")) {
                AbaEndereco tempForm = abaEndereco;
                abaEndereco = new AbaEndereco();

                PesquisaEnderecoVO pesquisaEndereco = PesquisaEnderecoVO.Factory.newInstance();
                pesquisaEndereco = telaInicialFacade.getPesquisaEnderecoIni(user);
                abaEndereco.setPesquisaEndereco(pesquisaEndereco);
                abaEndereco.getPesquisaEndereco().addNewFiltroPesquisa();
                abaEndereco.getPesquisaEndereco().addNewListaEnderecos();

                if (request.getParameter("limpar") != null && request.getParameter("limpar").equalsIgnoreCase(ConstantesCRM.STRUE)) {
                    String idEndereco = tempForm.getIdEndereco();
                    String idPessoa = tempForm.getIdPessoa();
                    abaEndereco.setIdEndereco(idEndereco);
                    abaEndereco.setIdPessoa(idPessoa);

                } else {
                    request.getSession().setAttribute("retorno", request.getParameter("retorno").toString());
                    abaEndereco.setIdEndereco(request.getParameter("idEndereco"));
                    abaEndereco.setIdPessoa(request.getParameter("idPessoa"));
                }

                abaEndereco.setIdTipoSelecionado(request.getParameter("idTipoSelecionado"));
                destino = "pesquisar";
                logLupaUsuario.debug("DetalheUsuarioController:pesquisaEndereco(" + user + ") >> FINALIZADO");
                request.setAttribute("abaEndereco", abaEndereco);
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(destino);

            } else {
                String idArray = new String();
                EnderecoVO endereco = EnderecoVO.Factory.newInstance();
                abaEndereco.setListas(telaInicialFacade.getIncluirEndereco(user));

                if (request.getParameter("idArray") != null) {
                    idArray = request.getParameter("idArray");
                } else {
                    idArray = ConstantesCRM.SVAZIO;
                }

                abaEndereco.setEndereco(abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(Integer.parseInt(idArray)));
                abaEndereco.setIdUFSelecionado(abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(Integer.parseInt(idArray)).getUFVO().getIdUF());
                abaEndereco.setIdPaisSelecionado(abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(Integer.parseInt(idArray)).getPaisVO().getIdPais());
                abaEndereco.getEndereco().setIdEndereco(abaEndereco.getIdEndereco());

                if (request.getSession().getAttribute("retorno").toString().equalsIgnoreCase("inclui")) {
                    request.getSession().removeAttribute("retorno");
                    destino = "cepinclui";

                } else {
                    request.getSession().removeAttribute("retorno");
                    destino = "cepaltera";
                    
                    // teste
                    form.setIdEndereco(abaEndereco.getIdEndereco());
                    form.setIdPessoa(abaEndereco.getIdPessoa());
                    
                }

                logLupaUsuario.debug("DetalheUsuarioController:pesquisaEndereco(" + user + ") >> FINALIZADO");
                request.setAttribute("abaEndereco", abaEndereco);
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(destino);
            }
        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:pesquisaEndereco(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="resultadoPesquisa.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward buscaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        try {
            AbaEndereco form = (AbaEndereco) formParam;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:buscaEndereco(" + user + ") >> INICIALIZADO");
            PesquisaEnderecoVO filtroEndereco = PesquisaEnderecoVO.Factory.newInstance();
            filtroEndereco.addNewFiltroPesquisa();
            abaEndereco.getPesquisaEndereco().addNewListaEnderecos();
            int segundosTimeout = 20;

            if (request.getParameter("iniciarTela") == null) {
                filtroEndereco.getFiltroPesquisa().setDsLogradouro(request.getParameter("filtroPesquisa.dsLogradouro").trim());
                filtroEndereco.getFiltroPesquisa().setDsBairro(request.getParameter("filtroPesquisa.dsBairro").trim());
                filtroEndereco.getFiltroPesquisa().setDsLocalidade(request.getParameter("filtroPesquisa.dsLocalidade").trim());
                filtroEndereco.getFiltroPesquisa().setNrCEP(request.getParameter("filtroPesquisa.nrCEP").trim());
                filtroEndereco.getFiltroPesquisa().setIdUFSelecionado(request.getParameter("filtroPesquisa.idUFSelecionado").trim());

                PesquisaEnderecoVO pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
                ParametroVO parametro;

                try {
                    parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_PESQUISA_ENDERECO_NA_BASE);
                } catch (Exception e) {
                    parametro = ParametroVO.Factory.newInstance();
                }
                pesquisaEnderecoWebService = new Boolean(ConstantesCRM.SZERO.equals(parametro.getDsValorParametro()) ? true : false);
                abaEndereco.setPesquisaBaseFO(pesquisaEnderecoWebService.booleanValue() ? false : true);

                if (pesquisaEnderecoWebService.booleanValue()) {
                    parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_PESQUISAENDERECO);
                    String sgUF = ConstantesCRM.SVAZIO.equals(request.getParameter("sgUF")) ? null : request.getParameter("sgUF");
                    EnderecoSOAPProxy proxy = new EnderecoSOAPProxy();
                    proxy.setEndpoint(new URL(parametro.getDsValorParametro()).toString());

                    String usuario = ConstantesCRM.SVAZIO;
                    String senha = ConstantesCRM.SVAZIO;
                    try {
	                    usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
	                    senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
                    } catch (Exception e) {
                    	logLupaUsuario.error("DetalheUsuarioController:buscaEndereco(" + user + ")Erro ao buscar usuário e senha :", e);
                    }

                    SecurityHeaderHelper securityHeader = new SecurityHeaderHelper(); 
                    securityHeader.setUserTimeout(new Integer(segundosTimeout * 1000));
                    securityHeader.setSecurityUserName(usuario);
                    securityHeader.setSecurityPassword(senha);
	    	        proxy.setSecurityHeaderHelper(securityHeader);
	    	        
	    	        logLupaUsuario.debug("DetalheUsuarioController:buscaEndereco:: securityHeader: (" + user + ") securityHeader : " + securityHeader);

                    Endereco[] retorno = null;
                    try {
                        BigInteger numeroPaginaBI = new BigInteger(ConstantesCRM.SONE);
                        BigInteger qtdRegistroBI = new BigInteger("50");
                        String dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                        logLupaUsuario.debug("DetalheUsuarioController:buscaEndereco:: buscarListaEnderecosControl.buscarListaEnderecos: (user=" + user + ") begin : " + dataHora);

                        ParametrosBuscarListaEnderecos parametrosBuscarListaEnderecos = new ParametrosBuscarListaEnderecos();
                        parametrosBuscarListaEnderecos.setNumeroCEP(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.nrCEP")) ? null : request.getParameter("filtroPesquisa.nrCEP")
                                .trim());
                        parametrosBuscarListaEnderecos.setDescricaoLogradouro(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.dsLogradouro")) ? null : request.getParameter(
                                "filtroPesquisa.dsLogradouro").trim());
                        parametrosBuscarListaEnderecos.setDescricaoCidade(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.dsLocalidade")) ? null : request.getParameter(
                                "filtroPesquisa.dsLocalidade").trim());
                        parametrosBuscarListaEnderecos.setDescricaoBairro(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.dsBairro")) ? null : request.getParameter(
                                "filtroPesquisa.dsBairro").trim());
                        parametrosBuscarListaEnderecos.setSiglaUf(sgUF);
                        parametrosBuscarListaEnderecos.setNumeroPagina(numeroPaginaBI);
                        parametrosBuscarListaEnderecos.setQtdRegistro(qtdRegistroBI);

                        retorno = proxy.buscarListaEnderecos(parametrosBuscarListaEnderecos);

                        dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                        logLupaUsuario.debug("DetalheUsuarioController:buscaEndereco:: proxy.buscarListaEnderecos(user=" + user + ") end : " + dataHora);

                    } catch (ErroInfo e) {
                        logLupaUsuario.debug("DetalheUsuarioController:buscaEndereco::ErroInfo " + e);
                        retorno = null;
                        pesquisaEnderecoVO.addNewListaEnderecos();
                        abaEndereco.getPesquisaEndereco().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
                        if ("31072".equals(e.getCodigo())) {
                        	request.setAttribute("erro", e.getDescricao());
                        } else {
                        	request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento. [" + e.getDescricao() + "]");
                        }
                    } catch (AxisFault axisFault) {
                        logLupaUsuario.debug("DetalheUsuarioController:buscaEndereco::axisFault " + axisFault);
                        retorno = null;
                        pesquisaEnderecoVO.addNewListaEnderecos();
                        abaEndereco.getPesquisaEndereco().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
                        request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento. [" + axisFault.getFaultString() + "]");

                    } catch (Exception e) {
                        logLupaUsuario.debug("DetalheUsuarioController::buscaEndereco:: " + e);

                        ListaEnderecos listaEnderecos = ListaEnderecos.Factory.newInstance();
                        pesquisaEnderecoVO.setListaEnderecos(listaEnderecos);
                        abaEndereco.setPesquisaEndereco(pesquisaEnderecoVO);
                        abaEndereco.setIsAddrPresent(false);
                        request.setAttribute("abaEndereco", abaEndereco);
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward(ConstantesCRM.SUCCESS);
                    }
                    
                    logLupaUsuario.debug("DetalheUsuarioController::buscaEndereco::retorno " + retorno);
                    if (retorno != null) { 
                    	ListaEnderecos listaEnderecos = ListaEnderecos.Factory.newInstance();
	                    for (int i = 0; i < retorno.length; i++) {
	                        listaEnderecos.addNewEnderecoVO();
	                        listaEnderecos.getEnderecoVOArray(i).addNewTipoEnderecoVO();
	                        listaEnderecos.getEnderecoVOArray(i).setIdEndereco(ConstantesCRM.SVAZIO);
	                        listaEnderecos.getEnderecoVOArray(i).setNmTipoLogradouro(
	                                retorno[i].getTipoLogradouro() == null || retorno[i].getTipoLogradouro().getDescricao() == null ? ConstantesCRM.SVAZIO : retorno[i].getTipoLogradouro().getDescricao());
	                        listaEnderecos.getEnderecoVOArray(i).setNmTituloLogradouro(
	                                retorno[i].getTituloLogradouro() == null || retorno[i].getTituloLogradouro().getDescricao() == null ? ConstantesCRM.SVAZIO : retorno[i].getTituloLogradouro()
	                                        .getDescricao());
	                        listaEnderecos.getEnderecoVOArray(i).setDsEnderecoComplemento(ConstantesCRM.SVAZIO);
	                        listaEnderecos.getEnderecoVOArray(i).setNrEndereco(ConstantesCRM.SVAZIO);
	                        listaEnderecos.getEnderecoVOArray(i).setNmLogradouro(retorno[i].getLogradouro() == null ? ConstantesCRM.SVAZIO : retorno[i].getLogradouro());
	                        listaEnderecos.getEnderecoVOArray(i).setNmBairro(retorno[i].getBairro() == null ? ConstantesCRM.SVAZIO : retorno[i].getBairro());
	                        listaEnderecos.getEnderecoVOArray(i).setNmMunicipio(retorno[i].getCidade() == null ? ConstantesCRM.SVAZIO : retorno[i].getCidade());
	                        listaEnderecos.getEnderecoVOArray(i).setNrCEP(retorno[i].getCep() == null ? ConstantesCRM.SVAZIO : retorno[i].getCep());
	                        listaEnderecos.getEnderecoVOArray(i).setDsLado(retorno[i].getDescricaoLado() == null ? ConstantesCRM.SVAZIO : retorno[i].getDescricaoLado());
	
	                        listaEnderecos.getEnderecoVOArray(i).addNewUFVO();
	                        listaEnderecos.getEnderecoVOArray(i).getUFVO()
	                                .setIdUF(retorno[i].getUf() == null || retorno[i].getUf().getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getCodigo().toString());
	                        listaEnderecos.getEnderecoVOArray(i).getUFVO()
	                                .setSgUF(retorno[i].getUf() == null || retorno[i].getUf().getSigla() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getSigla());
	                        listaEnderecos.getEnderecoVOArray(i).getUFVO()
	                                .setNmUF(retorno[i].getUf() == null || retorno[i].getUf().getNome() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getNome());
	
	                        listaEnderecos.getEnderecoVOArray(i).addNewPaisVO();
	                        listaEnderecos.getEnderecoVOArray(i).getPaisVO()
	                                .setIdPais(retorno[i].getPais() == null || retorno[i].getPais().getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getCodigo().toString());
	                        listaEnderecos.getEnderecoVOArray(i).getPaisVO()
	                                .setSgPais(retorno[i].getPais() == null || retorno[i].getPais().getSigla() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getSigla());
	                        listaEnderecos.getEnderecoVOArray(i).getPaisVO()
	                                .setNmPais(retorno[i].getPais() == null || retorno[i].getPais().getNome() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getNome());
	
	                        listaEnderecos.getEnderecoVOArray(i).setCodLogradouro(retorno[i].getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getCodigo().toString());
	                        listaEnderecos.getEnderecoVOArray(i).setInCnl(
	                                retorno[i].getEnderecoFiscal() == null || retorno[i].getEnderecoFiscal().getCodigoNacLocalidade() == null ? ConstantesCRM.SVAZIO : retorno[i].getEnderecoFiscal()
	                                        .getCodigoNacLocalidade());
	                        listaEnderecos.getEnderecoVOArray(i).setInCodigoIBGE(
	                                retorno[i].getEnderecoFiscal() == null || retorno[i].getEnderecoFiscal().getCodigoIBGE() == null ? ConstantesCRM.SVAZIO : retorno[i].getEnderecoFiscal()
	                                        .getCodigoIBGE());
	                    }
	                    pesquisaEnderecoVO.setListaEnderecos(listaEnderecos);
                    }
                    

                } else {
                    pesquisaEnderecoVO = telaInicialFacade.getPesquisaEnderecoFil(user, filtroEndereco);
                }

                abaEndereco.setPesquisaEndereco(pesquisaEnderecoVO);
                if (abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray().length == 0) {
                    abaEndereco.setIsAddrPresent(false);
                } else {
                    abaEndereco.setIsAddrPresent(true);
                }
            }

            logLupaUsuario.debug("DetalheUsuarioController::pesquisarEndereco(" + user + ") >> FINALIZADO");

            request.setAttribute("abaEndereco", abaEndereco);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:buscaEndereco(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    protected boolean checaEmail(String contato, String acao, String id, String ds, String nr, HttpServletRequest request) throws Exception {
        try {
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> INICIALIZADO");

            LinhasPorIdVO filtro = LinhasPorIdVO.Factory.newInstance();
            filtro.setIdPessoa(idPessoa);

            if (acao.equalsIgnoreCase("delete")) {
                logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> Exclusao");
                // Retorna todoas as linhas do cliente(id e linha)
                abaContato.setLinhas(telaInicialFacade.getLinhasPorId(user, filtro));
                if (abaContato.getLinhas().getLinhasArray().length > 0) {

                    LinhasPorIdVO linhasId = abaContato.getLinhas();

                    // Gera Array arvore[] com linhas para ser enviado para o Facade que
                    // retornara se email existe no legado
                    arvore = new ARVORE[abaContato.getLinhas().getLinhasArray().length];
                    for (int i = 0; i < abaContato.getLinhas().getLinhasArray().length; i++) { // Executa conforme qtde
                        // de linhas
                        arvore[i] = ARVORE.Factory.newInstance();
                        arvore[i].setCdAreaRegistro(linhasId.getLinhasArray(i).getNrLinha().substring(0, 2));
                        arvore[i].setIdTipoLinha(linhasId.getLinhasArray(i).getIdTipoLinha());
                        arvore[i].setNrLinha(linhasId.getLinhasArray(i).getNrLinha().substring(2, nrLinha.length()));
                        arvore[i].setStatus(ConstantesCRM.SVAZIO);
                    }

                    ApoioParametroVO apoio = ApoioParametroVO.Factory.newInstance();
                    apoio.setCdParametro(ConstantesCRM.CD_PARAM_URL_SERVICE_ROUTER);
                    apoio = prePagoFacade.getApoioParametro(user, apoio);
                    List receivePesquisa = prePagoFacade.pesquisaEmailLegado(user, abaContato.getDsOldEmail(), arvore, apoio.getDsValorParametro());

                    // Devolve numero de vezes em que o email foi encontrado nas linhas do cliente no legado.
                    // Se maior que 1, informa existencia de vinculo entre emails no FO e legado e nao permite exclusao.
                    if (receivePesquisa.size() > 0) {
                        logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> E-mail encontrado no legado");
                        String nrLinhas[] = new String[receivePesquisa.size()];
                        // Todo: verificar se eh somente uma linha... se for uma, informa uma linha
                        // se for mais que uma, informar todas as linhas
                        if (receivePesquisa.size() == 1) {
                            ARVORE arvoreLinhas = (ARVORE) receivePesquisa.get(0);
                            nrLinhas[0] = "(" + arvoreLinhas.getCdAreaRegistro() + ")" + arvoreLinhas.getNrLinha().substring(0, 4) + "-" + arvoreLinhas.getNrLinha().substring(4, 8);
                            abaContato.setMsgAlerta("E-mail vinculado ao envio de comprovante/conta para a linha " + nrLinhas[0] + ".\\nPara excluí-lo é necessário desvinculá-lo do envio!");
                        } else {
                            String formataLinhas = ConstantesCRM.SVAZIO;
                            for (int i = 0; i < receivePesquisa.size(); i++) {
                                ARVORE arvoreLinhas = (ARVORE) receivePesquisa.get(i);
                                nrLinhas[i] = "(" + arvoreLinhas.getCdAreaRegistro() + ")" + arvoreLinhas.getNrLinha().substring(0, 4) + "-" + arvoreLinhas.getNrLinha().substring(4, 8);
                            }
                            for (int j = 0; j < receivePesquisa.size(); j++) {
                                formataLinhas += nrLinhas[j] + ", ";
                            }
                            formataLinhas = formataLinhas.substring(0, formataLinhas.length() - 2);
                            formataLinhas = formataLinhas.substring(0, formataLinhas.lastIndexOf(",")) + " e" + formataLinhas.substring(formataLinhas.lastIndexOf(",") + 1);
                            abaContato.setMsgAlerta("E-mail vinculado ao envio de comprovante/conta para as linhas " + formataLinhas + ".\\nPara excluí-lo é necessário desvinculá-lo do envio!");
                        }

                        abaContato.setIdComunicacao(id);
                        abaContato.getContato().setDsContato(ds);
                        abaContato.getContato().setNrSequencia(nr);
                        abaContato.setInMsgRetorno("naoPermiteExclusao");
                        return false;
                    } else { // Nao encontrou e-mail igual no legado
                        logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> E-mail nao encontrado no legado");
                        abaContato.setInMsgRetorno("false");
                        return true;
                    }

                } else {
                    abaContato.setInMsgRetorno("false");
                    return true;
                }
            } else {
                if (contato.equalsIgnoreCase(abaContato.getDsOldEmail())) {
                    abaContato.setInMsgRetorno("false");
                    return true;
                } else { // Executa caso seja alteracao
                    logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> Alteracao de e-mail");
                    // Retorna todoas as linhas do cliente(id e linha)
                    abaContato.setLinhas(telaInicialFacade.getLinhasPorId(user, filtro));
                    if (abaContato.getLinhas().getLinhasArray().length > 0) {

                        LinhasPorIdVO linhasId = abaContato.getLinhas();

                        // Gera Array arvore[] com linhas para ser enviado para o Facade que
                        // retornara se email existe no legado
                        arvore = new ARVORE[abaContato.getLinhas().getLinhasArray().length];
                        for (int i = 0; i < abaContato.getLinhas().getLinhasArray().length; i++) { // Executa conforme
                            // qtde de linhas
                            arvore[i] = ARVORE.Factory.newInstance();
                            arvore[i].setCdAreaRegistro(linhasId.getLinhasArray(i).getNrLinha().substring(0, 2));
                            arvore[i].setIdTipoLinha(linhasId.getLinhasArray(i).getIdTipoLinha());
                            arvore[i].setNrLinha(linhasId.getLinhasArray(i).getNrLinha().substring(2, nrLinha.length()));
                            arvore[i].setStatus(ConstantesCRM.SVAZIO);
                        }

                        ApoioParametroVO apoio = ApoioParametroVO.Factory.newInstance();
                        apoio.setCdParametro(ConstantesCRM.CD_PARAM_URL_SERVICE_ROUTER);
                        apoio = prePagoFacade.getApoioParametro(user, apoio);
                        List receivePesquisa = prePagoFacade.pesquisaEmailLegado(user, abaContato.getDsOldEmail(), arvore, apoio.getDsValorParametro());

                        // Devolve numero de vezes em que o email foi encontrado nas linhas do cliente no legado.
                        // Se maior que 1, pede confirmacao para alterar tambem no legado
                        if (receivePesquisa.size() > 0) {
                            abaContato.setIdComunicacao(id);
                            abaContato.getContato().setDsContato(ds);
                            abaContato.getContato().setNrSequencia(nr);
                            abaContato.setInMsgRetorno("confirm");
                            logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> FINALIZADO (false)");
                            return false;
                        } else {
                            abaContato.setInMsgRetorno("false");
                            logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> FINALIZADO (true)");
                            return true;
                        }

                    } else {
                        abaContato.setInMsgRetorno("false");
                        logLupaUsuario.debug("DetalheUsuarioController:checaEmail(" + user + ") >> FINALIZADO (true)");
                        return true;
                    }
                }
                // }
            }
        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:checaEmail(" + user + ") - [" + e.getMessage() + "]", e);
            abaContato.setIdComunicacao(id);
            abaContato.getContato().setDsContato(ds);
            abaContato.getContato().setNrSequencia(nr);
            abaContato.setInMsgRetorno("erroLegado");
            if (acao.equals("delete")) {
                abaContato.setMsgAlerta("Houve um problema durante consulta ao sistema legado.\\nTemporariamente este endereço de e-mail não poderá ser excluído.");
            } else if (acao.equals("update")) {
                abaContato.setMsgAlerta("Houve um problema durante consulta ao sistema legado.\\nTemporariamente este endereço de e-mail não poderá ser alterado.");
            }
            return false;
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="executa.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward alterarEmailLegado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaContato form = (AbaContato) formParam;
            LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
            comunicacaoAlterada.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
            comunicacaoAlterada.addNewDadosLupaCliente();
            String resposta = ConstantesCRM.SVAZIO;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:alterarEmailLegado(" + user + ") >> INICIALIZADO");
            form.setIdComunicacao(request.getParameter("idEmail"));

            if (form.getInMsgRetorno().equals("erroFO")) {
                form.getContato().setDsContato(abaContato.getDsOldEmail());
                form.setInMsgRetorno(null);
            } else {
                form.getContato().setDsContato(request.getParameter("dsEmail"));
                form.setInMsgRetorno(null);
            }

            form.getContato().setNrSequencia(request.getParameter("nrSequencia"));
            form.setIdPessoa(abaContato.getIdPessoa());

            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());

            int persisteLegado = 0;

            ApoioParametroVO apoio = ApoioParametroVO.Factory.newInstance();
            apoio.setCdParametro(ConstantesCRM.CD_PARAM_URL_SERVICE_ROUTER);
            apoio = prePagoFacade.getApoioParametro(user, apoio);

            // CHAMADA DE ALTERAÇÃO DE E-MAIL NO LEGADO
            try {
                for (int i = 0; i < arvore.length; i++) {
                    prePagoFacade.persisteContaEmail(user, arvore[i].getCdAreaRegistro(), arvore[i].getNrLinha(), arvore[i].getIdTipoLinha(), form.contato.getDsContato().trim(),
                            apoio.getDsValorParametro());
                }
                persisteLegado = 1;

                resposta = telaInicialFacade.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());

                form.setInReload(ConstantesCRM.STRUE);
                abaContato = new AbaContato();
                abaContato.setIdPessoa(form.getIdPessoa());
                abaContato.setInReload(form.getInReload());

                if (resposta.indexOf("DUPLICATE KEY") > 0) {
                    abaContato.setInMsgRetorno("true");
                    abaContato.setMsgAlerta("Contato não pôde ser incluído/alterado pois já existe um contato do mesmo tipo com essa descrição ou uma seqüencia de contato com este valor!");

                } else {
                    request.setAttribute("reload", "ok");
                    abaContato.setInMsgRetorno("false");
                }

                logLupaUsuario.debug("DetalheUsuarioController:alterarEmailLegado(" + user + ") >> FINALIZADO");

                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(ConstantesCRM.SUCCESS);

            } catch (Exception e) {

                if (persisteLegado == 1) { // erro foi na base FO
                    // precisa chamar alteracao de legado novamente passando o email antigo
                    form.contato.setDsContato(abaContato.getDsOldEmail());
                    // alterarEmailLegado(form); -> quem chama eh o proprio jsp
                    abaContato.setInMsgRetorno("erroFO");
                    abaContato.setMsgAlerta("Houve um problema na alteração do Contato na base do FO. Alteração não realizada.");
                    logLupaUsuario.debug("DetalheUsuarioController:alterarEmailLegado(" + user + ") >> FINALIZADO (erro no FO)");

                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward(ConstantesCRM.SUCCESS);

                } else { // erro foi no legado
                    abaContato = new AbaContato();
                    abaContato.setIdPessoa(form.getIdPessoa());
                    abaContato.setInReload(form.getInReload());
                    abaContato.setDsOldEmail(form.getDsOldEmail());
                    abaContato.setInMsgRetorno("true");
                    abaContato.setMsgAlerta("Houve um problema na alteração do Contato na base do Legado. Alteração não realizada.");
                    logLupaUsuario.debug("DetalheUsuarioController:alterarEmailLegado(" + user + ") >> FINALIZADO (erro no legado)");

                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward(ConstantesCRM.SUCCESS);
                }
            }
        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:alterarEmailLegado(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="abaContato.jsp"
     * @jpf:forward name="reload" path="loadContato.do"
     * @jpf:forward name="executa" path="executa.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward controlaSequencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            AbaContato form = (AbaContato) formParam;
            user = ConstantesCRM.getCurrentUser(request.getSession());
            logLupaUsuario.debug("DetalheUsuarioController:controlaSequencia(" + user + ") >> INICIALIZADO");

            LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
            comunicacaoAlterada.addNewDadosAbaLupaCliente();
            comunicacaoAlterada.addNewDadosLupaCliente();

            ComunicacaoVO[] contatos = this.abaContato.getAbaContato();
            abaContato.setIdPessoa(form.idPessoa);

            String resposta = ConstantesCRM.SVAZIO;
            String idComunicacaoSelecionada = form.idComunicacao;
            int idArray = Integer.parseInt(form.idArray);
            String direcao = request.getParameter("direcao").toString();

            if (direcao.equals("up")) {
                logLupaUsuario.debug("DetalheUsuarioController:controlaSequencia(" + user + ") >> Ordenar para cima");
                if (idArray == 0) {
                    abaContato.setInMsgRetorno("alteracaoSequencia");
                    abaContato.setMsgAlerta("Este contato já é o primeiro da lista");

                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward("executa");

                } else {
                    for (int i = 0; i < contatos.length; i++) {
                        comunicacaoAlterada.getDadosAbaLupaCliente().addNewComunicacaoVO();
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setIdComunicacao(contatos[i].getIdComunicacao());
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setDsContato(contatos[i].getDsContato().trim());
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setNrSequencia(Integer.toString(i + 1));
                    }
                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray)
                            .setNrSequencia(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray - 1).getNrSequencia());
                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray - 1)
                            .setNrSequencia(Integer.toString(Integer.parseInt(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray - 1).getNrSequencia()) + 1));
                }

            } else if (direcao.equals("down")) {
                logLupaUsuario.debug("DetalheUsuarioController:controlaSequencia(" + user + ") >> Ordenar para baixo");
                if (idArray == contatos.length - 1) {
                    abaContato.setInMsgRetorno("alteracaoSequencia");
                    abaContato.setMsgAlerta("Este contato já é o último da lista");

                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward("executa");

                } else {
                    for (int i = 0; i < contatos.length; i++) {
                        comunicacaoAlterada.getDadosAbaLupaCliente().addNewComunicacaoVO();
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setIdComunicacao(contatos[i].getIdComunicacao());
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setDsContato(contatos[i].getDsContato());
                        comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setNrSequencia(Integer.toString(i + 1));
                    }
                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray)
                            .setNrSequencia(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray + 1).getNrSequencia());
                    comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray + 1)
                            .setNrSequencia(Integer.toString(Integer.parseInt(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray).getNrSequencia()) - 1));
                }
            }

            resposta = telaInicialFacade.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());

            form.setInReload(ConstantesCRM.STRUE);
            abaContato = new AbaContato();
            abaContato.setIdPessoa(form.getIdPessoa());
            abaContato.setInReload(form.getInReload());
            request.setAttribute("reload", "ok");
            abaContato.setInMsgRetorno("false");
            logLupaUsuario.debug("DetalheUsuarioController:controlaSequencia(" + user + ") >> FINALIZADO");

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("reload");

        } catch (Exception e) {
            logLupaUsuario.error("DetalheUsuarioController:controlaSequencia(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    public static class AbaContato extends ActionForm {

        private static final long      serialVersionUID  = -7634229181516611756L;
        private String                 inReload          = ConstantesCRM.SVAZIO;
        private boolean                inEmail;
        private String                 dsOldEmail;
        private LinhasPorIdVO          linhas;
        private String                 msgAlerta;

        private String                 inMsgRetorno      = new String();
        private ListaTipoComunicacaoVO listaTipos        = ListaTipoComunicacaoVO.Factory.newInstance();
        private String                 idTipoSelecionado = new String();
        private ComunicacaoVO          contato;
        private String                 idComunicacao     = new String();
        private String                 idPessoa          = new String();
        private String                 idArray           = new String();
        private String                 acao              = new String();
        private ComunicacaoVO[]        abaContato;

        public AbaContato() {
            contato = ComunicacaoVO.Factory.newInstance();
            contato.addNewTipoComunicacaoVO();
        }

        public void setAbaContato(ComunicacaoVO[] abaContato) {
            this.abaContato = abaContato;
        }

        public ComunicacaoVO[] getAbaContato() {
            return this.abaContato;
        }

        public void setAcao(String acao) {
            this.acao = acao;
        }

        public String getAcao() {
            return this.acao;
        }

        public void setLinhas(LinhasPorIdVO linhas) {
            this.linhas = linhas;
        }

        public LinhasPorIdVO getLinhas() {
            return this.linhas;
        }

        public void setInEmail(boolean inEmail) {
            this.inEmail = inEmail;
        }

        public boolean isInEmail() {
            return this.inEmail;
        }

        public void setIdArray(String idArray) {
            this.idArray = idArray;
        }

        public String getIdArray() {
            return this.idArray;
        }

        public void setDsOldEmail(String dsOldEmail) {
            this.dsOldEmail = dsOldEmail;
        }

        public String getDsOldEmail() {
            return this.dsOldEmail;
        }

        public void setMsgAlerta(String msgAlerta) {
            this.msgAlerta = msgAlerta;
        }

        public String getMsgAlerta() {
            return this.msgAlerta;
        }

        public void setIdPessoa(String idPessoa) {
            this.idPessoa = idPessoa;
        }

        public String getIdPessoa() {
            return this.idPessoa;
        }

        public void setIdComunicacao(String idComunicacao) {
            this.idComunicacao = idComunicacao;
        }

        public String getIdComunicacao() {
            return this.idComunicacao;
        }

        public void setContato(ComunicacaoVO contato) {
            this.contato = contato;
        }

        public ComunicacaoVO getContato() {
            return this.contato;
        }

        public void setIdTipoSelecionado(String idTipoSelecionado) {
            this.idTipoSelecionado = idTipoSelecionado;
        }

        public String getIdTipoSelecionado() {
            return this.idTipoSelecionado;
        }

        public void setListaTipos(ListaTipoComunicacaoVO listaTipos) {
            this.listaTipos = listaTipos;
        }

        public ListaTipoComunicacaoVO getListaTipos() {
            return this.listaTipos;
        }

        public void setInMsgRetorno(String inMsgRetorno) {
            this.inMsgRetorno = inMsgRetorno;
        }

        public String getInMsgRetorno() {
            return this.inMsgRetorno;
        }

        public void setInReload(String inReload) {
            this.inReload = inReload;
        }

        public String getInReload() {
            return this.inReload;
        }
    }

    public static class AbaEndereco extends ActionForm {

        private static final long      serialVersionUID  = -7277831655311090344L;
        private boolean                isAddrPresent;
        private String                 inReload          = ConstantesCRM.SVAZIO;
        private PesquisaEnderecoVO     pesquisaEndereco;
        private String                 inMsgRetorno      = ConstantesCRM.SVAZIO;
        private String                 dsMsgRetorno      = ConstantesCRM.SVAZIO;
        private String                 acao              = new String();
        private String                 idEndereco        = new String();
        private String                 idArray           = new String();
        private EnderecoVO[]           abaEndereco;
        private EnderecoVO             endereco;
        private String                 idTipoSelecionado = new String();
        private String                 idUFSelecionado   = new String();
        private String                 idPaisSelecionado = new String();
        private String                 idPessoa          = new String();
        private boolean                pesquisaBaseFO    = false;
        private CarregarNovoEnderecoVO listas            = CarregarNovoEnderecoVO.Factory.newInstance();

        public AbaEndereco() {
            endereco = EnderecoVO.Factory.newInstance();
            endereco.addNewTipoEnderecoVO();
            endereco.addNewPaisVO();
            endereco.addNewUFVO();
            pesquisaEndereco = PesquisaEnderecoVO.Factory.newInstance();
            pesquisaEndereco.addNewFiltroPesquisa();
        }

        public boolean isPesquisaBaseFO() {
            return this.pesquisaBaseFO;
        }

        public void setPesquisaBaseFO(boolean arg) {
            this.pesquisaBaseFO = arg;
        }

        public EnderecoVO getEndereco() {
            return this.endereco;
        }

        public void setEndereco(EnderecoVO endereco) {
            this.endereco = endereco;
        }

        public void setAbaEndereco(EnderecoVO[] abaEndereco) {
            this.abaEndereco = abaEndereco;
        }

        public EnderecoVO[] getAbaEndereco() {
            return this.abaEndereco;
        }

        public void setIdArray(String idArray) {
            this.idArray = idArray;
        }

        public String getIdArray() {
            return this.idArray;
        }

        public void setIdEndereco(String idEndereco) {
            this.idEndereco = idEndereco;
        }

        public String getIdEndereco() {
            return this.idEndereco;
        }

        public void setAcao(String acao) {
            this.acao = acao;
        }

        public String getAcao() {
            return this.acao;
        }

        public void setIdPessoa(String idPessoa) {
            this.idPessoa = idPessoa;
        }

        public String getIdPessoa() {
            return this.idPessoa;
        }

        public void setListas(CarregarNovoEnderecoVO listas) {
            this.listas = listas;
        }

        public CarregarNovoEnderecoVO getListas() {
            return this.listas;
        }

        public void setIdTipoSelecionado(String idTipoSelecionado) {
            this.idTipoSelecionado = idTipoSelecionado;
        }

        public String getIdTipoSelecionado() {
            return this.idTipoSelecionado;
        }

        public void setIdUFSelecionado(String idUFSelecionado) {
            this.idUFSelecionado = idUFSelecionado;
        }

        public String getIdUFSelecionado() {
            return this.idUFSelecionado;
        }

        public void setIdPaisSelecionado(String idPaisSelecionado) {
            this.idPaisSelecionado = idPaisSelecionado;
        }

        public String getIdPaisSelecionado() {
            return this.idPaisSelecionado;
        }

        public void setInMsgRetorno(String inMsgRetorno) {
            this.inMsgRetorno = inMsgRetorno;
        }

        public String getInMsgRetorno() {
            return this.inMsgRetorno;
        }

        public void setDsMsgRetorno(String dsMsgRetorno) {
            this.dsMsgRetorno = dsMsgRetorno;
        }

        public String getDsMsgRetorno() {
            return this.dsMsgRetorno;
        }

        public void setPesquisaEndereco(PesquisaEnderecoVO pesquisaEndereco) {
            this.pesquisaEndereco = pesquisaEndereco;
        }

        public PesquisaEnderecoVO getPesquisaEndereco() {
            return this.pesquisaEndereco;
        }

        public void setInReload(String inReload) {
            this.inReload = inReload;
        }

        public String getInReload() {
            return this.inReload;
        }

        public void setIsAddrPresent(boolean isAddrPresent) {
            this.isAddrPresent = isAddrPresent;
        }

        public boolean isIsAddrPresent() {
            return this.isAddrPresent;
        }
    }

    public static class PermissoesForm extends ActionForm {

        private static final long                                                                                       serialVersionUID = 920989774910186553L;
        private br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato[]                             formMeioContatoVO;
        private String[]                                                                                                formMeioContatoRecusado;
        private String[]                                                                                                formMeioContato;
        private br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado[] formMeioContatoRecusadoVO;
        private br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados[]        formCanaisRelacionadosVO;
        private String[]                                                                                                formCanaisRelacionados;
        private br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes[]              formCanaisExistentesVO;
        private String[]                                                                                                formCanaisExistentes;

        public PermissoesForm() {
            formMeioContatoVO = new MeioContato[0];
            formMeioContato = new String[0];
            formMeioContatoRecusadoVO = new MeioContatoRecusado[0];
            formMeioContatoRecusado = new String[0];
            formCanaisRelacionadosVO = new CanaisRelacionados[0];
            formCanaisRelacionados = new String[0];
            formCanaisExistentesVO = new CanaisExistentes[0];
            formCanaisExistentes = new String[0];
        }

        public void setFormCanaisExistentes(String[] formCanaisExistentes) {
            this.formCanaisExistentes = formCanaisExistentes;
        }

        public String[] getFormCanaisExistentes() {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null.
            if (this.formCanaisExistentes == null || this.formCanaisExistentes.length == 0) {
                this.formCanaisExistentes = new String[1];
            }

            return this.formCanaisExistentes;
        }

        public void setFormCanaisExistentesVO(br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes[] formCanaisExistentesVO) {
            this.formCanaisExistentesVO = formCanaisExistentesVO;
        }

        public br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes[] getFormCanaisExistentesVO() {
            return this.formCanaisExistentesVO;
        }

        public void setFormCanaisRelacionados(String[] formCanaisRelacionados) {
            this.formCanaisRelacionados = formCanaisRelacionados;
        }

        public String[] getFormCanaisRelacionados() {
            return this.formCanaisRelacionados;
        }

        public void setFormCanaisRelacionadosVO(br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados[] formCanaisRelacionadosVO) {
            this.formCanaisRelacionadosVO = formCanaisRelacionadosVO;
        }

        public br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados[] getFormCanaisRelacionadosVO() {
            return this.formCanaisRelacionadosVO;
        }

        public void setFormMeioContatoRecusadoVO(br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado[] formMeioContatoRecusadoVO) {
            this.formMeioContatoRecusadoVO = formMeioContatoRecusadoVO;
        }

        public br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado[] getFormMeioContatoRecusadoVO() {
            return this.formMeioContatoRecusadoVO;
        }

        public void setFormMeioContato(String[] formMeioContato) {
            this.formMeioContato = formMeioContato;
        }

        public String[] getFormMeioContato() {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null.
            if (this.formMeioContato == null || this.formMeioContato.length == 0) {
                this.formMeioContato = new String[1];
            }

            return this.formMeioContato;
        }

        public void setFormMeioContatoRecusado(String[] formMeioContatoRecusado) {
            this.formMeioContatoRecusado = formMeioContatoRecusado;
        }

        public String[] getFormMeioContatoRecusado() {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null.
            if (this.formMeioContatoRecusado == null || this.formMeioContatoRecusado.length == 0) {
                this.formMeioContatoRecusado = new String[1];
            }

            return this.formMeioContatoRecusado;
        }

        public void setFormMeioContatoVO(br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato[] formMeioContatoVO) {
            this.formMeioContatoVO = formMeioContatoVO;
        }

        public br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato[] getFormMeioContatoVO() {
            return this.formMeioContatoVO;
        }
    }

    public PermissoesForm getPermissoesForm() {
        return this.permissoesForm;
    }

    public static class FormID extends ActionForm {
        private static final long serialVersionUID = 1588061700974214260L;
        private String            idPessoaCliente  = new String();

        public void setIdPessoaCliente(String idPessoaCliente) {
            this.idPessoaCliente = idPessoaCliente;
        }

        public String getIdPessoaCliente() {
            return this.idPessoaCliente;
        }
    }
}
