package br.com.vivo.catalogoPRS.pageflows.param.servicofixa.relacionamento;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.ServicoFixaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoFixaForm;

import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class RelacionamentoServicoFixaAction extends BaseMappingDispatchAction implements Serializable {

	private static final long serialVersionUID = -6934146258826385368L;
	private static final String SUCCESS = "success";
	
    private List<RelacionamentoServicoFixaTO> relacionamentoServicoFixaTOList;
    private List<TipoRelacionamentoTO> tipoRelacionamentoTOList;
    private List<ServicoFixaTO> servicoFixaTOList;
    private List<TipoServicoTO> tipoServicoTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
        ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
        
        this.preencherComboPesquisa(request);

        if (request.getSession().getAttribute("idServico") != null) {
        	Integer idServicoSearch = new Integer( request.getSession().getAttribute("idServico").toString() );
            request.setAttribute("idServicoSearch", request.getSession().getAttribute("idServico"));
            servicoFixaForm.setIdSistemaSearch(new ServicoFixaDelegate().findIdSistemaByIdServico(idServicoSearch));
        }

        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
        ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
        this.preencherComboPesquisa(request);
        this.relacionamentoServicoFixaTOList = new ServicoFixaDelegate().searchRelationship(servicoFixaForm.buildRelacionamentoServicoFixaTO());
        this.filtrarComboTipRelacionamento();
        request.setAttribute("relacionamentoServicoFixaTOList", this.relacionamentoServicoFixaTOList);
        return mapping.findForward(SUCCESS);
    }
    
    private void filtrarComboTipRelacionamento() {
        String[] sgTipoRelacionamentoList = {
                TipoRelacionamentoTO.Sigla.AG.toString(),
                TipoRelacionamentoTO.Sigla.DE.toString(),
                TipoRelacionamentoTO.Sigla.DA.toString()
        };
        CollectionUtils.forAllDo(Arrays.asList(sgTipoRelacionamentoList), new Closure() {
            public void execute(Object obj) {
                final String sgTipoRelacionamento = (String) obj;
                if (CollectionUtils.exists(relacionamentoServicoFixaTOList, new Predicate() {
                    public boolean evaluate(Object obj) {
                        RelacionamentoServicoFixaTO to = (RelacionamentoServicoFixaTO) obj;
                        return to.getTipoRelacionamentoTO().getSgTipoRelacionamento().equals(sgTipoRelacionamento) && !to.getInCriacaoCatalogo();
                    }
                })) {
                    tipoRelacionamentoTOList.remove(new TipoRelacionamentoTO(sgTipoRelacionamento));
                }
            }
        });
    }

    public ActionForward changeStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
        ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
        this.preencherComboPesquisa(request);
        try {
            String novoStatus = new ServicoFixaDelegate().changeStatusRelationship(Integer.valueOf(servicoFixaForm.getIdRelacionamento()));
            if (novoStatus.equals("N")) {
                request.setAttribute("msg_success", "Configura&ccedil;&atilde;o desativada com sucesso");
            } else {
                request.setAttribute("msg_success", "Configura&ccedil;&atilde;o ativada com sucesso");
            }
        } catch (CatalogoPRSException e) {
            request.setAttribute("msg_error", e.getMessage());
            request.setAttribute("relacionamentoServicoFixaTOList", this.relacionamentoServicoFixaTOList);
            return mapping.findForward(SUCCESS);
        }
        return search(mapping,form,request,response);
    }    
    
    public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
        ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
        this.preencherComboPesquisa(request);
        request.setAttribute("relacionamentoServicoFixaTOList", this.relacionamentoServicoFixaTOList);
        try {
            new ServicoFixaDelegate().removeRelationship(Integer.valueOf(servicoFixaForm.getIdRelacionamento()));
            request.setAttribute("msg_success", "Relacionamento removido com sucesso");
        } catch (CatalogoPRSException e) {
            request.setAttribute("msg_error", e.getMessage());
        }
        return search(mapping,form,request,response);
    }
    
    public ActionForward searchServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
        ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
        this.preencherComboPesquisa(request);
        this.servicoFixaTOList = new ServicoFixaDelegate().search(servicoFixaForm.buildTOServicoFixa());
        request.setAttribute("relacionamentoServicoFixaTOList", this.relacionamentoServicoFixaTOList);
        request.setAttribute("mostrarBusca", "display:block");
        request.setAttribute("servicoFixaTOList", this.servicoFixaTOList);
        request.setAttribute("desabilitarIdTipoRelacionamento", "true");
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward gravarRelacionamento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
        ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
        this.preencherComboPesquisa(request);
        request.setAttribute("mostrarBusca", "display:none");
        new ServicoFixaDelegate().gravarRelacionamento(servicoFixaForm.buildTOListReccord());
        request.setAttribute("msg_success", "Relacionamento configurado com Sucesso");
        servicoFixaForm.clearSearchServicoData();
        return search(mapping,form,request,response);
    }
    
    private void preencherComboPesquisa(HttpServletRequest request){
        this.tipoRelacionamentoTOList = new ServicoFixaDelegate().findTipoRelacionamentoInsertFixa();
        if (this.relacionamentoServicoFixaTOList != null && this.relacionamentoServicoFixaTOList.size() > 0) {
            this.filtrarComboTipRelacionamento();
        }
        request.setAttribute("tipoRelacionamentoTOList", this.tipoRelacionamentoTOList);
        this.tipoServicoTOList = new ServicoFixaDelegate().findAllTpServico();
        request.setAttribute("tipoServicoTOList", this.tipoServicoTOList);
        request.setAttribute("mostrarBusca", "display:none");
        request.setAttribute("desabilitarIdTipoRelacionamento", "false");
    }
    
    public List<RelacionamentoServicoFixaTO> getRelacionamentoServicoFixaTOList(){
        return this.relacionamentoServicoFixaTOList;
    }
    
    public void setRelacionamentoServicoFixaTOList(List<RelacionamentoServicoFixaTO> relacionamentoServicoFixaTOList) {
        this.relacionamentoServicoFixaTOList = relacionamentoServicoFixaTOList;
    }

    public List<TipoRelacionamentoTO> getTipoRelacionamentoTOList() {
        return tipoRelacionamentoTOList;
    }

    public void setTipoRelacionamentoTOList(
            List<TipoRelacionamentoTO> tipoRelacionamentoTOList) {
        this.tipoRelacionamentoTOList = tipoRelacionamentoTOList;
    }

    public List<ServicoFixaTO> getServicoFixaTOList() {
        return servicoFixaTOList;
    }

    public void setServicoFixaTOList(List<ServicoFixaTO> servicoFixaTOList) {
        this.servicoFixaTOList = servicoFixaTOList;
    }

    public List<TipoServicoTO> getTipoServicoTOList() {
        return tipoServicoTOList;
    }

    public void setTipoServicoTOList(List<TipoServicoTO> tipoServicoTOList) {
        this.tipoServicoTOList = tipoServicoTOList;
    }
}
