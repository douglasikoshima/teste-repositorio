package br.com.vivo.catalogoPRS.pageflows.param.promocaofixa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.PromocaoFixaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.PromocaoFixaForm;

import com.indracompany.catalogo.to.AgrupadorDisponibilidadeByEps;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.ValorPoliticaPromocaoTO;

public class PromocaoFixaAction  extends BaseMappingDispatchAction implements Serializable{

    private static final long serialVersionUID = 2920777024812307739L;
    private static final String SUCCESS = "success";
    private static final String CODIGO_JA_EXISTE = "C&oacute;digo da Oferta Banda Larga j&aacute; existe!";
    private static final String NOME_JA_EXISTE = "Essa Oferta Banda Larga j&aacute; existe!";
    private static final String TEMPLATE_MENSAGEM_CRIADO_COM_SUCESSO = "Oferta Banda Larga %s criada com Sucesso";
    private static final Object MENSAGEM_GRAVADO_COM_SUCESSO = "Oferta Banda Larga alterada com Sucesso";
    private static final String MENSAGEM_BUSCA_SERVICO = "N&atilde;o existem descontos dispon&iacute;veis para o tipo selecionado";
	
    private static Logger log = Logger.getLogger(PromocaoFixaAction.class);
    private List<PromocaoTO> promocaoTOList;
    private List<ComposicaoPromocaoTO> composicaoPromocaoTOList;
    private List<ComposicaoPromocaoTO> composicaoPromocaoTORemoveList;
    private List<TipoServicoTO> tipoServicoTOList;
    private PromocaoTO promocaoTO;
    private AgrupadorDisponibilidadeByEps agrupadorDisponibilidadeByEps;
    private AgrupadorDisponibilidadeByEps agrupadorDisponibilidadeRemoveList;
    private List<EpsTO> epsTOList;
    Set<Integer> idCanalVendaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward carregarFormularioAlteracao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
        PromocaoTO promocaoTO;
        if (StringUtils.isBlank(promocaoFixaForm.getIdPromocao())) {
            promocaoTO = new PromocaoTO();
        } else {
            promocaoTO = new PromocaoFixaDelegate().findById(Integer.valueOf(promocaoFixaForm.getIdPromocao()));
            if (!StringUtils.isBlank(promocaoFixaForm.getDisabled())) {
                promocaoTO.setStatus(PromocaoTO.FINALIZADO);
            }
        }
        request.setAttribute("promocaoTO", promocaoTO);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug(promocaoFixaForm);
        try {
            this.promocaoTOList = new PromocaoFixaDelegate().search(promocaoFixaForm.buildTO());
        } catch( CatalogoPRSException e) {
            log.error("Erro ao efetuar busca", e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        request.setAttribute("promocaoTOList", this.promocaoTOList);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward orderPromocao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException{
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug(promocaoFixaForm);
        if ("reload".equals(promocaoFixaForm.getReload())) {
            this.promocaoTOList = new PromocaoFixaDelegate().reload(this.promocaoTOList);
        }
        request.setAttribute("promocaoTOList", this.promocaoTOList);
        request.setAttribute("element", promocaoFixaForm.getElement());
        this.cleanHeader(response);  
        return mapping.findForward(SUCCESS);
    }
    

    public ActionForward gravar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug(promocaoFixaForm);
        PromocaoTO promocaoTO = null;

        try {
            promocaoTO = new PromocaoFixaDelegate().reccord(this.obterTOParaGravar(promocaoFixaForm));
            request.setAttribute("type_msg", "success");
            if(StringUtils.isBlank(promocaoFixaForm.getIdPromocao())) {
                request.setAttribute("msg", String.format(TEMPLATE_MENSAGEM_CRIADO_COM_SUCESSO, promocaoTO.getCdPromocao()));
            } else {
                request.setAttribute("msg", MENSAGEM_GRAVADO_COM_SUCESSO);
            }
        } catch (CatalogoPRSException e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        log.debug(promocaoTO);
        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    }
    
    private PromocaoTO obterTOParaGravar(PromocaoFixaForm promocaoFixaForm) throws CatalogoPRSException {
        PromocaoFixaDelegate promocaoFixaDelegate = new PromocaoFixaDelegate();
        Integer idPromocao = StringUtils.isBlank(promocaoFixaForm.getIdPromocao()) ? null : Integer.valueOf(promocaoFixaForm.getIdPromocao());
        PromocaoTO promocaoTO = promocaoFixaDelegate.findByCodigoExceptId(promocaoFixaForm.getCodigo(), idPromocao);
        if(promocaoTO != null) {
            throw new CatalogoPRSException(CODIGO_JA_EXISTE);
        }
        promocaoTO = promocaoFixaDelegate.findByNameExceptId(promocaoFixaForm.getNome(), idPromocao);
        if(promocaoTO != null) {
            throw new CatalogoPRSException(NOME_JA_EXISTE);
        }
        if (idPromocao == null) {
            promocaoTO = new PromocaoTO();
        } else {
            promocaoTO = promocaoFixaDelegate.findById(idPromocao);
        }
        return promocaoFixaForm.buildRecordableTO(promocaoTO);
    }
    
    public ActionForward orderItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug(promocaoFixaForm);
        request.setAttribute("acao", "orderItem");
        request.setAttribute("promocaoTO", this.promocaoTO);
        request.setAttribute("composicaoPromocaoTOList", this.composicaoPromocaoTOList);
        request.setAttribute("composicaoPromocaoTORemoveList", this.composicaoPromocaoTORemoveList);
        request.setAttribute("element", promocaoFixaForm.getElement());
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward itensComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug(promocaoFixaForm);
        PromocaoFixaDelegate promocaoFixaDelegate = new PromocaoFixaDelegate();
        List<DisponibilidadePromocaoFixaTO> disponibilidadePromocaoFixaTOList = promocaoFixaDelegate.obterCanalVendaPorPromocao(Integer.valueOf(promocaoFixaForm.getIdPromocao()));
        this.agrupadorDisponibilidadeByEps = new AgrupadorDisponibilidadeByEps();
        this.agrupadorDisponibilidadeByEps.setDisponibilidadePromocaoFixaTOList(disponibilidadePromocaoFixaTOList);
        this.carregarCombos(request,response);
        Integer idPromocao = Integer.valueOf(promocaoFixaForm.getIdPromocao());
        this.composicaoPromocaoTOList = promocaoFixaDelegate.findComposicaoByIdPromocao(idPromocao);
        this.composicaoPromocaoTORemoveList = new ArrayList<ComposicaoPromocaoTO>();
        this.promocaoTO = promocaoFixaDelegate.findById(idPromocao);
        if (!StringUtils.isBlank(promocaoFixaForm.getDisabled())) {
            this.promocaoTO.setStatus(PromocaoTO.FINALIZADO);
        }
        request.setAttribute("promocaoTO", this.promocaoTO);
        request.setAttribute("composicaoPromocaoTOList", this.composicaoPromocaoTOList);
        request.setAttribute("composicaoPromocaoTORemoveList", this.composicaoPromocaoTORemoveList);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward addItensComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
        log.debug(promocaoFixaForm);
        ComposicaoPromocaoTO buildItemComposicaoTO = promocaoFixaForm.buildItemComposicaoTO();
        final Integer idServico = buildItemComposicaoTO.getIdServico();
        if (CollectionUtils.find(this.composicaoPromocaoTOList, new Predicate() {
            public boolean evaluate(Object o) {
                return idServico.equals(((ComposicaoPromocaoTO) o).getIdServico());
            }
        
        }) != null) {
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", "Servi&ccedil;o j&aacute; Configurado!");
        } else {
            this.composicaoPromocaoTOList.add(buildItemComposicaoTO);
        }
        request.setAttribute("order", "order");
        request.setAttribute("promocaoTO", this.promocaoTO);
        request.setAttribute("composicaoPromocaoTOList", this.composicaoPromocaoTOList);
        request.setAttribute("composicaoPromocaoTORemoveList", this.composicaoPromocaoTORemoveList);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward removeItensComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
        log.debug(promocaoFixaForm);
        ComposicaoPromocaoTO to = promocaoFixaForm.buildItemComposicaoTO();
        if (to.getIdComposicao() != null) {
            composicaoPromocaoTORemoveList.add(to);
        }
        this.composicaoPromocaoTOList.remove(to);
        request.setAttribute("promocaoTO", this.promocaoTO);
        request.setAttribute("composicaoPromocaoTOList", this.composicaoPromocaoTOList);
        request.setAttribute("composicaoPromocaoTORemoveList", this.composicaoPromocaoTORemoveList);
        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward gravarItensComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
        log.debug(promocaoFixaForm);
        boolean dataRemoved = false;
        
        PromocaoFixaDelegate promocaoFixaDelegate = new PromocaoFixaDelegate();
        if (this.composicaoPromocaoTORemoveList != null && this.composicaoPromocaoTORemoveList.size() > 0 ) {
        	dataRemoved = promocaoFixaDelegate.removeComposicaoById(composicaoPromocaoTORemoveList);
            this.composicaoPromocaoTORemoveList = new ArrayList<ComposicaoPromocaoTO>();
        }
        boolean dataPersited = promocaoFixaDelegate.persist(composicaoPromocaoTOList);
        if(dataPersited){
        	this.composicaoPromocaoTOList = promocaoFixaDelegate.findComposicaoByIdPromocao(Integer.valueOf(promocaoFixaForm.getIdPromocao()));
        }
        request.setAttribute("composicaoPromocaoTOList", this.composicaoPromocaoTOList);
        request.setAttribute("composicaoPromocaoTORemoveList", this.composicaoPromocaoTORemoveList);
        request.setAttribute("promocaoTO", this.promocaoTO);
        this.carregarCombos(request,response);
        if (this.composicaoPromocaoTORemoveList.size() != 0 || dataPersited || dataRemoved) {
            request.setAttribute("success", "Dados gravados com Sucesso");
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward carregarCombo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
        log.debug(promocaoFixaForm);
        if (promocaoFixaForm.getElement().equals("servicoList")) {
        	List<ServicoFixaTO> servicoFixaTOList = new PromocaoFixaDelegate().findServicoByTipoServico(Integer.valueOf(promocaoFixaForm.getId()));
            request.setAttribute("servicoFixaTOList", servicoFixaTOList);
            if(servicoFixaTOList.isEmpty()){
            	request.setAttribute("error", MENSAGEM_BUSCA_SERVICO);
            }
        } else if (promocaoFixaForm.getElement().equals("servicoDescontoList")) {
        	List<ServicoFixaTO> servicoDescontoTOList = new PromocaoFixaDelegate().findServicoDescontoByIdServico(Integer.valueOf(promocaoFixaForm.getId()));
        	request.setAttribute("servicoFixaDescontoTOList",servicoDescontoTOList);
        } else if (promocaoFixaForm.getElement().equals("solicitacaoComercialList")) {
        	List<SolicitacaoComercialFixaTO> solicitacaoComercialFixaTOList = new PromocaoFixaDelegate().findSolicitacaoComercialByIdServico(Integer.valueOf(promocaoFixaForm.getId()));
            request.setAttribute("solicitacaoComercialTOList",solicitacaoComercialFixaTOList);
        }
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward carregarDisponibilidade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
        log.debug(promocaoFixaForm);
        
        request.setAttribute("acaoCarregarAba", "carregarAba");
        
        PromocaoFixaDelegate promocaoFixaDelegate = new PromocaoFixaDelegate();
        List<DisponibilidadePromocaoFixaTO> disponibilidadePromocaoFixaTOList = promocaoFixaDelegate.obterCanalVendaPorPromocao(Integer.valueOf(promocaoFixaForm.getIdPromocao()));
        
        this.idCanalVendaTOList = new HashSet<Integer>();
        
        for(DisponibilidadePromocaoFixaTO disponibilidadePromocaoFixaTO : disponibilidadePromocaoFixaTOList){
        	this.idCanalVendaTOList.add(disponibilidadePromocaoFixaTO.getIdCanalVenda());
        }
        
        this.promocaoTO = promocaoFixaDelegate.findById(Integer.valueOf(promocaoFixaForm.getIdPromocao()));
        if (!StringUtils.isBlank(promocaoFixaForm.getDisabled())) {
            promocaoTO.setStatus(PromocaoTO.FINALIZADO);
        }
        this.agrupadorDisponibilidadeByEps = new AgrupadorDisponibilidadeByEps();
        this.agrupadorDisponibilidadeByEps.setDisponibilidadePromocaoFixaTOList(disponibilidadePromocaoFixaTOList);
        
        request.setAttribute("idEps2", "1");
        
        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward gravarDisponibilidade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist = new ArrayList<ValorPoliticaPromocaoTO>();
        List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove = new ArrayList<ValorPoliticaPromocaoTO>();
        PromocaoFixaDelegate promocaoFixaDelegate = new PromocaoFixaDelegate();
        Integer idPromocao = Integer.valueOf(promocaoFixaForm.getIdPromocao());

        request.setAttribute("acaoCarregar", "botaoCarregarFormulario");
        
		for (Iterator iter = this.agrupadorDisponibilidadeByEps.getDisponibilidadePromocaoFixaTOList().iterator(); iter.hasNext();) {
			DisponibilidadePromocaoFixaTO element = (DisponibilidadePromocaoFixaTO) iter.next();
            
			if(!StringUtils.isBlank(element.getIdCanalVenda().toString())) {
			   valorPoliticaPromocaoTOListPersist.add(new ValorPoliticaPromocaoTO(idPromocao, Long.valueOf(element.getIdCanalVenda())));
            }
      	
      	    if(element.getIdValorPoliticaPromocaoTO() != null && !StringUtils.isBlank(element.getIdValorPoliticaPromocaoTO().toString())){
      		   valorPoliticaPromocaoTOListRemove.add(new ValorPoliticaPromocaoTO(idPromocao, Long.valueOf(element.getIdCanalVenda()),element.getIdValorPoliticaPromocaoTO()));
      	    }            
		}

        for(Iterator iterr = this.agrupadorDisponibilidadeRemoveList.getDisponibilidadePromocaoFixaTOList().iterator(); iterr.hasNext();){
        	DisponibilidadePromocaoFixaTO elementRemove = (DisponibilidadePromocaoFixaTO) iterr.next();
        	boolean retorno = false;

        	for(Iterator iterrr = valorPoliticaPromocaoTOListRemove.iterator(); iterrr.hasNext();){
        		ValorPoliticaPromocaoTO element = (ValorPoliticaPromocaoTO) iterrr.next();

            	if(elementRemove.getIdCanalVenda() != null && element.getIdCanalVenda() != null 
            			&& elementRemove.getIdCanalVenda().intValue() == element.getIdCanalVenda().intValue()){
            		retorno = true;
            	}      	 	
        	}
        	
          if(!retorno){
      	    if(elementRemove.getIdValorPoliticaPromocaoTO() != null && !StringUtils.isBlank(elementRemove.getIdValorPoliticaPromocaoTO().toString())){
    		     valorPoliticaPromocaoTOListRemove.add(new ValorPoliticaPromocaoTO(idPromocao, Long.valueOf(elementRemove.getIdCanalVenda()),elementRemove.getIdValorPoliticaPromocaoTO()));
    	     }        	
          }
        }

        this.promocaoTO = promocaoFixaDelegate.findById(Integer.valueOf(promocaoFixaForm.getIdPromocao()));
        if (!StringUtils.isBlank(promocaoFixaForm.getDisabled())) {
            promocaoTO.setStatus(PromocaoTO.FINALIZADO);
        }

        promocaoFixaDelegate.persistDisponibilidade(valorPoliticaPromocaoTOListPersist, valorPoliticaPromocaoTOListRemove);
        
        this.carregarCombos(request,response);
        return this.carregarDisponibilidade(mapping,form,request,response);
    }
    
    public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug(promocaoFixaForm);
        if (!StringUtils.isBlank(promocaoFixaForm.getIdPromocao()) && StringUtils.isNumeric(promocaoFixaForm.getIdPromocao())) {
            Integer idPromocao = Integer.valueOf(promocaoFixaForm.getIdPromocao());
            new PromocaoFixaDelegate().remove(idPromocao);
        } else {
            log.debug(String.format("o valor informado nao corresponde a um idPromocao valido: %s", promocaoFixaForm.getIdPromocao()));
        }
        if (promocaoFixaForm.isClearSearch()) {
            return mapping.findForward(SUCCESS);
        }
        return this.search(mapping,form,request,response);
    }

    public ActionForward carregarFormularioCanalVenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acaoCarregarFormularioCanalVenda", "carregarFormularioCanalVenda");
        this.epsTOList = new PromocaoFixaDelegate().listEpsTO();

        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    }  
    
    public ActionForward pesquisarCanalVenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acaoPesquisarCanalVenda", "pesquisarCanalVenda");

        try {
            this.agrupadorDisponibilidadeByEps.setCanalVendaTOList(this.atualizarCanalVendaDisponibilidade(new PromocaoFixaDelegate().searchCanalVendaTO(promocaoFixaForm.buildSearchCanalVendaTO())));
            if (!this.agrupadorDisponibilidadeByEps.getCanalVendaTOList().isEmpty() && this.agrupadorDisponibilidadeByEps.getCanalVendaTOList().iterator().next().getEpsTO() != null) {
            	Integer i = this.agrupadorDisponibilidadeByEps.getCanalVendaTOList().iterator().next().getEpsTO().getIdEps();
                request.setAttribute("idEps2", this.agrupadorDisponibilidadeByEps.getCanalVendaTOList().iterator().next().getEpsTO().getIdEps());
            } else {
                request.setAttribute("idEps2", 0);
            }
        } catch (CatalogoPRSException e) {
            log.error(e.getMessage());
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    }
    
	private List<CanalVendaTO> atualizarCanalVendaDisponibilidade(final List<CanalVendaTO> canalVendaTOList) {
        final List<Integer> idCanalVendaListLoaded = new ArrayList<Integer>(this.agrupadorDisponibilidadeByEps.getDisponibilidadePromocaoFixaTOListSize());
        for (DisponibilidadePromocaoFixaTO cvTO : agrupadorDisponibilidadeByEps.getDisponibilidadePromocaoFixaTOList()) {
            idCanalVendaListLoaded.add(cvTO.getIdCanalVenda());
        }
        
		CollectionUtils.forAllDo(canalVendaTOList, new Closure() {
			public void execute(Object obj) {
                CanalVendaTO cvTO = (CanalVendaTO) obj;
                if (idCanalVendaListLoaded.contains(Integer.valueOf(cvTO.getIdCanalVenda().toString()))) { 
                	cvTO.setChecked("checked");
                } else {
                	cvTO.setChecked("");
                }
			}
		});
        return canalVendaTOList;
	}
    
    public ActionForward adicionarNovoCanalVenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug("init");
        request.setAttribute("acaoGravar", "gravarCanalVenda");
        request.setAttribute("acaoAdicionarCanal", "adicionarCanal");

        request.setAttribute("acaoCarregarFormularioCanalVenda", "carregarFormularioCanalVenda");
        request.setAttribute("acaoPesquisarCanalVenda", "pesquisarCanalVenda");
        this.agrupadorDisponibilidadeRemoveList = new AgrupadorDisponibilidadeByEps();
        this.agrupadorDisponibilidadeRemoveList.setDisponibilidadePromocaoFixaTOList(agrupadorDisponibilidadeByEps.getDisponibilidadePromocaoFixaTOList());
        this.agrupadorDisponibilidadeByEps.setDisponibilidadePromocaoFixaTOList(this.obterCanalVendaTOList(promocaoFixaForm));
        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    }
    
    private List<DisponibilidadePromocaoFixaTO> obterCanalVendaTOList(PromocaoFixaForm promocaoFixaForm) {

		Set<Integer> idCanalVendaList = promocaoFixaForm.obterIdCanalVendaListParaGravar();
		Set<Integer> idCanalVendaListRemove = promocaoFixaForm.obterIdCanalVendaListUnchecked();
		this.idCanalVendaTOList.addAll(idCanalVendaList);
		this.idCanalVendaTOList.removeAll(idCanalVendaListRemove);
        
		//recupera configuracao de disponibilidade das páginas diferentes da atual
        final Integer idEps = Integer.valueOf(promocaoFixaForm.getIdEps());
        List<DisponibilidadePromocaoFixaTO> disponibilidadePromocaoFixaTO = new ArrayList<DisponibilidadePromocaoFixaTO>(CollectionUtils.select(agrupadorDisponibilidadeByEps.getDisponibilidadePromocaoFixaTOList(), new Predicate() {
            public boolean evaluate(Object obj) {
            	DisponibilidadePromocaoFixaTO disponibilidadePromocao = (DisponibilidadePromocaoFixaTO)obj;
                if (idEps.equals(0)) {
                    return disponibilidadePromocao.getIdEps() != null;
                } else {
                    return disponibilidadePromocao.getIdEps() == null 
                    	|| !disponibilidadePromocao.getIdEps().equals(idEps);
                }
            }
        }));
        
        //constroi objetos DisponibilidadePromocaoFixaTO com base nos idsCanalVenda selecionados na pagina atual e os adiciona a lista de objetos configurados
        for (Integer idCanalVenda : this.idCanalVendaTOList) {
        	DisponibilidadePromocaoFixaTO disponibilidadePromocaoFixa = new DisponibilidadePromocaoFixaTO();
            boolean retorno = false;
            
        	for(Iterator iter = disponibilidadePromocaoFixaTO.iterator(); iter.hasNext();){
        		DisponibilidadePromocaoFixaTO element = (DisponibilidadePromocaoFixaTO) iter.next();
            	if(element.getIdCanalVenda() != null && idCanalVenda.intValue() == element.getIdCanalVenda().intValue()){
            		retorno = true;
            		break;
            	}      	 	
        	}
            
        	if(!retorno){
        		for (Iterator<CanalVendaTO> iter = this.agrupadorDisponibilidadeByEps.getCanalVendaTOList().iterator() ; iter.hasNext();) {
        			CanalVendaTO cv = iter.next();
					if(cv.getIdCanalVenda().intValue() == idCanalVenda.intValue()){
			              disponibilidadePromocaoFixa.setIdCanalVenda(cv.getIdCanalVenda().intValue());
			              disponibilidadePromocaoFixa.setNmCanalVenda(cv.getNmCanalVenda());
			              disponibilidadePromocaoFixa.setCdCanalVenda(Integer.parseInt(cv.getCdCanalVenda()));
			              if(cv.getEpsTO() != null){
				              disponibilidadePromocaoFixa.setIdEps(cv.getEpsTO().getIdEps());
				              disponibilidadePromocaoFixa.setNmEps(cv.getEpsTO().getNmEps());
			              }
			              for (DisponibilidadePromocaoFixaTO dpf : this.agrupadorDisponibilidadeByEps.getDisponibilidadePromocaoFixaTOList()){
			            	  if(dpf.getIdCanalVenda().intValue() == cv.getIdCanalVenda().intValue()){
			            		  disponibilidadePromocaoFixa.setIdValorPoliticaPromocaoTO(dpf.getIdValorPoliticaPromocaoTO());
			            		  break;
			            	  }
			              }
			        	  disponibilidadePromocaoFixaTO.add(disponibilidadePromocaoFixa);
			        	  break;
					}
				}
        	}
        }
        
        return disponibilidadePromocaoFixaTO;
    }
	
    public ActionForward orderDisponibilidade(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	PromocaoFixaForm promocaoFixaForm = (PromocaoFixaForm)form;
    	
        log.debug("init");
        
        Set<Integer> idCanalVendaList = promocaoFixaForm.obterIdCanalVendaListParaGravar();
        if(this.idCanalVendaTOList == null){
            this.idCanalVendaTOList = promocaoFixaForm.obterIdCanalVendaListParaGravar();
         }else{
            for(Integer idCanalVenda : idCanalVendaList) {
                this.idCanalVendaTOList.add(idCanalVenda);
                log.debug(idCanalVenda);
            }
        }
        request.setAttribute("element", promocaoFixaForm.getElement());
        request.setAttribute("acao", "orderDisponibilidade");
        this.carregarCombos(request,response);
        return mapping.findForward(SUCCESS);
    } 
    	
    private void carregarCombos(HttpServletRequest request,HttpServletResponse response) {
        
    	this.cleanHeader(response);
		
    	if (this.tipoServicoTOList == null) {
            this.tipoServicoTOList = new PromocaoFixaDelegate().findTipoServicoTO();
        }

        request.setAttribute("tipoServicoTOList", this.tipoServicoTOList);
        request.setAttribute("pesquisaCanalVendaTOList", this.agrupadorDisponibilidadeByEps.getCanalVendaTOList());
        request.setAttribute("epsTOList", epsTOList);
        request.setAttribute("agrupadorDisponibilidadeByEps", this.agrupadorDisponibilidadeByEps);
        request.setAttribute("promocaoTO", promocaoTO);
    }

    public PromocaoTO getPromocaoTO() {
        return promocaoTO;
    }

    public void setPromocaoTO(PromocaoTO promocaoTO) {
        this.promocaoTO = promocaoTO;
    }

}