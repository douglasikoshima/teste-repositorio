package br.com.vivo.catalogoPRS.pageflows.param.produtos.descontos;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.DescontosForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalRequest;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalResponse;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.ResultadoListaCanalCanal;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarFormaPagtoCanalParamRequest;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarParamDescontoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.BuscarListaParamDescontoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.BuscarListaParamDescontoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.DescontoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarFormaPagtoCanalParam;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDesconto;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosBuscarListaParamDesconto;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDesconto;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaParcelasParcelas;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaPagamentoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaPagamentoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.FormaPagamentoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ResultadoBuscarListaFormaCondPagtoFormaPagamento;
import edu.emory.mathcs.backport.java.util.Arrays;

public class DescontosAction extends BaseMappingDispatchAction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String SUCCESS = "success";
	private String DEFAULT = "default";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DescontosForm descontosForm = (DescontosForm)form;

		CanalPortTypeProxy canalPortTypeProxy = new CanalPortTypeProxy();
		BuscarListaCanalRequest buscarListaCanalRequest = new BuscarListaCanalRequest();
		BuscarListaCanalResponse buscarListaCanalResponse = null;
 
		FormaPagamentoPortTypeProxy formaPagamentoPortTypeProxy = new FormaPagamentoPortTypeProxy();
		BuscarListaFormaPagamentoRequest buscarListaFormaPagamentoRequest = new BuscarListaFormaPagamentoRequest();
		BuscarListaFormaPagamentoResponse buscarListaFormaPagamentoResponse = null;
		
		try {
			buscarListaCanalResponse = canalPortTypeProxy.buscarListaCanal(buscarListaCanalRequest, this.getUserName(), this.getPassword());
			buscarListaFormaPagamentoResponse = formaPagamentoPortTypeProxy.buscarListaFormaPagamento(buscarListaFormaPagamentoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, DescontosAction.class.getName(), ex.getMessage(), descontosForm, response );
			return null;
		}		

		List<ResultadoListaCanalCanal> canalList = Arrays.asList(buscarListaCanalResponse.getResultadoListaCanal());
		request.setAttribute("canais", canalList);
		
		List<ResultadoBuscarListaFormaCondPagtoFormaPagamento> formaPagamentoList = Arrays.asList(buscarListaFormaPagamentoResponse.getResultadoBuscarListaFormaCondPagto());
		request.setAttribute("formasPagamento", formaPagamentoList);

		this.cleanHeader(response);
		return mapping.findForward(DEFAULT);

	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DescontosForm descontosForm = (DescontosForm)form;

		Long idFormaPagamentoCartaoCredito = ApplicationConfiguration.getIdFormaPagamentoCartaoCredito();

		request.setAttribute("id_forma_pagamento", descontosForm.getIdFormaPagamento());
		request.setAttribute("id_canal", descontosForm.getIdCanal());
		request.setAttribute("id_forma_pagamento_cartao_credito", idFormaPagamentoCartaoCredito);

		BuscarListaParamDescontoRequest buscarListaParamDescontoRequest = new BuscarListaParamDescontoRequest();
		BuscarListaParamDescontoResponse buscarListaParamDescontoResponse = null;
		ParametrosBuscarListaParamDesconto parametrosBuscarListaParamDesconto = new ParametrosBuscarListaParamDesconto();
		DescontoPortTypeProxy descontoPortTypeProxy = new DescontoPortTypeProxy();
		
		parametrosBuscarListaParamDesconto.setIdCanal(descontosForm.getIdCanal());
		parametrosBuscarListaParamDesconto.setIdFormaPagamento(descontosForm.getIdFormaPagamento());
		
		buscarListaParamDescontoRequest.setParametrosBuscarListaParamDesconto(parametrosBuscarListaParamDesconto);
		
		try {
			buscarListaParamDescontoResponse = descontoPortTypeProxy.buscarListaParamDesconto(buscarListaParamDescontoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, DescontosAction.class.getName(), ex.getMessage(), descontosForm, response );
			return null;
		}	

		ResultadoBuscarListaParamDesconto listaParamDesconto = buscarListaParamDescontoResponse.getResultadoBuscarListaParamDesconto();
		if(listaParamDesconto != null){
			
			if(listaParamDesconto.getDesconto() !=null){
				List<ResultadoBuscarListaParamDescontoDesconto> descontoList = Arrays.asList(listaParamDesconto.getDesconto());
				request.setAttribute("dadosCanalFormaPagamento", descontoList);
				descontosForm.setNumMaxParcelas(Integer.parseInt(descontoList.get(0).getNrParcelasMax().toString()));				
			}
			
			if (listaParamDesconto.getListaParcelas() != null) {
				List<ResultadoBuscarListaParamDescontoListaParcelasParcelas> parcelasList = Arrays.asList(listaParamDesconto.getListaParcelas());
				//request.setAttribute("parcelas", parcelasList);
				descontosForm.setParcelasList(parcelasList);
			}
	
			if (listaParamDesconto.getListaCondicaoPagamento() != null) {
				List<ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento> condicaoPagamentoList = Arrays.asList(listaParamDesconto.getListaCondicaoPagamento());
				//request.setAttribute("condicoesPagamento", condicaoPagamentoList);
				descontosForm.setCondicaoPagamentoList(condicaoPagamentoList);
			}
		}
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward calcularDesconto(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DescontosForm descontosForm = (DescontosForm)form;

		request.setAttribute("id_forma_pagamento", descontosForm.getIdFormaPagamento());
		request.setAttribute("id_canal", descontosForm.getIdCanal());
		request.setAttribute("id_forma_pagamento_cartao_credito", ApplicationConfiguration.getIdFormaPagamentoCartaoCredito());
		request.setAttribute("num_max_parcelas", descontosForm.getNumMaxParcelas());
		request.setAttribute("taxa_juros", descontosForm.getTaxaJuros());
		request.setAttribute("num_parcelas_sem_juros", descontosForm.getNumParcelasSemJuros());
		request.setAttribute("ids_condicao_pagamento", descontosForm.getIdsCondicaoPagamento());

		descontosForm.setIdsCondicaoPagamento(null);

		Double taxaJuros = Double.parseDouble(descontosForm.getTaxaJuros().replaceAll(",", "\\."));
		
		List<Double> descontos = calcularDescontos(descontosForm.getNumMaxParcelas(), taxaJuros, descontosForm.getNumParcelasSemJuros());
		request.setAttribute("descontos", descontos);
		
		descontosForm.setDescontosList(descontos);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward gravarDadosCanalFormaPagamento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DescontosForm descontosForm = (DescontosForm)form;

		AlterarFormaPagtoCanalParamRequest alterarFormaPagtoCanalParamRequest = new AlterarFormaPagtoCanalParamRequest();
		ParametrosAlterarFormaPagtoCanalParam parametrosAlterarFormaPagtoCanalParam = new ParametrosAlterarFormaPagtoCanalParam();
		DescontoPortTypeProxy descontoPortTypeProxy = new DescontoPortTypeProxy();
		
		parametrosAlterarFormaPagtoCanalParam.setIdFormaPagamento(descontosForm.getIdFormaPagamento());
		parametrosAlterarFormaPagtoCanalParam.setNrParcelasMax(descontosForm.getNumMaxParcelas());
		parametrosAlterarFormaPagtoCanalParam.setIdCanal(descontosForm.getIdCanal());
		alterarFormaPagtoCanalParamRequest.setParametrosAlterarFormaPagtoCanalParam(parametrosAlterarFormaPagtoCanalParam);
		
		AlterarParamDescontoRequest alterarParamDescontoRequest = new AlterarParamDescontoRequest();
		ParametrosAlterarParamDesconto parametrosAlterarParamDesconto =new ParametrosAlterarParamDesconto();
		
		parametrosAlterarParamDesconto.setIdCanal(descontosForm.getIdCanal());
		parametrosAlterarParamDesconto.setIdFormaPagamento(descontosForm.getIdFormaPagamento());
		alterarParamDescontoRequest.setParametrosAlterarParamDesconto(parametrosAlterarParamDesconto);

		try {
			descontoPortTypeProxy.alterarFormaPagtoCanalParam(alterarFormaPagtoCanalParamRequest, this.getUserName(), this.getPassword());
			descontoPortTypeProxy.alterarParamDesconto(alterarParamDescontoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, DescontosAction.class.getName(), ex.getMessage(), descontosForm, response );
			return null;
		}			

		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('select_fpagamento').onchange();");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}

	public ActionForward gravarDadosCanalCartaoCredito(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DescontosForm descontosForm = (DescontosForm)form;

		AlterarFormaPagtoCanalParamRequest alterarFormaPagtoCanalParamRequest = new AlterarFormaPagtoCanalParamRequest();
		ParametrosAlterarFormaPagtoCanalParam parametrosAlterarFormaPagtoCanalParam = new ParametrosAlterarFormaPagtoCanalParam();
		DescontoPortTypeProxy descontoPortTypeProxy = new DescontoPortTypeProxy();
		
		parametrosAlterarFormaPagtoCanalParam.setIdFormaPagamento(descontosForm.getIdFormaPagamento());
		parametrosAlterarFormaPagtoCanalParam.setNrMaxParcSemJuros(Long.parseLong(descontosForm.getNumParcelasSemJuros().toString()));
		parametrosAlterarFormaPagtoCanalParam.setNrParcelasMax(descontosForm.getNumMaxParcelas());
		parametrosAlterarFormaPagtoCanalParam.setTaxaJuros(BigDecimal.valueOf(Double.parseDouble(descontosForm.getTaxaJuros().replaceAll(",", "\\."))));
		parametrosAlterarFormaPagtoCanalParam.setIdCanal(descontosForm.getIdCanal());

		alterarFormaPagtoCanalParamRequest.setParametrosAlterarFormaPagtoCanalParam(parametrosAlterarFormaPagtoCanalParam);
		
		AlterarParamDescontoRequest alterarParamDescontoRequest = new AlterarParamDescontoRequest();
		ParametrosAlterarParamDesconto parametrosAlterarParamDesconto = new ParametrosAlterarParamDesconto();
		ParametrosAlterarParamDescontoDescontoCondPagto[] descontoCondPagto = new ParametrosAlterarParamDescontoDescontoCondPagto[descontosForm.getIdsCondicaoPagamento().length];
		
		parametrosAlterarParamDesconto.setIdCanal(descontosForm.getIdCanal());
		parametrosAlterarParamDesconto.setIdFormaPagamento(descontosForm.getIdFormaPagamento());

		for (int i = 0; i < descontosForm.getDescontos().length; i++) {
			Double desconto = descontosForm.getDescontos()[i];
			if (i < descontosForm.getIdsCondicaoPagamento().length && descontosForm.getIdsCondicaoPagamento()[i] != null) {
				ParametrosAlterarParamDescontoDescontoCondPagto parametrosAlterarParamDescontoDescontoCondPagto = new ParametrosAlterarParamDescontoDescontoCondPagto();
				parametrosAlterarParamDescontoDescontoCondPagto.setFatorPreco(1-desconto.floatValue());
				parametrosAlterarParamDescontoDescontoCondPagto.setIdCondicaoPagamento(descontosForm.getIdsCondicaoPagamento()[i]);
				descontoCondPagto[i] = parametrosAlterarParamDescontoDescontoCondPagto;
			}

		}

		parametrosAlterarParamDesconto.setDescontoCondPagto(descontoCondPagto);
		alterarParamDescontoRequest.setParametrosAlterarParamDesconto(parametrosAlterarParamDesconto);		
		
		try {
			descontoPortTypeProxy.alterarFormaPagtoCanalParam(alterarFormaPagtoCanalParamRequest, this.getUserName(), this.getPassword());
			descontoPortTypeProxy.alterarParamDesconto(alterarParamDescontoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, DescontosAction.class.getName(), ex.getMessage(), descontosForm, response );
			return null;
		}

		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('select_fpagamento').onchange();");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}

	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DescontosForm descontosForm = (DescontosForm)form;

		StringBuffer sb = new StringBuffer();
		sb.append("Canal;Forma de pagamento;Numero máximo de parcelas;Taxa de juros mensal;Numero de parcela sem juros\n\n");

		BuscarListaParamDescontoRequest buscarListaParamDescontoRequest = new BuscarListaParamDescontoRequest();
		BuscarListaParamDescontoResponse buscarListaParamDescontoResponse = null;
		ParametrosBuscarListaParamDesconto parametrosBuscarListaParamDesconto = new ParametrosBuscarListaParamDesconto();
		DescontoPortTypeProxy descontoPortTypeProxy = new DescontoPortTypeProxy();
		
		parametrosBuscarListaParamDesconto.setIdCanal(35);
		parametrosBuscarListaParamDesconto.setIdFormaPagamento(7);
		
		buscarListaParamDescontoRequest.setParametrosBuscarListaParamDesconto(parametrosBuscarListaParamDesconto);
		
		try {
			buscarListaParamDescontoResponse = descontoPortTypeProxy.buscarListaParamDesconto(buscarListaParamDescontoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, DescontosAction.class.getName(), ex.getMessage(), descontosForm, response );
			return null;
		}
		
		ResultadoBuscarListaParamDesconto resultadoBuscarListaParamDesconto = buscarListaParamDescontoResponse.getResultadoBuscarListaParamDesconto();
		sb.append("LOJA PROPRIA;FINANCEIRA;");
		sb.append(resultadoBuscarListaParamDesconto.getDesconto(0).getNrParcelasMax());
		sb.append(";;\n");

		buscarListaParamDescontoRequest = new BuscarListaParamDescontoRequest();
		parametrosBuscarListaParamDesconto = new ParametrosBuscarListaParamDesconto();
		parametrosBuscarListaParamDesconto.setIdCanal(35);
		parametrosBuscarListaParamDesconto.setIdFormaPagamento(3);
		
		buscarListaParamDescontoRequest.setParametrosBuscarListaParamDesconto(parametrosBuscarListaParamDesconto);
		
		try {
			buscarListaParamDescontoResponse = descontoPortTypeProxy.buscarListaParamDesconto(buscarListaParamDescontoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, DescontosAction.class.getName(), ex.getMessage(), descontosForm, response );
			return null;
		}

		resultadoBuscarListaParamDesconto = buscarListaParamDescontoResponse.getResultadoBuscarListaParamDesconto();
		sb.append("LOJA PROPRIA;CARTÃO DE CRÉDITO;");
		sb.append(resultadoBuscarListaParamDesconto.getDesconto(0).getNrParcelasMax());
		sb.append(";");
		sb.append(resultadoBuscarListaParamDesconto.getDesconto(0).getTaxaJuros().toString().replaceAll("\\.", ","));
		sb.append(";");
		sb.append(resultadoBuscarListaParamDesconto.getDesconto(0).getNrMaxParcSemJuros());
		sb.append("\n\nNumero de parcelas;Percentual de desconto;;;\n");
		
		for (ResultadoBuscarListaParamDescontoListaParcelasParcelas parcelas : resultadoBuscarListaParamDesconto.getListaParcelas()) {
			sb.append(parcelas.getNrParcela());
			sb.append(";");
			sb.append(String.valueOf((1-parcelas.getFatorPreco().doubleValue())*100).toString().replaceAll("\\.", ","));
			sb.append("%;;;\n");
		}

		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition","attachment;filename=Desconto.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();
		
		out.write(sb.toString());
		out.flush();

		return null;
	}

	private List<Double> calcularDescontos(int numMaxParcelas, double taxaJuros, int numParcelasSemJuros) {

		List<Double> descontos = new ArrayList<Double>(numMaxParcelas);

		// divisão da taxaJuros por 100 = percentual da taxa de juros
		double percentualTaxaJuros = taxaJuros / 100;
		// (1 + iF)n
		double percentualValorFinal = Math.pow((1 + percentualTaxaJuros), numMaxParcelas);
		// iP = [(1 + iF)n - 1) / (i * (1 + iF)n) / 10
		double valorAVista = (percentualValorFinal - 1) / (percentualTaxaJuros * percentualValorFinal) / numMaxParcelas;
		double descontoAVista = (1 - valorAVista);

		for (int i = 1; i <= numMaxParcelas; i++) {
			double desconto;
			if (i <= numParcelasSemJuros)
				desconto = descontoAVista;
			else {
				// (1 + iI)n
				double percentualValorI = Math.pow(1 + percentualTaxaJuros, i);
				// VFn = n x ( VP x  [ ( i x (1+i)n )  / ( (1+i)n – 1 ) ]
				double valorComDesconto = i * (valorAVista * ((percentualTaxaJuros * percentualValorI) / (percentualValorI - 1)));
				desconto = 1 - valorComDesconto;
			}
			descontos.add(i - 1, desconto);
		}

		return descontos;
	}
}