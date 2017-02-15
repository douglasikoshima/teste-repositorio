package br.com.vivo.catalogoPRS.pageflows.param.formacondicaopagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.to.BandeiraTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;
import com.indracompany.catalogo.to.DescontoCondPagtoTO;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;
import com.indracompany.catalogo.to.FormaPagamentoTO;
import com.indracompany.catalogo.to.FormaPagtoCanalAtndParamTO;
import com.indracompany.catalogo.to.FormaPagtoCanalParamTO;
import com.indracompany.catalogo.to.MeioPagamentoTO;
import com.indracompany.catalogo.to.PlataformaTO;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.BandeiraDelegate;
import br.com.vivo.catalogoPRS.delegate.CanalAtendimentoDelegate;
import br.com.vivo.catalogoPRS.delegate.CanalDelegate;
import br.com.vivo.catalogoPRS.delegate.CondicaoPagamentoDelegate;
import br.com.vivo.catalogoPRS.delegate.FormaPagamentoBandeiraDelegate;
import br.com.vivo.catalogoPRS.delegate.FormaPagamentoDelegate;
import br.com.vivo.catalogoPRS.delegate.MeioPagamentoDelegate;
import br.com.vivo.catalogoPRS.delegate.PlataformaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.FormaCondicaoPagamentoForm;


public class FormaCondicaoPagamentoAction extends BaseMappingDispatchAction  implements Serializable{

	private static final long serialVersionUID = 1L;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) { 
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		carregar(request);
		search(mapping, formulario, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		carregar(request);
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		request.setAttribute("formaPagamentoResultList", formaPagamentoDelegate.searchFormaPagamento(doFormaPagamentoTOSearch(formulario)));
		
		return mapping.findForward("success");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		request.setAttribute("cadastrar", Boolean.TRUE); 
		search(mapping, formulario, request, response);
		resetForm(formulario);
		
		return mapping.findForward("success");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		doForm(formaPagamentoDelegate.findById(doFormaPagamentoTO(formulario, request)), formulario, request);
			
		request.setAttribute("cadastrar", Boolean.TRUE); 
		search(mapping, formulario, request, response);
		return mapping.findForward("success");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		FormaPagamentoTO formaPagamentoTO = formaPagamentoDelegate.createUpdateFormaPagamento(doFormaPagamentoTO(formulario, request));
		
		// SALVANDO AS CONDICOES
		CondicaoPagamentoDelegate condicaoPagamentoDelegate = new CondicaoPagamentoDelegate();
		List<CondicaoPagamentoTO> condicaoPagamentoList = doCondicaoPagamentoTO(formulario, formaPagamentoTO, request);
		condicaoPagamentoDelegate.createUpdateCondicaoPagamentoList(condicaoPagamentoList);
		
		// SALVANDO AS BANDEIRAS
		if (formulario.getCheckBandeira() != null && formulario.getCheckBandeira().length > 0) {
			FormaPagamentoBandeiraDelegate formaPagamentoBandeiraDelegate = new FormaPagamentoBandeiraDelegate();
			formaPagamentoBandeiraDelegate.createUpdateFormaPagamentoBandeira(doFormaPagamentoBandeiraTOList(formulario, formaPagamentoTO, request));
		}
		
		// SALVANDO AS INFORMAÇÕES ADICIONAIS
		formaPagamentoTO.setFormaPagtoCanalParamTO(doFormaPagtoCanalParamTO(formulario, formaPagamentoTO, condicaoPagamentoList, request));
		formaPagamentoDelegate.createUpdateFormaPagtoCanalParam(formaPagamentoTO);
		
		if (formulario.getIdFormaCondicaoPagamento() != null && !formulario.getIdFormaCondicaoPagamento().equals("")) {
			request.setAttribute("labelSucess", "Forma e Condi��o de Pagamento Alterada com sucesso.");
		} else {
			request.setAttribute("labelSucess", "Forma e Condi��o de Pagamento criada com sucesso.");
		}
		
		search(mapping, formulario, request, response);
		return mapping.findForward("success");
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		formaPagamentoDelegate.removeFormaPagamento(doFormaPagamentoTO(formulario, request));
		
		search(mapping, formulario, request, response);
		request.setAttribute("labelSucess", "Forma e Condi��o de Pagamento exclu�da com sucesso.");		
		
		return mapping.findForward("success");
	}
	
	public ActionForward abrirCondicoes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		request.setAttribute("condicaoList", formaPagamentoDelegate.findById(doFormaPagamentoTO(formulario, request)).getCondicaoPagamentoTOList());
		
		return mapping.findForward("success");
	}

	public ActionForward abrirBandeiras(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FormaCondicaoPagamentoForm formulario = (FormaCondicaoPagamentoForm) form;
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		request.setAttribute("bandeiraList", formaPagamentoDelegate.findById(doFormaPagamentoTO(formulario, request)).getFormaPagamentoBandeiraTOList());
		
		return mapping.findForward("success");
	}
	
	private void carregar(HttpServletRequest request) {
		FormaPagamentoDelegate formaPagamentoDelegate = new FormaPagamentoDelegate();
		request.setAttribute("formaPagamentoList", formaPagamentoDelegate.findAll());
		
		BandeiraDelegate bandeiraDelegate = new BandeiraDelegate();
		request.setAttribute("bandeiraList", bandeiraDelegate.findAll());
		
		CanalAtendimentoDelegate canalAtendimentoDelegate = new CanalAtendimentoDelegate();
		List<CanalAtendimentoTO> canalAtendimentoTOList = canalAtendimentoDelegate.findAll();
		CollectionUtils.filter(canalAtendimentoTOList, new Predicate() {
			public boolean evaluate(Object obj) {
				CanalAtendimentoTO canalAtendimentoTO = (CanalAtendimentoTO) obj;
				Integer idCanalAtendimento = canalAtendimentoTO.getIdCanalAtendimento();
				return (idCanalAtendimento == 23611	// TELEVENDAS
					|| idCanalAtendimento == 2);	// LOJAS
			}
		});
		request.setAttribute("canalAtendimentoList", canalAtendimentoTOList);
		
		CanalDelegate canalDelegate = new CanalDelegate();
		List<CanalTO> canalTOList = canalDelegate.findAll();
		CollectionUtils.filter(canalTOList, new Predicate() {
			public boolean evaluate(Object obj) {
				CanalTO canalTO = (CanalTO) obj;
				Integer idCanal = canalTO.getIdCanal();
				return (idCanal == 40	// LOJA VIRTUAL
					|| idCanal == 35);	// LOJAS PRÓPRIAS
			}
		});
		request.setAttribute("canalList", canalTOList);
		
		MeioPagamentoDelegate meioPagamentoDelegate = new MeioPagamentoDelegate();
		request.setAttribute("meioPagamentoList", meioPagamentoDelegate.findAll());
        
        request.setAttribute("plataformaList", new PlataformaDelegate().findByIdCanalAtendimento(CanalAtendimento.TELEVENDAS));
		
	}
	
	public FormaPagamentoTO doFormaPagamentoTOSearch(FormaCondicaoPagamentoForm formaCondicaoPagamentoForm) {
		FormaPagamentoTO formaPagamentoTO = new FormaPagamentoTO();
			formaPagamentoTO.setIdFormaPagamento(formaCondicaoPagamentoForm.getIdFormaPagamentoSearch());
		
		if (formaCondicaoPagamentoForm.getIdBandeiraSearch() != null && formaCondicaoPagamentoForm.getIdBandeiraSearch() > 0 ) {
			List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList = new ArrayList<FormaPagamentoBandeiraTO>();
			FormaPagamentoBandeiraTO formaPagamentoBandeiraTO = new FormaPagamentoBandeiraTO();
			formaPagamentoBandeiraTO.setBandeiraTO(new BandeiraTO(formaCondicaoPagamentoForm.getIdBandeiraSearch()));
			formaPagamentoBandeiraTOList.add(formaPagamentoBandeiraTO);
			formaPagamentoTO.setFormaPagamentoBandeiraTOList(formaPagamentoBandeiraTOList);
		}
		
			formaPagamentoTO.setMeioPagamentoTO(new MeioPagamentoTO(null, formaCondicaoPagamentoForm.getSgMeioPagamentoSearch()));
		
		return formaPagamentoTO;
	}
	
	public FormaPagamentoTO doFormaPagamentoTO(FormaCondicaoPagamentoForm formaCondicaoPagamentoForm, HttpServletRequest request) {
		
		FormaPagamentoTO formaPagamentoTO = new FormaPagamentoTO();
		formaPagamentoTO.setIdFormaPagamento(formaCondicaoPagamentoForm.getIdFormaPagamento());
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		if (formaCondicaoPagamentoForm.getSgMeioPagamento() != null && !formaCondicaoPagamentoForm.getSgMeioPagamento().equals("")) {
			formaPagamentoTO.setMeioPagamentoTO(
						new MeioPagamentoTO(
								Integer.parseInt(formaCondicaoPagamentoForm.getSgMeioPagamento().split("\\|")[0])
								, formaCondicaoPagamentoForm.getSgMeioPagamento().split("\\|")[1]));
		}
        
        formaPagamentoTO.setPlataformaTOList(new ArrayList<PlataformaTO>());
        if (formaCondicaoPagamentoForm.getCheckPlataforma() != null && formaCondicaoPagamentoForm.getCheckPlataforma().length > 0) {
            List<PlataformaTO> plataformaTOList = formaPagamentoTO.getPlataformaTOList();
            for(Integer idPlataforma : formaCondicaoPagamentoForm.getCheckPlataforma()) {
                plataformaTOList.add(new PlataformaTO(idPlataforma));
            }
            formaPagamentoTO.setPlataformaTOList(plataformaTOList);
        }
		formaPagamentoTO.setNmFormaPagamento(formaCondicaoPagamentoForm.getFormaPagamento());
		formaPagamentoTO.setNmUsuarioAlteracao(userCatalogo.getUser().getUsername());
		formaPagamentoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		formaPagamentoTO.setDtCriacao(new Date());
		formaPagamentoTO.setDtUltimaAlteracao(new Date());
		
		return formaPagamentoTO;
	}
	
	public List<CondicaoPagamentoTO> doCondicaoPagamentoTO(FormaCondicaoPagamentoForm formaCondicaoPagamentoForm, FormaPagamentoTO formaPagamentoTO, HttpServletRequest request) {
		
		List<CondicaoPagamentoTO> condicaoPagamentoTOList = new ArrayList<CondicaoPagamentoTO>();
		CondicaoPagamentoTO condicaoPagamentoTO = null;
		DescontoCondPagtoTO descontoCondPagtoTO;
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		for (int i = 0; i < formaCondicaoPagamentoForm.getCondicoesPagto().length; i++) {
			condicaoPagamentoTO = new CondicaoPagamentoTO();
			condicaoPagamentoTO.setIdCondicaoPagamento(formaCondicaoPagamentoForm.getIdCondicoesPagto()[i]);
			condicaoPagamentoTO.setQtParcelas(formaCondicaoPagamentoForm.getCondicoesPagto()[i]);
			condicaoPagamentoTO.setSgCondicaoPagamento(formaCondicaoPagamentoForm.getSiglasSAP()[i]);
			condicaoPagamentoTO.setNmCondicaoPagamento(formaCondicaoPagamentoForm.getDescricoes()[i]);
			condicaoPagamentoTO.setFormaPagamentoTO(formaPagamentoTO);
			condicaoPagamentoTO.setDtCriacao(new Date());
			condicaoPagamentoTO.setDtUltimaAlteracao(new Date());
			condicaoPagamentoTO.setNmUsuarioAlteracao(userCatalogo.getUser().getUsername());
			condicaoPagamentoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
			
			descontoCondPagtoTO = new DescontoCondPagtoTO();
			descontoCondPagtoTO.setCondicaoPagamentoTO(condicaoPagamentoTO);
			descontoCondPagtoTO.setCanalTO(new CanalTO(formaCondicaoPagamentoForm.getIdCanalDistribuicao()));
			descontoCondPagtoTO.setFatorPreco(1f);
			
			condicaoPagamentoTO.setDescontoCondPagtoTO(descontoCondPagtoTO);
			
			condicaoPagamentoTOList.add(condicaoPagamentoTO);
		}
		
		return condicaoPagamentoTOList;
	}
	
	public List<FormaPagamentoBandeiraTO> doFormaPagamentoBandeiraTOList(FormaCondicaoPagamentoForm formaCondicaoPagamentoForm, FormaPagamentoTO formaPagamentoTO, HttpServletRequest request) {
		// CRIANDO O RELACIONAMENTO DE BANDEIRA X FORMA PAGAMENTO
		List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList = new ArrayList<FormaPagamentoBandeiraTO>();
		FormaPagamentoBandeiraTO formaPagamentoBandeiraTO = null;	
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		for (int i = 0; i < formaCondicaoPagamentoForm.getCheckBandeira().length; i++) {
			formaPagamentoBandeiraTO = new FormaPagamentoBandeiraTO();
			formaPagamentoBandeiraTO.setBandeiraTO(new BandeiraTO(formaCondicaoPagamentoForm.getCheckBandeira()[i]));
			formaPagamentoBandeiraTO.setIdFormaPagamento(formaPagamentoTO.getIdFormaPagamento());
			formaPagamentoBandeiraTO.setDtCriacao(new Date());
			formaPagamentoBandeiraTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
			formaPagamentoBandeiraTOList.add(formaPagamentoBandeiraTO);
		}
		
		return formaPagamentoBandeiraTOList;
	}
	
	
	public FormaPagtoCanalParamTO doFormaPagtoCanalParamTO(FormaCondicaoPagamentoForm formaCondicaoPagamentoForm, FormaPagamentoTO formaPagamentoTO, List<CondicaoPagamentoTO> condicaoPagamentoList, HttpServletRequest request) {
		
		// CRIANDO RELACIONAMENTO ENTRE A FORMA DE PAGAMENTO E AS CONDIÇÕES DE PAGAMENTO
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		FormaPagtoCanalParamTO formaPagtoCanalParamTO = new FormaPagtoCanalParamTO();
		formaPagtoCanalParamTO.setCanalTO(new CanalTO(formaCondicaoPagamentoForm.getIdCanalDistribuicao()));
		formaPagtoCanalParamTO.setFormaPagamentoTO(formaPagamentoTO);
		formaPagtoCanalParamTO.setTaxaJuros(0f);
		FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO = new FormaPagtoCanalAtndParamTO();
		formaPagtoCanalAtndParamTO.setCanalAtendimentoTO(new CanalAtendimentoTO(formaCondicaoPagamentoForm.getIdCanalAtendimento()));
		formaPagtoCanalAtndParamTO.setCdInstituicaoFinanceira(formaCondicaoPagamentoForm.getCodInstituicaoFinanceira());
		formaPagtoCanalAtndParamTO.setDtCriacao(new Date());
		formaPagtoCanalAtndParamTO.setDtUltimaAlteracao(new Date());
		formaPagtoCanalAtndParamTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		formaPagtoCanalAtndParamTO.setNmUsuarioUltimaAlteracao(userCatalogo.getUser().getUsername());
		
		if (formaCondicaoPagamentoForm.getVlDesconto() != null && !formaCondicaoPagamentoForm.getVlDesconto().equals("")) {
			formaPagtoCanalAtndParamTO.setValorDesconto(Float.parseFloat(formaCondicaoPagamentoForm.getVlDesconto().replace(".", "").replace(",", ".")));
		} else {
			formaPagtoCanalAtndParamTO.setValorDesconto(0f);
		}
		if (formaCondicaoPagamentoForm.getVlParcelaMinima() != null && !formaCondicaoPagamentoForm.getVlParcelaMinima().equals("")) {
			formaPagtoCanalAtndParamTO.setVlParcelaMinima(Float.parseFloat(formaCondicaoPagamentoForm.getVlParcelaMinima().replace(".", "").replace(",", ".")));
		} else {
			formaPagtoCanalAtndParamTO.setVlParcelaMinima(0f);
		}
		
		Integer numMaximo = 0;
		
		for ( CondicaoPagamentoTO condicaoPagamentoTO : condicaoPagamentoList) {
			if (condicaoPagamentoTO.getQtParcelas() > numMaximo) {
				numMaximo = condicaoPagamentoTO.getQtParcelas();
			}
		}
		
		formaPagtoCanalParamTO.setNrMaxParcSemJuros(numMaximo);
		formaPagtoCanalParamTO.setNrParcelasMax(numMaximo);
		
		formaPagtoCanalParamTO.setFormaPagtoCanalAtndParamTO(formaPagtoCanalAtndParamTO);
		
		return formaPagtoCanalParamTO;
	}
	
	public FormaCondicaoPagamentoForm doForm(FormaPagamentoTO formaPagamentoTO, FormaCondicaoPagamentoForm formaCondicaoPagamentoForm, HttpServletRequest request) {
		
		formaCondicaoPagamentoForm.setFormaPagamento(formaPagamentoTO.getNmFormaPagamento());
		formaCondicaoPagamentoForm.setIdFormaPagamento(formaPagamentoTO.getIdFormaPagamento());
		
		if (formaPagamentoTO.getFormaPagtoCanalParamTO() != null) {
			if (formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO() != null) {
				formaCondicaoPagamentoForm.setIdCanalAtendimento(formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO().getCanalAtendimentoTO().getIdCanalAtendimento());
			}
			formaCondicaoPagamentoForm.setIdCanalDistribuicao(formaPagamentoTO.getFormaPagtoCanalParamTO().getCanalTO().getIdCanal());
			
			if (formaPagamentoTO.getFormaPagtoCanalParamTO() != null && formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO() != null && formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO().getValorDesconto() != null) {
				formaCondicaoPagamentoForm.setVlDesconto(formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO().getValorDesconto().toString().replaceAll("\\.", "\\,"));
			}
			if (formaPagamentoTO.getFormaPagtoCanalParamTO() != null && formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO() != null && formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO().getVlParcelaMinima() != null) {
				formaCondicaoPagamentoForm.setVlParcelaMinima(formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO().getVlParcelaMinima().toString().replaceAll("\\.", "\\,"));
			}
			if (formaPagamentoTO.getFormaPagtoCanalParamTO() != null && formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO() != null) {
				formaCondicaoPagamentoForm.setCodInstituicaoFinanceira(formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO().getCdInstituicaoFinanceira());
			}
		}
		
		if (formaPagamentoTO.getMeioPagamentoTO() != null) {
			formaCondicaoPagamentoForm.setSgMeioPagamento(formaPagamentoTO.getMeioPagamentoTO().getIdMeioPagamento() + "|" + formaPagamentoTO.getMeioPagamentoTO().getSgMeioPagamento());
		}
		
		if (formaPagamentoTO.getFormaPagamentoBandeiraTOList() != null && formaPagamentoTO.getFormaPagamentoBandeiraTOList().size() > 0) {
			Integer[] ckeckBandeiras = new Integer[formaPagamentoTO.getFormaPagamentoBandeiraTOList().size()];
			int contador = 0;
			for (FormaPagamentoBandeiraTO formaPagamentoBandeiraTO : formaPagamentoTO.getFormaPagamentoBandeiraTOList()) {
				ckeckBandeiras[contador] = formaPagamentoBandeiraTO.getBandeiraTO().getIdBandeira();
				contador++;
			}
			formaCondicaoPagamentoForm.setCheckBandeira(ckeckBandeiras);
		}
        if (formaPagamentoTO.getPlataformaTOList() != null && formaPagamentoTO.getPlataformaTOList().size() > 0) {
            Integer[] checkPlataformas = new Integer[formaPagamentoTO.getPlataformaTOList().size()];
            int contador = 0;
            for (PlataformaTO plataformaTO : formaPagamentoTO.getPlataformaTOList()) {
                checkPlataformas[contador] = plataformaTO.getIdPlataforma();
                contador++;
            }
            formaCondicaoPagamentoForm.setCheckPlataforma(checkPlataformas);            
        }
		
		if (formaPagamentoTO.getCondicaoPagamentoTOList() != null && formaPagamentoTO.getCondicaoPagamentoTOList().size() > 0) {
			request.setAttribute("condicaoPagamentoList", formaPagamentoTO.getCondicaoPagamentoTOList());
		}
		return formaCondicaoPagamentoForm;
	}
	
	public FormaCondicaoPagamentoForm resetForm(FormaCondicaoPagamentoForm formaCondicaoPagamentoForm) {
		formaCondicaoPagamentoForm.setFormaPagamento(null);
		formaCondicaoPagamentoForm.setIdBandeira(null);
		formaCondicaoPagamentoForm.setIdFormaCondicaoPagamento(null);
		formaCondicaoPagamentoForm.setVlDesconto(null);
		formaCondicaoPagamentoForm.setVlParcelaMinima(null);
		formaCondicaoPagamentoForm.setCodInstituicaoFinanceira(null);
		formaCondicaoPagamentoForm.setIdCanalAtendimento(null);
		formaCondicaoPagamentoForm.setIdCanalDistribuicao(null);
		formaCondicaoPagamentoForm.setSgMeioPagamento(null);
		formaCondicaoPagamentoForm.setCheckBandeira(null);
		formaCondicaoPagamentoForm.setCondicoesPagto(null);
		formaCondicaoPagamentoForm.setDescricoes(null);
		formaCondicaoPagamentoForm.setIdFormaPagamento(null);
		formaCondicaoPagamentoForm.setSiglasSAP(null);
		
		return formaCondicaoPagamentoForm;
	}

	

}

	