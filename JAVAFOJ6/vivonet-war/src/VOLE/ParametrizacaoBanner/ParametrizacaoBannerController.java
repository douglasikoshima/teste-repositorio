package VOLE.ParametrizacaoBanner;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import VOLE.ParametrizacaoBanner.formBeans.Banner;
import VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm;
import br.com.vivo.fo.cliente.vo.ListaUFVODocument.ListaUFVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLE.banner.BannerFacade;
import br.com.vivo.fo.ctrls.VOLE.banner.PaginacaoBannersVOLE;
import br.com.vivo.fo.ctrls.VOLTAV.manterTerminal.ManterTerminalFacade;
import br.com.vivo.fo.dbclasses.AcessoBannerVOLE;
import br.com.vivo.fo.log.Logger;
import cliente.URLErro;

import com.bea.wlw.netui.pageflow.NotLoggedInException;
import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unchecked","deprecation","unused"})
public class ParametrizacaoBannerController extends AbstractAction {

	private static final long serialVersionUID = -2176086062710868089L;

	protected global.Global globalApp = new global.Global();

	private ParametrizacaoBannerForm parametrizacaoBannerForm;

	@EJB
	private BannerFacade bannerFacadeControl;

	@EJB
	private ManterTerminalFacade manterTerminalFacadeControl;

	public ParametrizacaoBannerForm getParametrizacaoBannerForm() {
		if (this.parametrizacaoBannerForm == null) {
			this.parametrizacaoBannerForm = new ParametrizacaoBannerForm();
		}
		return this.parametrizacaoBannerForm;
	}

	private transient Logger log = new Logger("VOLE");

	private static final String BANNERVOLE_VAR_MANUTENCAO_ITENSPP = "BANNERVOLE_MANUTENCAO_ITENSPP";
	private static final String BANNERVOLE_VAR_PATH_UPLOAD = "BANNERVOLE_VAR_PATH_UPLOAD_NOVO";

	private String pathUpload = ConstantesCRM.SVAZIO;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("beginCadastroBanner".equals(mapping.getParameter())) {
			return beginCadastroBanner(mapping, form, request, response);
		} else if ("enviarArquivo".equals(mapping.getParameter())) {
			return enviarArquivo(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("associarDesassociarBanner".equals(mapping.getParameter())) {
			return associarDesassociarBanner(mapping, form, request, response);
		} else if ("salvarAssociacaoDesassociacaoBanner".equals(mapping.getParameter())) {
			return salvarAssociacaoDesassociacaoBanner(mapping, form, request, response);
		} else if ("mostrarImagemBanner".equals(mapping.getParameter())) {
			return mostrarImagemBanner(mapping, form, request, response);
		} else if ("salvarCadastroAlteracao".equals(mapping.getParameter())) {
			return salvarCadastroAlteracao(mapping, form, request, response);
		} else if ("buscarDadosBanner".equals(mapping.getParameter())) {
			return buscarDadosBanner(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;
		try {
			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

			HashMap listaAreas = bannerFacadeControl.buscarListaAreasBanner();
			getParametrizacaoBannerForm().setListaAreas(listaAreas);

			HashMap listaTiposBanner = bannerFacadeControl.buscarListaTiposBanner();
			getParametrizacaoBannerForm().setListaTiposBanner(listaTiposBanner);

			ListaUFVO listaUF = manterTerminalFacadeControl.obterComboUF(idUsuario);
			getParametrizacaoBannerForm().setListaUF(listaUF.getUFVOArray());

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch(Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="criarAlterarBanner.jsp"
	 */
	protected ActionForward beginCadastroBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		this.getParametrizacaoBannerForm().setBanner(new Banner());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retornoUpload.jsp"
	 */
	protected ActionForward enviarArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		String msgErro = ConstantesCRM.SVAZIO;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		boolean error = false;
		StringBuffer json = new StringBuffer("{ image : {");

		String nmArquivo = form.getBanner().getArquivo().getFileName();
		String extensao = nmArquivo.substring(nmArquivo.lastIndexOf(".") + 1, nmArquivo.length()).toUpperCase();

		String newFileName = "bnv";
		String paddedID = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String nomeAleatorio = RandomStringUtils.randomAlphabetic(7);
		newFileName = (newFileName + paddedID + nomeAleatorio + "." + extensao).toLowerCase();

		int idAreaBanner = 0;
		if (!"SWF".equals(extensao)) {
			if (ConstantesCRM.SVAZIO.equals(request.getParameter("idAreaBanner"))) {
				error = true;
				msgErro = "Por favor, selecione uma área.";
			} else {
				idAreaBanner = Integer.parseInt(request.getParameter("idAreaBanner"));
			}
		}

		int width = 0;
		int height = 0;
		int size = 0;

		if (extensao.equals("JPG")
				|| extensao.equals("JPE")
				|| extensao.equals("JPEG")
				|| extensao.equals("GIF")) {

			BufferedImage image = null;

			try {

				image  = ImageIO.read(form.getBanner().getArquivo().getInputStream());
				size   = form.getBanner().getArquivo().getFileSize();
				width  = image.getWidth();
				height = image.getHeight();

				int[] maxWH = new int[2];
				switch (idAreaBanner) {
				// Área Central
				case 1:
					maxWH = new int[] {630, 180};
					break;
					// Área lateral esquerda
				case 2:
					maxWH = new int[] {145, 310};
					break;
					// Aleatória
				case 3:
					maxWH = new int[] {778, 400};
					break;
				}

				if (!error && (width > maxWH[0] || height > maxWH[1])) {
					error = true;
					msgErro  = "Esta imagem possui dimensões maiores que o máximo permitido.\\n";
					msgErro += "Imagem: " + width + "x" + height + " pixels.\\n";
					msgErro += "Máximo permitido: " + maxWH[0] + "x" + maxWH[1] + " pixels.";
				}

			} catch (IOException e) {
				e.printStackTrace();
				error = true;
			}

		}

		if (ConstantesCRM.SVAZIO.equals(this.pathUpload)) {
			GetParametro parametro = GetParametro.getInstace();
			ParametroVO apoioParam = parametro.getParametroVO(idUsuario, BANNERVOLE_VAR_PATH_UPLOAD);
			if (ConstantesCRM.SVAZIO.equals(apoioParam.getDsValorParametro())) {
				error = true;
			} else {
				pathUpload = apoioParam.getDsValorParametro();
			}
		}
		if (pathUpload.charAt(pathUpload.length()-1) != '/') {
			pathUpload += "/";
		}
		File arquivo = new File(pathUpload + newFileName);

		if (!error && arquivo.exists()) {
			error = true;
			msgErro = "Já existe um arquivo com este nome (" + nmArquivo + ").\\nPor favor, selecione um nome de arquivo diferente.";
		}

		if (error) {
			json = new StringBuffer("{ error : {")
			.append("msg: '").append(msgErro).append("'")
			.append("}}");
		} else {

			// Efetua envio do arquivo para diretório destino
			InputStream stream = form.getBanner().getArquivo().getInputStream();
			File diretorio = new File(pathUpload);

			if (diretorio.exists()) {

				OutputStream bos = new FileOutputStream(arquivo);

				byte[] buffer = new byte[256*1024];
				int bytesRead = 0;
				while ((bytesRead = stream.read(buffer, 0, 256*1024)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				buffer = null;
				bos.close();
				stream.close();

			}

			json.append("extension: '").append(extensao).append("',")
			.append("size: ").append(size).append(",")
			.append("filename: '").append(newFileName).append("',")
			.append("width: ").append(width).append(",")
			.append("height: ").append(height).append("")
			.append("}}");

			getParametrizacaoBannerForm().getBanner().setNmArquivoBanner(newFileName);
			HashMap aTemp = getParametrizacaoBannerForm().getArquivosTemporarios();
			aTemp.put(Integer.toString(aTemp.size()), newFileName);

		}

		request.setAttribute("retornoJSON", json.toString());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="successUpload" path="listaBanners.jsp"
	 * @jpf:forward name="successAssociacao" path="listaRelacionamentos.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		try {

			String destino = ConstantesCRM.SVAZIO;

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			String tipoPesquisa = request.getParameter("tipoPesquisa");
			SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

			if (getParametrizacaoBannerForm().getItensPorPagina() == 0) {
				GetParametro parametro = GetParametro.getInstace();
				ParametroVO apoioParam = parametro.getParametroVO(idUsuario, BANNERVOLE_VAR_MANUTENCAO_ITENSPP);
				if (ConstantesCRM.SVAZIO.equals(apoioParam.getDsValorParametro())) {
					getParametrizacaoBannerForm().setItensPorPagina(10);
				} else {
					getParametrizacaoBannerForm().setItensPorPagina(Integer.parseInt(apoioParam.getDsValorParametro()));
				}
			}

			if ("upload".equals(tipoPesquisa)) {

				destino = "successUpload";

				PaginacaoBannersVOLE buscaBanners = bannerFacadeControl.buscarListaBanners(idUsuario,
						form.getFiltroAreas(),
						form.getNrPagina(),
						getParametrizacaoBannerForm().getItensPorPagina());

				getParametrizacaoBannerForm().setTotalItens(buscaBanners.getTotalItens());
				Banner[] listaBanners = new Banner[buscaBanners.getListaBanners().length];

				for (int i = 0; i < listaBanners.length; i++) {
					AcessoBannerVOLE bannerInstance = buscaBanners.getListaBanners()[i];
					Banner banner = new Banner(bannerInstance.getIdBannerVOLE(),
							bannerInstance.getDsBannerVOLE(),
							bannerInstance.getNmBannerVOLE(),
							bannerInstance.getUrlBannerVOLE() == null ? ConstantesCRM.SVAZIO : bannerInstance.getUrlBannerVOLE(),
									bannerInstance.getIdAreaBannerVOLE(),
									bannerInstance.getIdTipoBannerVOLE(),
									bannerInstance.getIPBannerVOLE(),
									formatoSaida.format(bannerInstance.getDtInicial()),
									(bannerInstance.getDtFinal() == null) ? null : formatoSaida.format(bannerInstance.getDtFinal()),
											(bannerInstance.getIPBannerVOLE() == null || ConstantesCRM.SVAZIO.equals(bannerInstance.getIPBannerVOLE())) ? false : true,
													(bannerInstance.getInContador() == 1) ? true : false
					);
					listaBanners[i] = banner;
				}

				getParametrizacaoBannerForm().setListaRelacionamentos(null);
				getParametrizacaoBannerForm().setListaBanners(listaBanners);

				getParametrizacaoBannerForm().setNrPagina(buscaBanners.getPaginaAtual());
				getParametrizacaoBannerForm().setUltimaPagina(buscaBanners.isUltimaPagina());

			} else if ("associacao".equals(tipoPesquisa)) {

				destino = "successAssociacao";

				PaginacaoBannersVOLE buscaRelacionamentos = bannerFacadeControl.buscarListaRelacionamentos(idUsuario,
						form.getFiltroAreas(),
						form.getFiltroUFs(),
						form.getNrPagina(),
						getParametrizacaoBannerForm().getItensPorPagina());

				getParametrizacaoBannerForm().setListaBanners(null);
				getParametrizacaoBannerForm().setListaRelacionamentos(buscaRelacionamentos.getListaRelacionamentos());

			}

			if (getParametrizacaoBannerForm().getListaAreas() == null) {
				HashMap listaAreas = bannerFacadeControl.buscarListaAreasBanner();
				getParametrizacaoBannerForm().setListaAreas(listaAreas);
			}

			if (getParametrizacaoBannerForm().getListaTiposBanner() == null) {
				HashMap listaTiposBanner = bannerFacadeControl.buscarListaTiposBanner();
				getParametrizacaoBannerForm().setListaTiposBanner(listaTiposBanner);
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch(NotLoggedInException e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, "Sessão expirada.");
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			response.setHeader("sessaoExpirada", "true");
			return null;

		} catch(Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="successAssociacao" path="listaBannersAssociacao.jsp"
	 * @jpf:forward name="sucessDesassociacao" path="sucessDesassociacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward associarDesassociarBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		try {

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			String destino = "associar".equals(request.getParameter("operacao")) ? "successAssociacao" : "sucessDesassociacao";
			getParametrizacaoBannerForm().setRelacionamentosSelecionados(form.getRelacionamentosSelecionados());
			getParametrizacaoBannerForm().setFiltroAreas(new String[]{form.getFiltroAreas()[0]});
			int idFiltroArea = Integer.parseInt(form.getFiltroAreas()[0]);
			SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

			if ("successAssociacao".equals(destino)) {

				PaginacaoBannersVOLE buscaBanners = bannerFacadeControl.buscarListaBannersPorArea(idUsuario, idFiltroArea, form.getNrPagina(),
						getParametrizacaoBannerForm().getItensPorPagina());
				Banner[] listaBanners = new Banner[buscaBanners.getListaBanners().length];
				for (int i = 0; i < listaBanners.length; i++) {
					AcessoBannerVOLE bannerInstance = buscaBanners.getListaBanners()[i];
					Banner banner = new Banner(bannerInstance.getIdBannerVOLE(),
							bannerInstance.getDsBannerVOLE(),
							bannerInstance.getNmBannerVOLE(),
							bannerInstance.getUrlBannerVOLE() == null ? ConstantesCRM.SVAZIO : bannerInstance.getUrlBannerVOLE(),
									bannerInstance.getIdAreaBannerVOLE(),
									bannerInstance.getIdTipoBannerVOLE(),
									bannerInstance.getIPBannerVOLE(),
									formatoSaida.format(bannerInstance.getDtInicial()),
									(bannerInstance.getDtFinal() == null) ? null : formatoSaida.format(bannerInstance.getDtFinal()),
											(bannerInstance.getIPBannerVOLE() == null || ConstantesCRM.SVAZIO.equals(bannerInstance.getIPBannerVOLE())) ? false : true,
													(bannerInstance.getInContador() == 1) ? true : false
					);
					listaBanners[i] = banner;
				}

				getParametrizacaoBannerForm().setTotalItens(buscaBanners.getTotalItens());
				getParametrizacaoBannerForm().setListaBanners(listaBanners);

				getParametrizacaoBannerForm().setNrPagina(form.getNrPagina());
				getParametrizacaoBannerForm().setUltimaPagina(buscaBanners.isUltimaPagina());

			} else if ("sucessDesassociacao".equals(destino)) {

				try {
					bannerFacadeControl.atualizarRelacionamentoBannerVOLE(idUsuario,
							"desassociacao",
							form.getRelacionamentosSelecionados(),
							0,
							null);
				} catch (Exception e) {
					request.setAttribute("erro", "Não possível realizar a desassociação de banners.");
					request.setAttribute("detalhesErro", e.getMessage());
				}

			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch(NotLoggedInException e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch(Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	/**
	 * @jpf:action
	 */
	protected ActionForward salvarAssociacaoDesassociacaoBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		try {

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			String operacao = request.getParameter("operacao");

			if ("associacao".equals(operacao)) {

				long idBanner = Long.parseLong(request.getParameter("idBanner"));
				String urlBanner = ConstantesCRM.SVAZIO.equals(request.getParameter("urlBanner")) ? null : request.getParameter("urlBanner");

				bannerFacadeControl.atualizarRelacionamentoBannerVOLE(idUsuario,
						operacao,
						getParametrizacaoBannerForm().getRelacionamentosSelecionados(),
						idBanner,
						urlBanner);

			} else if ("desassociacao".equals(operacao)) {

				bannerFacadeControl.atualizarRelacionamentoBannerVOLE(idUsuario,
						operacao,
						getParametrizacaoBannerForm().getRelacionamentosSelecionados(),
						0,
						null);

			}

			return null;

		} catch(Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getMessage());
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="imagemBanner.jsp"
	 */
	protected ActionForward mostrarImagemBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;
		try {
			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			String nmArquivoBanner = request.getParameter("nmArquivo");
			boolean gerarImagem = request.getParameter("geraImagem") == null ? false : true;

			if (ConstantesCRM.SVAZIO.equals(this.pathUpload)) {
				GetParametro parametro = GetParametro.getInstace();
				ParametroVO apoioParam = parametro.getParametroVO(idUsuario, BANNERVOLE_VAR_PATH_UPLOAD);
				if (ConstantesCRM.SVAZIO.equals(apoioParam.getDsValorParametro())) {
					return null;
				} else {
					pathUpload = apoioParam.getDsValorParametro();
				}
			}

			if (pathUpload.charAt(pathUpload.length()-1) != '/') {
				pathUpload += "/";
			}
			File arquivo = new File(pathUpload + nmArquivoBanner);

			if (!arquivo.exists()) {
				request.setAttribute("erro", "Arquivo do banner não existe.");
			}

			String contentType = new MimetypesFileTypeMap().getContentType(arquivo);
			String extensao = nmArquivoBanner.substring(
					nmArquivoBanner.lastIndexOf(".") + 1,
					nmArquivoBanner.length()).toUpperCase();
			if (!gerarImagem && (nmArquivoBanner != null || !ConstantesCRM.SVAZIO.equals(nmArquivoBanner))) {
				request.setAttribute("extensao", extensao);
				request.setAttribute("nmArquivo", nmArquivoBanner);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			response.setContentType(contentType);

			InputStream iS = new FileInputStream(arquivo);
			long length = arquivo.length();
			byte[] bytes = new byte[(int)length];

			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && ( numRead = iS.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead;
			}

			if (offset < bytes.length) {
				throw new IOException("Não foi possível ler o arquivo");
			}

			iS.close();

			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(bytes);
			bos.flush();
			bos.close();

			return null;

		} catch(Exception e) {
			return null;
		}

	}

	/**
	 * @jpf:action
	 */
	protected ActionForward salvarCadastroAlteracao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		try {
			log.info("ParametrizacaoBannerController::salvarCadastroAlteracao --> Método iniciado.");
			SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			Banner banner = form.getBanner();

			log.debug("ParametrizacaoBannerController::salvarCadastroAlteracao --> banner." + banner.toString(banner));

			AcessoBannerVOLE bannerVOLE = new AcessoBannerVOLE();

			String extensaoBanner = banner.getExtensaoBanner();
			if ("JP".equalsIgnoreCase(banner.getExtensaoBanner().substring(0, 2))) {
				extensaoBanner = "JPG";
			}

			long idTipoBanner = getIdTipoBannerByDescricao(extensaoBanner);
			bannerVOLE.setIdTipoBannerVOLE(idTipoBanner);
			bannerVOLE.setDsBannerVOLE(banner.getDsBanner());
			bannerVOLE.setNmBannerVOLE(banner.getNmArquivoBanner());
			bannerVOLE.setIdAreaBannerVOLE(banner.getIdAreaBanner());
			bannerVOLE.setInContador(banner.isContadorAcessos() ? 1 : 0);
			bannerVOLE.setUrlBannerVOLE(banner.getUrlBanner());
			bannerVOLE.setIPBannerVOLE(!banner.isAutenticacao() ? null : banner.getNrIPBanner());

			bannerVOLE.setDtInicial(new java.sql.Date(formatoEntrada.parse(banner.getDtVigenciaDe()).getTime()));
			if (!ConstantesCRM.SVAZIO.equals(banner.getDtVigenciaAte())) {
				bannerVOLE.setDtFinal(new java.sql.Date(formatoEntrada.parse(banner.getDtVigenciaAte()).getTime()));
			} else {
				bannerVOLE.setDtFinal(null);
			}

			if (form.getBanner().getIdBanner() == 0) {
				bannerVOLE.setNrContador(0);
				bannerFacadeControl.incluirBanner(idUsuario, bannerVOLE);

			} else {
				bannerVOLE.setIdBannerVOLE(banner.getIdBanner());
				bannerFacadeControl.alterarBanner(idUsuario, bannerVOLE);
			}

			// Caso tenha ocorrido alteração de arquivo de banner na mesma tela de cadastro/alteração, os antigos serão excluídos.
			HashMap aTemp = getParametrizacaoBannerForm().getArquivosTemporarios();
			Set entries = aTemp.entrySet();
			Iterator it = entries.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (!banner.getNmArquivoBanner().equals(entry.getValue())) {
					File arquivoAnterior = new File(pathUpload + (String)entry.getValue());
					arquivoAnterior.delete();
				}
			}

			getParametrizacaoBannerForm().setArquivosTemporarios(null);
			log.info("ParametrizacaoBannerController::salvarCadastroAlteracao --> Método finalizado.");

			return null;

		} catch(Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getMessage());
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="criarAlterarBanner.jsp"
	 */
	protected ActionForward buscarDadosBanner(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrizacaoBannerForm form = (ParametrizacaoBannerForm)formParam;

		try {

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			long idBanner = Long.parseLong(request.getParameter("idBanner"));

			AcessoBannerVOLE bannerInstance = bannerFacadeControl.buscarDadosBanner(idUsuario, idBanner);
			SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

			Banner banner = new Banner(bannerInstance.getIdBannerVOLE(),
					bannerInstance.getDsBannerVOLE(),
					bannerInstance.getNmBannerVOLE(),
					bannerInstance.getUrlBannerVOLE() == null ? ConstantesCRM.SVAZIO : bannerInstance.getUrlBannerVOLE(),
							bannerInstance.getIdAreaBannerVOLE(),
							bannerInstance.getIdTipoBannerVOLE(),
							(bannerInstance.getIPBannerVOLE() == null) ? ConstantesCRM.SVAZIO : bannerInstance.getIPBannerVOLE(),
									formatoSaida.format(bannerInstance.getDtInicial()),
									(bannerInstance.getDtFinal() == null) ? ConstantesCRM.SVAZIO : formatoSaida.format(bannerInstance.getDtFinal()),
											(bannerInstance.getIPBannerVOLE() == null || ConstantesCRM.SVAZIO.equals(bannerInstance.getIPBannerVOLE())) ? false : true,
													(bannerInstance.getInContador() == 1) ? true : false
			);

			String extensao = bannerInstance.getNmBannerVOLE().substring(
					bannerInstance.getNmBannerVOLE().lastIndexOf(".") + 1,
					bannerInstance.getNmBannerVOLE().length()).toUpperCase();
			banner.setExtensaoBanner(extensao);

			getParametrizacaoBannerForm().setBanner(banner);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch(Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	private long getIdTipoBannerByDescricao(String extensaoBanner) {
		try {
			Set entries = getParametrizacaoBannerForm().getListaTiposBanner().entrySet();
			Iterator it = entries.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (((String)entry.getValue()).toUpperCase().equals(extensaoBanner)) {
					return Long.parseLong((String)entry.getKey());
				}
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

}
