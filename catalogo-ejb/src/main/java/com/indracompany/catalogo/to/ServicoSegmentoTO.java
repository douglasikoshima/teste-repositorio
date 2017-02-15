package com.indracompany.catalogo.to;

import java.util.List;

public class ServicoSegmentoTO {
	
	private Long idServicoSegmento;
	private SegmentoTO segmentoTO;
	private List<ServicoFixaTO> servicoTOList;
	private String nmUsuarioCriacao;
	private String inInfancia;
	
	public Long getIdServicoSegmento() {
		return idServicoSegmento;
	}
	public void setIdServicoSegmento(Long idServicoSegmento) {
		this.idServicoSegmento = idServicoSegmento;
	}
	public SegmentoTO getSegmentoTO() {
		return segmentoTO;
	}
	public void setSegmentoTO(SegmentoTO segmentoTO) {
		this.segmentoTO = segmentoTO;
	}
	public List<ServicoFixaTO> getServicoTOList() {
		return servicoTOList;
	}
	public void setServicoTOList(List<ServicoFixaTO> servicoFixaTOList) {
		this.servicoTOList = servicoFixaTOList;
	}
	public String getInInfancia() {
		return inInfancia;
	}
	public void setInInfancia(String inInfancia) {
		this.inInfancia = inInfancia;
	}
	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}
	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
}
