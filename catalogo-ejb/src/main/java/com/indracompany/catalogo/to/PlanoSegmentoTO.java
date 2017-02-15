package com.indracompany.catalogo.to;

import java.util.List;

public class PlanoSegmentoTO {
	
	private Long idPlanoSegmento;
	private SegmentoTO segmentoTO;
	private List<PlanoTO> planoTOList;
	private String nmUsuarioCriacao;
	private String inInfancia;
	
	public Long getIdPlanoSegmento() {
		return idPlanoSegmento;
	}
	public void setIdPlanoSegmento(Long idPlanoSegmento) {
		this.idPlanoSegmento = idPlanoSegmento;
	}
	public List<PlanoTO> getPlanoTOList() {
		return planoTOList;
	}
	public void setPlanoTOList(List<PlanoTO> planoTOList) {
		this.planoTOList = planoTOList;
	}
	public SegmentoTO getSegmentoTO() {
		return segmentoTO;
	}
	public void setSegmentoTO(SegmentoTO segmentoTO) {
		this.segmentoTO = segmentoTO;
	}
	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}
	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	public String getInInfancia() {
		return inInfancia;
	}
	public void setInInfancia(String inInfancia) {
		this.inInfancia = inInfancia;
	}
}
