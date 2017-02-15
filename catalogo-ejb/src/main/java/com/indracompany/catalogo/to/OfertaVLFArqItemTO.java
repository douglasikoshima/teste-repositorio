package com.indracompany.catalogo.to;

import java.io.Serializable;

public class OfertaVLFArqItemTO implements Serializable {
	
	private static final long serialVersionUID = 3266244275751982431L;
	private Integer idArquivo;
    private Integer idItem;
    private String cdOferta;
    private String cdPsServico;
    private String cdOpComServico;
    private String pzVigencia;
    private String erros;
    
    public String getCdPsServico() {
		return cdPsServico;
	}

	public void setCdPsServico(String cdPsServico) {
		this.cdPsServico = cdPsServico;
	}

	public String getPzVigencia() {
		return pzVigencia;
	}

	public void setPzVigencia(String pzVigencia) {
		this.pzVigencia = pzVigencia;
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

	public String getCdOpComServico() {
		return cdOpComServico;
	}

	public void setCdOpComServico(String cdOpComServico) {
		this.cdOpComServico = cdOpComServico;
	}
}
