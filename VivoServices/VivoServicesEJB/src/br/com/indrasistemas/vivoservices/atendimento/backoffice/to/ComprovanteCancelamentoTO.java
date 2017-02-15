package br.com.indrasistemas.vivoservices.atendimento.backoffice.to;

import java.math.BigDecimal;
import java.util.Date;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class ComprovanteCancelamentoTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	public static final int RESULTADO_MAXIMO = 50;

	private String cdConta;
	
	private Integer cdDigitoConta;
	
	private Integer cdAreaRegistro;
	
	private BigDecimal nrLinha;
	
	private Date dtAbertura;
	
	private Long idAtendimento;
	
	private String nrDocumento;
	
	private String nmPessoa;

	public Integer getCdAreaRegistro() {
		return cdAreaRegistro;
	}

	public void setCdAreaRegistro(Integer cdAreaRegistro) {
		this.cdAreaRegistro = cdAreaRegistro;
	}

	public String getCdConta() {
		return cdConta;
	}

	public void setCdConta(String cdConta) {
		this.cdConta = cdConta;
	}

	public Integer getCdDigitoConta() {
		return cdDigitoConta;
	}

	public void setCdDigitoConta(Integer cdDigitoConta) {
		this.cdDigitoConta = cdDigitoConta;
	}

	public Date getDtAbertura() {
		return dtAbertura;
	}

	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getNmPessoa() {
		return nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public BigDecimal getNrLinha() {
		return nrLinha;
	}

	public void setNrLinha(BigDecimal nrLinha) {
		this.nrLinha = nrLinha;
	}

}
