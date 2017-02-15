package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CANALATENDIMENTOCANAL", schema = "CATALOGOPRS_OW")
public class CanalAtendimentoCanal {

	@Id
	@Column(name = "IDCANALATENDIMENTOCANAL") 
	private Long idCanalAtendimentoCanal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "IDCANAL") 
	private Canal canal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "IDCANALATENDIMENTO") 
	private CanalAtendimento canalAtendimento;

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public Long getIdCanalAtendimentoCanal() {
		return idCanalAtendimentoCanal;
	}

	public void setIdCanalAtendimentoCanal(Long idCanalAtendimentoCanal) {
		this.idCanalAtendimentoCanal = idCanalAtendimentoCanal;
	}
}
