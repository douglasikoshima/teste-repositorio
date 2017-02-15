package br.com.vivo.catalogoPRS.pageflows.param.canalvendaagrupador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;

import com.indracompany.catalogo.to.EpsTO;

public class CanalVendaAgrupadorAction extends BaseMappingDispatchAction implements Serializable {
	
	private static final long serialVersionUID = 8115207103624049929L;
    private static final String CANALVENDA = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/CanalVendaAction.do";
    private static final String AGRUPADOR = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/AgrupadorAction.do";
    private String SUCCESS = "success";
	
	private List<EpsTO> epsTOList = new ArrayList<EpsTO>(); 
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
        request.setAttribute("canalvenda", CANALVENDA);
        request.setAttribute("agrupador", AGRUPADOR);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public List<EpsTO> getEpsTOList() {
		return epsTOList;
	}

	public void setEpsTOList(List<EpsTO> epsTOList) {
		this.epsTOList = epsTOList;
	}
}
