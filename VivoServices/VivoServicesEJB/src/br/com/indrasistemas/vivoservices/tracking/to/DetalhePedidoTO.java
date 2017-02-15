package br.com.indrasistemas.vivoservices.tracking.to;

import java.util.Date;
import java.util.List;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class DetalhePedidoTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	public static final int RESULTADO_MAXIMO = 50;

	private Date dtAberturaPedido;

	private Long idPedido;

	private Long nrNotaFiscal;

	private Long nrDocumento;

	private String sgUF;

	private String serieNotaFiscal;

	private String nrOrdemVenda;

	private String nmSistemaOrigem;

	private String status;

	private String enderecoEntrega;

	private String valorTotal;
	
	List listaProdutosNotaFiscal;

	List listaEtapaPedido;

	public Date getDtAberturaPedido() {
		return dtAberturaPedido;
	}

	public void setDtAberturaPedido(Date dtAberturaPedido) {
		this.dtAberturaPedido = dtAberturaPedido;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getNmSistemaOrigem() {
		return nmSistemaOrigem;
	}

	public void setNmSistemaOrigem(String nmSistemaOrigem) {
		this.nmSistemaOrigem = nmSistemaOrigem;
	}

	public Long getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(Long nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public Long getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(Long nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public String getNrOrdemVenda() {
		return nrOrdemVenda;
	}

	public void setNrOrdemVenda(String nrOrdemVenda) {
		this.nrOrdemVenda = nrOrdemVenda;
	}

	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}

	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
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

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List getListaProdutosNotaFiscal() {
		return listaProdutosNotaFiscal;
	}

	public void setListaProdutosNotaFiscal(List listaProdutosNotaFiscal) {
		this.listaProdutosNotaFiscal = listaProdutosNotaFiscal;
	}

	public List getListaEtapaPedido() {
		return listaEtapaPedido;
	}

	public void setListaEtapaPedido(List listaEtapaPedido) {
		this.listaEtapaPedido = listaEtapaPedido;
	}
}
