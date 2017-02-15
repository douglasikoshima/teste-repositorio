package com.indracompany.catalogo.to;

public class SegmentoTO {
	public enum TipoPesquisaSegmento {
		SEGMENTO, SEGMENTADOS, NAO_SEGMENTADOS
	}
	private Long idSegmento;
	private String sgSegmento;
	private String dsSegmento;
	private String inInfancia;
	private TipoPesquisaSegmento tipoPesquisaSegmento = TipoPesquisaSegmento.SEGMENTADOS;
	
	public SegmentoTO() {
		super();
	}
	public SegmentoTO(Long idSegmento) {
		super();
		this.idSegmento = idSegmento;
	}
	public String getDsSegmento() {
		return dsSegmento;
	}
	public String getInInfancia() {
		return inInfancia;
	}
	public void setInInfancia(String inInfancia) {
		this.inInfancia = inInfancia;
	}
	public void setDsSegmento(String dsSegmento) {
		this.dsSegmento = dsSegmento;
	}
	public Long getIdSegmento() {
		return idSegmento;
	}
	public void setIdSegmento(Long idSegmento) {
		this.idSegmento = idSegmento;
	}
	public String getSgSegmento() {
		return sgSegmento;
	}
	public void setSgSegmento(String sgSegmento) {
		this.sgSegmento = sgSegmento;
	}
	public TipoPesquisaSegmento getTipoPesquisaSegmento() {
		return tipoPesquisaSegmento;
	}
	public void setTipoPesquisaSegmento(TipoPesquisaSegmento tipoPesquisaSegmento) {
		this.tipoPesquisaSegmento = tipoPesquisaSegmento;
	}
}
