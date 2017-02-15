package br.com.vivo.catalogoPRS.pageflows.param.gerenciadorregras.documentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.GerenciadorRegrasConfiguracaoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.GerenciadorRegrasAbaFiltroForm;

import com.indracompany.catalogo.to.IndicadorComercialDocumentoTO;
import com.indracompany.catalogo.to.TipoDocumentoTO;

public class GerenciadorRegrasConfiguracaoDocumentosAction extends BaseMappingDispatchAction implements Serializable{

	private static final long serialVersionUID = 8095816128686672544L;
	private static final String SUCCESS = "success";
	private static final String MENSAGEM_SUCESSO_DOCUMENTO_SALVO = "Documentos salvos com sucesso.";
	
	private List<IndicadorComercialDocumentoTO> indicadorComercialDocumentoTOList;
	private List<TipoDocumentoTO> tipoDocumentoTOList;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		GerenciadorRegrasConfiguracaoDelegate delegate = new GerenciadorRegrasConfiguracaoDelegate();
		
		this.setIndicadorComercialDocumentoTOList(new GerenciadorRegrasConfiguracaoDelegate().buscarTodosIndicadoresDocumento());
		this.setTipoDocumentoTOList(delegate.buscarTodosTiposDocumento());
		
		loadRequestObjects(request);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		GerenciadorRegrasAbaFiltroForm formBean = (GerenciadorRegrasAbaFiltroForm)form;
		
		GerenciadorRegrasConfiguracaoDelegate delegate = new GerenciadorRegrasConfiguracaoDelegate();
		delegate.salvar(doIndicadorComercialDocumentoTO(formBean));
		IndicadorComercialDocumentoTO indicadorComercialDocumentoTO = delegate.buscarTipoDocumentoPorIndicador(doIndicadorComercialDocumentoTO(formBean));
		formBean.setIdTipoDocumentoCheckList(doIdTipoDocumentoCheckList(indicadorComercialDocumentoTO));
		
		request.setAttribute("labelSucess", MENSAGEM_SUCESSO_DOCUMENTO_SALVO);
		
		loadRequestObjects(request);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		GerenciadorRegrasAbaFiltroForm formBean = (GerenciadorRegrasAbaFiltroForm)form;

		GerenciadorRegrasConfiguracaoDelegate delegate = new GerenciadorRegrasConfiguracaoDelegate();
		IndicadorComercialDocumentoTO indicadorComercialDocumentoTO = delegate.buscarTipoDocumentoPorIndicador(doIndicadorComercialDocumentoTO(formBean));
		formBean.setIdTipoDocumentoCheckList(doIdTipoDocumentoCheckList(indicadorComercialDocumentoTO));
		
		loadRequestObjects(request);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	private IndicadorComercialDocumentoTO doIndicadorComercialDocumentoTO(GerenciadorRegrasAbaFiltroForm formBean){
		IndicadorComercialDocumentoTO to = new IndicadorComercialDocumentoTO();
		
		to.setIdIndicadorComercial(formBean.getIdIndicadorComercial());
		if(formBean.getIdTipoDocumentoCheckList() != null){
			List<TipoDocumentoTO> tipoDocumentoTOList = new ArrayList<TipoDocumentoTO>();
			for(Long idTipoDocumento : formBean.getIdTipoDocumentoCheckList()){
				TipoDocumentoTO tipoDocumentoTO = new TipoDocumentoTO(idTipoDocumento);
				tipoDocumentoTOList.add(tipoDocumentoTO);
			}
			to.setTipoDocumentoTOList(tipoDocumentoTOList);
		}
		return to;
	}
	
	private Long[] doIdTipoDocumentoCheckList(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO){
		Long[] result = new Long[indicadorComercialDocumentoTO.getTipoDocumentoTOList().size()];
		for(int i = 0; i < indicadorComercialDocumentoTO.getTipoDocumentoTOList().size(); i++ ){
			result[i] = indicadorComercialDocumentoTO.getTipoDocumentoTOList().get(i).getIdTipoDocumento(); 
		}
		return result;
	}
	
	private void loadRequestObjects(HttpServletRequest request){
		request.setAttribute("indicadorComercialDocumentoTOList", this.getIndicadorComercialDocumentoTOList());
		request.setAttribute("tipoDocumentoTOList", this.getTipoDocumentoTOList());
	}

	public List<IndicadorComercialDocumentoTO> getIndicadorComercialDocumentoTOList() {
		return indicadorComercialDocumentoTOList;
	}

	public void setIndicadorComercialDocumentoTOList(
			List<IndicadorComercialDocumentoTO> indicadorComercialDocumentoTOList) {
		this.indicadorComercialDocumentoTOList = indicadorComercialDocumentoTOList;
	}

	public List<TipoDocumentoTO> getTipoDocumentoTOList() {
		return tipoDocumentoTOList;
	}

	public void setTipoDocumentoTOList(List<TipoDocumentoTO> tipoDocumentoTOList) {
		this.tipoDocumentoTOList = tipoDocumentoTOList;
	}
}
