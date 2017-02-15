package br.com.vivo.catalogoPRS.pageflows.param.pacotebonus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CanalAtendimentoDelegate;
import br.com.vivo.catalogoPRS.delegate.FuncionalidadeDelegate;
import br.com.vivo.catalogoPRS.delegate.SrvInteratividadeParamBaseDelegate;
import br.com.vivo.catalogoPRS.delegate.PlataformaDelegate;
import br.com.vivo.catalogoPRS.delegate.ServicoInteratividadeDelegate;
import br.com.vivo.catalogoPRS.delegate.TecnologiaDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoClienteDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoLinhaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.PacoteBonusForm;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.VivoNetDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.VivoNetVO;

import com.framework.coordinator.Transaction;
import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.datalayer.Funcionalidade;
import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.datalayer.TipoLinha;
import com.indracompany.catalogo.to.FuncionalidadeTO;
import com.indracompany.catalogo.to.ServicoIntCanalTO;
import com.indracompany.catalogo.to.ServicoIntClienteTO;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;
import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;
import com.indracompany.catalogo.to.SrvInteratividadeParamTO;
import com.indracompany.catalogo.to.TipoLinhaTO;


public class PacoteBonusAction extends BaseMappingDispatchAction {
	
	private Integer idPlataforma;
	private String url;
	private String nmServico;
	private String ativo;
	private Integer idCanalAtendimento;
	private String cdFuncionalidade;
	private List<ServicoInteratividadeTO> servicoInteratividadeList;
	private Integer idServicoInteratividade;
	private static final String MENSAGEM_RETORNO = "Servi&ccedil;o Interatividade j&aacute; cadastrado";
	private static final String MSG_ERRO_IP_SL = "Erro ao incluir o IP no Sistema Legado.";
	private static final String MSG_ERRO_IP_OBG = "O Campo IP é Obrigatório.";
	private static final String MSG_ERRO_IP_INVALIDO = "Máscara IP Invalido.";
	private static final String MSG_SUCESSO_IP = "Cadastro realizado com sucesso.";
	private static final String MSG_ERRO_IP = "Erro ao listar os IP's";
	
	
	private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
	private static VivoNetDAO vivoNetDAO = (VivoNetDAO) VivoNetDAO.getInstance();	
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PacoteBonusForm pacoteBonusForm = (PacoteBonusForm)form;
		
		carregarCombos(pacoteBonusForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		PacoteBonusForm pacoteBonusForm = (PacoteBonusForm)form;
		
		ServicoInteratividadeDelegate servicoInteratividadeDelegate = new ServicoInteratividadeDelegate();
		
		servicoInteratividadeList = servicoInteratividadeDelegate.searchServicoInteratividade(doServicoInteratividadeTO(pacoteBonusForm, request, response));
		
		request.setAttribute("servicoInteratividadeList", servicoInteratividadeList);
		
		carregarCombos(pacoteBonusForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		PacoteBonusForm pacoteBonusForm = (PacoteBonusForm)form;
		
		
		if (pacoteBonusForm.getIdServicoInteratividade() != null) {
				
			ServicoInteratividadeDelegate servicoInteratividadeDelegate = new ServicoInteratividadeDelegate();
			ServicoInteratividadeTO to = doServicoInteratividadeTO(pacoteBonusForm, request, response);
			
			try {
				ServicoInteratividadeTO to2 = servicoInteratividadeDelegate.findById(to);
				pacoteBonusForm = doForm(to2, pacoteBonusForm, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		SrvInteratividadeParamBaseDelegate parametroDelegate = new SrvInteratividadeParamBaseDelegate();
		request.setAttribute("parametroList", parametroDelegate.findAll());
		
		
		carregarCombos(pacoteBonusForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PacoteBonusForm pacoteBonusForm = (PacoteBonusForm)form;
		try {
					
			UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
			ServicoInteratividadeDelegate servicoInteratividadeDelegate = new ServicoInteratividadeDelegate();
			//  Método responsável em criar um TO a partir de um Form.
			ServicoInteratividadeTO servicoInteratividadeTO = doServicoInteratividadeTO(pacoteBonusForm, request, response);
			String mensagemRetorno = null;
			
			if (servicoInteratividadeTO.getIdServicoInteratividade() == null) {
				// NOVO
								
				servicoInteratividadeTO.setDtCriacao(new Date());
				servicoInteratividadeTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
				Integer si = servicoInteratividadeDelegate.validarServicointeratividade(servicoInteratividadeTO);
				
				if (si == null) {
					servicoInteratividadeDelegate.createUpdateServicoInteratividade(servicoInteratividadeTO);
					
				} else {
					
					request.setAttribute("labelError", MENSAGEM_RETORNO);
					carregarCombos(pacoteBonusForm, request, response);
					return mapping.findForward("success");
					
				}

				mensagemRetorno = "Cadastro realizado com sucesso";
			} else {
				// EDITAR
				servicoInteratividadeTO.setDtUltimaAlteracao(new Date());
				servicoInteratividadeTO.setNmUsuarioAlteracao(usuarioLogado.getUser().getUsername());				
				servicoInteratividadeTO.setNmServico(pacoteBonusForm.getNmServico());
				servicoInteratividadeTO.setFuncionalidadeTO(new FuncionalidadeTO(pacoteBonusForm.getCdFuncionalidade()));
				
				servicoInteratividadeTO.setUrl(pacoteBonusForm.getUrl());
				
				
				if (pacoteBonusForm.getSgTipoLinha() != null && !pacoteBonusForm.getSgTipoLinha().equals("0")) {
					servicoInteratividadeTO.setTipoLinhaTO(new TipoLinhaTO(new Integer(pacoteBonusForm.getSgTipoLinha().split("\\|")[0]), pacoteBonusForm.getSgTipoLinha().split("\\|")[1]));	
				}

				servicoInteratividadeTO.setAtivo(pacoteBonusForm.getAtivo());
				
				servicoInteratividadeDelegate.createUpdateServicoInteratividade(servicoInteratividadeTO);
		
				mensagemRetorno = "Altera&ccedil;&atilde;o realizada com sucesso";
			}
	
			
			request.setAttribute("labelSucess", mensagemRetorno);
			carregarCombos(pacoteBonusForm, request, response);
			
			return mapping.findForward("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("labelError", MENSAGEM_RETORNO);
			carregarCombos(pacoteBonusForm, request, response);
			return mapping.findForward("success");
		}
		

	}
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "/pages/pacotebonus/cadastroIPVivoNetPopup.jsp") })
	public ActionForward abrirCadastroIPpopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {
		PacoteBonusForm pacoteBonusForm = (PacoteBonusForm)form;
		try {
			List<VivoNetVO> listIP = vivoNetDAO.searchAll();
			//request.setAttribute("listIP", listIP);
			pacoteBonusForm.setListIP(listIP);
		} catch (Exception e) {
			request.setAttribute("labelError",MSG_ERRO_IP);
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}	
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "/pages/pacotebonus/cadastroIPVivoNetPopup.jsp") })
	public ActionForward saveIP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {
		Transaction tx = new Transaction();
		PacoteBonusForm pacoteBonusForm = (PacoteBonusForm)form;
		try {
			if (pacoteBonusForm.getNumeroIP() != null && !pacoteBonusForm.getNumeroIP().equals("")) {
				VivoNetVO vivoNetVO = new VivoNetVO();
				
				if(PATTERN.matcher(pacoteBonusForm.getNumeroIP()).matches()){
					vivoNetVO.setNumeroIP(pacoteBonusForm.getNumeroIP());
					
					tx.beginTrans();
				    
					VivoNetVO to = vivoNetDAO.insert(vivoNetVO, tx);
					
					tx.commitTrans();
					
					if(to.getCderro() == 0){
						request.setAttribute("labelSucess",MSG_SUCESSO_IP);	
					}else if(to.getCderro() == 1){
						request.setAttribute("labelError",to.getDserro());	
					}else if(to.getCderro() == 2){
						request.setAttribute("labelError",to.getDserro());	
					}else{
						request.setAttribute("labelError",to.getDserro());
					}
				}else{
					request.setAttribute("labelError",MSG_ERRO_IP_INVALIDO);
					abrirCadastroIPpopup(mapping, form, request, response);
					return mapping.findForward("success");
				}				
			}else{
				request.setAttribute("labelError", MSG_ERRO_IP_OBG);
				abrirCadastroIPpopup(mapping, form, request, response);
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			tx.rollBackTrans();
			request.setAttribute("labelError", MSG_ERRO_IP_SL);
			abrirCadastroIPpopup(mapping, form, request, response);
			return mapping.findForward("success");
		}
		abrirCadastroIPpopup(mapping, form, request, response);
		return mapping.findForward("success");
	}	
	
	public ServicoInteratividade createFormToServicoInteratividade (PacoteBonusForm pacoteBonusForm, HttpServletRequest request, HttpServletResponse response) {
		
		ServicoInteratividade si = new ServicoInteratividade();
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		
		si.setAtivo(pacoteBonusForm.getAtivo());
		si.setDtCriacao(new Date());
		si.setDtUltimaAlteracao(new Date());
		si.setNmServico(pacoteBonusForm.getNmServico());
		si.setNmUsuarioAlteracao(usuarioLogado.getUser().getUsername());
		si.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
		si.setUrl(pacoteBonusForm.getUrl());
		si.setFuncionalidade(new Funcionalidade(pacoteBonusForm.getCdFuncionalidade()));
				
		
		if (pacoteBonusForm.getSgTipoLinha() != null && !pacoteBonusForm.getSgTipoLinha().equals("0")) {
			si.setTipoLinha(new TipoLinha(new Integer(pacoteBonusForm.getSgTipoLinha().split("\\|")[0]), pacoteBonusForm.getSgTipoLinha().split("\\|")[1]));
		}
		
		return si;
	}

	public ServicoInteratividadeTO doServicoInteratividadeTO(PacoteBonusForm pacoteBonusForm, HttpServletRequest request, HttpServletResponse response) {
		
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		ServicoInteratividadeTO servicoInteratividadeTO = new ServicoInteratividadeTO();
		servicoInteratividadeTO.setUrl(pacoteBonusForm.getUrl());
		servicoInteratividadeTO.setNmServico(pacoteBonusForm.getNmServico());
		servicoInteratividadeTO.setFuncionalidadeTO(new FuncionalidadeTO(pacoteBonusForm.getCdFuncionalidade()));
		servicoInteratividadeTO.setDtCriacao(new Date());
		servicoInteratividadeTO.setDtUltimaAlteracao(new Date());
		servicoInteratividadeTO.setNmUsuarioAlteracao(usuarioLogado.getUser().getUsername());
		servicoInteratividadeTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
		
		//Canal Atendimento		
		List<ServicoIntCanalTO> servIntCanalListTO = new ArrayList<ServicoIntCanalTO>();
		
		if (pacoteBonusForm.getIdCanalAtendimento() != null) { 
			for (int i = 0; i < pacoteBonusForm.getIdCanalAtendimento().length; i++) {
				CanalAtendimentoDelegate canalAttDelegate = new CanalAtendimentoDelegate();
				ServicoIntCanalTO servIntCanalTO = new ServicoIntCanalTO();
				CanalAtendimento canalAtt = new CanalAtendimento();
				
				try {
					canalAtt = canalAttDelegate.findById(pacoteBonusForm.getIdCanalAtendimento()[i]);
				} catch (BusinessException e) {					
					e.printStackTrace();
				}
				servIntCanalTO.setCanalAtendimento(canalAtt);					
				servIntCanalTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(pacoteBonusForm.getIdServicoInteratividade()));	
				servIntCanalTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
				servIntCanalTO.setDtCriacao(new Date());
				servIntCanalListTO.add(servIntCanalTO);							
			}
		
			servicoInteratividadeTO.setServicoIntCanalTOList(servIntCanalListTO);
		
		}						
		// Plataforma			
		List<ServicoIntPlataformaTO> servIntPlatformListTO = new ArrayList<ServicoIntPlataformaTO>();	
		
		if (pacoteBonusForm.getIdPlataforma() != null) { 
			for (int i = 0; i < pacoteBonusForm.getIdPlataforma().length; i++ ) { 
				PlataformaDelegate plataformDelegate = new PlataformaDelegate();
				ServicoIntPlataformaTO servIntPlatformTO = new ServicoIntPlataformaTO();
				Plataforma platform = new Plataforma();																
				
				try {
					platform = plataformDelegate.findById(pacoteBonusForm.getIdPlataforma()[i]);
				} catch (Exception e) {					
					e.printStackTrace();
				}
				servIntPlatformTO.setPlataforma(platform);
				servIntPlatformTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(pacoteBonusForm.getIdServicoInteratividade()));
				servIntPlatformTO.setDtCriacao(new Date());
				servIntPlatformTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
				servIntPlatformListTO.add(servIntPlatformTO);
			}
			
			servicoInteratividadeTO.setServicoIntPlataformaTOList(servIntPlatformListTO);
		}
		// Tipo Cliente			
		List<ServicoIntClienteTO> servIntClienteListTO = new ArrayList<ServicoIntClienteTO>();
		
		if (pacoteBonusForm.getIdTipoCliente() != null) { 
			
			for (int i = 0; i < pacoteBonusForm.getIdTipoCliente().length; i++ ) {
				TipoClienteDelegate tpClienDelegate = new TipoClienteDelegate();
				ServicoIntClienteTO servIntClienteTO = new ServicoIntClienteTO();
				TipoCliente tpCli = new TipoCliente();
				
				try {
					tpCli = tpClienDelegate.findById(pacoteBonusForm.getIdTipoCliente()[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				servIntClienteTO.setTipoCliente(tpCli);
				servIntClienteTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(pacoteBonusForm.getIdServicoInteratividade()));
				servIntClienteTO.setDtCriacao(new Date());
				servIntClienteTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
				servIntClienteListTO.add(servIntClienteTO);
			}
			
			servicoInteratividadeTO.setServicoIntClienteTOList(servIntClienteListTO);
		}
		
		// Tecnologia		
		List<ServicoIntTecnologiaTO> servIntTecnoListTO = new ArrayList<ServicoIntTecnologiaTO>();
		
		if (pacoteBonusForm.getIdTecnologia() != null) {
			
			for (int i = 0; i < pacoteBonusForm.getIdTecnologia().length; i++) {
				TecnologiaDelegate tecnolDelegate = new TecnologiaDelegate();
				ServicoIntTecnologiaTO servIntTecno = new ServicoIntTecnologiaTO();
				Tecnologia tecno = new Tecnologia();
				
				try {
					tecno = tecnolDelegate.findById(pacoteBonusForm.getIdTecnologia()[i]);
				} catch (BusinessException e) {					
					e.printStackTrace();
				}
				servIntTecno.setTecnologia(tecno); 
				servIntTecno.setServicoInteratividadeTO(new ServicoInteratividadeTO(pacoteBonusForm.getIdServicoInteratividade()));	
				servIntTecno.setDtCriacao(new Date());
				servIntTecno.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
				servIntTecnoListTO.add(servIntTecno);
			}
			
			servicoInteratividadeTO.setServicoIntTecnologiaTOList(servIntTecnoListTO);				
		}
		
				
		servicoInteratividadeTO.setAtivo(pacoteBonusForm.getAtivo());
		servicoInteratividadeTO.setIdServicoInteratividade(pacoteBonusForm.getIdServicoInteratividade());

		if (pacoteBonusForm.getSgTipoLinha() != null && !pacoteBonusForm.getSgTipoLinha().isEmpty() && !pacoteBonusForm.getSgTipoLinha().equals("0")) {
			
			
			servicoInteratividadeTO.setTipoLinhaTO(new TipoLinhaTO(new Integer(pacoteBonusForm.getSgTipoLinha().split("\\|")[0]), pacoteBonusForm.getSgTipoLinha().split("\\|")[1]));
		}


		List<SrvInteratividadeParamTO> srvInteratividadeParametroTOList = new ArrayList<SrvInteratividadeParamTO>();
		
		SrvInteratividadeParamTO srvInteratividadeParametroTO = null;
		
		if (pacoteBonusForm.getIdParametroBaseChecked() != null) {
			for (int i = 0; i < pacoteBonusForm.getIdParametroBase().length; i++ ) {
				for (int j = 0; j < pacoteBonusForm.getIdParametroBaseChecked().length; j++ ) {
					if (pacoteBonusForm.getIdParametroBaseChecked()[j].equals(pacoteBonusForm.getIdParametroBase()[i])) {
						srvInteratividadeParametroTO = new SrvInteratividadeParamTO();
						srvInteratividadeParametroTO.setParametroTO(new SrvInteratividadeParamBaseTO(pacoteBonusForm.getIdParametroBaseChecked()[j]));
						srvInteratividadeParametroTO.setVlParametro(pacoteBonusForm.getVlParametro()[i]);
						srvInteratividadeParametroTOList.add(srvInteratividadeParametroTO);
					}
				}
			}
		}
		
		servicoInteratividadeTO.setSrvInteratividadeParametroTOList(srvInteratividadeParametroTOList);
		
		return servicoInteratividadeTO;
	}
	
	public PacoteBonusForm doForm(ServicoInteratividadeTO servicoInteratividadeTO, PacoteBonusForm pacoteBonusForm, HttpServletRequest request, HttpServletResponse response) {
		pacoteBonusForm.setUrl(servicoInteratividadeTO.getUrl());
		pacoteBonusForm.setNmServico(servicoInteratividadeTO.getNmServico());
		pacoteBonusForm.setCdFuncionalidade(servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade());
		
		// Canal Atendimento
		if (servicoInteratividadeTO.getServicoIntCanalTOList() != null && !servicoInteratividadeTO.getServicoIntCanalTOList().isEmpty()) {
			int sizeCanal = servicoInteratividadeTO.getServicoIntCanalTOList().size();
			Integer[] canal = new Integer[sizeCanal];
			for (int i = 0; i < servicoInteratividadeTO.getServicoIntCanalTOList().size(); i++) {
				canal[i] = servicoInteratividadeTO.getServicoIntCanalTOList().get(i).getCanalAtendimento().getIdCanalAtendimento();				
			}
			
			pacoteBonusForm.setIdCanalAtendimento(canal);	
			
		}		
		// Plataforma
		if (servicoInteratividadeTO.getServicoIntPlataformaTOList() != null && !servicoInteratividadeTO.getServicoIntPlataformaTOList().isEmpty()) {
			int sizePlat = servicoInteratividadeTO.getServicoIntPlataformaTOList().size();
			Integer[] platform = new Integer[sizePlat];
			for (int j = 0; j < servicoInteratividadeTO.getServicoIntPlataformaTOList().size(); j ++) {
				platform[j] = servicoInteratividadeTO.getServicoIntPlataformaTOList().get(j).getPlataforma().getIdPlataforma();				
			}
			
			pacoteBonusForm.setIdPlataforma(platform);
			
		}
		// Tipo Cliente
		if (servicoInteratividadeTO.getServicoIntClienteTOList() != null && !servicoInteratividadeTO.getServicoIntClienteTOList().isEmpty()) {
			int sizeCli = servicoInteratividadeTO.getServicoIntClienteTOList().size();
			Integer[] cliente = new Integer[sizeCli];
			for (int k = 0; k < servicoInteratividadeTO.getServicoIntClienteTOList().size(); k++) {			
				cliente[k] = servicoInteratividadeTO.getServicoIntClienteTOList().get(k).getTipoCliente().getIdTipoCliente();
			}
			
			pacoteBonusForm.setIdTipoCliente(cliente);
			
		}				
		// Tecnologia
		if (servicoInteratividadeTO.getServicoIntTecnologiaTOList() != null && !servicoInteratividadeTO.getServicoIntTecnologiaTOList().isEmpty()) {
			int sizeTec = servicoInteratividadeTO.getServicoIntTecnologiaTOList().size();
			Integer[] tecnologia = new Integer[sizeTec];
			for (int h = 0; h < servicoInteratividadeTO.getServicoIntTecnologiaTOList().size(); h++) {
				tecnologia[h] = servicoInteratividadeTO.getServicoIntTecnologiaTOList().get(h).getTecnologia().getIdTecnologia();				
			}
			
			pacoteBonusForm.setIdTecnologia(tecnologia);
			
		}

		pacoteBonusForm.setIdServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade());
		pacoteBonusForm.setAtivo(servicoInteratividadeTO.getAtivo());

		
		if (servicoInteratividadeTO.getTipoLinhaTO() != null) {
			pacoteBonusForm.setSgTipoLinha(servicoInteratividadeTO.getTipoLinhaTO().getIdTipoLinha() + "|" + servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha());
		}
		
		request.setAttribute("srvInteratividadeParametroTOList", servicoInteratividadeTO.getSrvInteratividadeParametroTOList());
		
		return pacoteBonusForm;
	}
	
	private void carregarCombos(PacoteBonusForm pacoteBonusForm, HttpServletRequest request, HttpServletResponse response) {
		PlataformaDelegate plataformaDelegate = new PlataformaDelegate();
		request.setAttribute("plataformaList", plataformaDelegate.findAll());
				
		CanalAtendimentoDelegate canalAtendimentoDelegate = new CanalAtendimentoDelegate();
		request.setAttribute("canalAtendimentoList", canalAtendimentoDelegate.findAll());
				
		FuncionalidadeDelegate funcionalidadeDelegate = new FuncionalidadeDelegate();
		request.setAttribute("funcionalidadeList", funcionalidadeDelegate.findAll());
								
		TipoLinhaDelegate tipoLinhaDelegate = new TipoLinhaDelegate();
		request.setAttribute("tipoLinhaList", tipoLinhaDelegate.findAll());
						
		TipoClienteDelegate tipoClienteDelegate = new TipoClienteDelegate();
		request.setAttribute("tipoClienteList", tipoClienteDelegate.findAll());
		
		TecnologiaDelegate tecnologiaDelegate = new TecnologiaDelegate();
		request.setAttribute("tecnologiaList", tecnologiaDelegate.findAll());
	}

	
	public Integer getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCdFuncionalidade() {
		return cdFuncionalidade;
	}
	public void setCdFuncionalidade(String cdFuncionalidade) {
		this.cdFuncionalidade = cdFuncionalidade;
	}
	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public String getNmServico() {
		return nmServico;
	}
	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}
	public List<ServicoInteratividadeTO> getServicoInteratividadeList() {
		return servicoInteratividadeList;
	}

	public void setServicoInteratividadeList(
			List<ServicoInteratividadeTO> servicoInteratividadeList) {
		this.servicoInteratividadeList = servicoInteratividadeList;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getAtivo() {
		return ativo;
	}
	public Integer getIdServicoInteratividade() {
		return idServicoInteratividade;
	}

	public void setIdServicoInteratividade(Integer idServicoInteratividade) {
		this.idServicoInteratividade = idServicoInteratividade;
	}

}

	