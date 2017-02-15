package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ESPSERVICOPLANOMINUTO", schema = "CATALOGOPRS_OW")
public class EspServicoPlanoMinuto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8540509835053546194L;
	
	@Id
	@Column(name = "IDSERVICO")
	private Integer idServico;
	
	@Column(name = "QTMINUTOLIVREFMLOCAL")
	private Long qtMinutoLivreFMLocal;
	
	@Column(name = "QTMINUTOLIVREFFLOCAL")
	private Long qtMinutoLivreFFLocal;
	
	@Column(name = "VLMINUTOADICIONALFFLOCAL")
	private BigDecimal vlMinutoAdicionalFFlocal;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "IDSERVICO", nullable = false)
	private Servico servico;
	
	@OneToMany(mappedBy = "espServicoPlanoMinuto")
	private List<DisponibilidadeSlctComercial> disponibilidadeSlctComercial;
	
	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public Long getQtMinutoLivreFFLocal() {
		return qtMinutoLivreFFLocal;
	}

	public void setQtMinutoLivreFFLocal(Long qtMinutoLivreFFLocal) {
		this.qtMinutoLivreFFLocal = qtMinutoLivreFFLocal;
	}

	public Long getQtMinutoLivreFMLocal() {
		return qtMinutoLivreFMLocal;
	}

	public void setQtMinutoLivreFMLocal(Long qtMinutoLivreFMLocal) {
		this.qtMinutoLivreFMLocal = qtMinutoLivreFMLocal;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<DisponibilidadeSlctComercial> getDisponibilidadeSlctComercial() {
		return disponibilidadeSlctComercial;
	}

	public void setDisponibilidadeSlctComercial(
			List<DisponibilidadeSlctComercial> disponibilidadeSlctComercial) {
		this.disponibilidadeSlctComercial = disponibilidadeSlctComercial;
	}

	public BigDecimal getVlMinutoAdicionalFFlocal() {
		return vlMinutoAdicionalFFlocal;
	}

	public void setVlMinutoAdicionalFFlocal(BigDecimal vlMinutoAdicionalFFlocal) {
		this.vlMinutoAdicionalFFlocal = vlMinutoAdicionalFFlocal;
	}

}
