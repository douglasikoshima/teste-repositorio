package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.produto;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;
import com.indracompany.catalogo.to.OfertaSAPTO;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;
import com.indracompany.catalogo.to.ProdutoTO;

import br.com.vivo.catalogoPRS.delegate.MatrizOfertaProdutoPrecoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.MatrizOfertaProdutoPrecoForm;
import edu.emory.mathcs.backport.java.util.Collections;

public class MatrizOfertaProdutoPrecoAction extends BaseMappingDispatchAction {
	
	private static final long serialVersionUID = 907411380383741835L;
	private static final String SUCCESS = "success";
	private static final String BLANK = "";
	private static final Integer ZERO = new Integer(0);
	private static final String CABECALHO_ARQUIVO_EXPORTACAO_CSV = "CODIGO DO PRODUTO;NOME DO PRODUTO;CANAL DISTRIBUICAO;OFERTA SAP;ORG. DE VENDA;DATA INICIAL;DATA FINAL;ACAO;VALOR";
	private static final String NOME_ARQUIVO_EXPORTACAO = "PrecosProdutoMatrizOfertaExport";
	private List<AcaoTO> acaoTOList;
	private List<CanalTO> canalDistribuicaoTOList;
	private List<CanalAtendimentoTO> canalAtendimentoTOList;
	private List<OfertaSAPTO> ofertaSAPTOList;
	private List<OrganizacaoVendaTO> organizacaoVendaTOList;
	private List<MatrizOfertaProdutoPrecoTO> matrizOfertaProdutoPrecoTOList;
		
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		MatrizOfertaProdutoPrecoForm formulario = (MatrizOfertaProdutoPrecoForm) form;
		this.setMatrizOfertaProdutoPrecoTOList(null);
		this.loadCombos(formulario, request);
		setRequestObjects(request);
		return mapping.findForward("success");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		MatrizOfertaProdutoPrecoForm formulario = (MatrizOfertaProdutoPrecoForm) form;
		if(formulario.getIdOfertaSAPList() != null && formulario.getIdOfertaSAPList().length > 0
			&& formulario.getIdOrganizacaoVendasList() != null && formulario.getIdOrganizacaoVendasList().length > 0){
			this.setMatrizOfertaProdutoPrecoTOList(new MatrizOfertaProdutoPrecoDelegate().search(doMatrizOfertaProdutoPrecoTO(formulario)));
		} else {
			request.setAttribute("labelError", "Favor preencher o(s) campo(s) obrigat&oacute;rio(s)");
		}
		
		setRequestObjects(request);
		return mapping.findForward("success");
	}
	
	public ActionForward removePrecoList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		MatrizOfertaProdutoPrecoForm formulario = (MatrizOfertaProdutoPrecoForm) form;
		List<Long> idPrecoList = new ArrayList<Long>();
		for(MatrizOfertaProdutoPrecoTO matrizOfertaProdutoPrecoTO : this.getMatrizOfertaProdutoPrecoTOList()){
			idPrecoList.add(matrizOfertaProdutoPrecoTO.getIdMatrizOfertaItemPreco());
		}
		if(idPrecoList.isEmpty()){
			setRequestObjects(request);
			return mapping.findForward("success");
		}
		new MatrizOfertaProdutoPrecoDelegate().removePrecoList(idPrecoList, this.getUserName());
		this.search(mapping,formulario, request, response);
		
		request.setAttribute("labelSucess", "Registro(s) removido(s) com sucesso");
		setRequestObjects(request);
		return mapping.findForward("success");
	}
	
	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		gerarArquivoExportacao(response);
		setRequestObjects(request);
		return mapping.findForward("success");
	}
	
    private void gerarArquivoExportacao(HttpServletResponse response){
        StringBuffer sb = new StringBuffer(CABECALHO_ARQUIVO_EXPORTACAO_CSV);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        for (MatrizOfertaProdutoPrecoTO to : this.getMatrizOfertaProdutoPrecoTOList()) {
            sb.append(String.format("\n%s;%s;%s;%s;%s;%s;%s;%s;%s;",
            	to.getProdutoTO().getCdProduto(),
            	to.getProdutoTO().getNmProduto(),
            	to.getCanalTO() != null ? to.getCanalTO().getSgCanal() : null,
            	to.getOfertaSAPTO().getCdOfertaSAP(),
            	to.getOrganizacaoVendaTO().getSgOrganizacaoVendas(),
            	simpleDateFormat.format(new Date(to.getDtInicial().getTimeInMillis())),
            	simpleDateFormat.format(new Date(to.getDtFinal().getTimeInMillis())),
            	to.getAcaoTO() != null ? to.getAcaoTO().getSgAcao() : null,
            	to.getValor()
            ));
        }
        String arquivo = sb.toString().replaceAll(";null", ";");
        
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.csv", NOME_ARQUIVO_EXPORTACAO));
        response.setContentLength(arquivo.getBytes().length);
        try {
            OutputStream out = response.getOutputStream();
            out.write(arquivo.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private MatrizOfertaProdutoPrecoTO doMatrizOfertaProdutoPrecoTO(MatrizOfertaProdutoPrecoForm formBean){
		MatrizOfertaProdutoPrecoTO to = new MatrizOfertaProdutoPrecoTO();
		
		to.setProdutoTO(new ProdutoTO());
		to.getProdutoTO().setNmProduto(formBean.getNmProduto().equals(BLANK) ? null : formBean.getNmProduto());
		to.getProdutoTO().setCdProduto(formBean.getCdProduto().equals(BLANK) ? null : formBean.getCdProduto());
		to.setCanalAtendimentoTO(formBean.getIdCanalAtendimento() != null && !formBean.getIdCanalAtendimento().equals(ZERO) ? new CanalAtendimentoTO(formBean.getIdCanalAtendimento()) : new CanalAtendimentoTO());
		to.setIdOfertaSAPListParam(new ArrayList<Integer>());
		for(Integer idOfertaSAP : formBean.getIdOfertaSAPList()){
			to.getIdOfertaSAPListParam().add(idOfertaSAP);
		}
		to.setIdOrganizacaoVendasListParam(new ArrayList<Integer>());
		for(Integer idOrganizacaoVendas : formBean.getIdOrganizacaoVendasList()){
			to.getIdOrganizacaoVendasListParam().add(idOrganizacaoVendas);
		}
		
		return to;
	}
	
	private void loadCombos(MatrizOfertaProdutoPrecoForm formBean, HttpServletRequest request){
		MatrizOfertaProdutoPrecoDelegate matrizOfertaProdutoPrecoDelegate = new MatrizOfertaProdutoPrecoDelegate();
		
		this.setAcaoTOList(matrizOfertaProdutoPrecoDelegate.findAllAcaoTO());
		this.setCanalDistribuicaoTOList(matrizOfertaProdutoPrecoDelegate.findAllCanalTO());
		List<CanalAtendimentoTO> canalAtendimentoAcessivelTOList = (List<CanalAtendimentoTO>) request.getSession().getAttribute("canalAtendimentoList");
		Collections.sort(canalAtendimentoAcessivelTOList);
		if(canalAtendimentoAcessivelTOList == null)
			canalAtendimentoAcessivelTOList = new ArrayList<CanalAtendimentoTO>();
		this.setCanalAtendimentoTOList(canalAtendimentoAcessivelTOList);
		
		List<OfertaSAPTO> ofertaSapTOList = matrizOfertaProdutoPrecoDelegate.findAllOfertaSAPTO();
		Collections.sort(ofertaSapTOList);
		this.setOfertaSAPTOList(ofertaSapTOList);
		
		List<OrganizacaoVendaTO> organizacaoVendaTOAcessivelList = new ArrayList<OrganizacaoVendaTO>();
		List<String> sgOrganizacaoVendasAcessivelList = (ArrayList<String>) request.getSession().getAttribute("LISTA_SIGLA_ORGANIZACAO_VENDAS");
		if(sgOrganizacaoVendasAcessivelList == null)
			sgOrganizacaoVendasAcessivelList = new ArrayList<String>();
		
		for(OrganizacaoVendaTO organizacaoVendaTO : matrizOfertaProdutoPrecoDelegate.findAllOrganizacaoVendaTO()){
			for(String sgOrgVendasAcessivel : sgOrganizacaoVendasAcessivelList){
				if(organizacaoVendaTO.getSgOrganizacaoVendas() != null && organizacaoVendaTO.getSgOrganizacaoVendas().equals(sgOrgVendasAcessivel)){
					organizacaoVendaTOAcessivelList.add(organizacaoVendaTO);
				}
			}
		}
		this.setOrganizacaoVendaTOList(organizacaoVendaTOAcessivelList);
	}
	
	private void setRequestObjects(HttpServletRequest request){
		request.setAttribute("acaoTOList", this.getAcaoTOList());
		request.setAttribute("canalDistribuicaoTOList", this.getCanalDistribuicaoTOList());
		request.setAttribute("canalAtendimentoTOList", this.getCanalAtendimentoTOList());
		request.setAttribute("ofertaSAPTOList", this.getOfertaSAPTOList());
		request.setAttribute("organizacaoVendaTOList", this.getOrganizacaoVendaTOList());
		if(this.getMatrizOfertaProdutoPrecoTOList() != null){
			Integer qtPrecos = this.getMatrizOfertaProdutoPrecoTOList().size();
			if(qtPrecos > 0)
				request.setAttribute("mensagemResultadoSearch", String.format("%s%s%s","Foram encontrados ",qtPrecos," registros"));
			else
				request.setAttribute("mensagemResultadoSearch", String.format("%s","N&atilde;o foram encontrado(s) registro(s)"));
		}
	}
	
	
	public List<AcaoTO> getAcaoTOList() {
		return acaoTOList;
	}

	public void setAcaoTOList(List<AcaoTO> acaoTOList) {
		this.acaoTOList = acaoTOList;
	}

	public List<CanalAtendimentoTO> getCanalAtendimentoTOList() {
		return canalAtendimentoTOList;
	}

	public void setCanalAtendimentoTOList(
			List<CanalAtendimentoTO> canalAtendimentoTOList) {
		this.canalAtendimentoTOList = canalAtendimentoTOList;
	}

	public List<CanalTO> getCanalDistribuicaoTOList() {
		return canalDistribuicaoTOList;
	}

	public void setCanalDistribuicaoTOList(List<CanalTO> canalDistribuicaoTOList) {
		this.canalDistribuicaoTOList = canalDistribuicaoTOList;
	}

	public List<OfertaSAPTO> getOfertaSAPTOList() {
		return ofertaSAPTOList;
	}

	public void setOfertaSAPTOList(List<OfertaSAPTO> ofertaSAPTOList) {
		this.ofertaSAPTOList = ofertaSAPTOList;
	}

	public List<OrganizacaoVendaTO> getOrganizacaoVendaTOList() {
		return organizacaoVendaTOList;
	}

	public void setOrganizacaoVendaTOList(
			List<OrganizacaoVendaTO> organizacaoVendaTOList) {
		this.organizacaoVendaTOList = organizacaoVendaTOList;
	}

	public List<MatrizOfertaProdutoPrecoTO> getMatrizOfertaProdutoPrecoTOList() {
		return matrizOfertaProdutoPrecoTOList;
	}

	public void setMatrizOfertaProdutoPrecoTOList(
			List<MatrizOfertaProdutoPrecoTO> matrizOfertaProdutoPrecoTOList) {
		this.matrizOfertaProdutoPrecoTOList = matrizOfertaProdutoPrecoTOList;
	}
}
