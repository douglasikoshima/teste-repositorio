package br.com.vivo.catalogoPRS.pageflows.param.ofertafixa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.DisponibilidadeDelegate;
import br.com.vivo.catalogoPRS.delegate.OfertafixaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.OfertaFixaForm;

import com.indracompany.catalogo.to.AgrupadorCNLTO;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.LocalidadeTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaDisponibilidadeTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.UfTO;

public class OfertaFixaAction extends BaseMappingDispatchAction implements Serializable{

	private static final long serialVersionUID = 8302075677624420760L;
	private static Logger log = Logger.getLogger(OfertaFixaAction.class);
    private static final String NOVO = "novo";
	private static final String ALTERAR = "alterar";
	private static final String VISUALIZAR = "visualizar";
    private static final String CARREGAR = "carregar";
    private static final String COPIAR = "copiar";
    private static final String CARREGARCOPIA = "carregarCopia";
    private static final String CONFIRM = "confirm";
    private static final String SUCCESS = "success";
    private static final String ABA1 = "aba1Link";
    private static final String ABA2 = "aba2Link";
    private static final String ABA3 = "aba3Link";
    private static final String CODIGO_JA_EXISTE = "C&oacute;digo da Oferta j&aacute; existe!";
    private static final String TEMPLATE_MENSAGEM_CRIADO_COM_SUCESSO = "Oferta %s Criada com Sucesso";
    private static final String MENSAGEM_GRAVADO_COM_SUCESSO = "Dados gravados com Sucesso";
    private static final String MENSAGEM_OFERTA_REMOVIDA = "Oferta removida com Sucesso";
    private static final String MENSAGEM_PARAMETROS_OBRIGATORIOS = "Favor preencher os par&acirc;metros obrigat&oacute;rios";
    private static final String MENSAGEM_ERRO_NAO_ESPERADO = "data not found or unknown.";
    private static final String MENSAGEM_PRAZO_VIGENCIA = "Tempo de Vig&ecirc;ncia superior ao prazo definido pelo Legado!";
    private static final String MENSAGEM_GRUPO_COMERCIAL_INCOMPATIVEL = "A lista de grupos comerciais abaixo &eacute; incompat&iacute;vel com esta opera&ccedil;&atilde;o de venda, ao prosseguir as configura&ccedil;&otilde;es do grupo ser&atilde;o perdidas. Deseja prosseguir?";
    public static final String NAO_INICIADO = "naoiniciado";

    private List<ServicoFixaTO> servicoLinhaList;
    private List<OfertafixaTO> ofertafixaTOList;
    private OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO;
    private OfertafixaTO ofertafixaTO;
    private List<SolicitacaoComercialFixaTO> solicitacaoComercialFixaTOComplementarList;
    private List<AreaRegistroTO> pesquisaAreaRegistroTOList;
    private List<OfertafixaComplementarTO> ofertafixaComplementarTORemoveList = new ArrayList<OfertafixaComplementarTO>();

    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	log.debug("begin");
        this.carregarObjetos(null,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward carregar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
    	log.debug("init");
    	request.setAttribute("acao", "carregar");
    	String idOferta = ofertafixaForm.getId();
    	idOferta = idOferta.split("&")[0];
        if (StringUtils.isBlank(ofertafixaForm.getElement())) {
            throw new BusinessException("element data not found.");
        }
        if (StringUtils.isBlank(idOferta) || !StringUtils.isNumeric(idOferta)) {
            throw new BusinessException("id data not found or unknown.");
        }
        Integer id = Integer.valueOf(idOferta);
        request.setAttribute("neo", ofertafixaForm.getNeo());
        if (ofertafixaForm.getElement().startsWith("selectSolicitacaoComercialNormal")) {
            List<SolicitacaoComercialFixaTO> solicitacaoComercalFixaTONormalList = new OfertafixaDelegate().carregarSolicitacaoComercialPorServicoList(id);
            request.setAttribute("solicitacaoComercalFixaTONormalList", solicitacaoComercalFixaTONormalList);
        } else if (ofertafixaForm.getElement().startsWith("selectServicoPlano")) {
            List<ServicoFixaTO> servicoFixaTOPlanoList = new OfertafixaDelegate().carregarServicoFixaTOPlanoList(id);
            request.setAttribute("servicoFixaTOPlanoList", servicoFixaTOPlanoList);
        } else if (ofertafixaForm.getElement().startsWith("selectSolicitacaoComercialPreCadastro")) {
            List<SolicitacaoComercialFixaTO> solicitacaoComercalFixaTOPreCadastroList = new OfertafixaDelegate().carregarSolicitacaoComercialPorServicoList(id);
            request.setAttribute("solicitacaoComercalFixaTOPreCadastroList", solicitacaoComercalFixaTOPreCadastroList);
        } else if (ofertafixaForm.getElement().startsWith("selectSolicitacaoComercialPlano")) {
            List<SolicitacaoComercialFixaTO> solicitacaoComercalFixaTOPlanoList = new OfertafixaDelegate().carregarSolicitacaoComercialPorServicoList(id);
            request.setAttribute("solicitacaoComercalFixaTOPlanoList", solicitacaoComercalFixaTOPlanoList);
        } else if (ofertafixaForm.getElement().startsWith("selectServicoOfertaComplementar")) {
            List<ServicoFixaTO> servicoFixaTOList = new OfertafixaDelegate().findServicoByTipoServico(id, this.ofertafixaTO);
            request.setAttribute("servicoFixaTOList", servicoFixaTOList);
        } else if (ofertafixaForm.getElement().startsWith("selectIdAreaRegistro")) {
            List<AreaRegistroTO> areaRegistroTOList = new DisponibilidadeDelegate().findAreaRegistroByUf(id);
            request.setAttribute("areaRegistroTOList", areaRegistroTOList);
        } else if (ofertafixaForm.getElement().startsWith("selectIdCidade")) {
            List<CidadeTO> cidadeTOList = new DisponibilidadeDelegate().findCidadeByAreaRegistro(id);
            request.setAttribute("cidadeTOList", cidadeTOList);
        } 
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "search");
        try {
            this.ofertafixaTOList = new OfertafixaDelegate().search(ofertafixaForm.buildTOSearch());
        } catch (CatalogoPRSException e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }       
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward order(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("element", ofertafixaForm.getElement());
        request.setAttribute("acao", "order");
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    } 
	
	private List<String> obterListaCanalVendaIncompativel(List<OfertafixaComplementarTO> ofertafixaComplementarTOList) {
		List<Long> idCanalVendaListCompativel = obterIdCanalVendaListCompativel(this.ofertafixaTO, ofertafixaComplementarTOList);
    	List<String> listaCanalVendaIncompativel = new ArrayList<String>();
    	
    	for (CanalVendaTO cvTO : this.ofertafixaDisponibilidadeTO.getCanalVendaTOList()) {
    		if (!idCanalVendaListCompativel.contains(cvTO.getIdCanalVenda())) {
    			listaCanalVendaIncompativel.add(String.format("%s - %s", cvTO.getCdCanalVenda(), cvTO.getNmCanalVenda()));
    		}
    	}
		return listaCanalVendaIncompativel;
	}
    
    public ActionForward gravar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "gravar");
        try {
        	this.prepararObjetoGravacao(ofertafixaForm,request);
          	
        		//this.validarDisponibilidadeTO();
            	OfertafixaTO ofertafixaTO = new OfertafixaDelegate().gravar(this.ofertafixaTO, this.ofertafixaDisponibilidadeTO);
            	this.ofertafixaTOList = new ArrayList<OfertafixaTO>();
            	this.ofertafixaTOList.add(ofertafixaTO);
                request.setAttribute("type_msg", "success");
                if(StringUtils.isBlank(ofertafixaForm.getIdOfertafixa())) {
                    request.setAttribute("msg", String.format(TEMPLATE_MENSAGEM_CRIADO_COM_SUCESSO, ofertafixaTO.getCdOfertafixa()));
                } else {
                    request.setAttribute("msg", MENSAGEM_GRAVADO_COM_SUCESSO);
                }
        } catch (CatalogoPRSException e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }    
    
    private void prepararObjetoGravacao(OfertaFixaForm ofertafixaForm, HttpServletRequest request) throws CatalogoPRSException {
    	this.ofertafixaTO = ofertafixaForm.buildRecordableTO(this.ofertafixaTO);
		OfertafixaDelegate ofertafixaDelegate = new OfertafixaDelegate();
//        OfertafixaTO ofertafixaTO = ofertafixaDelegate.findByCodigoExceptId(ofertafixaForm.getCdOferta(), this.ofertafixaTO.getIdOfertafixa());
//        if(ofertafixaTO != null) {
//            this.ofertafixaTO.setCdOfertafixa("");
//            throw new CatalogoPRSException(CODIGO_JA_EXISTE);
//        }
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		OfertafixaTO ofertafixaTO = ofertafixaDelegate.findByCodigoExceptId(ofertafixaForm.getCdOferta(), this.ofertafixaTO.getIdOfertafixa());
        if(ofertafixaTO != null) {
            this.ofertafixaTO.setCdOfertafixa("");
            throw new CatalogoPRSException(CODIGO_JA_EXISTE);
        }
        if (this.ofertafixaTO.getIdOfertafixa() == null) {
            this.ofertafixaTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
        } else {
            this.ofertafixaTO.setNmUsuarioAlteracao(userCatalogo.getUser().getUsername());
        }
        this.ofertafixaTO.addAllOfertafixaComplementarTOList(this.ofertafixaComplementarTORemoveList);
	}

    public ActionForward excluir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "excluir");
        final Integer idOfertafixa = Integer.valueOf(ofertafixaForm.getIdOfertafixa());
        new OfertafixaDelegate().excluir(idOfertafixa);
        CollectionUtils.filter(this.ofertafixaTOList, new Predicate() {
            public boolean evaluate(Object obj) {
                return !((OfertafixaTO)obj).getIdOfertafixa().equals(idOfertafixa);
            }
        });
        request.setAttribute("type_msg", "success");
        request.setAttribute("msg", MENSAGEM_OFERTA_REMOVIDA);
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward carregarFormularioNovaOfertaComplementar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "carregarFormularioNovaOfertaComplementar");
        OfertafixaDelegate ofertafixaDelegate = new OfertafixaDelegate();
        if (ofertafixaForm.getElement().equals("novaOfertaComplementar")) {
            List<TipoServicoTO> tipoServicoTOList = ofertafixaDelegate.carregarTipoServicoTOList();
            request.setAttribute("tipoServicoTOList", tipoServicoTOList);
            this.solicitacaoComercialFixaTOComplementarList = null;
        } else if (ofertafixaForm.getElement().equals("resultadoPesquisaComplementar")) {        	
        	String idServComp1 = ofertafixaForm.getIdServicoOfertaComplementar();        	
        	idServComp1 = idServComp1.split("&")[0];        
            if (StringUtils.isBlank(idServComp1) || !StringUtils.isNumeric(idServComp1)) {
                request.setAttribute("type_msg", "error");
                request.setAttribute("msg", MENSAGEM_PARAMETROS_OBRIGATORIOS);            
            } else {
            	String idServComp2 = ofertafixaForm.getIdServicoOfertaComplementar();
            	idServComp2 = idServComp2.split("&")[0];            	
                Integer idServicoOfertaComplementar = Integer.valueOf(idServComp2);
				this.solicitacaoComercialFixaTOComplementarList = ofertafixaDelegate.carregarSolicitacaoComercialPorServicoList(idServicoOfertaComplementar);
            }
        }
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward adicionarComplementar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "adicionarComplementar");
        try {
            List<OfertafixaComplementarTO> validateDataToOfertafixaComplementar = this.validateDataToOfertafixaComplementar(ofertafixaForm);
            if(validateDataToOfertafixaComplementar.size() > 0) {
            	this.ofertafixaTO.addAllOfertafixaComplementarTOList(validateDataToOfertafixaComplementar);
            } else {
            	this.ofertafixaTO.setOfertafixaComplementarTOList(null);
            }
        } catch (CatalogoPRSException e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward validarCompatibilidadeComplementar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "donothing");
        try {
			List<OfertafixaComplementarTO> ofertafixaComplementarTOList = this.validateDataToOfertafixaComplementar(ofertafixaForm);
			if (!ofertafixaComplementarTOList.isEmpty()) {
				List<String> listaCanalVendaIncompativel = this.obterListaCanalVendaIncompativel(ofertafixaComplementarTOList);
				if (!listaCanalVendaIncompativel.isEmpty()) {
					request.setAttribute("listaCanalVendaIncompativel", listaCanalVendaIncompativel);
					request.setAttribute("type_msg", CONFIRM);
					request.setAttribute("msg", MENSAGEM_GRUPO_COMERCIAL_INCOMPATIVEL);				
					request.setAttribute("acao", "validarCompatibilidadeComplementar");
				}
			}
		} catch (CatalogoPRSException e) {
			log.debug("", e);
		}
    	return mapping.findForward(SUCCESS);
    }
    
    public ActionForward excluirOfertaComplementar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        final Long idSolicitacaoComercial = Long.valueOf(ofertafixaForm.getIdSolicitacaoComercial());
        
        Collection<OfertafixaComplementarTO> ofertafixaComplementarTORemoveList = CollectionUtils.select(this.ofertafixaTO.getOfertafixaComplementarTOList(), new Predicate() {
            public boolean evaluate(Object obj) {
                return ((OfertafixaComplementarTO) obj).getIdSolicitacaoComercialPai().equals(idSolicitacaoComercial);
            }
        });
        this.ofertafixaTO.getOfertafixaComplementarTOList().removeAll(ofertafixaComplementarTORemoveList);
        for(OfertafixaComplementarTO ofertafixaComplementarTO : ofertafixaComplementarTORemoveList) {
            ofertafixaComplementarTO.setParaExcluir(true);
        }
        this.ofertafixaComplementarTORemoveList.addAll(CollectionUtils.select(ofertafixaComplementarTORemoveList, new Predicate() {
            public boolean evaluate(Object obj) {
                return !((OfertafixaComplementarTO) obj).isDependente();
            }
        }));
        request.setAttribute("acao", "excluirOfertaComplementar");
        request.setAttribute("type_msg", "success");
        request.setAttribute("msg", MENSAGEM_OFERTA_REMOVIDA);
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward excluirAgrupadorCNL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
    	log.debug("init");
    	request.setAttribute("acao", "excluirAgrupadorCNL");
    	final Integer idCidade = Integer.valueOf(ofertafixaForm.getIdCidade());
        this.ofertafixaDisponibilidadeTO.setAgrupadorCNLTOList(CollectionUtils.selectRejected(this.ofertafixaDisponibilidadeTO.getAgrupadorCNLTOList(), new Predicate() {
			public boolean evaluate(Object obj) {
				return ((AgrupadorCNLTO) obj).getLocalidadeTOList().iterator().next().getCidadeTO().getIdCidade().equals(idCidade);
			}
		}));    	
    	this.carregarObjetos(ofertafixaForm,request,response);
    	return mapping.findForward(SUCCESS);
    }
    
    public ActionForward adicionarOfertafixaFatorLocalidade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
        log.debug("init");
        request.setAttribute("acao", "adicionarOfertafixaFatorLocalidade");
        final List<Long> idLocalidadeList = ofertafixaForm.obterIdLocalidadeListParaGravar();
        CidadeTO cidadeTO = new OfertafixaDelegate().findCidadeById(Integer.valueOf(ofertafixaForm.getIdCidade()));
        AgrupadorCNLTO agrupadorCNLTO = new AgrupadorCNLTO(cidadeTO.getNmCidade(),cidadeTO.getAreaRegistroTO().getCodigoArea(), cidadeTO.getAreaRegistroTO().getUfTO().getNmUf());
        agrupadorCNLTO.setLocalidadeTOList(new ArrayList<LocalidadeTO>(
	    	CollectionUtils.select(cidadeTO.getLocalidadeTOList(), new Predicate() {
				public boolean evaluate(Object obj) {
					return idLocalidadeList.contains(((LocalidadeTO)obj).getIdLocalidade());
				}
			})
    	));
		this.ofertafixaDisponibilidadeTO.agrupadorCNLTOListRemove(agrupadorCNLTO);
		this.ofertafixaDisponibilidadeTO.agrupadorCNLTOListAdd(agrupadorCNLTO);
		this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward pesquisarAreaRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;

        log.debug("init");
        request.setAttribute("acao", "pesquisarAreaRegistro");
        try {
            this.pesquisaAreaRegistroTOList = this.atualizarAreaRegistroDisponibilidade(new DisponibilidadeDelegate().searchAreaRegistroTO(ofertafixaForm.buildSearchAreaRegistroTO()));
            if (!this.pesquisaAreaRegistroTOList.isEmpty()) {
                request.setAttribute("idUf", this.pesquisaAreaRegistroTOList.iterator().next().getUfTO().getIdUf());
            } else {
                request.setAttribute("idUf", 0);
            }
        } catch (CatalogoPRSException e) {
            log.error(e.getMessage());
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward pesquisarCNL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;

        log.debug("init");
        request.setAttribute("acao", "pesquisarCNL");
    	if (NumberUtils.isNumber(ofertafixaForm.getIdCidade())) {
    		Integer idCidade = Integer.valueOf(ofertafixaForm.getIdCidade());
    		List<LocalidadeTO> localidadeTOList = this.atualizarLocalidadeTOList(new DisponibilidadeDelegate().findLocalidadeByIdCidade(idCidade));
            
    		request.setAttribute("idCidade", idCidade);
    		request.setAttribute("localidadeTOList", localidadeTOList);
    	} else {
    		request.setAttribute("type_msg", "error");
    		request.setAttribute("msg", MENSAGEM_PARAMETROS_OBRIGATORIOS);
    	}
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward carregarFormularioCanalVenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "carregarFormularioCanalVenda");
        List<EpsTO> epsTOList = new DisponibilidadeDelegate().listEpsTO();
        request.setAttribute("epsTOList", epsTOList);
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward carregarFormularioAreaRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "carregarFormularioAreaRegistro");
        List<UfTO> ufTOList = new DisponibilidadeDelegate().obterUfTOList();
        request.setAttribute("ufTOList", ufTOList);
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward carregarFormularioCNL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        
        request.setAttribute("acao", "carregarFormularioCNL");
        List<UfTO> ufTOList = new DisponibilidadeDelegate().obterUfTOList();
        request.setAttribute("ufTOList", ufTOList);
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward carregarAba(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "carregarAba");
        try {
	        if (ofertafixaForm.getIdAbaSelecionada().equals(ABA1)) {
	        	this.prepararOfertafixaAba1(ofertafixaForm,request);
	        } else if (ofertafixaForm.getIdAbaSelecionada().equals(ABA2)) {
	        	this.prepararOfertafixaAba2(ofertafixaForm);
	        } else if (ofertafixaForm.getIdAbaSelecionada().equals(ABA3)) {
	        	this.prepararOfertafixaAba3(ofertafixaForm);
	        }
	        if (ofertafixaForm.getOperacao().equals(VISUALIZAR)) {
	        	this.ofertafixaTO.setStatus(OfertafixaTO.FINALIZADO);
	        }
	        request.setAttribute("type_msg", "success");
        } catch (CatalogoPRSException e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.carregarObjetos(ofertafixaForm,request,response);
    	return mapping.findForward(ofertafixaForm.getIdAbaSelecionada());
    }
    
    public ActionForward adicionarOfertafixaFatorAreaRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	
    	OfertaFixaForm ofertafixaForm = (OfertaFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acao", "adicionarAreaRegistro");
        this.ofertafixaDisponibilidadeTO.setAreaRegistroTOList(this.obterAreaRegistroTOList(ofertafixaForm));
        this.carregarObjetos(ofertafixaForm,request,response);
        return mapping.findForward(SUCCESS);
    }

	private List<AreaRegistroTO> obterAreaRegistroTOList(OfertaFixaForm ofertafixaForm) {
	    final Long idUf = Long.valueOf(ofertafixaForm.getIdUf());
	    List<AreaRegistroTO> areaRegistroTOList = new ArrayList<AreaRegistroTO>(CollectionUtils.select(this.ofertafixaDisponibilidadeTO.getAreaRegistroTOList(), new Predicate() {
	        public boolean evaluate(Object obj) {
	            return !((AreaRegistroTO)obj).getUfTO().getIdUf().equals(idUf);
	        }
	    }));
	    List<Integer> idAreaRegistroTOList = ofertafixaForm.obterIdAreaRegistroListParaGravar();
	    for (Integer idAreaRegistro : idAreaRegistroTOList) {
            areaRegistroTOList.add(new OfertafixaDelegate().findAreaRegistroTOById(idAreaRegistro));
	    }
	    return areaRegistroTOList;
	}

	private void prepararOfertafixaAba3(OfertaFixaForm ofertafixaForm) throws CatalogoPRSException {
        if (ofertafixaForm.getOperacao().equals(VISUALIZAR)) {
            this.ofertafixaDisponibilidadeTO = new OfertafixaDelegate().carregarDisponibilidadeTO(this.ofertafixaTO.getIdOfertafixa());
        } else { 
            this.ofertafixaTO = ofertafixaForm.buildRecordableTO(this.ofertafixaTO);
        }
	}
    
    private List<LocalidadeTO> atualizarLocalidadeTOList(List<LocalidadeTO> localidadeTOList) {
        if (localidadeTOList == null || localidadeTOList.size() == 0) {
            return localidadeTOList;
        }
        List<LocalidadeTO> localidadeTOListByIdCidade = this.ofertafixaDisponibilidadeTO.getLocalidadeTOListByIdCidade(localidadeTOList.iterator().next().getCidadeTO().getIdCidade());
        final List<Long> idLocalidadeListLoaded = new ArrayList<Long>(localidadeTOListByIdCidade.size());
        for (LocalidadeTO lTO : localidadeTOListByIdCidade) {
            idLocalidadeListLoaded.add(lTO.getIdLocalidade());
        }
        CollectionUtils.forAllDo(localidadeTOList, new Closure() {
            public void execute(Object obj) {
                LocalidadeTO lTO = (LocalidadeTO) obj;
                lTO.setChecked(idLocalidadeListLoaded.contains(lTO.getIdLocalidade()) ? "checked" : "");
            }
        });        
        return localidadeTOList;
    }
    
    private List<AreaRegistroTO> atualizarAreaRegistroDisponibilidade(final List<AreaRegistroTO> areaRegistroTOList) {
        final List<Integer> idAreaRegistroListLoaded = new ArrayList<Integer>(this.ofertafixaDisponibilidadeTO.getAreaRegistroTOListSize());
        for (AreaRegistroTO arTO : this.ofertafixaDisponibilidadeTO.getAreaRegistroTOList()) {
            idAreaRegistroListLoaded.add(arTO.getIdAreaRegistro());
        }
        CollectionUtils.forAllDo(areaRegistroTOList, new Closure() {
            public void execute(Object obj) {
                AreaRegistroTO arTO = (AreaRegistroTO) obj;
                arTO.setChecked(idAreaRegistroListLoaded.contains(arTO.getIdAreaRegistro()) ? "checked" : "");
            }
        });
        return areaRegistroTOList;
    }

	private void prepararOfertafixaAba2(OfertaFixaForm ofertafixaForm) throws CatalogoPRSException {
		if (ofertafixaForm.getOperacao().equals(VISUALIZAR)) {
			this.ofertafixaTO = new OfertafixaDelegate().findByIdWithComplementar(Integer.valueOf(this.ofertafixaTO.getIdOfertafixa()));
		} else { 
			this.ofertafixaTO = ofertafixaForm.buildRecordableTO(this.ofertafixaTO);
		}
	}

	private void prepararOfertafixaAba1(OfertaFixaForm ofertafixaForm, HttpServletRequest request) throws CatalogoPRSException {	
		if (ofertafixaForm.getOperacao().equals(NOVO)) {
			this.ofertafixaTO = new OfertafixaTO();
			this.ofertafixaDisponibilidadeTO = new OfertafixaDisponibilidadeTO();
			
		} else if (ofertafixaForm.getOperacao().equals(VISUALIZAR)) {
			this.ofertafixaTO = new OfertafixaDelegate().findByIdWithComplementar(Integer.valueOf(ofertafixaForm.getIdOfertafixa()));
			this.ofertafixaTO.setStatusOfertaFixa(VISUALIZAR);
		} else if (ofertafixaForm.getOperacao().equals(ALTERAR) && ofertafixaForm.getCarregar().equals(CARREGAR)) {
        	this.ofertafixaTO = new OfertafixaDelegate().findByIdWithComplementar(Integer.valueOf(ofertafixaForm.getIdOfertafixa()));
            this.ofertafixaDisponibilidadeTO = new OfertafixaDelegate().carregarDisponibilidadeTO(this.ofertafixaTO.getIdOfertafixa());
            this.ofertafixaTO.setStatusOfertaFixa(ALTERAR);
        } else if (ofertafixaForm.getOperacao().equals(COPIAR) ) {
        	if(ofertafixaForm.getCarregar().equals(CARREGARCOPIA)){
            ofertafixaForm.setCarregar(CARREGAR);
        	this.ofertafixaTO = new OfertafixaDelegate().findByIdWithComplementar(Integer.valueOf(ofertafixaForm.getIdOfertafixa()));
        	this.ofertafixaDisponibilidadeTO = new OfertafixaDelegate().carregarDisponibilidadeTO(this.ofertafixaTO.getIdOfertafixa());
        	this.ofertafixaTO.setStatus(NAO_INICIADO);
    	  	this.ofertafixaTO.setCdOfertafixa("");
        	this.ofertafixaTO.setDtInicial(null);
        	this.ofertafixaTO.setDtFinal(null);
			this.ofertafixaTO.setIdOfertafixa(null);
        	}
        }
		if (this.ofertafixaTO.getIdServicoLinha() != null) {
		    List<SolicitacaoComercialFixaTO> solicitacaoComercalFixaTONormalList = new OfertafixaDelegate().carregarSolicitacaoComercialPorServicoList(ofertafixaTO.getIdServicoLinha());
		    request.setAttribute("solicitacaoComercalFixaTONormalList", solicitacaoComercalFixaTONormalList);
		    List<SolicitacaoComercialFixaTO> solicitacaoComercalFixaTOPreCadastroList = new OfertafixaDelegate().carregarSolicitacaoComercialPorServicoList(ofertafixaTO.getIdServicoLinha());
		    request.setAttribute("solicitacaoComercalFixaTOPreCadastroList", solicitacaoComercalFixaTOPreCadastroList);
		    List<ServicoFixaTO> servicoFixaTOPlanoList = new OfertafixaDelegate().carregarServicoFixaTOPlanoList(ofertafixaTO.getIdServicoLinha());
		    request.setAttribute("servicoFixaTOPlanoList", servicoFixaTOPlanoList);
		    
		}
		if (this.ofertafixaTO.getIdServicoPlano() != null) {
		    List<SolicitacaoComercialFixaTO> solicitacaoComercalFixaTOPlanoList = new OfertafixaDelegate().carregarSolicitacaoComercialPorServicoList(ofertafixaTO.getIdServicoPlano());
		    request.setAttribute("solicitacaoComercalFixaTOPlanoList", solicitacaoComercalFixaTOPlanoList);
		}		
	}

	private List<OfertafixaComplementarTO> validateDataToOfertafixaComplementar(OfertaFixaForm ofertafixaForm) throws BusinessException, CatalogoPRSException {
        List<OfertafixaComplementarTO> ofertaFixaComplementarTOList = new ArrayList<OfertafixaComplementarTO>();
        if (!StringUtils.isBlank(ofertafixaForm.getSolicitacaoComercialChecked())) {
            for (String ofertaComplementar : ofertafixaForm.getSolicitacaoComercialChecked().split(";")) {
                String[] ofertaComplementarArray = ofertaComplementar.split("\\|");
                if (StringUtils.isBlank(ofertaComplementarArray[0]) || !StringUtils.isNumeric(ofertaComplementarArray[0])) {
                    throw new BusinessException(MENSAGEM_ERRO_NAO_ESPERADO);
                }
                if (StringUtils.isBlank(ofertaComplementarArray[1]) || !StringUtils.isNumeric(ofertaComplementarArray[1])) {
                    throw new CatalogoPRSException(MENSAGEM_PARAMETROS_OBRIGATORIOS);
                }
                Long idSolicitacaoComercial = Long.valueOf(ofertaComplementarArray[0]);
                Integer pzMaximoVigencia = Integer.valueOf(ofertaComplementarArray[1]);
                Integer pzMaximoVigenciaOld = Integer.valueOf(ofertaComplementarArray[2]);
                if (pzMaximoVigencia.compareTo(pzMaximoVigenciaOld) > 0) {
                    throw new CatalogoPRSException(MENSAGEM_PRAZO_VIGENCIA);
                }
                ofertaFixaComplementarTOList.addAll(new OfertafixaDelegate().findOfertafixaComplementarTOWithDependentes(idSolicitacaoComercial, pzMaximoVigencia));
            }            
        }
        return ofertaFixaComplementarTOList;
    }
	
	@SuppressWarnings("unchecked")
	private List<Long> obterIdCanalVendaListCompativel(OfertafixaTO ofertafixaTO) {
		OfertafixaDelegate ofertafixaDelegate = new OfertafixaDelegate();
		
		Long idSolicitacaoComercial1 = ofertafixaTO.getSolicitacaoComercialFixaTOLinha().getIdSolicitacaoComercial();
		List<Long> idCanalVendaListCompativel1 = ofertafixaDelegate.carregarIdCanalVendaListCompativel(idSolicitacaoComercial1);
		
		Long idSolicitacaoComercial2 = ofertafixaTO.getSolicitacaoComercialFixaTOPlano().getIdSolicitacaoComercial();
		List<Long> idCanalVendaListCompativel2 = ofertafixaDelegate.carregarIdCanalVendaListCompativel(idSolicitacaoComercial2);
		
		List<Long> idCanalVendaListCompativelResultado = (List<Long>) CollectionUtils.intersection(idCanalVendaListCompativel1, idCanalVendaListCompativel2);
		
		for (OfertafixaComplementarTO ofertafixaComplementarTO : ofertafixaTO.getOfertafixaComplementarTOList()) {
			Long idSolicitacaoComercial3 = ofertafixaComplementarTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial();
			List<Long> idCanalVendaListCompativel3 = ofertafixaDelegate.carregarIdCanalVendaListCompativel(idSolicitacaoComercial3);
			idCanalVendaListCompativelResultado = (List<Long>) CollectionUtils.intersection(idCanalVendaListCompativelResultado, idCanalVendaListCompativel3);
		}
		
		return idCanalVendaListCompativelResultado;
	}
	
	@SuppressWarnings("unchecked")
	private List<Long> obterIdCanalVendaListCompativel(OfertafixaTO ofertafixaTO, List<OfertafixaComplementarTO> ofertafixaComplementarTOList) {
		List<Long> idCanalVendaListCompativelResultado = obterIdCanalVendaListCompativel(ofertafixaTO);
		if (idCanalVendaListCompativelResultado != null && !idCanalVendaListCompativelResultado.isEmpty() && ofertafixaComplementarTOList != null && !ofertafixaComplementarTOList.isEmpty()) {
			OfertafixaDelegate ofertafixaDelegate = new OfertafixaDelegate();
			for (OfertafixaComplementarTO ofertafixaComplementarTO : ofertafixaComplementarTOList) {
				Long idSolicitacaoComercial4 = ofertafixaComplementarTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial();
				List<Long> idCanalVendaListCompativel4 = ofertafixaDelegate.carregarIdCanalVendaListCompativel(idSolicitacaoComercial4);
				idCanalVendaListCompativelResultado = (List<Long>) CollectionUtils.intersection(idCanalVendaListCompativelResultado, idCanalVendaListCompativel4);
			}
		}
		return idCanalVendaListCompativelResultado;
	}
    
    private void carregarObjetos(OfertaFixaForm ofertafixaForm,HttpServletRequest request, HttpServletResponse response) {
    	
    	this.cleanHeader(response);
		
        if (this.servicoLinhaList == null) {
            this.servicoLinhaList = new OfertafixaDelegate().searchServicoLinha();
        } else {
            log.debug("servicoLinhaList encontrado no cache");
        }
        request.setAttribute("pesquisaAreaRegistroTOList", this.pesquisaAreaRegistroTOList);
        request.setAttribute("servicoLinhaList", this.servicoLinhaList);
        request.setAttribute("ofertafixaTOList", this.ofertafixaTOList);
        request.setAttribute("ofertafixaDisponibilidadeTO", this.ofertafixaDisponibilidadeTO);
        request.setAttribute("ofertafixaTO", this.ofertafixaTO);
        request.setAttribute("ofertafixaComplementarTORemoveList", this.ofertafixaComplementarTORemoveList);
        request.setAttribute("solicitacaoComercialFixaTOComplementarList", solicitacaoComercialFixaTOComplementarList);
    }

    public List<ServicoFixaTO> getServicoLinhaList() {
        return servicoLinhaList;
    }

    public void setServicoLinhaList(List<ServicoFixaTO> servicoLinhaList) {
        this.servicoLinhaList = servicoLinhaList;
    }

	public OfertafixaTO getOfertafixaTO() {
		return ofertafixaTO;
	}

	public void setOfertafixaTO(OfertafixaTO ofertafixaTO) {
		this.ofertafixaTO = ofertafixaTO;
	}

	public List<SolicitacaoComercialFixaTO> getSolicitacaoComercialFixaTOComplementarList() {
		return solicitacaoComercialFixaTOComplementarList;
	}

	public void setSolicitacaoComercialFixaTOComplementarList(
			List<SolicitacaoComercialFixaTO> solicitacaoComercialFixaTOComplementarList) {
		this.solicitacaoComercialFixaTOComplementarList = solicitacaoComercialFixaTOComplementarList;
	}

    public List<AreaRegistroTO> getPesquisaAreaRegistroTOList() {
        return pesquisaAreaRegistroTOList;
    }

    public void setPesquisaAreaRegistroTOList(
            List<AreaRegistroTO> pesquisaAreaRegistroTOList) {
        this.pesquisaAreaRegistroTOList = pesquisaAreaRegistroTOList;
    }

    public List<OfertafixaComplementarTO> getOfertafixaComplementarTORemoveList() {
        return ofertafixaComplementarTORemoveList;
    }

    public void setOfertafixaComplementarTORemoveList(
            List<OfertafixaComplementarTO> ofertafixaComplementarTORemoveList) {
        this.ofertafixaComplementarTORemoveList = ofertafixaComplementarTORemoveList;
    }
}