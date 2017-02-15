package br.com.indrasistemas.vivoservices.tracking.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class PedidoTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	public static final int RESULTADO_MAXIMO = 50;
	
	public PedidoTO()
	{
		listaOrdemVenda = new ArrayList();
	}

	private Long nrDocumento;
	
	private Date dataInicial;

	private Date dataFinal;

	private Date dtAberturaPedido;

	private String sgUF;

	private Long idPedido;

	private Long nrNotaFiscal;
	
	private String textoNotaFiscal;

	private String serieNotaFiscal;
	
	//private String nrOrdemVenda;
	
	private String nmSistemaOrigem;

	private String status;
	
	private String statusEntrega;
	
	private String nmPessoa;
	
	private List listaOrdemVenda; 

	public Date getDataFinal() {
		return dataFinal;
	}
	
	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}
	
	public String getNmPessoa() {
		return this.nmPessoa;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDtAberturaPedido() {
		return dtAberturaPedido;
	}

	public void setDtAberturaPedido(Date dtAberturaPedido) {
		this.dtAberturaPedido = dtAberturaPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(Long nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(Long nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}

	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}

	//public String getNrOrdemVenda() {
	//	return nrOrdemVenda;
	//}

	//public void setNrOrdemVenda(String nrOrdemVenda) {
	//	this.nrOrdemVenda = nrOrdemVenda;
	//}

	public String getNmSistemaOrigem() {
		return nmSistemaOrigem;
	}

	public void setNmSistemaOrigem(String nmSistemaOrigem) {
		this.nmSistemaOrigem = nmSistemaOrigem;
	}

	public String getTextoNotaFiscal() {
		return textoNotaFiscal;
	}

	public void setTextoNotaFiscal(String textoNotaFiscal) {
		this.textoNotaFiscal = textoNotaFiscal;
	}

	public String getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(String statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public List getListaOrdemVenda() {
		return listaOrdemVenda;
	}

	public void setListaOrdemVenda(List listaOrdemVenda) {
		this.listaOrdemVenda = listaOrdemVenda;
	}
	
	public void addItemListaOrdemVenda(OrdemVendaTO ordemVendaTO) {
		this.listaOrdemVenda.add(ordemVendaTO);
	}
	
}
