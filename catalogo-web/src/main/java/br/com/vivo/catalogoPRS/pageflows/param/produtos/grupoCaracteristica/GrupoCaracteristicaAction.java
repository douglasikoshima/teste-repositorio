package br.com.vivo.catalogoPRS.pageflows.param.produtos.grupoCaracteristica;


import java.io.File;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.GrupoCaracteristicaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.GrupoCaracteristicaForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;

import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

public class GrupoCaracteristicaAction extends BaseMappingDispatchAction {
	
	private static Logger log = Logger.getLogger(GrupoCaracteristicaAction.class);
	private List<GrupoCaracteristicaTO> grupoCaracteristicaList;


	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("inicio begin");
		GrupoCaracteristicaDelegate grupoCaracteristicaDelegate = new GrupoCaracteristicaDelegate();
		grupoCaracteristicaList = grupoCaracteristicaDelegate.findAll();
		request.setAttribute("grupoCaracteristicaList", grupoCaracteristicaList);
		return mapping.findForward("success");
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("inicio excluir");
		GrupoCaracteristicaForm grupoCaracteristicaForm = (GrupoCaracteristicaForm)form;
		GrupoCaracteristicaTO grupocaracteristicaTO = new GrupoCaracteristicaTO();
		grupocaracteristicaTO.setIdGrupoCaracteristica(new Integer(grupoCaracteristicaForm.getIdGrupoCaracteristica()));
		try {
			GrupoCaracteristicaDelegate grupoCaracteristicaDelegate = new GrupoCaracteristicaDelegate();
			grupoCaracteristicaDelegate.deleteGrupoCaracteristica(grupocaracteristicaTO);
			String path = ApplicationConfiguration.getCaminhoSalvarImagensCaracteristicas();		
			File file = new File(path + grupoCaracteristicaForm.getNomeIcone());
			file.delete();
		} catch (EJBException ex) {
			request.setAttribute("errorLabel", "Opera&ccedil;&atilde;o n&atilde;o permitida. Esse Grupo possui caracter&iacute;sticas associadas. <!-- " + ex.getMessage() + " -->");
		} catch (BusinessException e) {
			request.setAttribute("errorLabel", e.getMessage());
		} catch (Exception ex) {
			request.setAttribute("errorLabel", "erro ao deletar arquivo. <!-- " + ex.getMessage() + " -->");
		}
		return mapping.findForward("success");
	}	
		
	
	public ActionForward gravar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("inicio do gravar");
		GrupoCaracteristicaForm grupoCaracteristicaForm = (GrupoCaracteristicaForm)form;
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");

			GrupoCaracteristicaTO grupocaracteristicaTO = new GrupoCaracteristicaTO();
			grupocaracteristicaTO.setDtAlteracao(new Date());
			grupocaracteristicaTO.setDtCriacao(new Date());
			grupocaracteristicaTO.setNmGrupoCaracteristica(grupoCaracteristicaForm.getCaracteristica());
			grupocaracteristicaTO.setNmUsuarioAlteracao(usuarioLogado.getUser().getUsername());
			grupocaracteristicaTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());		
			grupocaracteristicaTO.setIdGrupoCaracteristica(grupoCaracteristicaForm.getIdGrupoCaracteristica() == 0 ? null : grupoCaracteristicaForm.getIdGrupoCaracteristica());
			
			GrupoCaracteristicaDelegate grupoCaracteristicaDelegate = new GrupoCaracteristicaDelegate();
			GrupoCaracteristicaTO grupo = grupoCaracteristicaDelegate.getGrupoCaracteristica(grupocaracteristicaTO);
			
			// SE FOR ALTERAÇÃO
			if (grupoCaracteristicaForm.getIdGrupoCaracteristica() != null && grupoCaracteristicaForm.getIdGrupoCaracteristica() > 0 ) {
				if (grupo != null && grupo.getIdGrupoCaracteristica().intValue() != grupocaracteristicaTO.getIdGrupoCaracteristica().intValue()) {
					request.setAttribute("labelError", "Opera&ccedil;&atilde;o n&atilde;o permitida. O nome do Grupo de Caracter&iacute;stica j&aacute; existe.");
					request.setAttribute("grupoCaracteristicaList", grupoCaracteristicaList);
					return mapping.findForward("error");
				}
			} else if ( grupo != null &&  grupoCaracteristicaForm.getCaracteristica().equalsIgnoreCase(grupo.getNmGrupoCaracteristica())) {
				request.setAttribute("labelError", "Opera&ccedil;&atilde;o n&atilde;o permitida. O nome do Grupo de Caracter&iacute;stica j&aacute; existe.");
				request.setAttribute("grupoCaracteristicaList", grupoCaracteristicaList);
				return mapping.findForward("error");
			}

			try {
				
				grupoCaracteristicaDelegate.createUpdateGrupoCaracteristica(grupocaracteristicaTO);
				
				if (grupoCaracteristicaForm.getIdGrupoCaracteristica() != null && grupoCaracteristicaForm.getIdGrupoCaracteristica() > 0) {
					request.setAttribute("labelSucess", "Grupo de Caracter&iacute;stica alterado com sucesso");
				} else {
					request.setAttribute("labelSucess", "Grupo Caracter&iacute;stica cadastrado com sucesso.");
				}
				
				grupoCaracteristicaForm.setIdGrupoCaracteristica(null);
				grupoCaracteristicaForm.setCaracteristica(null);
				
			} catch (EJBException ex) {
				request.setAttribute("errorLabel", "erro ao gravar registro. <!-- " + ex.getMessage() + " -->");
			}
		
			return mapping.findForward("success");
	}
	
	public List<GrupoCaracteristicaTO> getGrupoCaracteristicaList() {
		return grupoCaracteristicaList;
	}

	public void setGrupoCaracteristicaList(
			List<GrupoCaracteristicaTO> grupoCaracteristicaList) {
		this.grupoCaracteristicaList = grupoCaracteristicaList;
	}

}