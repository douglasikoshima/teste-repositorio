package cliente.Prospects;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.AlterProtocoloTODocument.AlterProtocoloTO;
import br.com.vivo.fo.cliente.vo.CadastroProspectVODocument.CadastroProspectVO;
import br.com.vivo.fo.cliente.vo.ContatoProspectVODocument.ContatoProspectVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO;
import br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO.ListaTipoEndereco;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.ProspectVODocument.ProspectVO;
import br.com.vivo.fo.cliente.vo.TipoComunicacaoVODocument.TipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.impl.ContatoProspectVODocumentImpl.ContatoProspectVOImpl;
import br.com.vivo.fo.cliente.vo.impl.PaisVODocumentImpl.PaisVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoComunicacaoVODocumentImpl.TipoComunicacaoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoDocumentoVODocumentImpl.TipoDocumentoVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import cliente.URLErro;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings("unused")
public class ProspectsController extends AbstractAction {

	private static final long serialVersionUID = -4088870117661065112L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.prospect.ProspectFacade prospectFacade;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.protocolo.ProtocoloFacade protocoloFacade;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacade;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.prePago.PrePagoFacade prePagoFacade;

	private static Logger log = new Logger("prepago");

	private String user = null;
	private CadastroProspectForm cadastroProspectForm = null;

	protected global.Global globalApp = new global.Global();

	private final int ID_SISTEMA_ORIGEM_VIVONET = 7;
	private final int IDTIPOABERTURAPROTOCOLO_LINHACLIENTE = 3;
	private final int IDTIPOABERTURAPROTOCOLO_PESSOA = 4;
	private final int IDTIPOABERTURAPROTOCOLO_CONTA = 1;
	private final int IDTIPOABERTURAPROTOCOLO_LINHA = 2;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisarEndereco".equals(mapping.getParameter())) {
			return pesquisarEndereco(mapping, form, request, response);
		} else if ("adicionarContatoProspect".equals(mapping.getParameter())) {
			return adicionarContatoProspect(mapping, form, request, response);
		} else if ("salvarProspect".equals(mapping.getParameter())) {
			return salvarProspect(mapping, form, request, response);
		} else if ("loadProspect".equals(mapping.getParameter())) {
			return loadProspect(mapping, form, request, response);
		} else if ("removeContatoProspect".equals(mapping.getParameter())) {
			return removeContatoProspect(mapping, form, request, response);
		} else if ("limpaProspect".equals(mapping.getParameter())) {
			return limpaProspect(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, request);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="loadProspect.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));

			boolean screenPop = request.getSession().getAttribute("screenPop") != null
				&& ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("screenPop")) ? true : false; 
			
			if (screenPop) {
				
				getCadastroProspectForm().setScreenPop(true);
				String nrLinhaProspect = (String) request.getSession().getAttribute("nrLinhaProspect");
				getCadastroProspectForm().setNrLinhaProspect(nrLinhaProspect);

				request.getSession().removeAttribute("screenPop");
				request.getSession().removeAttribute("nrLinhaProspect");
				
				request.getSession().setAttribute("idTipoPessoaProspect", ConstantesCRM.SONE);
				request.getSession().setAttribute("destinoProspect", "POPUP");

				log.debug("f.getPath():: "+f.getPath());

				f.addParameter("idTipoPessoa", ConstantesCRM.SONE);
				f.addParameter("destino", "POPUP");

			}

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ProspectsController:begin(" + user + ") >> INICIALIZADO e FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return f;

		} catch (Exception e) {
			log.error("ProspectsController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaEnderecoResultado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		CadastroProspectForm form = (CadastroProspectForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		PesquisaEnderecoVO pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
		pesquisaEnderecoVO.addNewFiltroPesquisa();
		response.setContentType(ConstantesCRM.SContentType);

		try {

			pesquisaEnderecoVO.getFiltroPesquisa().setNrCEP(form.getEnderecoVO().getNrCEP().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
			pesquisaEnderecoVO.getFiltroPesquisa().setDsLogradouro(null);
			pesquisaEnderecoVO.getFiltroPesquisa().setDsLocalidade(null);
			pesquisaEnderecoVO.getFiltroPesquisa().setIdUFSelecionado(null);
			pesquisaEnderecoVO.addNewListaEnderecos();
			pesquisaEnderecoVO = telaInicialFacade.getPesquisaEnderecoFil(user, pesquisaEnderecoVO);
			bufferedOutputStream.write(ClienteUtils.getCleanXMLFromXSD(pesquisaEnderecoVO, null).getBytes(ConstantesCRM.SISO));

		} catch (Exception ex) {
			bufferedOutputStream.write("<?xml version='1.0' encoding='ISO-8859-1'?><erro>Ocorreu um erro durante a pesquisa de endereço.</erro>".getBytes(ConstantesCRM.SISO));

		} finally {
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroProspect.jsp"
	 * @jpf:forward name="success1" path="cadastroProspectPopUp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward adicionarContatoProspect(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			CadastroProspectForm form = (CadastroProspectForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ProspectsController:adicionarContatoProspect(" + user + ") >> INICIALIZADO");

			// Recupera informações do Form e monta novo contato
			form.getCadastroProspectVO().getProspectVO().setContatoProspectVOArray(cadastroProspectForm.getCadastroProspectVO().getProspectVO().getContatoProspectVOArray());
			cadastroProspectForm.cadastroProspectVO.setProspectVO(form.getCadastroProspectVO().getProspectVO());

			getCadastroProspectForm().getCadastroProspectVO().getProspectVO().setNmPrimeiro(form.getPrNome());
			getCadastroProspectForm().getCadastroProspectVO().getProspectVO().setNmMeio(form.getMnNome());
			getCadastroProspectForm().getCadastroProspectVO().getProspectVO().setNmSobrenome(form.getUlNome());

			ContatoProspectVO contato = cadastroProspectForm.cadastroProspectVO.getProspectVO().addNewContatoProspectVO();
			contato.setDescricao(form.dsComunicacao);
			contato.setIdTipoComunicacao(form.idComunicacao);
			cadastroProspectForm.setPrNome(form.getPrNome());
			cadastroProspectForm.setMnNome(form.getMnNome());
			cadastroProspectForm.setUlNome(form.getPrNome());
			cadastroProspectForm.setEnderecoVO(form.getEnderecoVO());

			// Busca a descrição no array de Tipo de Documento e coloca pra ser mostrada na tela
			TipoComunicacaoVO[] doctoArr = cadastroProspectForm.cadastroProspectVO.getTipoComunicacaoVOArray();
			for (int i = 0; i < doctoArr.length; i++) {
				if (doctoArr[i].getIdTipoComunicacao().equalsIgnoreCase(form.idComunicacao)) {
					contato.setDsTipoComunicacao(doctoArr[i].getDsTipoComunicacao());
				}
			}

			if (request.getParameter("destino") != null) {
				if (request.getParameter("destino").equalsIgnoreCase("POPUP")) {
					log.debug("ProspectsController:adicionarContatoProspect(" + user + ") >> FINALIZADO (Popup)");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				} else {
					log.debug("ProspectsController:adicionarContatoProspect(" + user + ") >> FINALIZADO");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			} else {
				log.debug("ProspectsController:adicionarContatoProspect(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {

			log.error("ProspectsController:adicionarContatoProspect(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="erro" path="cadastroProspect.jsp"
	 * @jpf:forward name="erroPOPUP" path="cadastroProspectPopUp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarProspect(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			CadastroProspectForm form = (CadastroProspectForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());
			String idNovoProspect = ConstantesCRM.SVAZIO;
			String idPessoaDePara = ConstantesCRM.SVAZIO;
			log.debug("ProspectsController:salvarProspect(" + user + ") >> INICIALIZADO");

			String resposta = ConstantesCRM.SVAZIO;
			String destino = ConstantesCRM.SUCCESS;
			ActionRedirect f = new ActionRedirect(mapping.findForward("success"));
			f.addParameter("destino", request.getParameter("destino"));
			f.addParameter("idTipoPessoa", request.getParameter("idTipoPessoa"));

			// recupera informações do Form e monta estrutura para gravação
			form.cadastroProspectVO.getProspectVO().setContatoProspectVOArray(cadastroProspectForm.cadastroProspectVO.getProspectVO().getContatoProspectVOArray());

			// Dispara serviço TUXEDO para gravação
			String primeiroNome = form.getCadastroProspectVO().getProspectVO().getNmPrimeiro().trim();
			String nomeMeio = form.getCadastroProspectVO().getProspectVO().getNmMeio().trim();
			String sobreNome = form.getCadastroProspectVO().getProspectVO().getNmSobrenome().trim();

			log.debug("ProspectsController:salvarProspect(" + user + ") >> Salvar Prospect: " + primeiroNome + " " + nomeMeio + " " + sobreNome);

			form.getCadastroProspectVO().getProspectVO().setNmPrimeiro(primeiroNome);
			form.getCadastroProspectVO().getProspectVO().setNmMeio(nomeMeio);
			form.getCadastroProspectVO().getProspectVO().setNmSobrenome(sobreNome);
			form.getCadastroProspectVO().getProspectVO().setTipoPessoa(request.getParameter("idTipoPessoa"));
			form.getCadastroProspectVO().getProspectVO().setNrLinhaProspect(getCadastroProspectForm().getNrLinhaProspect());

			if (nomeMeio.length() > 0) {
				form.getCadastroProspectVO().getProspectVO().setNome(primeiroNome + " " + nomeMeio + " " + sobreNome);
			} else {
				if (form.getCadastroProspectVO().getProspectVO().getTipoPessoa().equalsIgnoreCase("1")) {
					form.getCadastroProspectVO().getProspectVO().setNome(primeiroNome + " " + sobreNome);
				} else {
					form.getCadastroProspectVO().getProspectVO().setNome(primeiroNome);
				}
			}

			if (ConstantesCRM.SVAZIO.equals(form.getEnderecoVO().getNrCEP())) {
				form.getCadastroProspectVO().getProspectVO().setEnderecoVOArray(new EnderecoVO[0]);
			} else {
				form.getCadastroProspectVO().getProspectVO().getEnderecoVOArray(0).set(form.getEnderecoVO().copy());
			}
			resposta = prospectFacade.gravaProspect(form.getCadastroProspectVO(), user);

			if (request.getParameter("destino") != null) {
				request.setAttribute("destinoProspect", "POPUP");
				if (resposta.indexOf("</idPessoa>") > 0) {
					idNovoProspect = resposta.substring(resposta.indexOf("<idPessoa>") + 10, resposta.indexOf("</idPessoa>"));
					request.getSession().setAttribute("idNovoProspect", idNovoProspect);
				}
				if (resposta.indexOf("</idPessoaDePara>") > 0) {
					idPessoaDePara = resposta.substring(resposta.indexOf("<idPessoaDePara>") + 16, resposta.indexOf("</idPessoaDePara>"));
				}
			}

			if (resposta.indexOf("unique constraint table.column violated") > 0) {
				cadastroProspectForm.getCadastroProspectVO().setProspectVO(form.getCadastroProspectVO().getProspectVO());
				cadastroProspectForm.setInMsgRetorno("true");
				if (request.getParameter("destino") != null) {
					request.setAttribute("destinoProspect", "POPUP");
					log.debug("ProspectsController:salvarProspect(" + user + ") >> FINALIZADO (Erro Popup)");
					destino = "erroPOPUP";
				} else {
					log.debug("ProspectsController:salvarProspect(" + user + ") >> FINALIZADO (Erro)");
					destino = "erro";
				}
			} else {
	            getCadastroProspectForm().setNrLinhaProspect(ConstantesCRM.SVAZIO);
				request.getSession().removeAttribute("erroProspect");
			}
			log.debug("ProspectsController:salvarProspect(" + user + ") >> FINALIZADO");


			// Altera dados do protocolo: Abertura por linha -> Abertura por Pessoa
			String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;
			if (!ConstantesCRM.SVAZIO.equals(nrProtocolo)) {
				AlterProtocoloTO alterProtocoloTO = AlterProtocoloTO.Factory.newInstance();
				alterProtocoloTO.setIdTipoAberturaProtocolo(Integer.toString(IDTIPOABERTURAPROTOCOLO_PESSOA));
				alterProtocoloTO.setIdPessoaDePara(idPessoaDePara);
				protocoloFacade.setAlteracaoProtocolo(user, alterProtocoloTO, "alterar", nrProtocolo, 0, 0);
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			if(ConstantesCRM.SUCCESS.equals(destino)) {
	            return mapping.findForward("erroPOPUP");
			}else {
                return mapping.findForward(destino);
			}

		} catch (Exception e) {
			log.error("ProspectsController:salvarProspect(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroProspect.jsp"
	 * @jpf:forward name="success1" path="cadastroProspectPopUp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadProspect(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			CadastroProspectForm form = (CadastroProspectForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ProspectsController:loadProspect(" + user + ") >> INICIALIZADO");

			/*
			 * idTipoPessoa: 1 - PF 2 - PJ
			 */
			String idTipoPessoa = request.getParameter("idTipoPessoa");
			if (idTipoPessoa == null) {
				idTipoPessoa = request.getSession().getAttribute("idTipoPessoaProspect") != null
					? (String) request.getSession().getAttribute("idTipoPessoaProspect")
					: null;
			}
			request.getSession().removeAttribute("idTipoPessoaProspect");

			if (idTipoPessoa == null) {
				if ("razao".equalsIgnoreCase(form.getTipoPesquisa()) || "cnpj".equalsIgnoreCase(form.getTipoPesquisa()) || "ie".equalsIgnoreCase(form.getTipoPesquisa()) || "im".equalsIgnoreCase(form.getTipoPesquisa())) {
					idTipoPessoa = ConstantesCRM.STWO;
				} else {
					idTipoPessoa = ConstantesCRM.SONE;
				}
			}

			log.debug("ProspectsController:loadProspect(" + user + ") >> idTipoPessoa: " + idTipoPessoa);

			// Obtém as listas de Tipo de documento, país e tipo de comunicação
			CadastroProspectVO cadastroProspectVO = prospectFacade.getListas(user, idTipoPessoa);
			cadastroProspectVO.addNewProspectVO();

			for (int i = 0; i < cadastroProspectVO.getPaisVOArray().length; i++) {
				if (cadastroProspectVO.getPaisVOArray(i).getSgPais().trim().equalsIgnoreCase("BRA")) {
					cadastroProspectVO.getProspectVO().setIdPais(cadastroProspectVO.getPaisVOArray(i).getIdPais());
				}
			}

			getCadastroProspectForm().setCadastroProspectVO(cadastroProspectVO);

			ListasVO listasVO = ListasVO.Factory.newInstance();
			listasVO = prePagoFacade.getListasVO(user);
			
			EnderecoVO endProspect = EnderecoVODocument.Factory.newInstance().getEnderecoVO();
			getCadastroProspectForm().setEnderecoVO(endProspect);

			getCadastroProspectForm().getEnderecoVO().addNewTipoEnderecoVO();
			getCadastroProspectForm().getEnderecoVO().addNewUFVO();
			getCadastroProspectForm().getEnderecoVO().addNewPaisVO();
			getCadastroProspectForm().setListaTiposEndereco(listasVO.getListaTipoEnderecoArray());

			if (form.getTipoPesquisa() != null && !ConstantesCRM.SVAZIO.equals(form.getTipoPesquisa())) {
				getCadastroProspectForm().setTipoPesquisa(form.getTipoPesquisa());
				getCadastroProspectForm().setPrNome(form.getPrNome());
				getCadastroProspectForm().setMnNome(form.getMnNome());
				getCadastroProspectForm().setUlNome(form.getUlNome());

			} else {
				form.getCadastroProspectVO().getProspectVO().setNmPrimeiro(ConstantesCRM.SVAZIO);
				form.getCadastroProspectVO().getProspectVO().setNmMeio(ConstantesCRM.SVAZIO);
				form.getCadastroProspectVO().getProspectVO().setNmSobrenome(ConstantesCRM.SVAZIO);
			}

			String param = ConstantesCRM.SVAZIO;
			getCadastroProspectForm().setIdTipoPessoa(idTipoPessoa);

			if (request.getParameter("destino") != null) {
				param = request.getParameter("destino");
			} else if (request.getSession().getAttribute("destinoProspect") != null) {
				param = (String) request.getSession().getAttribute("destinoProspect");
			}
			request.getSession().removeAttribute("destinoProspect");

			getCadastroProspectForm().setInMsgRetorno("false");

			if (param.equalsIgnoreCase("POPUP") || request.getSession().getAttribute("dadosProspect") != null) {
				if (request.getSession().getAttribute("dadosProspect") != null) {
					String[] nome = (String[]) request.getSession().getAttribute("dadosProspect");
					getCadastroProspectForm().setTipoPesquisa(nome[3]);
					if (getCadastroProspectForm().getTipoPesquisa().equalsIgnoreCase("nome") || getCadastroProspectForm().getTipoPesquisa().equalsIgnoreCase("razao")) {
						getCadastroProspectForm().cadastroProspectVO.getProspectVO().setNmPrimeiro(nome[0]);
						getCadastroProspectForm().cadastroProspectVO.getProspectVO().setNmMeio(nome[1]);
						getCadastroProspectForm().cadastroProspectVO.getProspectVO().setNmSobrenome(nome[2]);
					}

					if (cadastroProspectForm.getTipoPesquisa().equalsIgnoreCase("razao")) {
						getCadastroProspectForm().setIdTipoPessoa(ConstantesCRM.STWO);
					}

					getCadastroProspectForm().setPrNome(nome[0]);
					getCadastroProspectForm().setMnNome(nome[1]);
					getCadastroProspectForm().setUlNome(nome[2]);
					request.getSession().removeAttribute("dadosProspect");

				} else {
					if (request.getParameter("limpar") == null) {
						getCadastroProspectForm().setPrNome(form.getPrNome());
						getCadastroProspectForm().setMnNome(form.getMnNome());
						getCadastroProspectForm().setUlNome(form.getUlNome());
						getCadastroProspectForm().setTipoPesquisa(form.getTipoPesquisa());
						getCadastroProspectForm().getCadastroProspectVO().setProspectVO(form.getCadastroProspectVO().getProspectVO());
					}
				}

				log.debug("ProspectsController:loadProspect(" + user + ") >> FINALIZADO (Popup)");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("success1");

			} else {
				log.debug("ProspectsController:loadProspect(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {
			log.error("ProspectsController:loadProspect(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroProspect.jsp"
	 * @jpf:forward name="success1" path="cadastroProspectPopUp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeContatoProspect(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			CadastroProspectForm form = (CadastroProspectForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ProspectsController:removeContatoProspect(" + user + ") >> INICIALIZADO");

			// Sincroniza as informações do FORM local e do cadastro.
			form.getCadastroProspectVO().getProspectVO().setContatoProspectVOArray(cadastroProspectForm.getCadastroProspectVO().getProspectVO().getContatoProspectVOArray());
			cadastroProspectForm.cadastroProspectVO.setProspectVO(form.getCadastroProspectVO().getProspectVO());
			cadastroProspectForm.cadastroProspectVO.getProspectVO().removeContatoProspectVO(Integer.parseInt(form.getNdxContatoARemover()));
			cadastroProspectForm.setPrNome(form.getPrNome());
			cadastroProspectForm.setMnNome(form.getMnNome());
			cadastroProspectForm.setUlNome(form.getPrNome());
			if (request.getParameter("destino") != null) {
				if (request.getParameter("destino").equalsIgnoreCase("POPUP")) {
					log.debug("ProspectsController:removeContatoProspect(" + user + ") >> FINALIZADO (Popup)");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				} else {
					log.debug("ProspectsController:removeContatoProspect(" + user + ") >> FINALIZADO");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			} else {
				log.debug("ProspectsController:removeContatoProspect(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("ProspectsController:removeContatoProspect(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroProspect.jsp"
	 * @jpf:forward name="success1" path="cadastroProspectPopUp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaProspect(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ProspectsController:limpaProspect(" + user + ") >> INICIALIZADO");

			cadastroProspectForm.cadastroProspectVO.setProspectVO(ProspectVO.Factory.newInstance());

			if (request.getParameter("destino") != null) {
				if (request.getParameter("destino").equalsIgnoreCase("POPUP")) {
					log.debug("ProspectsController:limpaProspect(" + user + ") >> FINALIZADO (Popup)");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				} else {
					log.debug("ProspectsController:limpaProspect(" + user + ") >> FINALIZADO");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			} else {
				log.debug("ProspectsController:limpaProspect(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("ProspectsController:limpaProspect(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-action="ProspectsDone"
	 */
	public ActionForward done(ActionMapping mapping, HttpServletRequest request) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class CadastroProspectForm extends ActionForm {

		private static final long serialVersionUID = 1497289655519060888L;

		private String ulNome = ConstantesCRM.SVAZIO;
		private String mnNome = ConstantesCRM.SVAZIO;
		private String prNome = ConstantesCRM.SVAZIO;
		private String tipoPesquisa = ConstantesCRM.SVAZIO;
		private String idTipoPessoa = ConstantesCRM.SVAZIO;
		private boolean screenPop = false;
		private String inMsgRetorno = ConstantesCRM.SVAZIO;
		private String ndxContatoARemover = ConstantesCRM.SVAZIO;
		private String dsComunicacao = ConstantesCRM.SVAZIO;
		private String idComunicacao = ConstantesCRM.SVAZIO;
		private String nrLinhaProspect = ConstantesCRM.SVAZIO;

		private CadastroProspectVO cadastroProspectVO;
		private EnderecoVO enderecoVO;
		private ListaTipoEndereco[] listaTiposEndereco;

		public CadastroProspectForm() {
			// inicializa VO
			cadastroProspectVO = CadastroProspectVO.Factory.newInstance();
			enderecoVO = EnderecoVO.Factory.newInstance();
			enderecoVO.addNewPaisVO();
			enderecoVO.addNewUFVO();
			enderecoVO.addNewTipoEnderecoVO();

			cadastroProspectVO.setTipoDocumentoVOArray(new TipoDocumentoVOImpl[0]);
			cadastroProspectVO.setPaisVOArray(new PaisVOImpl[0]);
			cadastroProspectVO.setTipoComunicacaoVOArray(new TipoComunicacaoVOImpl[0]);

			ProspectVO prospect = ProspectVO.Factory.newInstance();
			prospect.setTipoPessoa(ConstantesCRM.SVAZIO);
			prospect.addNewEnderecoVO().set(enderecoVO.copy());
			cadastroProspectVO.setProspectVO(prospect);
			cadastroProspectVO.getProspectVO().setContatoProspectVOArray(new ContatoProspectVOImpl[0]);
		}

		public CadastroProspectVO getCadastroProspectVO() {
			return cadastroProspectVO;
		}

		public void setCadastroProspectVO(CadastroProspectVO prospectVO) {
			cadastroProspectVO = prospectVO;
		}

		public void setIdComunicacao(String idComunicacao) {
			this.idComunicacao = idComunicacao;
		}

		public String getIdComunicacao() {
			return this.idComunicacao;
		}

		public void setDsComunicacao(String dsComunicacao) {
			this.dsComunicacao = dsComunicacao;
		}

		public String getDsComunicacao() {
			return this.dsComunicacao;
		}

		public void setNdxContatoARemover(String ndxContatoARemover) {
			this.ndxContatoARemover = ndxContatoARemover;
		}

		public String getNdxContatoARemover() {
			return this.ndxContatoARemover;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}

		public void setIdTipoPessoa(String idTipoPessoa) {
			this.idTipoPessoa = idTipoPessoa;
		}

		public String getIdTipoPessoa() {
			return this.idTipoPessoa;
		}

		public void setTipoPesquisa(String tipoPesquisa) {
			this.tipoPesquisa = tipoPesquisa;
		}

		public String getTipoPesquisa() {
			return this.tipoPesquisa;
		}

		public void setPrNome(String prNome) {
			this.prNome = prNome;
		}

		public String getPrNome() {
			return this.prNome;
		}

		public void setMnNome(String mnNome) {
			this.mnNome = mnNome;
		}

		public String getMnNome() {
			return this.mnNome;
		}

		public void setUlNome(String ulNome) {
			this.ulNome = ulNome;
		}

		public String getUlNome() {
			return this.ulNome;
		}

		public void setScreenPop(boolean arg) {
			this.screenPop = arg;
		}

		public boolean isScreenPop() {
			return this.screenPop;
		}

		public void setNrLinhaProspect(String arg) {
			this.nrLinhaProspect = arg;
		}

		public String getNrLinhaProspect() {
			if (this.nrLinhaProspect == null) {
				this.nrLinhaProspect = ConstantesCRM.SVAZIO;
			}
			return this.nrLinhaProspect;
		}

		public void setEnderecoVO(EnderecoVO enderecoVO) {
			this.enderecoVO = enderecoVO;
		}

		public EnderecoVO getEnderecoVO() {
			if (this.enderecoVO == null) {
				this.enderecoVO = EnderecoVO.Factory.newInstance();
				this.enderecoVO.addNewPaisVO();
				this.enderecoVO.addNewTipoEnderecoVO();
				this.enderecoVO.addNewUFVO();
			}
			return this.enderecoVO;
		}

		public void setListaTiposEndereco(ListaTipoEndereco[] arg) {
			this.listaTiposEndereco = arg;
		}

		public ListaTipoEndereco[] getListaTiposEndereco() {
			if (this.listaTiposEndereco == null) {
				this.listaTiposEndereco = new ListaTipoEndereco[0];
			}
			return this.listaTiposEndereco;
		}
	}

	public CadastroProspectForm getCadastroProspectForm() {
		if (this.cadastroProspectForm == null) {
			this.cadastroProspectForm = new CadastroProspectForm();
		}
		return this.cadastroProspectForm;
	}
}