package br.com.vivo.catalogoPRS.pageflows.param.analisecreditopriorizar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.AnaliseCreditoDelegate;
import br.com.vivo.catalogoPRS.delegate.AnaliseCreditoPriorizarDelegate;
import br.com.vivo.catalogoPRS.delegate.DisponibilidadeDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.AnaliseCreditoPriorisarForm;

import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.AnalisePriorizarTO;
import com.indracompany.catalogo.to.EpsTO;

public class AnaliseCreditoPriorizarAction extends BaseMappingDispatchAction implements Serializable{

    private static final long serialVersionUID = -8163401499800593405L;
    private static Logger log = Logger.getLogger(AnaliseCreditoPriorizarAction.class);
    
    private static final String SUCCESS = "success";
    private static final String MENSAGEM_PARAMETROS_OBRIGATORIOS = "Favor preencher os par&acirc;metros obrigat&oacute;rios";
    private static final String MENSAGEM_GRAVADO_SUCESSO = "Configura&ccedil;&atilde;o gravada com sucesso!";
    private List<AnaliseCreditoTO> analiseCreditoTOList;
    private List<AnalisePriorizarTO>  analisePriorizarTOList;
    private List<EpsTO> epsTOList;

    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {
        this.carregarComboEstatico(request);
        this.carregarComboEstaticoAgrupador(request);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward pesquisarOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	AnaliseCreditoPriorisarForm analiseCreditoPriorisarForm = (AnaliseCreditoPriorisarForm)form;   
    	
        try {
            this.carregarComboEstatico(request);
            this.carregarComboEstaticoAgrupador(request);
            this.validateForm(analiseCreditoPriorisarForm);
            final Integer idEps = Integer.valueOf(analiseCreditoPriorisarForm.getIdEps());           
            final Integer idAnaliseCredito = Integer.valueOf(analiseCreditoPriorisarForm.getIdAnaliseCredito());
            
            AnaliseCreditoTO analiseCreditoTO = (AnaliseCreditoTO) CollectionUtils.find(this.analiseCreditoTOList, new Predicate() {
                public boolean evaluate(Object obj) {
                    return ((AnaliseCreditoTO)obj).getIdAnaliseCredito().equals(idAnaliseCredito);
                }
            }); 
            
            EpsTO epsTO = (EpsTO) CollectionUtils.find(this.epsTOList, new Predicate() {
                public boolean evaluate(Object obj) {
                    return ((EpsTO)obj).getIdEps().equals(idEps);
                }
            });            

            request.setAttribute("epsTO", epsTO);
            request.setAttribute("analiseCreditoTO", analiseCreditoTO);
            
            analisePriorizarTOList = new AnaliseCreditoPriorizarDelegate().pesquisarOferta(idAnaliseCredito, idEps);
            request.setAttribute("analisePriorizarTOList", analisePriorizarTOList);
        } catch (CatalogoPRSException e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward gravarPriorizacao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	AnaliseCreditoPriorisarForm analiseCreditoPriorisarForm = (AnaliseCreditoPriorisarForm)form;

        try {

        	final Integer idEPS = Integer.valueOf(analiseCreditoPriorisarForm.getIdEps());
        	
            List<Integer> idOfertafixaCreditoScoreList = this.extrairIdList(analiseCreditoPriorisarForm.getIdOfertafixaCreditoScoreSelected());
            Integer idAnaliseCredito = Integer.valueOf(analiseCreditoPriorisarForm.getIdAnaliseCredito());     
            
            
            
            ArrayList<AnalisePriorizarTO> analisePriTOList = new ArrayList<AnalisePriorizarTO>();
            ArrayList<AnalisePriorizarTO> analisePriTOListRemove = new ArrayList<AnalisePriorizarTO>();
            
            for (int i = 0; i < analisePriorizarTOList.size(); i++) {         		
        		if (analisePriorizarTOList.get(i).getCdPrioridade() != null ) {
        			analisePriTOList.add(analisePriorizarTOList.get(i));
        			analisePriTOListRemove.add(analisePriorizarTOList.get(i));
        		}            		            	         	
            }
            
            if (!idOfertafixaCreditoScoreList.isEmpty()) {
            	if (!analisePriTOList.isEmpty()) {
                	for (int h = 0; h < analisePriTOList.size(); h++) {
                		for (int k = 0; k < idOfertafixaCreditoScoreList.size(); k++) {
                			if (analisePriTOList.get(h).getIdOfertafixaCreditoScore().equals(idOfertafixaCreditoScoreList.get(k))){                			
                				analisePriTOListRemove.remove(analisePriTOList.get(h));
                			}
                		}
                	}            		            		
            	}
            }

            new AnaliseCreditoPriorizarDelegate().gravarPriorizacao(idAnaliseCredito, idOfertafixaCreditoScoreList, idEPS, analisePriTOListRemove);
            request.setAttribute("type_msg", "success");
            request.setAttribute("msg", MENSAGEM_GRAVADO_SUCESSO);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            request.setAttribute("type_msg", "error");
            request.setAttribute("msg", e.getMessage());
        }
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    private List<Integer> extrairIdList(String string) {
        List<Integer> idList = new ArrayList<Integer>();
        if (!StringUtils.isBlank(string)) {
            for(String str : string.split("\\|")) {
                idList.add(Integer.valueOf(str));
            }
        }
        return idList;
    }

    private void validateForm(AnaliseCreditoPriorisarForm form) throws CatalogoPRSException {
        if (!NumberUtils.isNumber(form.getIdAnaliseCredito()) || !NumberUtils.isNumber(form.getIdEps()) ) {
            throw new CatalogoPRSException(MENSAGEM_PARAMETROS_OBRIGATORIOS);
        }
    }

    private void carregarComboEstatico(HttpServletRequest request) {
        if (this.analiseCreditoTOList == null) {
            this.analiseCreditoTOList = new AnaliseCreditoDelegate().findAll();
        }
        request.setAttribute("analiseCreditoTOList", this.analiseCreditoTOList);        
    }

    private void carregarComboEstaticoAgrupador(HttpServletRequest request) throws CatalogoPRSException {
        if (this.epsTOList == null) {
            this.epsTOList = new DisponibilidadeDelegate().listEpsTO();
        }
        request.setAttribute("epsTOList", this.epsTOList);    	
    }

}
