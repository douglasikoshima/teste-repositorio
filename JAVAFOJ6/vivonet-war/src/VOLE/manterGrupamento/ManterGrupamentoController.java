package VOLE.manterGrupamento;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLE.banner.ManterGrupamentoFacade;
import br.com.vivo.fo.dbclasses.GrupamentoAcesso;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ManterGrupamentoController extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	@EJB
	ManterGrupamentoFacade manterGrupamentoFacade;
	
	private ManterGrupamentoForm manterGrupamentoForm;
	private ManterGrupamentoForm manterGrupamentoFormIncluir;
	
	private transient Logger log = new Logger("VOLE");
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("listarGrupamentos".equals(mapping.getParameter())) {
			return listarGrupamentos(mapping, form, request, response);
		} else if ("incluirGrupamento".equals(mapping.getParameter())) {
			return incluirGrupamento(mapping, form, request, response);
		} else if ("alterarGrupamento".equals(mapping.getParameter())) {
			return alterarGrupamento(mapping, form, request, response);
		} else if ("salvarGrupamento".equals(mapping.getParameter())) {
			return salvarGrupamento(mapping, form, request, response);
		} else if ("removerGrupamento".equals(mapping.getParameter())) {
			return removerGrupamento(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		log.debug("ManterGrupamentoController.begin inicio");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);	
		manterGrupamentoForm = new ManterGrupamentoForm();
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	protected ActionForward removerGrupamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		log.debug("ManterGrupamentoController.removerGrupamento inicio");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);	
		ManterGrupamentoForm form = (ManterGrupamentoForm) formParam;
		GrupamentoAcesso grupamentoAcesso = new GrupamentoAcesso();
		grupamentoAcesso.setIdGrupamento(form.getIdGrupamento());
		try {
			manterGrupamentoFacade.removerGrupamento(grupamentoAcesso);
			manterGrupamentoForm.setMsgError(ConstantesCRM.SVAZIO);
			manterGrupamentoForm.setMsgWarning(ConstantesCRM.SVAZIO);
		} catch (SQLException e) {
			e.printStackTrace();
			manterGrupamentoForm.setMsgError("Registro não pode ser excluído");
			manterGrupamentoForm.setMsgWarning(ConstantesCRM.SVAZIO);
		}
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	protected ActionForward listarGrupamentos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		log.debug("ManterGrupamentoController.listarGrupamentos inicio");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		ManterGrupamentoForm form = (ManterGrupamentoForm) formParam;
		GrupamentoAcesso grupamentoAcesso = new GrupamentoAcesso();
		
		if ("pesquisar".equals(form.getAcao())) {
			manterGrupamentoForm.setMsgError(ConstantesCRM.SVAZIO);
			manterGrupamentoForm.setMsgWarning(ConstantesCRM.SVAZIO);
			manterGrupamentoForm.setSiglaGrupamento(form.getSiglaGrupamento());
			manterGrupamentoForm.setNomeGrupamento(form.getNomeGrupamento());
			manterGrupamentoForm.setDescricaoGrupamento(form.getDescricaoGrupamento());
		} 
		grupamentoAcesso.setDescricaoGrupamento(manterGrupamentoForm.getDescricaoGrupamento());
		grupamentoAcesso.setNomeGrupamento(manterGrupamentoForm.getNomeGrupamento());
		grupamentoAcesso.setSiglaGrupamento(manterGrupamentoForm.getSiglaGrupamento());
		try {
			manterGrupamentoForm.setGruposVO(manterGrupamentoFacade.listarGrupamento(grupamentoAcesso));
		} catch (SQLException e) {
			manterGrupamentoForm.setMsgError("Não foram encontrados registros para o critério de pesquisa");			
		}
		if (manterGrupamentoForm.getGruposVO() != null && manterGrupamentoForm.getGruposVO().length > 0) {
			manterGrupamentoForm.setIndicativoOperacao("Resultado");
		} else {
			manterGrupamentoForm.setIndicativoOperacao("Vazio");
		}
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	protected ActionForward incluirGrupamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		log.debug("ManterGrupamentoController.incluirGrupamento inicio");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);	
		manterGrupamentoFormIncluir = new ManterGrupamentoForm();
		manterGrupamentoForm.setMsgError(ConstantesCRM.SVAZIO);
		manterGrupamentoForm.setMsgWarning(ConstantesCRM.SVAZIO);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	protected ActionForward alterarGrupamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		log.debug("ManterGrupamentoController.alterarGrupamento inicio");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);	
		ManterGrupamentoForm form = (ManterGrupamentoForm) formParam;
		manterGrupamentoForm.setMsgError(ConstantesCRM.SVAZIO);
		manterGrupamentoForm.setMsgWarning(ConstantesCRM.SVAZIO);
		int index = Integer.parseInt(request.getParameter("idArray"));
		log.debug("index array = " + index);
		GrupamentoAcesso grupamentoAcesso =  manterGrupamentoForm.gruposVO[index];
				
		manterGrupamentoFormIncluir = new ManterGrupamentoForm();
		manterGrupamentoFormIncluir.setIdGrupamento(grupamentoAcesso.getIdGrupamento());
		manterGrupamentoFormIncluir.setDescricaoGrupamento(grupamentoAcesso.getDescricaoGrupamento());
		manterGrupamentoFormIncluir.setSiglaGrupamento(grupamentoAcesso.getSiglaGrupamento());
		manterGrupamentoFormIncluir.setNomeGrupamento(grupamentoAcesso.getNomeGrupamento());
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	protected ActionForward salvarGrupamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		log.debug("ManterGrupamentoController.salvarGrupamento inicio");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);	
		ManterGrupamentoForm form = (ManterGrupamentoForm) formParam;
		GrupamentoAcesso grupamentoAcesso = new GrupamentoAcesso();
		grupamentoAcesso.setNomeGrupamento(form.getNomeGrupamento());
		grupamentoAcesso.setDescricaoGrupamento(form.getDescricaoGrupamento());
		grupamentoAcesso.setSiglaGrupamento(form.getSiglaGrupamento());
		grupamentoAcesso.setIdGrupamento(form.getIdGrupamento());
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			int siglaCount = manterGrupamentoFacade.existeSiglaGrupamento(form.getSiglaGrupamento());
			log.debug("existe sigla = " + siglaCount);
			if (siglaCount > 0 && StringUtils.isEmpty(form.getIdGrupamento())) {				
				manterGrupamentoForm.setMsgError("Registro já existente");
			} else if (siglaCount > 0 && !StringUtils.isEmpty(form.getIdGrupamento()) && 
					!form.getSiglaGrupamento().equalsIgnoreCase(manterGrupamentoFormIncluir.getSiglaGrupamento())) {				
				manterGrupamentoForm.setMsgError("Registro já existente");
			} else if (StringUtils.isEmpty(form.getIdGrupamento())) {
				manterGrupamentoFacade.incluirGrupamento(grupamentoAcesso, user);
				manterGrupamentoForm.setMsgWarning("Registro incluído com sucesso");
			} else {
				manterGrupamentoFacade.alterarGrupamento(grupamentoAcesso, user);
				manterGrupamentoForm.setMsgWarning("Registro alterado com sucesso");
			}
		} catch (SQLException e) {		
			manterGrupamentoForm.setMsgError("erro ao inserir grupo");			
		}
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	public void setManterGrupamentoForm(ManterGrupamentoForm manterGrupamentoForm) {
		this.manterGrupamentoForm = manterGrupamentoForm;
	}

	public ManterGrupamentoForm getManterGrupamentoForm() {
		return manterGrupamentoForm;
	}

	public ManterGrupamentoForm getManterGrupamentoFormIncluir() {
		return manterGrupamentoFormIncluir;
	}

	public void setManterGrupamentoFormIncluir(
			ManterGrupamentoForm manterGrupamentoFormIncluir) {
		this.manterGrupamentoFormIncluir = manterGrupamentoFormIncluir;
	}


	public static class ManterGrupamentoForm extends ActionForm {
		private static final long serialVersionUID = 1L;		
		private String idGrupamento;
		private String siglaGrupamento;
		private String nomeGrupamento;
		private String descricaoGrupamento;
		private String indicativoOperacao;
		private String msgError;
		private String msgWarning;
		private String acao;
		
		private GrupamentoAcesso[] gruposVO = new GrupamentoAcesso[0];
		
		public String getAcao() {
			return acao;
		}
		public void setAcao(String acao) {
			this.acao = acao;
		}
		public void setSiglaGrupamento(String siglaGrupamento) {
			this.siglaGrupamento = siglaGrupamento;
		}
		public String getSiglaGrupamento() {
			return siglaGrupamento;
		}
		public void setNomeGrupamento(String nomeGrupamento) {
			this.nomeGrupamento = nomeGrupamento;
		}
		public String getNomeGrupamento() {
			return nomeGrupamento;
		}
		public void setDescricaoGrupamento(String descricaoGrupamento) {
			this.descricaoGrupamento = descricaoGrupamento;
		}
		public String getDescricaoGrupamento() {
			return descricaoGrupamento;
		}
		public void setIdGrupamento(String idGrupamento) {
			this.idGrupamento = idGrupamento;
		}
		public String getIdGrupamento() {
			return idGrupamento;
		}
		public void setIndicativoOperacao(String indicativoOperacao) {
			this.indicativoOperacao = indicativoOperacao;
		}
		public String getIndicativoOperacao() {
			return indicativoOperacao;
		}
		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}
		public String getMsgError() {
			return msgError;
		}
		public void setGruposVO(GrupamentoAcesso[] gruposVO) {
			this.gruposVO = gruposVO;
		}
		public GrupamentoAcesso[] getGruposVO() {
			return gruposVO;
		}
		public String getMsgWarning() {
			return msgWarning;
		}
		public void setMsgWarning(String msgWarning) {
			this.msgWarning = msgWarning;
		}
	}
}
