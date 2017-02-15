package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OfertaVLArqItemTO implements Serializable {

	private static final long serialVersionUID = -6100049151022996894L;
	private Integer idArquivo;
    private Integer idItem;
    private String cdOferta;
    private String nmOferta;
    private Calendar dtInicioVigencia;
    private Calendar dtFimVigencia;
    private String cdPsLinha;
    private String cdOpcomLinha;
    private String cdOpComPreCadastro;
    private String cdPsPlano;
    private String cdOpComPlano;
    private String inPortabilidade;
    private String inFwt;
    private String inBasePontual;
    private String inInadimplente;
    private String inFatb;
    private String inProdutoObrigatorio;
    private String inSpeedySolo;
    private String erros;
    
	public String getCdOpcomLinha() {
		return cdOpcomLinha;
	}

	public void setCdOpcomLinha(String cdOpcomLinha) {
		this.cdOpcomLinha = cdOpcomLinha;
	}

	public String getCdOpComPlano() {
		return cdOpComPlano;
	}

	public void setCdOpComPlano(String cdOpComPlano) {
		this.cdOpComPlano = cdOpComPlano;
	}

	public String getCdOpComPreCadastro() {
		return cdOpComPreCadastro;
	}

	public void setCdOpComPreCadastro(String cdOpComPreCadastro) {
		this.cdOpComPreCadastro = cdOpComPreCadastro;
	}

	public String getCdPsLinha() {
		return cdPsLinha;
	}

	public void setCdPsLinha(String cdPsLinha) {
		this.cdPsLinha = cdPsLinha;
	}

	public String getCdPsPlano() {
		return cdPsPlano;
	}

	public void setCdPsPlano(String cdPsPlano) {
		this.cdPsPlano = cdPsPlano;
	}

	public Calendar getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Calendar dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public Calendar getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Calendar dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public String getInBasePontual() {
		return inBasePontual;
	}

	public void setInBasePontual(String inBasePontual) {
		this.inBasePontual = inBasePontual;
	}

	public String getInFatb() {
		return inFatb;
	}

	public void setInFatb(String inFatb) {
		this.inFatb = inFatb;
	}

	public String getInFwt() {
		return inFwt;
	}

	public void setInFwt(String inFwt) {
		this.inFwt = inFwt;
	}

	public String getInInadimplente() {
		return inInadimplente;
	}

	public void setInInadimplente(String inInadimplente) {
		this.inInadimplente = inInadimplente;
	}

	public String getInPortabilidade() {
		return inPortabilidade;
	}

	public void setInPortabilidade(String inPortabilidade) {
		this.inPortabilidade = inPortabilidade;
	}

	public String getInProdutoObrigatorio() {
		return inProdutoObrigatorio;
	}

	public void setInProdutoObrigatorio(String inProdutoObrigatorio) {
		this.inProdutoObrigatorio = inProdutoObrigatorio;
	}

	public String getInSpeedySolo() {
		return inSpeedySolo;
	}

	public void setInSpeedySolo(String inSpeedySolo) {
		this.inSpeedySolo = inSpeedySolo;
	}

	public String getErrosFormatado() {
        return this.getErros().replaceAll("\\|", "<br/>");
    }

	public String getCdOferta() {
		return cdOferta;
	}

	public void setCdOferta(String cdOferta) {
		this.cdOferta = cdOferta;
	}

	public String getErros() {
		return erros;
	}

	public void setErros(String erros) {
		this.erros = erros;
	}

	public Integer getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public String getNmOferta() {
		return nmOferta;
	}

	public void setNmOferta(String nmOferta) {
		this.nmOferta = nmOferta;
	}
    
	public String getDtIncioFormat() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");		
        Date dtInicio = this.getDtInicioVigencia().getTime();
        
		return formatador.format(dtInicio);
	}
	
	public String getDtFimFormat() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");		
        Date dtFim = this.getDtFimVigencia().getTime();
        
		return formatador.format(dtFim);
	}	

}
