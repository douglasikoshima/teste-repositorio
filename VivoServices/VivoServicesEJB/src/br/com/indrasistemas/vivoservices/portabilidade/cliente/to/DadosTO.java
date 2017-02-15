package br.com.indrasistemas.vivoservices.portabilidade.cliente.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class DadosTO extends BaseTransferObject {

	public DadosTO() {
	}

	private static final long serialVersionUID = -133703219789322218L;

	private int tpOperacao;
	private String sgTipoPessoa;
	private long nrLinha;
	private String sgTipoLinha;
	private long idPessoa;
	private DadosPJTO dadosPJ;
	private DadosPFTO dadosPF;

	public int getTpOperacao() {
		return tpOperacao;
	}

	public void setTpOperacao(int tpOperacao) {
		this.tpOperacao = tpOperacao;
	}

	public String getSgTipoPessoa() {
		return sgTipoPessoa;
	}

	public void setSgTipoPessoa(String sgTipoPessoa) {
		this.sgTipoPessoa = sgTipoPessoa;
	}

	public long getNrLinha() {
		return nrLinha;
	}

	public void setNrLinha(long nrLinha) {
		this.nrLinha = nrLinha;
	}

	public String getSgTipoLinha() {
		return sgTipoLinha;
	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setSgTipoLinha(String sgTipoLinha) {
		this.sgTipoLinha = sgTipoLinha;
	}

	public DadosPJTO getDadosPJ() {
		return dadosPJ;
	}

	public void setDadosPJ(DadosPJTO dadosPJ) {
		this.dadosPJ = dadosPJ;
	}

	public DadosPFTO getDadosPF() {
		return dadosPF;
	}

	public void setDadosPF(DadosPFTO dadosPF) {
		this.dadosPF = dadosPF;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

}