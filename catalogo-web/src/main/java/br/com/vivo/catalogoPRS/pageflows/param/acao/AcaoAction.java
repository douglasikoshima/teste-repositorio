package br.com.vivo.catalogoPRS.pageflows.param.acao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ptinovacao.sca.LoginProcess;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.AcaoDelegate;
import br.com.vivo.catalogoPRS.delegate.FornecedorSCADelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.AcaoForm;
import br.com.vivo.catalogoPRS.util.ProfileSys;
import br.com.vivo.catalogoPRS.util.SCAUtils;

import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;

public class AcaoAction extends BaseMappingDispatchAction {
	
	
	
	private static Logger logger = Logger.getLogger(AcaoAction.class);
	
	private List<AcaoTO> acaoList;
	private static final String SUCCESS = "success";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception  {
		
		AcaoForm formulario = (AcaoForm)form;
		
		if (formulario.getIsPesquisa() != null && formulario.getIsPesquisa().equals("S")) {
			AcaoDelegate acaoDelegate = new AcaoDelegate();
				
			acaoList = acaoDelegate.searchAcao(doAcaoTOSearch(formulario));
			formulario.setAcaoList(acaoList);
			request.setAttribute("acaoList", acaoList);
		}
		carregarCombos(formulario,request);
		formulario.setIndAnaliseFraude("S");
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute("cadastrar", Boolean.TRUE); 
		this.search(mapping,form,request,response);
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AcaoForm formulario = (AcaoForm)form;
		
		request.setAttribute("cadastrar", Boolean.TRUE); 
		this.search(mapping,form,request,response);
		
		if (formulario.getIdAcao() != null && formulario.getIdAcao() > 0) {
			AcaoDelegate acaoDelegate = new AcaoDelegate();
			doForm(acaoDelegate.findById(doAcaoTO(formulario)), formulario);
		}
		
		return mapping.findForward(SUCCESS);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AcaoForm formulario = (AcaoForm)form;
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		try {
			AcaoDelegate acaoDelegate = new AcaoDelegate();
			AcaoTO acaoTO = doAcaoTO(formulario);
			String mensagemRetorno = null;
			
			if ((acaoTO.getIdAcao() == null) || (acaoTO.getIdAcao()== 0)) {
				acaoTO.setDtCriacao(new Date());
				acaoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				mensagemRetorno = "A��o de venda cadastrada com sucesso";
			} else {
				//acaoTO.setDtCriacao(new Date());
				acaoTO.setDtAlteracao(new Date());
				acaoTO.setNmUsuarioAlteracao(userCatalogo.getUser().getUsername());
				acaoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				mensagemRetorno = "A��o de venda alterada com sucesso";
			}
			
			acaoDelegate.createUpdateAcao(acaoTO);
			request.setAttribute("labelSucess", mensagemRetorno);
			this.search(mapping,form,request,response);
		} catch (BusinessException e) {
			edit(mapping,form,request,response);
			request.setAttribute("labelError", e.getMessage());
		}
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AcaoForm formulario = (AcaoForm)form;
		
		try {
			AcaoDelegate acaoDelegate = new AcaoDelegate();
			acaoDelegate.removeAcao(doAcaoTO(formulario));
			request.setAttribute("labelSucess", "Ac�o de Venda exclu�da com sucesso.");
		} catch (BusinessException e) {
			request.setAttribute("labelErrorSearch", e.getMessage());
		}
		
		this.search(mapping,form,request,response);
		
		return mapping.findForward(SUCCESS);
	}
	
	private void carregarCombos(AcaoForm acaoForm,HttpServletRequest request) {
		
		FornecedorSCADelegate fornecedorSCADelegate = new FornecedorSCADelegate();
		request.setAttribute("fornecedorList", fornecedorSCADelegate.findAll());
		
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess;
		
		Map<Long, ProfileSys> map = new TreeMap<Long, ProfileSys>();
		
		try {
			loginProcess = SCAUtils.verify(usuarioLogado);
			map = SCAUtils.obterPerfisCallCenter(loginProcess, usuarioLogado);
			
		} catch (LoginException e) {
			logger.info(e);
		}
		
		
		request.setAttribute("perfilList", map.values());
		
	}
	
	/**
	 * @param acaoForm
	 * @return
	 * 
	 * Método responsável em criar um TO a partir de um Form para pesquisa.
	 */
	public AcaoTO doAcaoTOSearch(AcaoForm acaoForm) {
		AcaoTO acaoTO = new AcaoTO();
		
		acaoTO.setDsAcao(acaoForm.getDsAcao());
		acaoTO.setIdAcao(acaoForm.getIdAcaoSearch());
		acaoTO.setNmAcao(acaoForm.getNmAcaoSearch());
		acaoTO.setSgAcao(acaoForm.getSgAcaoSearch());
	
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if (acaoForm.getDtInicialSearch() != null && !acaoForm.getDtInicialSearch().equals("")) {
				acaoTO.setDtInicial(simpleDateFormat.parse(acaoForm.getDtInicialSearch()));
			}
			if (acaoForm.getDtFinalSearch() != null && !acaoForm.getDtFinalSearch().equals("")) {
				acaoTO.setDtFinal(simpleDateFormat.parse(acaoForm.getDtFinalSearch()));
			}
		} catch (ParseException e) {
			logger.info("Erro ao parsear as datas.");
		}
		
		acaoTO.setIsVigente(acaoForm.getInDisponivelSearch());
		
		if(acaoForm.getIdCanalAtendimentoSearch() != null){
			acaoTO.setCanalAtendimentoTO(new CanalAtendimentoTO(acaoForm.getIdCanalAtendimentoSearch()));
		}
		
		return acaoTO;
	}
	
	/**
	 * @param acaoForm
	 * @return
	 * 
	 * Método responsável em criar um TO a partir de um Form.
	 */
	public AcaoTO doAcaoTO(AcaoForm acaoForm) {
		AcaoTO acaoTO = new AcaoTO();
		acaoTO.setDsAcao(acaoForm.getDsAcao());
		
		if (acaoForm.getIdAcao() != null && acaoForm.getIdAcao() > 0) {		
			acaoTO.setIdAcao(acaoForm.getIdAcao());			
		} else {
			acaoTO.setIdAcao(null);			
		}
						
		acaoTO.setInDisponivel(acaoForm.getInDisponivel());
		acaoTO.setNmAcao(acaoForm.getNmAcao());
		acaoTO.setSgAcao(acaoForm.getSgAcao());
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			if (acaoForm.getDtInicial() != null && !acaoForm.getDtInicial().equals("")) {
				acaoTO.setDtInicial(simpleDateFormat.parse(acaoForm.getDtInicial()));
			}
			if (acaoForm.getDtFinal() != null && !acaoForm.getDtFinal().equals("")) {
				acaoTO.setDtFinal(simpleDateFormat.parse(acaoForm.getDtFinal()));
			}
		} catch (ParseException e) {
		}
		
		acaoTO.setIndAnaliseFraude(acaoForm.getIndAnaliseFraude());
		
		if (acaoForm.getIdCanalAtendimento() != null) {
			acaoTO.setCanalAtendimentoTO(new CanalAtendimentoTO(acaoForm.getIdCanalAtendimento()));
		}
		
		acaoTO.setPerfilList(acaoForm.getIdPerfis());
		acaoTO.setFornecedorList(acaoForm.getIdFornecedores());
		
		return acaoTO;
	}
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 * Método responsável em criar um Form a partir de um TO.
	 */
	public AcaoForm doForm(AcaoTO acaoTO, AcaoForm acaoForm) {
		
		if (acaoTO != null && acaoForm != null) {
			acaoForm.setDsAcao(acaoTO.getDsAcao());
			acaoForm.setIdAcao(acaoTO.getIdAcao());
			acaoForm.setInDisponivel(acaoTO.getInDisponivel());
			acaoForm.setNmAcao(acaoTO.getNmAcao());
			acaoForm.setSgAcao(acaoTO.getSgAcao());
			acaoForm.setIndAnaliseFraude(acaoTO.getIndAnaliseFraude());
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			if (acaoTO.getDtInicial() != null) {
				acaoForm.setDtInicial(simpleDateFormat.format(acaoTO.getDtInicial()));
			}
			if (acaoTO.getDtFinal() != null) {
				acaoForm.setDtFinal(simpleDateFormat.format(acaoTO.getDtFinal()));
			}
			
			acaoForm.setIdPerfis(acaoTO.getPerfilList());
			acaoForm.setIdFornecedores(acaoTO.getFornecedorList());
		}
		return acaoForm;
	}
	
	/**
	 * @param acaoForm
	 * @return
	 * 
	 * Método responsável em resetar o form.
	 */
	public AcaoForm resetForm(AcaoForm acaoForm) {
		if (acaoForm != null) {
			acaoForm.setDsAcao(null);
			acaoForm.setIdAcao(null);
			acaoForm.setInDisponivel(null);
			acaoForm.setNmAcao(null);
			acaoForm.setSgAcao(null);
			acaoForm.setIndAnaliseFraude(null);
			acaoForm.setDtInicial(null);
			acaoForm.setDtFinal(null);
			acaoForm.setIdFornecedores(null);
			acaoForm.setIdPerfis(null);
			acaoForm.setIdCanalAtendimento(null);
		}
		return acaoForm;
	}
	
	public List<AcaoTO> getAcaoList() {
		return acaoList;
	}

	public void setAcaoList(List<AcaoTO> acaoList) {
		this.acaoList = acaoList;
	}
}

	